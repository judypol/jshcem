package com.shcem.util;

import com.shcem.constants.SystemDefine;
import com.shcem.server.model.ServerContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import com.shcem.util.TraceInterceptor.Task;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TraceDaoInterceptor {
	
	@Autowired
	private TraceReporter reporter;

	public TraceReporter getReporter() {
		return reporter;
	}

	public void setReporter(TraceReporter reporter) {
		this.reporter = reporter;
	}

	public Object aroundMethodDao(ProceedingJoinPoint pjp) throws Throwable {

		Object result = null;
		// 获取方法名
		String methodName = pjp.getSignature().getName() + "_dao";
		String serviceName = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
		String params = Arrays.asList(pjp.getArgs()).toString();

		final BlockingQueue<Task> queue = new ArrayBlockingQueue<Task>(100);
		final BlockingQueue<Task> queueReturn = new ArrayBlockingQueue<Task>(100);
		TraceQueue.getContext().addQueue(SystemDefine.TRACEQUEUE_DAO_PUT, queue);
		TraceQueue.getContext().addQueue(SystemDefine.TRACEQUEUE_DAO_GET, queueReturn);

		// 设定当前Handle
		TraceInterceptor.setCurrentHandle(SystemDefine.TRACE_CUR_HANDLE_DAO);

		String strRndCmp = ServerContext.currentContext().getHeader(SystemDefine.RND_CMP);
		BlockingQueue<Task> queueCmp = TraceQueue.getContext().getQueue(SystemDefine.TRACEQUEUE_CMP_PUT + strRndCmp);
		BlockingQueue<Task> queueReturnCmp = TraceQueue.getContext().getQueue(SystemDefine.TRACEQUEUE_CMP_GET + strRndCmp);

		try {
			// Father Client Send
			queueCmp.offer(new Task(SystemDefine.TRACETASK_SEND_CLIENT, params));
			queueReturnCmp.take();

			// 启动DAO链路线程
			TraceThread threadDao = new TraceThread(queue, queueReturn, methodName, serviceName, reporter);
			// 设定为守护线程,确保主线程退出的同时,Trace子线程马上结束.
			threadDao.setDaemon(true);
			threadDao.start();

			// Server Send by self
			queue.offer(new Task(SystemDefine.TRACETASK_SEND_SERVER, params));
			queueReturn.take();

			// 执行目标方法
			result = pjp.proceed();

			// 返回通知
			queue.offer(new Task(SystemDefine.TRACETASK_RECEIVE_SERVER, TraceInterceptor.getResult(result)));
			queueReturn.take();

			queueCmp.offer(new Task(SystemDefine.TRACETASK_RECEIVE_CLIENT, TraceInterceptor.getResult(result)));
			queueReturnCmp.take();
		} catch (Throwable e) {
			// 异常通知
			queue.offer(new Task(SystemDefine.TRACETASK_RECEIVE_SERVER, e.getMessage()));
			queueReturn.take();

			queueCmp.offer(new Task(SystemDefine.TRACETASK_RECEIVE_CLIENT, e.getMessage()));
			queueReturnCmp.take();
			throw e;
		} finally {
			// 关闭service子线程
			queue.offer(new Task(SystemDefine.TRACETASK_CLOSE, ""));
			// 设定当前Handle
			TraceInterceptor.setCurrentHandle(SystemDefine.TRACE_CUR_HANDLE_CMP);
		}

		return result;
	}

}

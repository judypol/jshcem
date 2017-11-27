package com.shcem.util;

import com.shcem.constants.SystemDefine;
import com.shcem.server.model.ServerContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import com.shcem.util.TraceInterceptor.Task;

public class TraceCmpInterceptor {
	
	@Autowired
	private TraceReporter reporter;

	public TraceReporter getReporter() {
		return reporter;
	}

	public void setReporter(TraceReporter reporter) {
		this.reporter = reporter;
	}

	public Object aroundMethodCmp(ProceedingJoinPoint pjp) throws Throwable {

		Object result = null;
		// 获取方法名
		String methodName = pjp.getSignature().getName() + "_cmp";
		String serviceName = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
		String params = Arrays.asList(pjp.getArgs()).toString();
		Random randomGenerator = new Random();
		long rndCmp = randomGenerator.nextLong();
		String strRndCmp = Long.toString(rndCmp);

		final BlockingQueue<Task> queue = new ArrayBlockingQueue<Task>(100);
		final BlockingQueue<Task> queueReturn = new ArrayBlockingQueue<Task>(100);
		TraceQueue.getContext().addQueue(SystemDefine.TRACEQUEUE_CMP_PUT + strRndCmp, queue);
		TraceQueue.getContext().addQueue(SystemDefine.TRACEQUEUE_CMP_GET + strRndCmp, queueReturn);
		String strOldRndCmp = ServerContext.currentContext().getHeader(SystemDefine.RND_CMP);
		ServerContext.currentContext().addHeader(SystemDefine.RND_CMP, strRndCmp);

		// 设定当前Handle
		TraceInterceptor.setCurrentHandle(SystemDefine.TRACE_CUR_HANDLE_CMP);

		String strRndSvc = ServerContext.currentContext().getHeader(SystemDefine.RND_SVC);
		BlockingQueue<Task> queueSvc = TraceQueue.getContext().getQueue(SystemDefine.TRACEQUEUE_SVC_PUT + strRndSvc);
		BlockingQueue<Task> queueReturnSvc = TraceQueue.getContext().getQueue(SystemDefine.TRACEQUEUE_SVC_GET + strRndSvc);

		try {
			// Father Client Send
			queueSvc.offer(new Task(SystemDefine.TRACETASK_SEND_CLIENT, params));
			queueReturnSvc.take();

			// 启动Component链路线程
			TraceThread threadCmp = new TraceThread(queue, queueReturn, methodName, serviceName, reporter);
			// 设定为守护线程,确保主线程退出的同时,Trace子线程马上结束.
			threadCmp.setDaemon(true);
			threadCmp.start();

			// Server Send by self
			queue.offer(new Task(SystemDefine.TRACETASK_SEND_SERVER, params));
			queueReturn.take();

			// 执行目标方法
			result = pjp.proceed();

			// 返回通知
			queue.offer(new Task(SystemDefine.TRACETASK_RECEIVE_SERVER, TraceInterceptor.getResult(result)));
			queueReturn.take();

			queueSvc.offer(new Task(SystemDefine.TRACETASK_RECEIVE_CLIENT, TraceInterceptor.getResult(result)));
			queueReturnSvc.take();

		} catch (Throwable e) {
			// 异常通知
			queue.offer(new Task(SystemDefine.TRACETASK_RECEIVE_SERVER, e.getMessage()));
			queueReturn.take();

			queueSvc.offer(new Task(SystemDefine.TRACETASK_RECEIVE_CLIENT, e.getMessage()));
			queueReturnSvc.take();
			throw e;
		} finally {
			// 关闭service子线程
			queue.offer(new Task(SystemDefine.TRACETASK_CLOSE, ""));
			// 设定当前Handle
			TraceInterceptor.setCurrentHandle(SystemDefine.TRACE_CUR_HANDLE_SVC);
			// 返回自己的RND
			ServerContext.currentContext().addHeader(SystemDefine.RND_CMP, strOldRndCmp);
		}

		return result;
	}

}

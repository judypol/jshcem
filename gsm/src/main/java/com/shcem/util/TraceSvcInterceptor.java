package com.shcem.util;

import com.shcem.constants.SystemDefine;
import com.shcem.server.model.ServerContext;
import com.shcem.util.TraceInterceptor.Task;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TraceSvcInterceptor {

	@Autowired
	private TraceReporter reporter;

	public TraceReporter getReporter() {
		return reporter;
	}

	public void setReporter(TraceReporter reporter) {
		this.reporter = reporter;
	}

	public Object aroundMethodSvc(ProceedingJoinPoint pjp) throws Throwable {

		Object result = null;
		// 获取方法名
		String methodName = pjp.getSignature().getName() + "_svc";
		String serviceName = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();
		String params = Arrays.asList(pjp.getArgs()).toString();
		Random randomGenerator = new Random();
		long rndSvc = randomGenerator.nextLong();
		String strRndSvc = Long.toString(rndSvc);

		final BlockingQueue<Task> queue = new ArrayBlockingQueue<Task>(100);
		final BlockingQueue<Task> queueReturn = new ArrayBlockingQueue<Task>(100);
		TraceQueue.getContext().addQueue(SystemDefine.TRACEQUEUE_SVC_PUT + strRndSvc, queue);
		TraceQueue.getContext().addQueue(SystemDefine.TRACEQUEUE_SVC_GET + strRndSvc, queueReturn);

		String strOldRndSvc = ServerContext.currentContext().getHeader(SystemDefine.RND_SVC);
		ServerContext.currentContext().addHeader(SystemDefine.RND_SVC, strRndSvc);

		// 设定当前Handle
		TraceInterceptor.setCurrentHandle(SystemDefine.TRACE_CUR_HANDLE_SVC);

		// 启动Service链路线程
		TraceThread threadSvc = new TraceThread(queue, queueReturn, methodName, serviceName, reporter);
		// 设定为守护线程,确保主线程退出的同时,Trace子线程马上结束.
		threadSvc.setDaemon(true);
		threadSvc.start();

		try {
			// 前置通知,Arrays.asList(pjp.getArgs())为该方法的参数个数，为数组集合
			queue.offer(new Task(SystemDefine.TRACETASK_SEND_SERVER, params));
			queueReturn.take();

			// 执行目标方法
			result = pjp.proceed();

			// 返回通知
			queue.offer(new Task(SystemDefine.TRACETASK_RECEIVE_SERVER, TraceInterceptor.getResult(result)));
			queueReturn.take();
		} catch (Throwable e) {
			// 异常通知
			queue.offer(new Task(SystemDefine.TRACETASK_RECEIVE_SERVER, e.getMessage()));
			queueReturn.take();
			throw e;
		} finally {
			// 关闭Queue对列
			TraceQueue.close();
			// 关闭service子线程
			queue.offer(new Task(SystemDefine.TRACETASK_CLOSE, ""));
			// 返回自己的RND
			ServerContext.currentContext().addHeader(SystemDefine.RND_SVC, strOldRndSvc);
		}

		return result;
	}
}

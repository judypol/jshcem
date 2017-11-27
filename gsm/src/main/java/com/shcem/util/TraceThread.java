package com.shcem.util;

import com.shcem.constants.SystemDefine;
import com.shcem.util.TraceInterceptor.*;
import java.util.concurrent.BlockingQueue;

public class TraceThread extends Thread {

	BlockingQueue<Task> queue = null;
	BlockingQueue<Task> queueReturn = null;
	String methodName = null;
	String serviceName = null;
	TraceReporter reporter = null;

	public TraceThread(BlockingQueue<Task> queue, BlockingQueue<Task> queueReturn,
			String methodName, String serviceName, TraceReporter reporter) {
		this.queue = queue;
		this.queueReturn = queueReturn;
		this.methodName = methodName;
		this.serviceName = serviceName;
		this.reporter = reporter;
	}

	public void run() {

		TraceInterceptor intcpt = new TraceInterceptor(reporter);

		// 初期化
		intcpt.init(methodName);
		
		boolean flag = true;

		while (flag) {
			try {
				Task task = queue.take();
				if (SystemDefine.TRACETASK_SEND.equals(task.name)) {
					// Server and Client Send
					intcpt.sendTrace(SystemDefine.TRACELOG_TYPE_S, methodName, serviceName, task.result);
					queueReturn.put(new Task("Send finish", ""));
				} else if (SystemDefine.TRACETASK_SEND_SERVER.equals(task.name)) {
					// Server only send
					intcpt.sendTrace(SystemDefine.TRACELOG_TYPE_S_SERVER, methodName, serviceName, task.result);
					queueReturn.put(new Task("Send Server finish", ""));
				} else if (SystemDefine.TRACETASK_SEND_CLIENT.equals(task.name)) {
					// Client only send
					intcpt.sendTrace(SystemDefine.TRACELOG_TYPE_S_CLIENT, methodName, serviceName, task.result);
					queueReturn.put(new Task("Send Client finish", ""));
				} else if (SystemDefine.TRACETASK_RECEIVE.equals(task.name)) {
					// Server and Client Receive
					intcpt.sendTrace(SystemDefine.TRACELOG_TYPE_R, methodName, serviceName, task.result);
					queueReturn.put(new Task("Receive finish", ""));
				} else if (SystemDefine.TRACETASK_RECEIVE_SERVER.equals(task.name)) {
					// Server only receive
					intcpt.sendTrace(SystemDefine.TRACELOG_TYPE_R_SERVER, methodName, serviceName, task.result);
					queueReturn.put(new Task("Receive Server finish", ""));
				} else if (SystemDefine.TRACETASK_RECEIVE_CLIENT.equals(task.name)) {
					// Client only receive
					intcpt.sendTrace(SystemDefine.TRACELOG_TYPE_R_CLIENT, methodName, serviceName, task.result);
					queueReturn.put(new Task("Receive Client finish", ""));
				} else if (SystemDefine.TRACETASK_CLOSE.equals(task.name)) {
					// close and quit
					flag = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

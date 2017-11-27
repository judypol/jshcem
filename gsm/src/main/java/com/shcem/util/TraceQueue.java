package com.shcem.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import com.shcem.util.TraceInterceptor.Task;

public class TraceQueue {

	private static final ThreadLocal<TraceQueue> THREAD_LOCAL = new ThreadLocal<TraceQueue>();

	private Map<String, BlockingQueue<Task>> queueMap = new HashMap<String, BlockingQueue<Task>>();

	private TraceQueue() {
	}

	public static TraceQueue getContext() {
		TraceQueue context = THREAD_LOCAL.get();
		if (context == null) {
			context = new TraceQueue();
			THREAD_LOCAL.set(context);
		}
		return context;
	}

	public void addQueue(String name, BlockingQueue<TraceInterceptor.Task> queue) {
		queueMap.put(name, queue);
	}

	public BlockingQueue<Task> getQueue(String name) {
		return queueMap.get(name);
	}

	public Map<String, BlockingQueue<Task>> getQueues() {
		return queueMap;
	}

	public static void close() {
		TraceQueue context = THREAD_LOCAL.get();
		if (context != null) {
			context.queueMap.clear();
			THREAD_LOCAL.set(null);
		}
	}

}

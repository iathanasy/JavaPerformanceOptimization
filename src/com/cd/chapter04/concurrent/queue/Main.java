package com.cd.chapter04.concurrent.queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.cd.chapter04.concurrent.CounterPoolExecutor;
import com.cd.chapter04.concurrent.map.AccessMapThread;

public class Main {

	public static int MAX_THREADS = 2000;// 线程数

	public static void main(String[] args) {

		CounterPoolExecutor ex = new CounterPoolExecutor(MAX_THREADS,
				MAX_THREADS, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
		long start = System.currentTimeMillis();
		ex.startTime = start;
		ex.funcname = "testLinkedBlockingQueue";
		for (int i = 0; i < ex.TASK_COUNT; i++) { // TASK_COUNT = 4000 任务数
			ex.submit(new HandleQueueThread());//
		}

	}
}

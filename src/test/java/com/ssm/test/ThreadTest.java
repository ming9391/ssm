package com.ssm.test;

import org.junit.Test;

public class ThreadTest {

	class thread_test extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				System.out.println(getName() + " thread: " + i);
			}
		}
	}

	@Test
	public void test_thread() {
		 thread_test thread = new thread_test();
		 thread.setName("my_thread");
		 thread.start();
	}
	
	class runnable_test implements Runnable{
		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				System.out.println(Thread.currentThread().getName() + " runnable: " + i);
			}
		}
	}
	@Test
	public void test_runnable() {
		runnable_test run = new runnable_test();
		Thread thread = new Thread(run);
		 thread.setName("my_runnable");
		 thread.start();
	}
	
	@Test
	public void thread_runnable() {
		 Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					System.out.println(Thread.currentThread().getName() + " thread_runnable: " + i);
				}
			}
		});
		 thread.setName("thread_runnable");
		 thread.start();
	}
	
	
	
}

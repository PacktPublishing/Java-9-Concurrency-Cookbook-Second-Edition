package com.packtpub.java9.concurrency.cookbook.appendix.recipe09.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Task implements Runnable {
	
	private final Lock lock;
	
	public Task (Lock lock) {
		this.lock=lock;
	}
	
	@Override
	public void run() {
		System.out.printf("%s: Starting\n",Thread.currentThread().getName());
		lock.lock();
		try {
			criticalSection();
		
			System.out.printf("%s: Press a key to continue: \n",Thread.currentThread().getName());
			InputStreamReader converter = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(converter);
			String line=in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
			lock.unlock();
		}
	}

	private void criticalSection() {
		Random random=new Random();
		int wait=random.nextInt(10);
		System.out.printf("%s: Wait for %d seconds\n",Thread.currentThread().getName(),wait);
		try {
			TimeUnit.SECONDS.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

}

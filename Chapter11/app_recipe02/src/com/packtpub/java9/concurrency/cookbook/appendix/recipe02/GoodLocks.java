package com.packtpub.java9.concurrency.cookbook.appendix.recipe02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class GoodLocks {
	private Lock lock1, lock2;
	
	public GoodLocks(Lock lock1, Lock lock2) {
		this.lock1=lock1;
		this.lock2=lock2;
	}
	
	public void operation1(){
		lock1.lock();
		lock2.lock();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock2.unlock();
			lock1.unlock();
		}
		
	}

	public void operation2(){
		lock1.lock();
		lock2.lock();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock2.unlock();
			lock1.unlock();
		}
	}
}

package com.packtpub.java9.concurrency.cookbook.chapter02.recipe06.task;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class Writer implements Runnable {

	private final Position position;
	private final StampedLock lock;
	
	public Writer (Position position, StampedLock lock) {
		this.position=position;
		this.lock=lock;
	}
	
	@Override
	public void run() {
		
		for (int i=0; i<10; i++) {
			long stamp = lock.writeLock();
			
			try {
				System.out.printf("Writer: Lock acquired %d\n",stamp);
				position.setX(position.getX()+1);
				position.setY(position.getY()+1);
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlockWrite(stamp);
				System.out.printf("Writer: Lock released %d\n",stamp);
			}

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	
}

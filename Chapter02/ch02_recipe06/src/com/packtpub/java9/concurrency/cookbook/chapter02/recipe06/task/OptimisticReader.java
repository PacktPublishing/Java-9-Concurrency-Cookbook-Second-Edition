package com.packtpub.java9.concurrency.cookbook.chapter02.recipe06.task;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class OptimisticReader implements Runnable {

	private final Position position;
	private final StampedLock lock;
	
	public OptimisticReader (Position position, StampedLock lock) {
		this.position=position;
		this.lock=lock;
	}
	
	@Override
	public void run() {
		long stamp;
		for (int i=0; i<100; i++) {
			try {
				stamp=lock.tryOptimisticRead();
				int x = position.getX();
				int y = position.getY();
				if (lock.validate(stamp)) {
					System.out.printf("OptmisticReader: %d - (%d,%d)\n", stamp,x, y);
				} else {
					System.out.printf("OptmisticReader: %d - Not Free\n", stamp);
				}
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

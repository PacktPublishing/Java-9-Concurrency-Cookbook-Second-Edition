package com.packtpub.java9.concurrency.cookbook.chapter02.recipe06.main;

import java.util.concurrent.locks.StampedLock;

import com.packtpub.java9.concurrency.cookbook.chapter02.recipe06.task.OptimisticReader;
import com.packtpub.java9.concurrency.cookbook.chapter02.recipe06.task.Position;
import com.packtpub.java9.concurrency.cookbook.chapter02.recipe06.task.Reader;
import com.packtpub.java9.concurrency.cookbook.chapter02.recipe06.task.Writer;

public class Main {

	public static void main(String[] args) {
		
		Position position=new Position();
		StampedLock lock=new StampedLock();
		
		Thread threadWriter=new Thread(new Writer(position,lock));
		Thread threadReader=new Thread(new Reader(position, lock));
		Thread threadOptReader=new Thread(new OptimisticReader(position, lock));
		
		threadWriter.start();
		threadReader.start();
		threadOptReader.start();
		
		try {
			threadWriter.join();
			threadReader.join();
			threadOptReader.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

}

package com.packtpub.java9.concurrency.cookbook.chapter09.recipe02.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Task to write information about a Phaser. It executes three phases. In each
 * phase, it sleeps the thread the number of seconds specified by the time attribute
 *
 */
public class Task implements Runnable {
	
	/**
	 * Number of seconds this task is going to sleep the thread in each phase
	 */
	private final int time;
	
	/**
	 * Phaser to synchronize the execution of phases
	 */
	private final Phaser phaser;
	
	/**
	 * Constructor of the class. Initialize its attributes
	 * @param time Number of seconds this task is going to sleep the thread in each phase
	 * @param phaser Phaser to synchronize the execution of tasks
	 */
	public Task(int time, Phaser phaser) {
		this.time=time;
		this.phaser=phaser;
	}
	
	/**
	 * Main method of the task. Executes three phases. In each phase, sleeps
	 * the thread the number of seconds specified by the time attribute.
	 */
	@Override
	public void run() {
		/*
		 * Arrive to the phaser
		 */
		phaser.arrive();
		/*
		 * Phase 1
		 */
		System.out.printf("%s: Entering phase 1.\n",Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Finishing phase 1.\n",Thread.currentThread().getName());
		/*
		 * End of Phase 1
		 */
		phaser.arriveAndAwaitAdvance();
		/*
		 * Phase 2
		 */
		System.out.printf("%s: Entering phase 2.\n",Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Finishing phase 2.\n",Thread.currentThread().getName());
		/*
		 * End of Phase 2
		 */
		phaser.arriveAndAwaitAdvance();
		/*
		 * Phase 3
		 */
		System.out.printf("%s: Entering phase 3.\n",Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Finishing phase 3.\n",Thread.currentThread().getName());
		/*
		 * End of Phase 3
		 */
		phaser.arriveAndDeregister();
	}
}

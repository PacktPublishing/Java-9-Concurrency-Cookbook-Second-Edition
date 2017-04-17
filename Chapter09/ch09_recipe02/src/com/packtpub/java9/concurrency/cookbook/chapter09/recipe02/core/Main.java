package com.packtpub.java9.concurrency.cookbook.chapter09.recipe02.core;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter09.recipe02.phaser.Task;

/**
 * Maîn class of the example. Creates a Phaser with three participants and
 * Three task objects. Write information about the evolution of the Phaser
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * Create a new Phaser for three participants
		 */
		Phaser phaser=new Phaser(3);
		
		/*
		 * Create and launch three tasks
		 */
		for (int i=0; i<3; i++) {
			Task task=new Task(i+1, phaser);
			Thread thread=new Thread(task);
			thread.start();
		}
		
		/*
		 * Write information about the Phaser
		 */
		for (int i=0; i<10; i++) {
			System.out.printf("********************\n");
			System.out.printf("Main: Phaser Log\n");
			System.out.printf("Main: Phaser: Phase: %d\n",phaser.getPhase());
			System.out.printf("Main: Phaser: Registered Parties: %d\n",phaser.getRegisteredParties());
			System.out.printf("Main: Phaser: Arrived Parties: %d\n",phaser.getArrivedParties());
			System.out.printf("Main: Phaser: Unarrived Parties: %d\n",phaser.getUnarrivedParties());
			System.out.printf("********************\n");

			TimeUnit.SECONDS.sleep(1);
		}
	}
}

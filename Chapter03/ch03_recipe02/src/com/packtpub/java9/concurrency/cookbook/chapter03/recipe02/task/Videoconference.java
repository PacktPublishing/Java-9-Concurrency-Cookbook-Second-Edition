package com.packtpub.java9.concurrency.cookbook.chapter03.recipe02.task;

import java.util.concurrent.CountDownLatch;

/**
 * This class implements the controller of the Videoconference
 * 
 * It uses a CountDownLatch to control the arrival of all the 
 * participants in the conference.
 *
 */
public class Videoconference implements Runnable{

	/**
	 * This class uses a CountDownLatch to control the arrivel of all
	 * the participants
	 */
	private final CountDownLatch controller;
	
	/**
	 * Constructor of the class. Initializes the CountDownLatch
	 * @param number The number of participants in the videoconference
	 */
	public Videoconference(int number) {
		controller=new CountDownLatch(number);
	}

	/**
	 * This method is called by every participant when he incorporates to the VideoConference
	 * @param participant
	 */
	public void arrive(String name){
		System.out.printf("%s has arrived.\n",name);
		// This method uses the countDown method to decrement the internal counter of the
		// CountDownLatch
		controller.countDown();
		System.out.printf("VideoConference: Waiting for %d participants.\n",controller.getCount());
	}
	
	/**
	 * This is the main method of the Controller of the VideoConference. It waits for all
	 * the participants and the, starts the conference
	 */
	@Override
	public void run() {
		System.out.printf("VideoConference: Initialization: %d participants.\n",controller.getCount());
		try {
			// Wait for all the participants
			controller.await();
			// Starts the conference
			System.out.printf("VideoConference: All the participants have come\n");
			System.out.printf("VideoConference: Let's start...\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}

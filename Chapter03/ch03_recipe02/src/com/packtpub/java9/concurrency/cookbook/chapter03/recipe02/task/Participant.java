package com.packtpub.java9.concurrency.cookbook.chapter03.recipe02.task;

import java.util.concurrent.TimeUnit;

/**
 * This class implements a participant in the VideoConference
 *
 */
public class Participant implements Runnable {

	/**
	 * VideoConference in which this participant will take part off
	 */
	private Videoconference conference;
	
	/**
	 * Name of the participant. For log purposes only
	 */
	private String name;
	
	/**
	 * Constructor of the class. Initialize its attributes
	 * @param conference VideoConference in which is going to take part off
	 * @param name Name of the participant
	 */
	public Participant(Videoconference conference, String name) {
		this.conference=conference;
		this.name=name;
	}

	/**
	 * Core method of the participant. Waits a random time and joins the VideoConference 
	 */
	@Override
	public void run() {
		Long duration=(long)(Math.random()*10);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		conference.arrive(name);

	}
}

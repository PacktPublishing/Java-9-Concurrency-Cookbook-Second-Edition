package com.packtpub.java9.concurrency.cookbook.chapter03.recipe02.core;

import com.packtpub.java9.concurrency.cookbook.chapter03.recipe02.task.Participant;
import com.packtpub.java9.concurrency.cookbook.chapter03.recipe02.task.Videoconference;

/**
 * Main class of the example. Create, initialize and execute all the objects
 * necessaries for the example
 *
 */
public class Main {

	/**
	 * Main method of the example
	 * @param args
	 */
	public static void main(String[] args) {

		// Creates a VideoConference with 10 participants.
		Videoconference conference=new Videoconference(10);
		// Creates a thread to run the VideoConference and start it.
		Thread threadConference=new Thread(conference);
		threadConference.start();
		
		// Creates ten participants, a thread for each one and starts them
		for (int i=0; i<10; i++){
			Participant p=new Participant(conference, "Participant "+i);
			Thread t=new Thread(p);
			t.start();
		}

	}

}

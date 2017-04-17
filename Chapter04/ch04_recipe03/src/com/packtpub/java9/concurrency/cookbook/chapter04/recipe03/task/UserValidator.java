package com.packtpub.java9.concurrency.cookbook.chapter04.recipe03.task;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * This class implement a simulation of a user validation system. It suspend the Thread
 * a random period of time and then returns a random boolean value. We consider that it
 * returns the true value when the user is validated and the false value when it's not
 *
 */
public class UserValidator {
	
	/**
	 * The name of the validation system
	 */
	private final String name;
	
	/**
	 * Constructor of the class
	 * @param name The name of the user validation system
	 */
	public UserValidator(String name) {
		this.name=name;
	}
	
	/**
	 * Method that validates a user
	 * @param name Name of the user
	 * @param password Password of the user
	 * @return true if the user is validated and false if not
	 */
	public boolean validate(String name, String password) {
		// Create a new Random objects generator
		Random random=new Random();
		
		// Sleep the thread during a random period of time
		try {
			Long duration=(long)(Math.random()*10);
			System.out.printf("Validator %s: Validating a user during %d seconds\n",this.name,duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			return false;
		}
		
		// Return a random boolean value
		return random.nextBoolean();
	}
	
	/**
	 * Return the name of the validation system
	 * @return The name of the validation system
	 */
	public String getName(){
		return name;
	}

}

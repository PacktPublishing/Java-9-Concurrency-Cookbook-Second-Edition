package com.packtpub.java9.concurrency.cookbook.appendix.recipe01;

import java.util.Date;

public final class PersonImmutable {
	
	final private String firstName;
	final private String lastName;
	final private Date birthDate;
	
	public PersonImmutable (String firstName, String lastName, String address, Date birthDate) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.birthDate=birthDate;
	}

	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}
	
	public Date getBirthDate() {
		return new Date(birthDate.getTime());
	}
}

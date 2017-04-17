package com.packtpub.java9.concurrency.cookbook.chapter06.recipe01.util;

import java.util.Date;

public class Person implements Comparable<Person> {

	private int id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private int salary;
	private double coeficient;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the salary
	 */
	public int getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}

	/**
	 * @return the coeficient
	 */
	public double getCoeficient() {
		return coeficient;
	}

	/**
	 * @param coeficient
	 *            the coeficient to set
	 */
	public void setCoeficient(double coeficient) {
		this.coeficient = coeficient;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	@Override
	public int compareTo(Person otherPerson) {
		int compareLastNames = this.getLastName().compareTo(otherPerson.getLastName());
		if (compareLastNames != 0) {
			return compareLastNames;
		} else {
			return this.getFirstName().compareTo(otherPerson.getFirstName());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		Person otherPerson = (Person)object;
		int compareLastNames=this.getLastName().compareTo(otherPerson.getLastName());
		int compareFirstNames=this.getFirstName().compareTo(otherPerson.getFirstName());
		return ((compareLastNames==0) && (compareFirstNames==0));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		String sequence=this.getLastName()+this.getFirstName();
		return sequence.hashCode();
	}

}

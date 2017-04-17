package com.packtpub.java9.concurrency.cookbook.appendix.recipe12.task;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.RecursiveAction;

import com.packtpub.java9.concurrency.cookbook.appendix.recipe12.utils.Person;

public class PersonMapTask extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6687678520745563790L;
	
	private List<Person> persons;
	private ConcurrentHashMap<String, ConcurrentLinkedDeque<Person>> personMap;
	
	public PersonMapTask(List<Person> persons, ConcurrentHashMap<String, ConcurrentLinkedDeque<Person>> personMap) {
		this.persons = persons;
		this.personMap = personMap;
	}
	
	@Override
	protected void compute() {
		
		if (persons.size() < 1000) {
			
			for (Person person: persons) {
				ConcurrentLinkedDeque<Person> personList=personMap.computeIfAbsent(person.getFirstName(), name -> {
					return new ConcurrentLinkedDeque<>();
				});
				personList.add(person);
			}
			return;
		}
		
		PersonMapTask child1, child2;

		child1 = new PersonMapTask(persons.subList(0, persons.size() / 2), personMap);
		child2 = new PersonMapTask(persons.subList(persons.size() / 2, persons.size()), personMap);

		invokeAll(child1, child2);

	}

}

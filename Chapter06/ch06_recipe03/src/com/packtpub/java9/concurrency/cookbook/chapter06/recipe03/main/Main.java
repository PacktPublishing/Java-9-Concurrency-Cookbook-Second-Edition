package com.packtpub.java9.concurrency.cookbook.chapter06.recipe03.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Collectors;

import com.packtpub.java9.concurrency.cookbook.chapter06.recipe03.util.Counter;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe03.util.Person;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe03.util.PersonGenerator;

public class Main {

	public static void main(String args[]) {

		System.out.printf("********************************************************\n");
		System.out.printf("Main: Examples of collect methods.\n");

		// Generating a list of persons
		List<Person> persons = PersonGenerator.generatePersonList(100);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		// Collecctors.groupingByConcurrent.
		System.out.printf("********************************************************\n");	
		System.out.printf("Grouping By Concurrent\n");
		System.out.printf("Concurrent: %b\n", Collectors.groupingByConcurrent(p -> p).characteristics().contains(Characteristics.CONCURRENT));
		Map<String, List<Person>> personsByName = persons.parallelStream()
				.collect(Collectors.groupingByConcurrent(Person::getFirstName));
		personsByName.keySet().forEach(key -> {
			List<Person> listOfPersons = personsByName.get(key);
			System.out.printf("%s: There are %d persons with that name\n", key, listOfPersons.size());
		});
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		// Collectors.joining
		System.out.printf("********************************************************\n");	
		System.out.printf("Joining\n");
		System.out.printf("Concurrent: %b\n", Collectors.joining().characteristics().contains(Characteristics.CONCURRENT));
		String message = persons.parallelStream().map(p -> p.toString()).collect(Collectors.joining(","));
		System.out.printf("%s\n", message);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		// Collectors.partitionBy
		System.out.printf("********************************************************\n");	
		System.out.printf("Partitioning By\n");
		System.out.printf("Concurrent: %s\n", Collectors.partitioningBy(p-> true).characteristics().contains(Characteristics.CONCURRENT));
		Map<Boolean, List<Person>> personsBySalary = persons.parallelStream()
				.collect(Collectors.partitioningBy(p -> p.getSalary() > 50000));
		personsBySalary.keySet().forEach(key -> {
			List<Person> listOfPersons = personsBySalary.get(key);
			System.out.printf("%s: %d \n", key, listOfPersons.size());
		});
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		// Collectors.toConcurrentMap
		System.out.printf("********************************************************\n");	
		System.out.printf("To Concurrent Map\n");
		System.out.printf("Concurrent: %b\n",Collectors.toConcurrentMap(p -> p, p->p).characteristics().contains(Characteristics.CONCURRENT));
		ConcurrentMap<String, String> nameMap = persons.parallelStream().collect(
				Collectors.toConcurrentMap(p -> p.getFirstName(), p -> p.getLastName(), (s1, s2) -> s1 + ", " + s2));
		nameMap.forEach((key, value) -> {
			System.out.printf("%s: %s \n", key, value);
		});
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
		
		// Collect, first example
		System.out.printf("********************************************************\n");
		System.out.printf("Collect, first example\n");
		List<Person> highSalaryPeople = persons.parallelStream().collect(
					ArrayList::new,
					(list, person) -> { 
						if (person.getSalary() > 50000) {
							list.add(person);
						}
					},
					ArrayList::addAll
				);
		System.out.printf("High Salary People: %d\n", highSalaryPeople.size());
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
		
		// Collect, second example
		System.out.printf("********************************************************\n");	
		System.out.printf("Collect, second example\n");
		ConcurrentHashMap<String, Counter> peopleNames = persons.parallelStream().collect(
					ConcurrentHashMap::new,
					(hash, person) -> {
						hash.computeIfPresent(person.getFirstName(), (name, counter) -> {
							counter.increment();
							return counter;
						});
						hash.computeIfAbsent(person.getFirstName(), name -> {
						  Counter c=new Counter();
						  c.setValue(name);
						  return c;
						});
					},
					(hash1, hash2) -> {
						hash2.forEach (10, (key, value) -> {
							hash1.merge(key, value, (v1,v2) -> {
								v1.setCounter(v1.getCounter()+v2.getCounter());
								return v1;
							});
						});
					}
				);
		
		peopleNames.forEach((name, counter) -> {
			System.out.printf("%s: %d\n", name, counter.getCounter());
		});
		
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

	}

}

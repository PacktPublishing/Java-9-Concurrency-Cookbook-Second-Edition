package com.packtpub.java9.concurrency.cookbook.chapter06.recipe07.main;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Collectors;

import com.packtpub.java9.concurrency.cookbook.chapter06.recipe07.util.Person;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe07.util.PersonGenerator;

public class Main {
	
	public static void main(String args[]) {
		
		// Sorted array of integer
		int[] numbers={9,8,7,6,5,4,3,2,1,2,3,4,5,6,7,8,9};
		System.out.printf("********************************************************\n");
		Arrays.stream(numbers).parallel().sorted().forEachOrdered(n -> {
			System.out.printf("%d\n", n);
		});
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
		
		// Sorted for Persons
		System.out.printf("********************************************************\n");
		List<Person> persons=PersonGenerator.generatePersonList(10);
		persons.parallelStream().sorted().forEachOrdered(p -> {
			System.out.printf("%s, %s\n",p.getLastName(),p.getFirstName());
		});
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
		
		// Unordered
		System.out.printf("********************************************************\n");
		Person person=persons.get(0);
		System.out.printf("%s %s\n", person.getFirstName(),person.getLastName());
		
		TreeSet<Person> personSet=new TreeSet<>(persons);
		for (int i=0; i<10; i++) {
			System.out.printf("********** %d ***********\n",i);
			person=personSet.stream().parallel().limit(1).collect(Collectors.toList()).get(0);
			System.out.printf("%s %s\n", person.getFirstName(),person.getLastName());
		
			person=personSet.stream().unordered().parallel().limit(1).collect(Collectors.toList()).get(0);
			System.out.printf("%s %s\n", person.getFirstName(),person.getLastName());
			System.out.printf("*************************\n",i);
		}
		
	}

}

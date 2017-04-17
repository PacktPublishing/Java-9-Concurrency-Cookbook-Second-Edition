package com.packtpub.java9.concurrency.cookbook.chapter06.recipe04.main;

import java.util.List;

import com.packtpub.java9.concurrency.cookbook.chapter06.recipe04.util.DoubleGenerator;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe04.util.Person;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe04.util.PersonGenerator;

public class Main {

	public static void main(String[] args) {
		
		List<Person> persons=PersonGenerator.generatePersonList(10);
		
		// For each parallel
		System.out.printf("********************************************************\n");
		System.out.printf("Parallel forEach()\n");
		persons.parallelStream().forEach(p -> {
			System.out.printf("%s, %s\n", p.getLastName(), p.getFirstName());
		});
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
		
		// For each ordered
		List<Double> doubles= DoubleGenerator.generateDoubleList(10, 100);
		
		// For each ordered parallel with numbers
		System.out.printf("********************************************************\n");
		System.out.printf("Parallel forEachOrdered() with numbers\n");
		doubles.parallelStream().sorted().forEachOrdered(n -> {
			System.out.printf("%f\n",n);
		});
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
		
		// For each after sort with numbers
		System.out.printf("********************************************************\n");
		System.out.printf("Parallel forEach() after sorted() with numbers\n");
		doubles.parallelStream().sorted().forEach(n -> {
			System.out.printf("%f\n",n);
		});
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
		
		// For each ordered parallel with objects
		System.out.printf("********************************************************\n");
		System.out.printf("Parallel forEachOrdered with Persons\n");
		persons.parallelStream().sorted().forEachOrdered( p -> {
			System.out.printf("%s, %s\n", p.getLastName(), p.getFirstName());
		});
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
		
		// Peek
		System.out.printf("********************************************************\n");
		System.out.printf("Peek\n");
		doubles
			.parallelStream()
			.peek(d -> System.out.printf("Step 1: Number: %f\n",d))
			.filter( n -> n < 50)
			.peek(d -> System.out.printf("Step 2: Number: %f\n",d))
			.forEach(d -> System.out.printf("Final Step: Number: %f\n",d));	
		System.out.printf("********************************************************\n");
		
		
	}

}

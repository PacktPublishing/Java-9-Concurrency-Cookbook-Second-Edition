package com.packtpub.java9.concurrency.cookbook.chapter06.recipe08.main;

import java.util.List;

import com.packtpub.java9.concurrency.cookbook.chapter06.recipe08.util.Person;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe08.util.PersonGenerator;

public class Main {

	public static void main(String[] args) {
		List<Person> persons = PersonGenerator.generatePersonList(10);

		System.out.printf("********************************************************\n");
		int maxSalary = persons.parallelStream().map(p -> p.getSalary()).max(Integer::compare).get();
		int minSalary = persons.parallelStream().mapToInt(p -> p.getSalary()).min().getAsInt();
		System.out.printf("Salaries are between %d and %d\n", minSalary, maxSalary);
		System.out.printf("********************************************************\n");

		boolean condition;

		System.out.printf("********************************************************\n");
		condition = persons.parallelStream().allMatch(p -> p.getSalary() > 0);
		System.out.printf("Salary > 0: %b\n", condition);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		System.out.printf("********************************************************\n");
		condition = persons.parallelStream().allMatch(p -> p.getSalary() > 10000);
		System.out.printf("Salary > 10000: %b\n", condition);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		System.out.printf("********************************************************\n");
		condition = persons.parallelStream().allMatch(p -> p.getSalary() > 30000);
		System.out.printf("Salary > 30000: %b\n", condition);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		System.out.printf("********************************************************\n");
		condition = persons.parallelStream().anyMatch(p -> p.getSalary() > 50000);
		System.out.printf("Any with salary > 50000: %b\n", condition);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		System.out.printf("********************************************************\n");
		condition = persons.parallelStream().anyMatch(p -> p.getSalary() > 100000);
		System.out.printf("Any with salary > 100000: %b\n", condition);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		System.out.printf("********************************************************\n");
		condition = persons.parallelStream().noneMatch(p -> p.getSalary() > 100000);
		System.out.printf("None with salary > 100000: %b\n", condition);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		Person person;
		System.out.printf("********************************************************\n");

		person = persons.parallelStream().findAny().get();
		System.out.printf("Any: %s %s: %d\n", person.getFirstName(), person.getLastName(), person.getSalary());

		person = persons.parallelStream().findFirst().get();
		System.out.printf("First: %s %s: %d\n", person.getFirstName(), person.getLastName(), person.getSalary());

		person = persons.parallelStream().sorted((p1,p2) -> { 
			return p1.getSalary() - p2.getSalary();
		}).findFirst().get();
		System.out.printf("First Sorted: %s %s: %d\n", person.getFirstName(), person.getLastName(), person.getSalary());
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
	}

}

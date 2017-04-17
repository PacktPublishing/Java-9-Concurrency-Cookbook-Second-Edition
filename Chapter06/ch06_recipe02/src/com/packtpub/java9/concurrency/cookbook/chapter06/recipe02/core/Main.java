package com.packtpub.java9.concurrency.cookbook.chapter06.recipe02.core;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

import com.packtpub.java9.concurrency.cookbook.chapter06.recipe02.util.DoubleGenerator;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe02.util.Person;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe02.util.PersonGenerator;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe02.util.Point;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe02.util.PointGenerator;

public class Main {

	public static void main(String args[]) {
		
		System.out.printf("********************************************************\n");
		System.out.printf("Main: Examples of reduce methods.\n");
		
		// Working with streams of numbers
		System.out.printf("Main: Creating a list of double numbers.\n");
		List<Double> numbers=DoubleGenerator.generateDoubleList(10000, 1000);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
		
		// Getting the number of elements
		System.out.printf("********************************************************\n");
		DoubleStream doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		long numberOfElements = doubleStream.parallel().count();
		System.out.printf("The list of numbers has %d elements.\n", numberOfElements);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		// Getting the sum of the numbers
		System.out.printf("********************************************************\n");
		doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		double sum = doubleStream.parallel().sum();
		System.out.printf("Its numbers sum %f.\n", sum);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		// Getting the average of the numbers
		System.out.printf("********************************************************\n");
		doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		double average = doubleStream.parallel().average().getAsDouble();
		System.out.printf("Its numbers have an average value of %f.\n", average);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		// Getting the highest value
		System.out.printf("********************************************************\n");
		doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		double max = doubleStream.parallel().max().getAsDouble();
		System.out.printf("The maximum value in the list is %f.\n", max);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		// Getting the lowest value
		System.out.printf("********************************************************\n");
		doubleStream = DoubleGenerator.generateStreamFromList(numbers);
		double min = doubleStream.parallel().min().getAsDouble();
		System.out.printf("The minimum value in the list is %f.\n", min);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
		
		// Reduce method. First version
		System.out.printf("********************************************************\n");
		System.out.printf("Reduce - First Version\n");
		List<Point> points=PointGenerator.generatePointList(10000);		
		Optional<Point> point=points.parallelStream().reduce((p1,p2) -> {
			Point p=new Point();
			p.setX(p1.getX()+p2.getX());
			p.setY(p1.getY()+p2.getY());
			return p;
		});
		System.out.println(point.get().getX()+":"+point.get().getY());
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
	
		// Reduce method. Second version
		System.out.printf("********************************************************\n");
		System.out.printf("Reduce, second version\n");
		List<Person> persons = PersonGenerator.generatePersonList(10000);
		
		long totalSalary=persons.parallelStream().map(p -> p.getSalary()).reduce(0, (s1,s2) -> s1+s2);
		System.out.printf("Total salary: %d\n",totalSalary);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
		
		// Reduce mehtod. Third version
		System.out.printf("********************************************************\n");
		System.out.printf("Reduce, third version\n");
		Integer value=0;
		value=persons.parallelStream().reduce(value, (n,p) -> {
			if (p.getSalary() > 50000) {
				return n+1;
			} else {
				return n;
			}
		}, (n1,n2) -> n1+n2);
		System.out.printf("The number of people with a salary bigger that 50,000 is %d\n",value);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");
		
	}
}

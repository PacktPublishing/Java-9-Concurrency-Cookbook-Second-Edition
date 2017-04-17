package com.packtpub.java9.concurrency.cookbook.chapter06.recipe06.main;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import com.packtpub.java9.concurrency.cookbook.chapter06.recipe06.util.BasicPerson;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe06.util.FileGenerator;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe06.util.Person;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe06.util.PersonGenerator;

public class Main {

	public static void main(String[] args) {

		// Create list of persons
		List<Person> persons = PersonGenerator.generatePersonList(100);

		// Map to double
		System.out.printf("********************************************************\n");
		DoubleStream ds = persons.parallelStream().mapToDouble(p -> p.getSalary());
		ds.distinct().forEach(d -> {
			System.out.printf("Salary: %f\n", d);
		});
		ds = persons.parallelStream().mapToDouble(p -> p.getSalary());
		long size = ds.distinct().count();
		System.out.printf("Size: %d\n", size);
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		// Map to object
		System.out.printf("********************************************************\n");
		List<BasicPerson> basicPersons = persons.parallelStream().map(p -> {
			BasicPerson bp = new BasicPerson();
			bp.setName(p.getFirstName() + " " + p.getLastName());
			bp.setAge(getAge(p.getBirthDate()));
			return bp;
		}).collect(Collectors.toList());

		basicPersons.forEach(bp -> {
			System.out.printf("%s: %d\n", bp.getName(), bp.getAge());
		});
		System.out.printf("********************************************************\n");
		System.out.printf("\n");

		// Flap Map
		System.out.printf("********************************************************\n");
		List<String> file = FileGenerator.generateFile(100);
		Map<String, Long> wordCount = file.parallelStream().flatMap(line -> Stream.of(line.split("[ ,.]")))
				.filter(w -> w.length() > 0).sorted().collect(Collectors.groupingByConcurrent(e -> e, Collectors.counting()));

		wordCount.forEach((k, v) -> {
			System.out.printf("%s: %d\n", k, v);
		});
		System.out.printf("********************************************************\n");
	}

	private static long getAge(Date birthDate) {
		LocalDate start = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = LocalDate.now();
		long ret = ChronoUnit.YEARS.between(start, now);

		return ret;
	}

}

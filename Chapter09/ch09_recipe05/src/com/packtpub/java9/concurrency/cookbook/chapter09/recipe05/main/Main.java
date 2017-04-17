package com.packtpub.java9.concurrency.cookbook.chapter09.recipe05.main;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

	public static void main(String[] args) {
		
		AtomicLong counter = new AtomicLong(0);
		Random random=new Random();
		
		long streamCounter = random.doubles(1000).parallel().peek( number -> {
			long actual=counter.incrementAndGet();
			System.out.printf("%d - %f\n", actual, number);
		}).count();
		
		System.out.printf("Counter: %d\n", counter.get());
		System.out.printf("Stream Counter: %d\n", streamCounter);
		
		counter.set(0);
		random.doubles(1000).parallel().peek(number -> {
			long actual=counter.incrementAndGet();
			System.out.printf("Peek: %d - %f\n", actual,number);
		}).forEach( number -> {
			System.out.printf("For Each: %f\n", number);
		});
		
		System.out.printf("Counter: %d\n", counter.get());
	}
}

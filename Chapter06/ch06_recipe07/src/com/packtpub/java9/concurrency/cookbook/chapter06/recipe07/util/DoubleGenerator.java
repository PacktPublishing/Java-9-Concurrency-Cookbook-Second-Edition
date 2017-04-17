package com.packtpub.java9.concurrency.cookbook.chapter06.recipe07.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;

public class DoubleGenerator {

	public static List<Double> generateDoubleList(int size, int max) {
		Random random=new Random();
		List<Double> numbers=new ArrayList<>();
		
		for (int i=0; i<size; i++) {
			double value=random.nextDouble()*max;
			numbers.add(value);
		}
		return numbers;
	}
	
	public static DoubleStream generateStreamFromList(List<Double> list) {
		DoubleStream.Builder builder=DoubleStream.builder();
		
		for (Double number : list) {
			builder.add(number);
		}
		
		return builder.build();
	}
}
 
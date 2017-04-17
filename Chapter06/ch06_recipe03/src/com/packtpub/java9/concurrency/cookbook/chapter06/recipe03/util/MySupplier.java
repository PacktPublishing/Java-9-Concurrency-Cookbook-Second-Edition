package com.packtpub.java9.concurrency.cookbook.chapter06.recipe03.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class MySupplier implements Supplier<String> {

	private AtomicInteger counter;

	public MySupplier() {
		counter=new AtomicInteger(0);
	}
	
	@Override
	public String get() {
		int value=counter.getAndAdd(1);
		return "String "+value;
	}

}

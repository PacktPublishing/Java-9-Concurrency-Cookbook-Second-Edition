package com.packtpub.java9.concurrency.cookbook.chapter06.recipe09.main;

import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter06.recipe09.consumers.Consumer1;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe09.consumers.Consumer2;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe09.consumers.Consumer3;
import com.packtpub.java9.concurrency.cookbook.chapter06.recipe09.items.Item;

public class Main {

	public static void main(String[] args) {

		Consumer1 consumer1=new Consumer1();
		Consumer2 consumer2=new Consumer2();
		Consumer3 consumer3=new Consumer3();
		
		SubmissionPublisher<Item> publisher=new SubmissionPublisher<>();
		
		publisher.subscribe(consumer1);
		publisher.subscribe(consumer2);
		publisher.subscribe(consumer3);
		
		for (int i=0; i<10; i++) {
			Item item =new Item();
			item.setTitle("Item "+i);
			item.setContent("This is the item "+i);
			publisher.submit(item);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		publisher.close();
	}

}

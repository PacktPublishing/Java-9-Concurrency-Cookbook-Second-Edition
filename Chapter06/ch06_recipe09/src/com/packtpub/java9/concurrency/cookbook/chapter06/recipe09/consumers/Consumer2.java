package com.packtpub.java9.concurrency.cookbook.chapter06.recipe09.consumers;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;

import com.packtpub.java9.concurrency.cookbook.chapter06.recipe09.items.Item;

public class Consumer2 implements Flow.Subscriber<Item> {
	
	private Subscription subscription;

	@Override
	public void onComplete() {
		System.out.printf("%s: Consumer 2: Completed\n", Thread.currentThread().getName());	
	}

	@Override
	public void onError(Throwable exception) {
		System.out.printf("%s: Consumer 2: Error\n", Thread.currentThread().getName());
		exception.printStackTrace(System.err);
	}

	@Override
	public void onNext(Item item) {
		System.out.printf("%s: Consumer 2: Item received\n", Thread.currentThread().getName());
		System.out.printf("%s: Consumer 2: %s\n", Thread.currentThread().getName(),item.getTitle());
		System.out.printf("%s: Consumer 2: %s\n", Thread.currentThread().getName(), item.getContent());
		subscription.request(1);
	}

	@Override
	public void onSubscribe(Flow.Subscription subscription) {
		System.out.printf("%s: Consumer 2: Subscription received\n", Thread.currentThread().getName());
		this.subscription=subscription;
		subscription.request(1);
	}

}

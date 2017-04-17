package com.packtpub.java9.concurrency.cookbook.chapter08.recipe12.stream;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.ThreadPoolExecutor;

import com.packtpub.java9.concurrency.cookbook.chapter08.recipe12.data.News;

public class MyPublisher implements Publisher<News> {

	private ConcurrentLinkedDeque<ConsumerData> consumers;
	private ThreadPoolExecutor executor;
	
	public MyPublisher() {
		consumers=new ConcurrentLinkedDeque<>();
		executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
	
	@Override
	public void subscribe(Subscriber<? super News> subscriber) {
		
		ConsumerData consumerData=new ConsumerData();
		consumerData.setConsumer((Consumer)subscriber);
		
		MySubscription subscription=new MySubscription();
		consumerData.setSubscription(subscription);
		
		subscriber.onSubscribe(subscription);
		
		consumers.add(consumerData);
	}
	
	public void publish(News news) {
		consumers.forEach( consumerData -> {
			try {
				executor.execute(new PublisherTask(consumerData, news));
			} catch (Exception e) {
				consumerData.getConsumer().onError(e);
			}
		});
	}

}

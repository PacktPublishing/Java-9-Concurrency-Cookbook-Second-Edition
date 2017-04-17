package com.packtpub.java9.concurrency.cookbook.chapter08.recipe12.main;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Subscriber;

import com.packtpub.java9.concurrency.cookbook.chapter08.recipe12.data.News;
import com.packtpub.java9.concurrency.cookbook.chapter08.recipe12.stream.Consumer;
import com.packtpub.java9.concurrency.cookbook.chapter08.recipe12.stream.MyPublisher;

public class Main {

	public static void main(String[] args) {

		MyPublisher publisher=new MyPublisher();
		
		Subscriber<News> consumer1, consumer2;
		consumer1=new Consumer("Consumer 1");
		consumer2=new Consumer("Consumer 2");
		
		publisher.subscribe(consumer1);
		publisher.subscribe(consumer2);
		
		System.out.printf("Main: Start\n");
		
		News news=new News();
		news.setTitle("My first news");
		news.setContent("This is the content");
		news.setDate(new Date());

		publisher.publish(news);
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		news=new News();
		news.setTitle("My second news");
		news.setContent("This is the content of the second news");
		news.setDate(new Date());
		publisher.publish(news);
		
		System.out.printf("Main: End\n");
		
	}

}

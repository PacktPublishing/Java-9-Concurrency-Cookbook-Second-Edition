package com.packtpub.java9.concurrency.cookbook.chapter05.recipe01.core;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter05.recipe01.task.Task;
import com.packtpub.java9.concurrency.cookbook.chapter05.recipe01.util.Product;
import com.packtpub.java9.concurrency.cookbook.chapter05.recipe01.util.ProductListGenerator;

/**
 * Main class of the example. It creates a list of products, a ForkJoinPool and 
 * a task to execute the actualization of products. 
 *
 */
public class Main {

	/**
	 * Main method of the example
	 * @param args
	 */
	public static void main(String[] args) {

		// Create a list of products
		ProductListGenerator generator=new ProductListGenerator();
		List<Product> products=generator.generate(10000);
		
		// Craete a task
		Task task=new Task(products, 0, products.size(), 0.20);
		
		// Create a ForkJoinPool
		ForkJoinPool pool=new ForkJoinPool();
		
		// Execute the Task
		pool.execute(task);

		// Write information about the pool
		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n",pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n",pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n",pool.getStealCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
	
		// Shutdown the pool
		pool.shutdown();
		
		// Check if the task has completed normally
		if (task.isCompletedNormally()){
			System.out.printf("Main: The process has completed normally.\n");
		}

		// Expected result: 12. Write products which price is not 12
		for (int i=0; i<products.size(); i++){
			Product product=products.get(i);
			if (product.getPrice()!=12) {
				System.out.printf("Product %s: %f\n",product.getName(),product.getPrice());
			}
		}
		
		// End of the program
		System.out.println("Main: End of the program.\n");

	}

}

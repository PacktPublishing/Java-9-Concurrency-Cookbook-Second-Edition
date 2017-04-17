package com.packtpub.java9.concurrency.cookbook.chapter05.recipe03.core;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import com.packtpub.java9.concurrency.cookbook.chapter05.recipe03.task.FolderProcessor;

/**
 * Main class of the example
 */
public class Main {

	/**
	 * Main method of the example
	*/
	public static void main(String[] args) {
		// Create the pool
		ForkJoinPool pool=new ForkJoinPool();
		
		// Create three FolderProcessor tasks for three diferent folders
		FolderProcessor system=new FolderProcessor("C:\\Windows", "log");
		FolderProcessor apps=new FolderProcessor("C:\\Program Files","log");
		FolderProcessor documents=new FolderProcessor("C:\\Documents And Settings","log");
		
		// Execute the three tasks in the pool
		pool.execute(system);
		pool.execute(apps);
		pool.execute(documents);
		
		// Write statistics of the pool until the three tasks end
		do {
			System.out.printf("******************************************\n");
			System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
			System.out.printf("Main: Active Threads: %d\n",pool.getActiveThreadCount());
			System.out.printf("Main: Task Count: %d\n",pool.getQueuedTaskCount());
			System.out.printf("Main: Steal Count: %d\n",pool.getStealCount());
			System.out.printf("Main: %b - %b - %b\n",system.isDone(), apps.isDone(), documents.isDone());
			System.out.printf("Main: %d - %d - %d\n",system.getPendingCount(), apps.getPendingCount(), documents.getPendingCount());
			System.out.printf("******************************************\n");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while ((!system.isDone())||(!apps.isDone())||(!documents.isDone()));
		
		// Shutdown the pool
		pool.shutdown();
		
		// Write the number of results calculate by each task
		List<String> results;
		
		results=system.getResultList();
		System.out.printf("System: %d files found.\n",results.size());
		
		results=apps.getResultList();
		System.out.printf("Apps: %d files found.\n",results.size());
		
		results=documents.getResultList();
		System.out.printf("Documents: %d files found.\n",results.size());
	}

}

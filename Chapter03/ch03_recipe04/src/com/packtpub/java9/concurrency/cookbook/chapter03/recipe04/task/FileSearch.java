package com.packtpub.java9.concurrency.cookbook.chapter03.recipe04.task;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * This class search for files with an extension in a directory
 */
public class FileSearch implements Runnable {

	/**
	 * Initial path for the search
	 */
	private final String initPath;

	/**
	 * Extension of the file we are searching for
	 */
	private final String fileExtension;

	/**
	 * List that stores the full path of the files that have the extension we
	 * are searching for
	 */
	private List<String> results;

	/**
	 * Phaser to control the execution of the FileSearch objects. Their
	 * execution will be divided in three phases 1st: Look in the folder and its
	 * subfolders for the files with the extension 2nd: Filter the results. We
	 * only want the files modified today 3rd: Print the results
	 */
	private Phaser phaser;

	/**
	 * Constructor of the class. Initializes its attributes
	 * 
	 * @param initPath
	 *            Initial path for the search
	 * @param fileExtension
	 *            Extension of the files we are searching for
	 * @param phaser
	 *            Phaser object to control the execution
	 */
	public FileSearch(String initPath, String fileExtension, Phaser phaser) {
		this.initPath = initPath;
		this.fileExtension = fileExtension;
		this.phaser = phaser;
		results = new ArrayList<>();
	}

	/**
	 * Main method of the class. See the comments inside to a better description
	 * of it
	 */
	@Override
	public void run() {

		// Waits for the creation of all the FileSearch objects
		phaser.arriveAndAwaitAdvance();

		System.out.printf("%s: Starting.\n", Thread.currentThread().getName());

		// 1st Phase: Look for the files
		File file = new File(initPath);
		if (file.isDirectory()) {
			directoryProcess(file);
		}

		// If no results, deregister in the phaser and ends
		if (!checkResults()) {
			return;
		}

		// 2nd Phase: Filter the results
		filterResults();

		// If no results after the filter, deregister in the phaser and ends
		if (!checkResults()) {
			return;
		}

		// 3rd Phase: Show info
		showInfo();
		phaser.arriveAndDeregister();
		System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());

	}

	/**
	 * This method prints the final results of the search
	 */
	private void showInfo() {
		for (int i = 0; i < results.size(); i++) {
			File file = new File(results.get(i));
			System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
		}
		// Waits for the end of all the FileSearch threads that are registered
		// in the phaser
		phaser.arriveAndAwaitAdvance();
	}

	/**
	 * This method checks if there are results after the execution of a phase.
	 * If there aren't results, deregister the thread of the phaser.
	 * 
	 * @return true if there are results, false if not
	 */
	private boolean checkResults() {
		if (results.isEmpty()) {
			System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());
			System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), phaser.getPhase());
			// No results. Phase is completed but no more work to do. Deregister
			// for the phaser
			phaser.arriveAndDeregister();
			return false;
		} else {
			// There are results. Phase is completed. Wait to continue with the
			// next phase
			System.out.printf("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), phaser.getPhase(),
					results.size());
			phaser.arriveAndAwaitAdvance();
			return true;
		}
	}

	/**
	 * Method that filter the results to delete the files modified more than a
	 * day before now
	 */
	private void filterResults() {
		List<String> newResults = new ArrayList<>();
		long actualDate = new Date().getTime();
		for (int i = 0; i < results.size(); i++) {
			File file = new File(results.get(i));
			long fileDate = file.lastModified();

			if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
				newResults.add(results.get(i));
			}
		}
		results = newResults;
	}

	/**
	 * Method that process a directory
	 * 
	 * @param file
	 *            : Directory to process
	 */
	private void directoryProcess(File file) {

		// Get the content of the directory
		File list[] = file.listFiles();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].isDirectory()) {
					// If is a directory, process it
					directoryProcess(list[i]);
				} else {
					// If is a file, process it
					fileProcess(list[i]);
				}
			}
		}
	}

	/**
	 * Method that process a File
	 * 
	 * @param file
	 *            : File to process
	 */
	private void fileProcess(File file) {
		if (file.getName().endsWith(fileExtension)) {
			results.add(file.getAbsolutePath());
		}
	}

}

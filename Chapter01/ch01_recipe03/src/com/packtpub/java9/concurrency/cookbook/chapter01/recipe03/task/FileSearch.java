package com.packtpub.java9.concurrency.cookbook.chapter01.recipe03.task;

import java.io.File;

/**
 * This class search for files with a name in a directory
 */
public class FileSearch implements Runnable {

	/**
	 * Initial path for the search
	 */
	private String initPath;
	/**
	 * Name of the file we are searching for
	 */
	private String fileName;

	/**
	 * Constructor of the class
	 * 
	 * @param initPath
	 *            : Initial path for the search
	 * @param fileName
	 *            : Name of the file we are searching for
	 */
	public FileSearch(String initPath, String fileName) {
		this.initPath = initPath;
		this.fileName = fileName;
	}

	/**
	 * Main method of the class
	 */
	@Override
	public void run() {
		File file = new File(initPath);
		if (file.isDirectory()) {
			try {
				directoryProcess(file);
			} catch (InterruptedException e) {
				System.out.printf("%s: The search has been interrupted", Thread.currentThread().getName());
				cleanResources();
			}
		}
	}

	/**
	 * Method for cleaning the resources. In this case, is empty
	 */
	private void cleanResources() {

	}

	/**
	 * Method that process a directory
	 * 
	 * @param file
	 *            : Directory to process
	 * @throws InterruptedException
	 *             : If the thread is interrupted
	 */
	private void directoryProcess(File file) throws InterruptedException {

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
		// Check the interruption
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

	/**
	 * Method that process a File
	 * 
	 * @param file
	 *            : File to process
	 * @throws InterruptedException
	 *             : If the thread is interrupted
	 */
	private void fileProcess(File file) throws InterruptedException {
		// Check the name
		if (file.getName().equals(fileName)) {
			System.out.printf("%s : %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
		}

		// Check the interruption
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}

}

package com.packtpub.java9.concurrency.cookbook.chapter10.recipe01.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	private final  String end;
	
	/**
	 * List that stores the full path of the files that have the extension we are searching for
	 */
	private final List<String> results;
	

	/**
	 * Constructor of the class. Initializes its attributes
	 * @param initPath Initial path for the search
	 * @param end Extension of the files we are searching for
	 * @param phaser Phaser object to control the execution
	 */
	public FileSearch(String initPath, String end) {
		this.initPath = initPath;
		this.end = end;
		results=new ArrayList<>();
	}

	/**
	 * Method that returns the list or results
	 * @return
	 */
	public List<String> getResults() {
		return results;
	}

	/**
	 * Main method of the class. See the comments inside to a better description of it
	 */
	@Override
	public void run() {
		
		System.out.printf("%s: Starting\n",Thread.currentThread().getName());
		
		// 1st Phase: Look for the files
		File file = new File(initPath);
		if (file.isDirectory()) {
			directoryProcess(file);
		}
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
		if (file.getName().endsWith(end)) {
			results.add(file.getAbsolutePath());
		}
	}

}

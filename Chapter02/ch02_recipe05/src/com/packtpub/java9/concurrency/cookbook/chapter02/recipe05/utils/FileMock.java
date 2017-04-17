package com.packtpub.java9.concurrency.cookbook.chapter02.recipe05.utils;

/**
 * This class simulates a text file. It creates a defined number of random lines
 * to process them sequentially.
 *
 */
public class FileMock {

	/**
	 * Content of the simulate file
	 */
	private String[] content;
	/**
	 * Number of the line we are processing
	 */
	private int index;

	/**
	 * Constructor of the class. Generate the random data of the file
	 * 
	 * @param size:
	 *            Number of lines in the simulate file
	 * @param length:
	 *            Length of the lines
	 */
	public FileMock(int size, int length) {
		content = new String[size];
		for (int i = 0; i < size; i++) {
			StringBuilder buffer = new StringBuilder(length);
			for (int j = 0; j < length; j++) {
				int randomCharacter = (int) Math.random() * 255;
				buffer.append((char) randomCharacter);
			}
			content[i] = buffer.toString();
		}
		index = 0;
	}

	/**
	 * Returns true if the file has more lines to process or false if not
	 * 
	 * @return true if the file has more lines to process or false if not
	 */
	public boolean hasMoreLines() {
		return index < content.length;
	}

	/**
	 * Returns the next line of the simulate file or null if there aren't more
	 * lines
	 * 
	 * @return
	 */
	public String getLine() {
		if (this.hasMoreLines()) {
			System.out.println("Mock: " + (content.length - index));
			return content[index++];
		}
		return null;
	}

}

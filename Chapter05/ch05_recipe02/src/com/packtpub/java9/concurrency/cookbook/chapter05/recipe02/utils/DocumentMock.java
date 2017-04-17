package com.packtpub.java9.concurrency.cookbook.chapter05.recipe02.utils;

import java.util.Random;

/**
 * This class will simulate a document generating a String array with a determined number
 * of rows (numLines) and columns(numWords). The content of the document will be generated
 * selecting in a random way words from a String array.
 *
 */
public class DocumentMock {
	
	/**
	 * String array with the words of the document
	 */
	private String words[]={"the","hello","goodbye","packt","java","thread","pool","random","class","main"};

	/**
	 * Method that generates the String matrix
	 * @param numLines Number of lines of the document
	 * @param numWords Number of words of the document
	 * @param word Word we are going to search for
	 * @return The String matrix
	 */
	public String[][] generateDocument(int numLines, int numWords, String word){
		
		int counter=0;
		String document[][]=new String[numLines][numWords];
		Random random=new Random();
		for (int i=0; i<numLines; i++){
			for (int j=0; j<numWords; j++) {
				int index=random.nextInt(words.length);
				document[i][j]=words[index];
				if (document[i][j].equals(word)){
					counter++;
				}
			}
		}
		System.out.printf("DocumentMock: The word appears %d times in the document.\n",counter);
		return document;
	}
}

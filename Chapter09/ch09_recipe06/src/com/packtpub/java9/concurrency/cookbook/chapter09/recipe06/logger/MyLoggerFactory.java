package com.packtpub.java9.concurrency.cookbook.chapter09.recipe06.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to create a Logger object with the configuration
 * you want. In this case, you're going to write all the log messages generated
 * in the application to the recipe8.log file and with the format specified in the
 * MyFormatter class. It uses the Logger class to create the Logger object. This class
 * creates a Logger object per name that is passed as parameter. 
 *
 */
public class MyLoggerFactory {
	
	/**
	 * Handler to control that the log messages are written in the recipe8.log file
	 */
	private static Handler handler;
	
	/**
	 * Static method that returns the log object associated with the name received
	 * as parameter. If it's a new Logger object, this method configures it with your
	 * configuration. 
	 * @param name Name of the Logger object you want to obtain.
	 * @return The Logger object generated.
	 */
	public synchronized static Logger getLogger(String name){
		/*
		 * Get the logger
		 */
		Logger logger=Logger.getLogger(name);
		/*
		 * Set the level to show all the messages
		 */
		logger.setLevel(Level.ALL);
		try {
			/*
			 * If the Handler object is null, we create one to
			 * write the log messages in the recipe6.log file
			 * with the format specified by the MyFormatter class
			 */
			if (handler==null) {
				handler=new FileHandler("recipe6.log");
				Formatter format=new MyFormatter();
				handler.setFormatter(format);
			}
			/*
			 * If the Logger object hasn't handler, we add the Handler object
			 * to it
			 */
			if (logger.getHandlers().length==0) {
				logger.addHandler(handler);
			}
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * Return the Logger object.
		 */
		return logger;
	}

}

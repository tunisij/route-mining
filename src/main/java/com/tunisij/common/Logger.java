package com.tunisij.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * This is a Bill Pugh singleton class for logging errors
 */
public class Logger {
	
	private static final String LOG_FILE_PATH = System.getProperty("user.dir") + "\\" + "route-mining.txt";

	private Logger() {}
	
	//static inner classnot loaded until referenced
	private static class LoggerHelper {
		private static final Logger INSTANCE = new Logger();
	}
	
	public static Logger getLogger() {
		return LoggerHelper.INSTANCE;
	}
	
	public void log(String log) {
		log += System.lineSeparator();
		try {
			Files.write(Paths.get(LOG_FILE_PATH), log.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

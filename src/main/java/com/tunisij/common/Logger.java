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
	
	//static inner class not loaded until referenced
	private static class LoggerHelper {
		private static final Logger INSTANCE = new Logger();
	}
	
	public static Logger getLogger() {
		return LoggerHelper.INSTANCE;
	}
	
	public void log(String log, Exception exception) {
		StringBuilder sb = new StringBuilder(log);
		sb.append(System.lineSeparator());
		sb.append("Caused by:");
		sb.append(System.lineSeparator());
		for (StackTraceElement element : exception.getStackTrace()) {
			sb.append(element.getClassName());
			sb.append(": ");
			sb.append(element.getMethodName());
			sb.append(": line ");
			sb.append(element.getLineNumber());
			sb.append(System.lineSeparator());
		}
		sb.append(System.lineSeparator());
		try {
			Files.write(Paths.get(LOG_FILE_PATH), sb.toString().getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

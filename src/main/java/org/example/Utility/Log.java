package org.example.Utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {// Utility class for logging messages using Log4j2
    private static final Logger logger = LogManager.getLogger(Log.class);// Create a logger instance for this class

    public static void info(String message) {
        logger.info(message);
    }// Log an informational message

    public static void warn(String message) {
        logger.warn(message);
    }// Log a warning message

    public static void error(String message) {
        logger.error(message);
    }// Log an error message

    public static void debug(String message) {
        logger.debug(message);
    }// Log a debug message
}

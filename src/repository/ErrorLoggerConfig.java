package repository;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ErrorLoggerConfig {

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void setup() {
        try {

            for (var handler : logger.getHandlers()) {
                logger.removeHandler(handler);
            }

            FileHandler fileHandler = new FileHandler("application.log", true);

            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);

            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            Logger.getLogger(ErrorLoggerConfig.class.getName()).log(Level.SEVERE, "Error setting up logger", e);
        }
    }

    public static void logMessage(String message) {
        logger.log(Level.SEVERE, message);
    }
}

package LoggingFramework.demo;

import LoggingFramework.appender.ConsoleAppender;
import LoggingFramework.core.Logger;
import LoggingFramework.core.LoggerManager;
import LoggingFramework.dto.LogLevel;
import LoggingFramework.formatter.DefaultFormatter;

public class LoggerDemo {
    public static void main(String[] args) {
        Logger logger = LoggerManager.getInstance().getLogger(LoggerDemo.class.getName());

        logger.addAppender(new ConsoleAppender(new DefaultFormatter()));
        logger.setLogLevel(LogLevel.FATAL);

        // this will not be printed
        logger.debug("This is an debug message");

        // this will be printed
        logger.fatal("This is an fatal message");
    }
}

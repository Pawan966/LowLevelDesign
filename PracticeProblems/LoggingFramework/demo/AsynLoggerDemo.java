package LoggingFramework.demo;

import LoggingFramework.appender.AsynAppender;
import LoggingFramework.appender.ConsoleAppender;
import LoggingFramework.appender.FileAppender;
import LoggingFramework.core.Logger;
import LoggingFramework.core.LoggerManager;
import LoggingFramework.dto.LogLevel;
import LoggingFramework.formatter.DefaultFormatter;
import LoggingFramework.formatter.Formatter;

public class AsynLoggerDemo {
    public static void main(String[] args) {

        Logger logger = LoggerManager.getInstance().getLogger(AsynLoggerDemo.class.getName());
        logger.setLogLevel(LogLevel.DEBUG);

        Formatter formatter = new DefaultFormatter();

        FileAppender fileAppender = new FileAppender(formatter, "app.log");
        ConsoleAppender consoleAppender = new ConsoleAppender(formatter);

        AsynAppender asynAppender = new AsynAppender(fileAppender, 1000);
        logger.addAppender(asynAppender); // async
        logger.addAppender(consoleAppender); // sync

        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                logger.debug("This is a test log message " + i);
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

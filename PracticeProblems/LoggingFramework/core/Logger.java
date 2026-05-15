package LoggingFramework.core;

import LoggingFramework.appender.Appender;
import LoggingFramework.dto.LogLevel;
import LoggingFramework.dto.LogMessage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {
    private final String loggerName;
    private volatile LogLevel logLevel;
    private final List<Appender> appenders;

    public Logger(String loggerName, LogLevel logLevel) {
        this.loggerName = loggerName;
        this.logLevel = logLevel;

        // if thread A is adding appender and thread B is iterating over the appenders list
        // then concurrent modification exception will be thrown
        // to prevent this CopyOnWriteArrayList is used, it creates a copy of the list when it's modified
        this.appenders = new CopyOnWriteArrayList<>();
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void addAppender(Appender appender) {
        this.appenders.add(appender);
    }

    public void log(LogLevel logLevel, String message) {
        if (logLevel.getLevel() < this.logLevel.getLevel()) {
            return;
        }

        LogMessage log = new LogMessage(message, logLevel, loggerName);
        appenders.forEach(appender -> appender.append(log));
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

}

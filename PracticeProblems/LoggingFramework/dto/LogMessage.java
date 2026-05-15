package LoggingFramework.dto;

import java.time.LocalDateTime;

// Immutable classes are thread safe
public class LogMessage {
    private final String message;
    private final LogLevel logLevel;
    private final LocalDateTime timestamp;
    private final String loggerName;
    private final String threadName;

    public LogMessage(String message, LogLevel logLevel, String loggerName) {
        this.message = message;
        this.logLevel = logLevel;
        this.timestamp = LocalDateTime.now();
        this.loggerName = loggerName;
        this.threadName = Thread.currentThread().getName();
    }

    public String getMessage() {
        return message;
    }

    public String getThreadName() {
        return threadName;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }
}

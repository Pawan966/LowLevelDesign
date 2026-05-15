package LoggingFramework.formatter;

import LoggingFramework.dto.LogMessage;

import java.time.format.DateTimeFormatter;

public class DefaultFormatter implements Formatter {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public String format(LogMessage logMessage) {
        return String.format("%s [%s] %s %s - %s",
                logMessage.getTimestamp().format(dateTimeFormatter),
                logMessage.getThreadName(),
                logMessage.getLoggerName(),
                logMessage.getLogLevel(),
                logMessage.getMessage()
        );
    }
}

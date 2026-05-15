package LoggingFramework.appender;

import LoggingFramework.dto.LogMessage;

public interface Appender {
    void append(LogMessage logMessage);
}

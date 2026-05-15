package LoggingFramework.formatter;

import LoggingFramework.dto.LogMessage;

public interface Formatter {
    String format(LogMessage logMessage);
}

package LoggingFramework.appender;

import LoggingFramework.dto.LogMessage;
import LoggingFramework.formatter.Formatter;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileAppender implements  Appender {
    private final Formatter formatter;
    private final BufferedWriter writer;
    private final Object lock = new Object();

    public FileAppender(Formatter formatter, String fileName) {
        this.formatter = formatter;
        try{
            this.writer = Files.newBufferedWriter(Paths.get(fileName));
        }catch(Exception e){
            throw new RuntimeException("Failed to create file appender", e);
        }

        // adding this hook will ensure that the file is closed when the application is shutdown.
        // before JVM exists it waits for all hooks to complete.
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    @Override
    public void append(LogMessage logMessage) {
        synchronized (lock) {
            try{
                writer.write(formatter.format(logMessage));
                writer.newLine();
                // writer keeps a buffer in memory, close() calls flush automatically
                // but keeping it here will not accumulate data in memory before flushing

                // flush is a costly operation we should not do it for every log message.
                // doing it here to keep simple
                // ideally it should be flushed in batches or after a certain time interval.
                writer.flush();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void shutdown() {
        synchronized (lock) {
            try{
                writer.flush();
                writer.close();
            }catch(Exception e){
                throw new RuntimeException("Failed to close file appender", e);
            }
        }
    }
}

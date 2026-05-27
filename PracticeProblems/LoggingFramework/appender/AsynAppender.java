package LoggingFramework.appender;

import LoggingFramework.dto.LogMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class AsynAppender implements Appender{

    private final BlockingDeque<LogMessage> queue;
    private final Appender delegate;
    private final Thread workerThread;
    private volatile boolean running = true;

    private static final int BATCH_SIZE = 50;

    public AsynAppender(Appender delegate, int capacity) {
        this.queue = new LinkedBlockingDeque<>(capacity);
        this.delegate = delegate;

        this.workerThread = new Thread(this::processLogs, "AsyncAppender-Worker");
        this.workerThread.setDaemon(true);
        this.workerThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    @Override
    public void append(LogMessage logMessage) {
        try{
            // this put is a blocking call, if the queue is full it will wait until there is space available
            queue.put(logMessage);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void processLogs() {
        while(running || !queue.isEmpty()){
            try{
                List<LogMessage> batch = new ArrayList<>();
                // if less elements are available than BATCH_SIZE, it will return all the available elements
                queue.drainTo(batch, BATCH_SIZE);

                if(batch.isEmpty()){
                    // this is a blocking call, if the queue is empty it will wait until there is an element available
                    // otherwise the while loop will keep on running
                    batch.add(queue.take());
                }

                for(LogMessage logMessage : batch){
                    delegate.append(logMessage);
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }

    private void shutdown() {
        running = false;
        workerThread.interrupt();
    }
}

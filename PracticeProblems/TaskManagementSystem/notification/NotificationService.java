package TaskManagementSystem.notification;

import TaskManagementSystem.enums.TaskEventType;
import TaskManagementSystem.model.Task;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationService {
    private final List<TaskObserver> observers = new CopyOnWriteArrayList<>();

    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Task task, TaskEventType eventType) {
        for (TaskObserver observer : observers) {
            executor.submit(() -> {
                try{
                    observer.onTaskEvent(task, eventType);
                }catch(Exception e){
                    System.out.println("Notification failed: " + e.getMessage());
                }
            });
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}

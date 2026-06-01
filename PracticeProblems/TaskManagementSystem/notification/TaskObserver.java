package TaskManagementSystem.notification;


import TaskManagementSystem.enums.TaskEventType;
import TaskManagementSystem.model.Task;

public interface TaskObserver {
    void onTaskEvent(Task task, TaskEventType eventType);
}

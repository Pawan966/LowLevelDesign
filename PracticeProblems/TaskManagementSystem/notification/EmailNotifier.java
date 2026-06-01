package TaskManagementSystem.notification;

import TaskManagementSystem.enums.TaskEventType;
import TaskManagementSystem.model.Task;

public class EmailNotifier implements TaskObserver {
    @Override
    public void onTaskEvent(Task task, TaskEventType eventType) {
        System.out.println("[Email] Event: " + eventType +
                " | Task: " + task.getTitle() +
                " Assignee: " + task.getAssigneeId());
    }
}

package TaskManagementSystem.model;

import TaskManagementSystem.enums.Priority;
import TaskManagementSystem.enums.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

public class Task {
    private final String taskId;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private String assigneeId;
    private LocalDate dueDate;

    public Task(String title, String description, Priority priority, LocalDate dueDate) {
        this.taskId = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.status = TaskStatus.TO_DO;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public void setStatus(TaskStatus status) {
        if(this.status == TaskStatus.COMPLETED) {
            throw new IllegalStateException("Task is already completed");
        }

        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }
}

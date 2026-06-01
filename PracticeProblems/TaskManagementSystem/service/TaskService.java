package TaskManagementSystem.service;

import TaskManagementSystem.dto.TaskFilter;
import TaskManagementSystem.enums.Priority;
import TaskManagementSystem.enums.TaskEventType;
import TaskManagementSystem.enums.TaskStatus;
import TaskManagementSystem.model.Task;
import TaskManagementSystem.notification.NotificationService;
import TaskManagementSystem.repository.TaskRepository;
import TaskManagementSystem.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, NotificationService notificationService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public Task createTask(String title, String description, Priority priority, LocalDate dueDate) {
        Task task = new Task(title, description, priority, dueDate);
        taskRepository.save(task);
        notificationService.notifyObservers(task, TaskEventType.CREATED);
        return task;
    }

    public Task updateTask(String taskId, TaskStatus status) {
        Task task = findTaskOrThrow(taskId);

        synchronized (task) {
            task.setStatus(status);
            taskRepository.save(task);
        }

        notificationService.notifyObservers(task, TaskEventType.STATUS_CHANGED);
        return task;
    }

    public Task assignTask(String taskId, String assigneeId) {
        Task task = findTaskOrThrow(taskId);
        validateAssignee(assigneeId);

        synchronized (task) {
            task.setAssigneeId(assigneeId);
            taskRepository.save(task);
        }

        notificationService.notifyObservers(task, TaskEventType.ASSIGNED);
        return task;
    }

    public List<Task> getTasks(TaskFilter filter) {
        return List.copyOf(taskRepository.findAll(filter));
    }

    public void deleteTask(String taskId) {
        findTaskOrThrow(taskId);
        taskRepository.deleteById(taskId);
    }

    private Task findTaskOrThrow(String taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + taskId));
    }

    private void validateAssignee(String assigneeId) {
        userRepository.findById(assigneeId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + assigneeId));
    }
}

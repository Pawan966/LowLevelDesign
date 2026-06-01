package TaskManagementSystem;

import TaskManagementSystem.dto.TaskFilter;
import TaskManagementSystem.enums.Priority;
import TaskManagementSystem.enums.TaskStatus;
import TaskManagementSystem.model.Task;
import TaskManagementSystem.model.User;
import TaskManagementSystem.notification.EmailNotifier;
import TaskManagementSystem.notification.NotificationService;
import TaskManagementSystem.repository.InMemoryTaskRepository;
import TaskManagementSystem.repository.InMemoryUserRepository;
import TaskManagementSystem.repository.TaskRepository;
import TaskManagementSystem.repository.UserRepository;
import TaskManagementSystem.service.TaskService;

import java.time.LocalDate;
import java.util.List;

public class ClientApplication {
    public static void main(String[] args) {
        TaskRepository taskRepository = new InMemoryTaskRepository();
        UserRepository userRepository = new InMemoryUserRepository();
        NotificationService notificationService = new NotificationService();
        notificationService.addObserver(new EmailNotifier());

        TaskService taskService = new TaskService(taskRepository, userRepository, notificationService);

        // users
        User user1 = new User("a@b.com", "User 1");
        User user2 = new User("c@d.com", "User 2");
        userRepository.saveUser(user1);
        userRepository.saveUser(user2);

        // tasks
        Task task1 = taskService.createTask("Task 1", "Task 1 description", Priority.HIGH, LocalDate.now().plusDays(3));
        Task task2 = taskService.createTask("Task 2", "Task 2 description", Priority.MEDIUM, LocalDate.now().plusDays(5));
        Task task3 = taskService.createTask("Task 3", "Task 3 description", Priority.LOW, LocalDate.now().plusDays(7));

        // assign tasks
        taskService.assignTask(task1.getTaskId(), user1.getUserId());
        taskService.assignTask(task2.getTaskId(), user2.getUserId());
        taskService.assignTask(task3.getTaskId(), user1.getUserId());

        // update status
        taskService.updateTask(task1.getTaskId(), TaskStatus.IN_PROGRESS);
        taskService.updateTask(task2.getTaskId(), TaskStatus.COMPLETED);
        taskService.updateTask(task3.getTaskId(), TaskStatus.IN_PROGRESS);

        // filter tasks
        TaskFilter filter = new TaskFilter.Builder()
                .status(TaskStatus.IN_PROGRESS)
                .priority(Priority.HIGH)
                .build();
        List<Task> tasks = taskService.getTasks(filter);
        System.out.println("Filtered tasks: " + tasks.size());
    }
}

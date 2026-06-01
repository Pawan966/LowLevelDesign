package TaskManagementSystem.repository;

import TaskManagementSystem.dto.TaskFilter;
import TaskManagementSystem.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryTaskRepository implements  TaskRepository {
    private final Map<String, Task> tasks = new ConcurrentHashMap<>();


    @Override
    public void save(Task task) {
        tasks.put(task.getTaskId(), task);
    }

    @Override
    public Optional<Task> findById(String taskId) {
        return Optional.ofNullable(tasks.get(taskId));
    }

    @Override
    public List<Task> findAll(TaskFilter filter) {
        return tasks.values().stream()
                .filter(task -> filter.getStatus() == null || task.getStatus() == filter.getStatus())
                .filter(task -> filter.getAssigneeId() == null || task.getAssigneeId().equals(filter.getAssigneeId()))
                .filter(task -> filter.getPriority() == null || task.getPriority() == filter.getPriority())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String taskId) {
        tasks.remove(taskId);
    }
}

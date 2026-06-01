package TaskManagementSystem.dto;

import TaskManagementSystem.enums.Priority;
import TaskManagementSystem.enums.TaskStatus;

public class TaskFilter {
    private TaskStatus status;
    private String assigneeId;
    private Priority priority;

    private TaskFilter() {}

    public TaskStatus getStatus() {
        return status;
    }
    public String getAssigneeId() {
        return assigneeId;
    }
    public Priority getPriority() {
        return priority;
    }

    public static class Builder {
        private final TaskFilter filter = new TaskFilter();

        public Builder status(TaskStatus status) {
            filter.status = status;
            return this;
        }

        public Builder assigneeId(String assigneeId) {
            filter.assigneeId = assigneeId;
            return this;
        }

        public Builder priority(Priority priority) {
            filter.priority = priority;
            return this;
        }

        public TaskFilter build() {
            return filter;
        }
    }
}

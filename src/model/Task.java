package model;

import java.time.LocalDateTime;

public class Task {
    private String taskId;
    private String title;
    private String description;
    private String status;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;

    public Task(String taskId, String title, String description, String status, String userId, LocalDateTime createdAt, LocalDateTime dueDate) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.userId = userId;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
    }

    public Task() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", userId='" + userId + '\'' +
                ", createdAt=" + createdAt +
                ", dueDate=" + dueDate +
                '}';
    }
}

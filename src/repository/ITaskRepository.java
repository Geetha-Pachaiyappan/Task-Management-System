package repository;

import model.Task;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ITaskRepository {
    void addTask(Task task);
    void addAllTasks(List<Task> taskList);
    List<Task> findAll();
    Task findByTaskId(String taskId);
    Task getLastTask();
    boolean deleteByTaskId(String taskId);
    boolean updateTitleByTaskId(String taskId, String title);
    boolean updateDescriptionByTaskId(String taskId, String description);
    boolean updateStatusByTaskId(String taskId, String status);
    boolean updateDueDateByTaskId(String taskId, LocalDateTime dueDate);
    List<Task> findTaskByStatus(String status);
    List<Task> findTaskByCreatedAt(String createdAt);
    List<Task> findTaskByUserId(String userId);
}

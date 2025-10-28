package service;

import model.Task;
import model.TaskStatus;
import repository.ITaskRepository;
import repository.IUserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TaskService {
    private ITaskRepository taskRepository;
    private IUserRepository userRepo;
    public TaskService(ITaskRepository taskRepository, IUserRepository userRepo){
        this.taskRepository = taskRepository;
        this.userRepo = userRepo;
    }
    public void addTask(Task task){
        if(userRepo.findByUserId(task.getUserId()) == null){
            System.out.println("User Not Found! Register the User First");
            return;
        }
        // Set Generated TaskId
        task.setTaskId(taskIdGeneration());
        taskRepository.addTask(task);
    }

    public String taskIdGeneration(){
       Task task = taskRepository.getLastTask();
       String prefix = "Task";
       int id = 1001;
       String newTaskId = "";
       if(task == null){
           newTaskId = prefix + String.valueOf(id);
       }else {
           String lastTaskId  = task.getTaskId();
           int lastNum = Integer.parseInt(lastTaskId.replace("Task",""));
           lastNum++;
           newTaskId = prefix + String.valueOf(lastNum);
       }
     return newTaskId;
    }

    public void addAllTask(List<Task> taskList){
        String taskId = taskIdGeneration();
        int taskLastNum = Integer.parseInt(taskId.replace("Task",""));
        for (int i =0; i < taskList.size(); i++){
            taskList.get(i).setTaskId(taskId);
            taskLastNum++;
            taskId = "Task" + String.valueOf(taskLastNum);
        }
        taskRepository.addAllTasks(taskList);
    }

    public void findAllTask(){
        List<Task> taskList = taskRepository.findAll();
        if(taskList != null && !taskList.isEmpty())
            System.out.println(taskList);
        else
            System.out.println("No Task Found!");
    }

    public void findByTaskId(String taskId){
        Task task = taskRepository.findByTaskId(taskId);
        if(task != null)
            System.out.println(task);
        else
            System.out.println("Task not found!");
    }
    public void deleteById(String taskId){
        if(taskRepository.deleteByTaskId(taskId))
            System.out.println("Deletion Successful");
        else
            System.out.println("TaskId Not Found");
    }

    public void updateTitle(String taskId, String title) {
        if (taskRepository.updateTitleByTaskId(taskId, title))
        {
            showTaskAfterUpdate(taskId);
            System.out.println("Title updated successfully!");
        }
        else
            System.out.println("Task ID not found!");
    }

    public void updateDescription(String taskId, String description) {
        if (taskRepository.updateDescriptionByTaskId(taskId, description))
        {
            showTaskAfterUpdate(taskId);
            System.out.println("Description updated successfully!");
        }
        else
            System.out.println("Task ID not found!");
    }

    public void updateStatus(String taskId, String status) {
        if (taskRepository.updateStatusByTaskId(taskId, status)) {
            showTaskAfterUpdate(taskId);
            System.out.println("Status updated successfully!");
        }
        else
            System.out.println("Task ID not found!");
    }

    public void updateDueDate(String taskId, LocalDateTime dueDate) {
        if (taskRepository.updateDueDateByTaskId(taskId, dueDate))
        {
            showTaskAfterUpdate(taskId);
            System.out.println("Due date updated successfully!");
        }
        else
            System.out.println("Task ID not found!");
    }

    public void showTaskAfterUpdate(String taskId) {
        Task updatedTask = taskRepository.findByTaskId(taskId);
        System.out.println("Updated Task Details: " + updatedTask);
    }

    public void getTasksByStatus(String status){
        List<Task> taskList = taskRepository.findTaskByStatus(status);
        if(taskList != null && !taskList.isEmpty())
            System.out.println(taskList);
        else
            System.out.println("No Task Found!");
    }
    public void getTasksByCreatedAt(String createdAt)  {
        List<Task> taskList = taskRepository.findTaskByCreatedAt(createdAt);
        if(taskList != null && !taskList.isEmpty())
            System.out.println(taskList);
        else
            System.out.println("No Task Found!");
    }

}

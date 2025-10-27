package menu;

import exceptionHandling.DueDateFormatNotValidateException;
import exceptionHandling.EmailNotValidException;
import exceptionHandling.UsernameNotValidException;
import model.Task;
import model.TaskStatus;
import service.TaskService;
import validation.TaskValidation;
import validation.UserValidation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskMenu {

    private TaskService taskService;
    private Scanner scan;

    public TaskMenu(TaskService taskService, Scanner scan){
        this.taskService = taskService;
        this.scan = scan;
    }

    public Task getTaskInput() throws DueDateFormatNotValidateException {
        System.out.println("Enter UserId: ");
        String userId = scan.nextLine();
        System.out.println("Enter Task Title: ");
        String title = scan.nextLine();
        System.out.println("Enter Task Description: ");
        String description = scan.nextLine();
        System.out.println("Enter Due Date and Time (format: yyyy-MM-dd HH:mm): ");
        String dueDate = scan.nextLine();
        // Set Values as Task and return
        Task task = new Task();
        task.setUserId(userId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(TaskValidation.convertAndValidateDueDate(dueDate));
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(String.valueOf(TaskStatus.PENDING));
        return task;

    }

    public void taskMainMenu() throws DueDateFormatNotValidateException {
        Menus.taskMenu();
        int taskMenu = scan.nextInt();
        scan.nextLine();
        switch (taskMenu){
            case 1 -> {
                Menus.addTaskMenu();
                int addTaskMenu = scan.nextInt();
                scan.nextLine();
                switch (addTaskMenu){
                    case 1 -> {
                        Task task = getTaskInput();
                        taskService.addTask(task);
                        System.out.println("Inserted Successfully");
                        break;
                    }
                    case 2 -> {
                        System.out.println("How many Task, Do you want to add: ");
                        int taskCount = scan.nextInt();
                        scan.nextLine();
                        List<Task> taskList = new ArrayList<>();
                        for (int i = 1; i <= taskCount; i++){
                            Task  task = getTaskInput();
                            taskList.add(task);
                        }
                        taskService.addAllTask(taskList);
                        System.out.println("Tasks Inserted Successfully");
                        break;
                    }
                    default -> {
                        System.out.println("Incorrect Option!");
                        break;
                    }
                }
                break;
            }
            case 2 ->{
                taskService.findAllTask();
                break;
            }
            case 3 -> {
                Menus.updateTaskMenu();
                int updateMenu = scan.nextInt();
                scan.nextLine();
                System.out.println("Enter TaskId: ");
                String taskId = scan.nextLine();
                switch (updateMenu){
                    case 1 ->{
                        System.out.println("Enter Title: ");
                        String title = scan.nextLine();
                        taskService.updateTitle(taskId,title);
                    }
                    case 2 ->{
                        System.out.println("Enter Description: ");
                        String description = scan.nextLine();
                        taskService.updateDescription(taskId,description);
                    }
                    case 3 -> {
                        System.out.println("Enter Status(PENDING, IN_PROCESS, COMPLETED) : ");
                        String status = scan.nextLine();
                        if(TaskValidation.isStatusValid(status))
                            taskService.updateStatus(taskId,status);
                        else
                            System.out.println("Invalid Inputs");
                    }
                    case 4 -> {
                        System.out.println("Enter Due Date and Time (format: yyyy-MM-dd HH:mm): ");
                        String dueDate = scan.nextLine();
                        taskService.updateDueDate(taskId,TaskValidation.convertAndValidateDueDate(dueDate));
                    }
                }
                break;
            }
            case 4 -> {
                System.out.println("Enter TaskId: ");
                String taskId = scan.nextLine();
                taskService.deleteById(taskId);
                break;
            }
            case 5 -> {
                System.out.println("Enter TaskId: ");
                String taskId = scan.nextLine();
                taskService.findByTaskId(taskId);
                break;
            }
            default -> {
                System.out.println("incorrect");
                break;
            }
        }
    }
}

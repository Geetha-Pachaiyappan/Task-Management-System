import exceptionHandling.DueDateFormatNotValidateException;
import exceptionHandling.EmailNotValidException;
import exceptionHandling.UsernameNotValidException;
import menu.Menus;
import menu.TaskMenu;
import menu.UserMenu;
import repository.*;
import service.TaskService;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UsernameNotValidException, EmailNotValidException, DueDateFormatNotValidateException {

        Scanner scan = new Scanner(System.in);
        int input = 0;
        ConnectionDao connectionDao = new ConnectionDao();
        IUserRepository userRepository = new UserRepositoryImpl(connectionDao);
        ITaskRepository taskRepository = new TaskRepositoryImpl(connectionDao);
        UserService userService = new UserService(userRepository);
        TaskService taskService = new TaskService(taskRepository,userRepository);
        do{
            Menus.mainMenu();
            input = scan.nextInt();
            scan.nextLine();
            switch (input){
                case 1 -> {
                    UserMenu userMenu = new UserMenu(userService, scan);
                    userMenu.userMainMenu();
                }
                case 2 -> {
                    TaskMenu taskMenu = new TaskMenu(taskService,scan);
                    taskMenu.taskMainMenu();
                    break;
                }
                default -> {
                    System.out.println("Incorrect Main menu");
                }
            }
        }while(input < 3);
    }
}
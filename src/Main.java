import exceptionHandling.DueDateFormatNotValidateException;
import exceptionHandling.EmailNotValidException;
import exceptionHandling.UsernameNotValidException;
import menu.Menus;
import menu.TaskMenu;
import menu.UserMenu;
import repository.ConnectionDao;
import repository.IUserRepository;
import repository.TaskRepositoryImpl;
import repository.UserRepositoryImpl;
import service.TaskService;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UsernameNotValidException, EmailNotValidException, DueDateFormatNotValidateException {

        Scanner scan = new Scanner(System.in);
        int input = 0;
        UserService userService = new UserService(new UserRepositoryImpl(new ConnectionDao()));
        TaskService taskService = new TaskService(new TaskRepositoryImpl(new ConnectionDao()), new UserRepositoryImpl(new ConnectionDao()));
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
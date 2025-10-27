import exceptionHandling.EmailNotValidException;
import exceptionHandling.UsernameNotValidException;
import menu.Menus;
import menu.UserMenu;
import repository.ConnectionDao;
import repository.IUserRepository;
import repository.UserRepositoryImpl;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UsernameNotValidException, EmailNotValidException {

        Scanner scan = new Scanner(System.in);
        int input = 0;
        do{
            UserService userService = new UserService(new UserRepositoryImpl(new ConnectionDao()));
            Menus.mainMenu();
            input = scan.nextInt();
            scan.nextLine();
            switch (input){
                case 1 -> {
                    UserMenu userMenu = new UserMenu(userService, scan);
                    userMenu.userMainMenu();
                }
                case 2 -> {
                    System.out.println("Task menu");
                    break;
                }
                default -> {
                    System.out.println("Incorrect Main menu");
                }
            }
        }while(input < 3);
    }
}
package menu;

import exceptionHandling.EmailNotValidException;
import exceptionHandling.UsernameNotValidException;
import service.UserService;
import validation.UserValidation;

import java.util.*;

public class UserMenu {

    private UserService userService;
    private Scanner scan;
    public UserMenu(UserService userService, Scanner scan){
        this.userService = userService;
        this.scan = scan;
    }

    public Map<String,String> getUserInput() throws UsernameNotValidException, EmailNotValidException {
        System.out.println("Enter User Name: ");
        String name = scan.nextLine();
        // It's Username Validation, it'll throw UsernameNotValidException
        UserValidation.isUsernameValid(name);
        System.out.println("Enter User Email: ");
        String email = scan.nextLine();
        // It's Email Validation, it'll throw EmailNotValidException
        UserValidation.isEmailValid(email);
        Map<String,String> user = new HashMap<>();
        user.put("name", name);
        user.put("email",email);
        return user;

    }

    public void userMainMenu() throws UsernameNotValidException, EmailNotValidException {
        Menus.userMenu();
        int userMenu = scan.nextInt();
        scan.nextLine();
        switch (userMenu){
            case 1 -> {
                Menus.addUserMenu();
                int adduserMenu = scan.nextInt();
                scan.nextLine();
                switch (adduserMenu){
                    case 1 -> {
                        Map<String, String> user = getUserInput();
                        userService.addUser(user.get("name"), user.get("email"));
                        System.out.println("Document Inserted Successfully");
                        break;
                    }
                    case 2 -> {
                        System.out.println("How many Users, Do you want to add: ");
                        int userCount = scan.nextInt();
                        scan.nextLine();
                        List<Map<String, String>> users = new ArrayList<>();
                        for (int i = 1; i <= userCount; i++){
                            Map<String,String> user = getUserInput();
                            users.add(user);
                        }
                        userService.addAllUser(users);
                        System.out.println("Documents Inserted Successfully");
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
                userService.findAll();
                break;
            }
            case 3 -> {
                System.out.println("Enter UserId: ");
                String userId = scan.nextLine();
                Map<String,String> user = getUserInput();
                userService.updateByUserId(userId,user.get("name"),user.get("email"));
                System.out.println("User updated successfully with userId: " + userId);
                break;
            }
            case 4 -> {
                System.out.println("Enter User Id: ");
                String userId = scan.nextLine();
                userService.deleteByUserId(userId);
                break;
            }
            case 5 -> {
                System.out.println("Enter User Id: ");
                String userId = scan.nextLine();
                userService.findByUserId(userId);
                break;
            }
            default -> {
                System.out.println("incorrect");
                break;
            }
        }
    }
}

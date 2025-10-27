package menu;

public class Menus {
    public static void mainMenu(){
        System.out.println("\n=========================");
        System.out.println("       MAIN MENU");
        System.out.println("=========================");
        System.out.println("1. User");
        System.out.println("2. Task");
        System.out.print("👉 Select an option: ");
    }
    public static void userMenu(){
        System.out.println("\n========== USER MENU ==========");
        System.out.println("1. Add User");
        System.out.println("2. View All Users");
        System.out.println("3. Update User");
        System.out.println("4. Delete User");
        System.out.println("5. Search User by ID");
        System.out.print("👉 Enter your choice: ");
    }
    public static void addUserMenu() {
        System.out.println("\n========== ADD USER MENU ==========");
        System.out.println("1. Add Single User");
        System.out.println("2. Add Multiple Users");
        System.out.print("Enter your choice: ");
    }



}

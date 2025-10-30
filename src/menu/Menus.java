package menu;

public class Menus {
    public static void mainMenu(){
        System.out.println("\n=========================");
        System.out.println("       MAIN MENU");
        System.out.println("=========================");
        System.out.println("1. User");
        System.out.println("2. Task");
        System.out.print(" ===> Select an option: ");
    }
    public static void userMenu(){
        System.out.println("\n========== USER MENU ==========");
        System.out.println("1. Add User");
        System.out.println("2. View All Users");
        System.out.println("3. Update User");
        System.out.println("4. Delete User");
        System.out.println("5. Search User by ID");
        System.out.print(" ===> Enter your choice: ");
    }
    public static void addUserMenu() {
        System.out.println("\n========== ADD USER MENU ==========");
        System.out.println("1. Add Single User");
        System.out.println("2. Add Multiple Users");
        System.out.print(" ===> Enter your choice: ");
    }
    public static void updateUserMenu(){
        System.out.println("\n========== UPDATE USER MENU ==========");
        System.out.println("1. Update Username");
        System.out.println("2. Update Email");
        System.out.print(" ===> Enter your choice: ");
    }
    public static void taskMenu(){
        System.out.println("\n========== TASK MENU ==========");
        System.out.println("1. Add Task");
        System.out.println("2. View All Task");
        System.out.println("3. Update Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Search Task by ID");
        System.out.print(" ===> Enter your choice: ");
    }

    public static void addTaskMenu() {
        System.out.println("\n========== ADD TASK MENU ==========");
        System.out.println("1. Add Single Task");
        System.out.println("2. Add Multiple Task");
        System.out.print(" ===> Enter your choice: ");
    }

    public static void updateTaskMenu(){
        System.out.println("\n========== UPDATE TASK MENU ==========");
        System.out.println("1. Update Title");
        System.out.println("2. Update Description");
        System.out.println("3. Update Status");
        System.out.println("4. Update Due Date");
        System.out.print(" ===> Enter your choice: ");
    }
    public static void searchTaskMenu(){
        System.out.println("\n========== SEARCH TASK MENU ==========");
        System.out.println("1. Search By TaskId");
        System.out.println("2. Search by Status");
        System.out.println("3. Search By Created Date");
        System.out.println("4. Search By UserId: ");
        System.out.print(" ===> Enter your choice: ");
    }


}

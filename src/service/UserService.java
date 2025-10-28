package service;

import com.mongodb.client.FindIterable;
import exceptionHandling.EmailNotValidException;
import exceptionHandling.UsernameNotValidException;
import model.User;
import org.bson.Document;
import repository.IUserRepository;
import repository.UserRepositoryImpl;

import java.util.*;

public class UserService {
    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    /*
        Method:  Add User
           * Get Inputs
           * Add to the user collection using userRepository
     */
    public void addUser(String name, String email) throws UsernameNotValidException, EmailNotValidException {
        String userId = userIdGeneration();
        userRepository.addUser(userId,name,email);
    }

    /*
        Method: User Id Generator -> User1001
           * fetch all documents using userRepository
           * sort userId from reverse and limit 1
           * get the first document
           * generate new userId
     */
    private String userIdGeneration(){
        String prefix = "User";
        int id = 1001;
        User user = userRepository.getLastUser();
        if(user == null){
            return  prefix + String.valueOf(id);
        }else {
           String lastUserId =  user.getUserId();
           int lastNumId = Integer.parseInt(lastUserId.replace(prefix,""));
           return prefix + String.valueOf ((lastNumId + 1));
        }

    }


    /*
       Method:  Add Multiple Users
          * Get How many users do you want to add
          * Get Three User Inputs userId, userName and email
          * Add to the user collection using userRepository
    */
    public void addAllUser(List<Map<String,String>> usersList){
        List<User> users = new ArrayList<>();
        String lastUserId = userIdGeneration();
        int lastNumId = Integer.parseInt(lastUserId.replace("User",""));
        for (int i = 0; i < usersList.size(); i++){
            String name = usersList.get(i).get("name");
            String email = usersList.get(i).get("email");
            // add them to the document
           User user = new User("User" + String.valueOf(lastNumId), name,email);
           lastNumId++;
            // add the documents to the list
            users.add(user);
        }
        userRepository.addAllUsers(users);

    }

    /*
        Method:  Find All Documents
           * Fetch All User Documents using User Repository
           * Iterate using for loop
           * print each document
     */
    public void findAll(){
        List<User> users = userRepository.findAll();

        if(!users.isEmpty()){
            System.out.println("\n=== USER DETAILS ===");
            for (User user: users){
                // System.out.println(doc.toJson());
                System.out.println("User ID: " + user.getUserId());
                System.out.println("Name   : " + user.getUserName());
                System.out.println("Email  : " + user.getEmail());
                System.out.println("==================================");
            }
        }else {
            System.out.println("Users Not Found!");
        }
    }

    /*
      Method:  Find Documents by user id
         * Fetch All User Documents using User Repository
         * Iterate using for loop
         * print each document
   */
    public void findByUserId(String userId){
        User user = userRepository.findByUserId(userId);
        if(user == null){
            System.out.println("Invalid UserId");
        }else {
            System.out.println(user);
        }
    }

    public void deleteByUserId(String userId){
        if(userRepository.deleteByUserId(userId))
            System.out.println("Documents Deleted with the user id: "+ userId);
        else
            System.out.println("User Not Found");
    }

    public void updateUsernameByUserId(String userId, String username){
       if(userRepository.updateUsernameByUserId(userId,username))
           System.out.println("User updated successfully with userId: " + userId);
       else
           System.out.println("Invalid Inputs");
    }

    public void updateEmailByUserId(String userId, String email){
        if(userRepository.updateEmailByUserId(userId,email))
            System.out.println("User updated successfully with userId: " + userId);
        else
            System.out.println("Invalid Inputs");
    }

}

package repository;

import com.mongodb.client.FindIterable;
import model.User;
import org.bson.Document;
import java.util.List;

public interface IUserRepository {
    void addUser(String userId, String userName, String email);
    void addAllUsers(List<User> users);
    List<User> findAll();
    User findByUserId(String userId);
    boolean deleteByUserId(String userId);
    void updateByUserId(String userId, String userName, String email);
    User getLastUser();
}

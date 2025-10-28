package repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import model.User;
import org.bson.Document;
import org.bson.internal.BsonUtil;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements IUserRepository{
    private MongoCollection<Document> collection;

    public UserRepositoryImpl(ConnectionDao connectionDao){
        collection = connectionDao.getConnection().getCollection("users");
    }

    public void addUser(String userId, String userName, String email){
        Document document = new Document("userId", userId)
                .append("userName",userName)
                .append("email", email);
        collection.insertOne(document);
    }

    public void addAllUsers(List<User> usersList){
        List<Document> users = new ArrayList<>();
        for(User user: usersList){
            users.add(new Document("userId", user.getUserId())
                    .append("userName", user.getUserName())
                    .append("email",user.getEmail()));
        }
        collection.insertMany(users);
    }

    public List<User> findAll(){
        FindIterable<Document> users = collection.find();
        // System.out.println(collection.countDocuments());
        List<User> usersList = new ArrayList<>();
        for (Document doc: users){
            User user = new User(
                    doc.getString("userId"),
                    doc.getString("userName"),
                    doc.getString("email")
            );
            usersList.add(user);
        }
        return usersList;
    }

    public User findByUserId(String userId){
        Document filter = new Document("userId", userId);
        Document doc = collection.find(filter).first();
        if(doc != null){
            return new User( doc.getString("userId"),
                    doc.getString("userName"),
                    doc.getString("email"));
        }else {
            return null;
        }

    }

    public boolean deleteByUserId(String userId){
        Document filter = new Document("userId", userId);
        DeleteResult deleteResult = collection.deleteOne(filter);
        return deleteResult.getDeletedCount() > 0;
    }

    @Override
    public boolean updateUsernameByUserId(String userId, String userName) {
        Document filter = new Document("userId", userId);
        Document update = new Document("$set", new Document("userName",userName));
        return collection.updateOne(filter,update).getModifiedCount() > 0;
    }

    @Override
    public boolean updateEmailByUserId(String userId, String email) {
        Document filter = new Document("userId", userId);
        Document update = new Document("$set", new Document("email", email));
        return collection.updateOne(filter,update).getModifiedCount() > 0;
    }

    @Override
    public User getLastUser() {
        Document user = collection.find().sort(new Document("userId", -1)).limit(1).first();
        if(user != null){
            return new User(
                    user.getString("userId"),
                    user.getString("userName"),
                    user.getString("email")
            );
        }else {
            return null;
        }

    }

}

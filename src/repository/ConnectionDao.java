package repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionDao {
    private static final MongoClient client = MongoClients.create("mongodb://localhost:27017");
    private  static final MongoDatabase db = client.getDatabase("taskdb");

    static {
        System.out.println("Connected to MongoDB: " + db.getName());
    }

    public MongoDatabase getConnection(){
        return db;
    }
}

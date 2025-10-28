package repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import model.Task;
import org.bson.Document;

import javax.print.Doc;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskRepositoryImpl implements ITaskRepository{
    private MongoCollection<Document> collection;

    public TaskRepositoryImpl(ConnectionDao connectionDao){
        this.collection = connectionDao.getConnection().getCollection("task");
    }

    @Override
    public void addTask(Task task) {
        Document document = new Document("taskId", task.getTaskId())
                .append("title", task.getTitle())
                .append("description", task.getDescription())
                .append("status", task.getStatus())
                .append("userId", task.getUserId())
                .append("createdAt", task.getCreatedAt())
                .append("dueDate", task.getDueDate());
       collection.insertOne(document);
    }

    @Override
    public void addAllTasks(List<Task> taskList) {
        List<Document> taskDoc = new ArrayList<>();
        for (Task task : taskList){
            taskDoc.add(
                    new Document("taskId",task.getTaskId())
                    .append("title", task.getTitle())
                    .append("description", task.getDescription())
                    .append("status", task.getStatus())
                    .append("userId", task.getUserId())
                    .append("createdAt", task.getCreatedAt())
                    .append("dueDate", task.getDueDate()));
        }
        collection.insertMany(taskDoc);
    }

    @Override
    public List<Task> findAll() {
        FindIterable<Document> taskDoc = collection.find();
        List<Task> taskList = new ArrayList<>();
        for (Document doc: taskDoc){
            Date dueDateDate = doc.getDate("dueDate");
            Date createdAtDate = doc.getDate("createdAt");
            LocalDateTime createdAt = createdAtDate != null
                    ? createdAtDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                    : null;

            LocalDateTime dueDate = dueDateDate != null
                    ? dueDateDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                    : null;


            Task task = new Task(
                        doc.getString("taskId"),
                        doc.getString("title"),
                        doc.getString("description"),
                        doc.getString("status"),
                        doc.getString("userId"),
                        createdAt,
                        dueDate
            );
            taskList.add(task);
        }
        return taskList;
    }

    @Override
    public Task findByTaskId(String taskId) {
        Document filter = new Document("taskId", taskId);
        Document doc = collection.find(filter).first();
        Date dueDateDate = doc.getDate("dueDate");
        Date createdAtDate = doc.getDate("createdAt");
        LocalDateTime createdAt = createdAtDate != null
                ? createdAtDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                : null;

        LocalDateTime dueDate = dueDateDate != null
                ? dueDateDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                : null;
        return new Task(doc.getString("taskId"),
                doc.getString("title"),
                doc.getString("description"),
                doc.getString("status"),
                doc.getString("userId"),
                createdAt,
                dueDate
                );
    }

    @Override
    public Task getLastTask() {
       Document doc = collection.find().sort(new Document("taskId", -1)).limit(1).first();
       if(doc != null){
           Date dueDateDate = doc.getDate("dueDate");
           Date createdAtDate = doc.getDate("createdAt");
           LocalDateTime createdAt = createdAtDate != null
                   ? createdAtDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                   : null;

           LocalDateTime dueDate = dueDateDate != null
                   ? dueDateDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                   : null;
           return new Task(
                   doc.getString("taskId"),
                   doc.getString("title"),
                   doc.getString("description"),
                   doc.getString("status"),
                   doc.getString("userId"),
                   createdAt,
                   dueDate
           );
       }else {
           return null;
       }

    }

    @Override
    public boolean deleteByTaskId(String taskId) {
        Document filter = new Document("taskId", taskId);
        DeleteResult deleteResult = collection.deleteOne(filter);
        return deleteResult.getDeletedCount() > 0;
    }

    @Override
    public boolean updateTitleByTaskId(String taskId, String title) {
        Document filter = new Document("taskId", taskId);
        Document update = new Document("$set", new Document("title", title));
        return collection.updateOne(filter, update).getModifiedCount() > 0;
    }

    @Override
    public boolean updateDescriptionByTaskId(String taskId, String description) {
        Document filter = new Document("taskId", taskId);
        Document update = new Document("$set", new Document("description", description));
        return collection.updateOne(filter,update).getModifiedCount() > 0;
    }

    @Override
    public boolean updateStatusByTaskId(String taskId, String status) {
        Document filter = new Document("taskId", taskId);
        Document update = new Document("$set", new Document("status", status));
        return collection.updateOne(filter, update).getModifiedCount() > 0;
    }

    @Override
    public boolean updateDueDateByTaskId(String taskId, LocalDateTime dueDate) {
        Document filter = new Document("taskId", taskId);
        Document update = new Document("$set", new Document("dueDate", dueDate));
        return collection.updateOne(filter, update).getModifiedCount() > 0;
    }

    @Override
    public List<Task> findTaskByStatus(String status) {
        Document filter = new Document("status", status);
        FindIterable<Document> taskList = collection.find(filter);
        List<Task> tasks = new ArrayList<>();
        for (Document doc: taskList){
            Date dueDateDate = doc.getDate("dueDate");
            Date createdAtDate = doc.getDate("createdAt");
            LocalDateTime createdAt = createdAtDate != null
                    ? createdAtDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                    : null;

            LocalDateTime dueDate = dueDateDate != null
                    ? dueDateDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                    : null;
            Task task = new Task(
                    doc.getString("taskId"),
                    doc.getString("title"),
                    doc.getString("description"),
                    doc.getString("status"),
                    doc.getString("userId"),
                    createdAt,
                    dueDate
                    );
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public List<Task> findTaskByCreatedAt(String createdAtInput) {
        Document filter = new Document("$expr",
                new Document("$eq", List.of(
                        new Document("$dateToString", new Document("format", "%Y-%m-%d").append("date", "$createdAt")),
                        createdAtInput
                ))
        );
        FindIterable<Document> taskList = collection.find(filter);
        List<Task> tasks = new ArrayList<>();
        for (Document doc: taskList){
            Date dueDateDate = doc.getDate("dueDate");
            Date createdAtDate = doc.getDate("createdAt");
            LocalDateTime createdAt = createdAtDate != null
                    ? createdAtDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                    : null;

            LocalDateTime dueDate = dueDateDate != null
                    ? dueDateDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                    : null;
            Task task = new Task(
                    doc.getString("taskId"),
                    doc.getString("title"),
                    doc.getString("description"),
                    doc.getString("status"),
                    doc.getString("userId"),
                    createdAt,
                    dueDate
            );
            tasks.add(task);
        }
        return tasks;
    }


}

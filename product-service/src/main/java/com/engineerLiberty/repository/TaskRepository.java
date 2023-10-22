//package com.engineerLiberty.repository;
//
//import com.engineerLiberty.model.Task;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//
//import java.util.List;
//
//public interface TaskRepository extends MongoRepository<Task,String> {
//    List<Task> findBySeverity(int severity);
//    @Query("{assignee: ?0}")
//    List<Task>findByAssignee(String assignee);
//    @Query("{assignee: ?0, severity: ?1}")
//    List<Task>findByAssigneeAndSeverity(String assignee, int severity);
//}

//package com.engineerLiberty.controller;
//
//import com.engineerLiberty.model.Task;
//import com.engineerLiberty.service.TaskService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/task")
//@RequiredArgsConstructor
//public class TaskController {
//    private final TaskService taskService;
//    @PostMapping("addTask")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Task addtask(@RequestBody Task taskRequest) {
//        return taskService.addTask(taskRequest);
//    }
//    @GetMapping
//    public List<Task> getTasks() {
//        return taskService.findAll();
//    }
//    @GetMapping ("/{taskId}")
//    public Task getTask(@PathVariable String taskId) {
//        return taskService.getTaskById(taskId);
//    }
//    @GetMapping ("/severity/{severity}")
//    public List<Task>findTaskUsingSeverity(@PathVariable int severity) {
//        return taskService.findBySeverity(severity);
//    }
//
//    @GetMapping ("/assignee/{assignee}")
//    public List<Task>getTaskByAssignee(@PathVariable String assignee) {
//        return taskService.findByAssignee(assignee);
//    }
//    @GetMapping("/assignee-severity")
//    public List<Task> findByAssigneeAndSeverity(@RequestParam String assignee, @RequestParam int severity) {
//        return taskService.findByAssigneeAndSeverity(assignee,severity);
//    }
//    @PutMapping
//    public Task modifyTask(@RequestBody Task taskRequest) {
//        return taskService.updateTask(taskRequest);
//    }
//
//    @DeleteMapping ("/{taskId}")
//    public String deleteTask(@PathVariable String taskId) {
//        return taskService.deleteTask(taskId);
//    }
//}

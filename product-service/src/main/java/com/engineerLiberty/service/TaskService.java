package com.engineerLiberty.service;

import com.engineerLiberty.model.Task;
import com.engineerLiberty.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class TaskService {
    private final TaskRepository taskRepository;

    public Task addTask(Task task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public Task getTaskById(String taskId) {
        return taskRepository.findById(taskId).get();
    }
    public List<Task>findBySeverity(int severity) {
        return taskRepository.findBySeverity(severity);
    }
    public List<Task>findByAssignee(String assignee) {
         return taskRepository.findByAssignee(assignee);
    }
    public List<Task> findByAssigneeAndSeverity(String assignee, int severity) {
          return taskRepository.findByAssigneeAndSeverity(assignee,severity);
    }
    public Task updateTask(Task taskRequest) {
        Task existingTask = taskRepository.findById(taskRequest.getTaskId()).get();
         BeanUtils.copyProperties(taskRequest,existingTask);
         return taskRepository.save(existingTask);
    }
    public String deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
        return taskId+" Deleted from dashboard";
    }
}

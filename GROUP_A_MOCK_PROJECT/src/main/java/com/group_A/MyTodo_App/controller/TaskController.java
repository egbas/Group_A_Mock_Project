package com.group_A.MyTodo_App.controller;

import com.group_A.MyTodo_App.dto.TaskDto;
import com.group_A.MyTodo_App.entity.Task;
import com.group_A.MyTodo_App.enums.Status;
import com.group_A.MyTodo_App.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/login")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @PostMapping("/{id}/new_task")
    public ResponseEntity<TaskDto> createTask(@PathVariable Long id, @RequestBody TaskDto taskDTO) {
        TaskDto createdTask = taskService.createTask(id, taskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<TaskDto> getTaskByTitle(@PathVariable String title) {
        TaskDto taskTitle = taskService.getTaskByTitle(title);
        if (taskTitle != null) {
            return new ResponseEntity<>(taskTitle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id){
        TaskDto taskID = taskService.getTaskById(id);
        if(taskID != null){
            return new ResponseEntity<>(taskID, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/user/{userId}")
    public List<Task> getAllTasksByUserId(@PathVariable Long userId) {
        return taskService.findTasksByUserId(userId);
    }
    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskUpdateDto){
        return taskService.updateTask(taskId, taskUpdateDto);
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public Task deleteTask(@PathVariable Long taskId){
        return taskService.deleteTask(taskId);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskDto>> getTaskByStatus(@PathVariable Status status) {
        List<TaskDto> taskStatus = taskService.getTaskByStatus(status);
        if (taskStatus != null) {
            return new ResponseEntity<>(taskStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/status")
    public ResponseEntity<List<TaskDto>> getCompletedTasks() {
        List<TaskDto> taskStatus = taskService.getCompletedTask();
        if (taskStatus != null) {
            return new ResponseEntity<>(taskStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


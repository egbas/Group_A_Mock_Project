package com.group_A.MyTodo_App.service;

import com.group_A.MyTodo_App.dto.TaskDto;
import com.group_A.MyTodo_App.entity.Task;
import com.group_A.MyTodo_App.enums.Status;
import com.group_A.MyTodo_App.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface TaskService {

    public List<Task> findTasksByUserId(Long UserId);
    public Task updateTask(Long taskId, TaskDto taskUpdateDto);
    TaskDto createTask(Long id, TaskDto taskDTO);
    TaskDto getTaskByTitle(String title);
    TaskDto getTaskById(Long id);
    public Task deleteTask(Long taskId);
    List<TaskDto> getTaskByStatus(Status status);
    List<TaskDto> getCompletedTask();

}



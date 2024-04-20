package com.group_A.MyTodo_App.service.impl;

import com.group_A.MyTodo_App.dto.TaskDto;
import com.group_A.MyTodo_App.entity.Task;
import com.group_A.MyTodo_App.entity.User;
import com.group_A.MyTodo_App.enums.Status;
import com.group_A.MyTodo_App.exceptions.TaskNotFoundException;
import com.group_A.MyTodo_App.repository.TaskRepository;
import com.group_A.MyTodo_App.repository.UserRepository;
import com.group_A.MyTodo_App.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    public TaskDto createTask(Long id, TaskDto taskDTO) {
        User user = userRepository.findById(id).get();
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        task.setDeadline(now);
        task.setPriorityLevel(taskDTO.getPriorityLevel());
        task.setStatus(taskDTO.getStatus());
        task.setUser(user);
        Task savedTask = taskRepository.save(task);
        taskDTO.setId(savedTask.getId());
        return taskDTO;
    }

    @Override
    public TaskDto getTaskByTitle(String title) {
        Task task = taskRepository.findByTitle(title);
        if (task != null) {
            TaskDto taskDTO = convertToDTO(task);
            return taskDTO;
        } else {
            return null;
        }
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findTaskById(id);
        if(task != null){
            TaskDto taskDto = convertToDTO(task);
            return taskDto;
        }else{
            return null;
        }
    }


    private TaskDto convertToDTO(Task task) {
        TaskDto taskDTO = new TaskDto();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDeadline(task.getDeadline());
        taskDTO.setPriorityLevel(task.getPriorityLevel());
        taskDTO.setStatus(task.getStatus());
        return taskDTO;
    }


    @Override
    public List<Task> findTasksByUserId(Long userId) {
        return taskRepository.findByUserId_Id(userId);
    }

    @Override
    public Task updateTask(Long taskId, TaskDto taskUpdateDto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + taskId));
        task.setTitle(taskUpdateDto.getTitle());
        task.setDescription(taskUpdateDto.getDescription());
        task.setDeadline(taskUpdateDto.getDeadline());
        task.setPriorityLevel(taskUpdateDto.getPriorityLevel());
        task.setStatus(taskUpdateDto.getStatus());

        return taskRepository.save(task);
    }

    @Override
    public Task deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + taskId));
        taskRepository.delete(task);

        return task;
    }

    @Override
    public List<TaskDto> getTaskByStatus(Status status) {
    List<Task> task = taskRepository.findTaskByStatus(status);
        if(task != null){
           List <TaskDto> taskDto = convertToDTOList(task);
            return taskDto;
        }else {
            return null;
        }

    }

    @Override
    public List<TaskDto> getCompletedTask() {
                List<Task> task = taskRepository.findTaskByStatus(Status.COMPLETED);
        if(task != null){
            List <TaskDto> taskDto = convertToDTOList(task);
            return taskDto;
        }else {
            return null;
        }
    }

    //
//    @Override
//    public List<TaskDto> getCompletedTask() {
//        List<Task> task = taskRepository.findTaskByStatus(Status.COMPLETED);
//        if(task != null){
//            List <TaskDto> taskDto = convertToDTOList(task);
//            return taskDto;
//        }else {
//            return null;
//        }
//
//    }
    private List <TaskDto> convertToDTOList(List<Task> task) {
        List<TaskDto> dtoList = new ArrayList<>();

        for(Task task1 : task){
            TaskDto dto = new TaskDto();
            dto.setId(task1.getId());
            dto.setTitle(task1.getTitle());
            dto.setDescription(task1.getDescription());
            dto.setDeadline(task1.getDeadline());
            dto.setPriorityLevel(task1.getPriorityLevel());
            dto.setStatus(task1.getStatus());
            dtoList.add(dto);
        }

        return dtoList;
    }

}



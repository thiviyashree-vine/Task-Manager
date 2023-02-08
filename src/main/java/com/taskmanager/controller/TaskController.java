package com.taskmanager.controller;

import com.taskmanager.dao.TaskEntity;
import com.taskmanager.dao.TaskRepository;
import com.taskmanager.dto.TaskDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the REST API for Task Management purpose.
 * It exposes methods to list tasks and to create a new task.
 */
@Slf4j
@RestController
@RequestMapping("/taskmanager")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Retrieves a list of tasks
     */
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskRepository
                .findAll()
                .stream().map(entity -> {
                    TaskDto dto = new TaskDto();
                    dto.copyFrom(entity);
                    return dto;
                }).collect(Collectors.toList()));
    }

    /**
     * Creates the task and save it in database.
     */
    @PostMapping("/task/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto item) {
        TaskEntity entity = taskRepository.findTaskByTitle(item.getTitle());
        if (entity != null) {
            log.info("Found existing task with title [{}]. Returning 409.", item.getTitle());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        entity = new TaskEntity();
        entity.copyFrom(item);

        taskRepository.save(entity);
        log.info("Successfully added the task [{}].", item.getTitle());
        return ResponseEntity.ok(item);

    }

}

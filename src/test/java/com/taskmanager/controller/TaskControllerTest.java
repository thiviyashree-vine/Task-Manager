package com.taskmanager.controller;

import com.taskmanager.dao.TaskEntity;
import com.taskmanager.dao.TaskRepository;
import com.taskmanager.dto.TaskDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskControllerTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    TaskController taskController;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);

        taskRepository = mock(TaskRepository.class);
        when(taskRepository.findTaskByTitle("Task1"))
                .thenReturn(getMockInvestorEntities());
    }

    private TaskEntity getMockInvestorEntities() {
        TaskEntity task_one = new TaskEntity();
        task_one.setTitle("Task1");
        task_one.setDescription("Testing1");
        task_one.setCompleted(false);

        return task_one;
    }

    @Test
    public void testCreateTask() {

        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("Task2");
        taskDto.setDescription("Testing2");
        taskDto.setCompleted(false);

        ResponseEntity<?> response = taskController.createTask(taskDto);
        assertTrue(response.getStatusCode().is2xxSuccessful());

    }

    @Test
    public void testConflictForCreatingTask() {
        when(taskRepository.findTaskByTitle("Task1"))
                .thenReturn(getMockInvestorEntities());

        TaskDto taskDto = new TaskDto();
        taskDto.setTitle("Task1");
        taskDto.setDescription("Testing1");
        taskDto.setCompleted(false);

        ResponseEntity<?> response = taskController.createTask(taskDto);
        assertFalse(response.getStatusCode().is2xxSuccessful());

    }
}


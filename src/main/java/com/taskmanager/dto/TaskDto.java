package com.taskmanager.dto;

import com.taskmanager.dao.TaskEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDto {
    private String title;
    private String description;
    private boolean isCompleted;

    public TaskDto copyFrom(TaskEntity task) {
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.isCompleted = task.isCompleted();
        return this;
    }

}

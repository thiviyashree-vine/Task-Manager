package com.taskmanager.dao;

import com.taskmanager.dto.TaskDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class TaskEntity {

    /* The Name of the task*/
    @Id
    private String title;

    /* The Description about the Task */
    private String description;

    /* a flag to indicate if the task has finished or not*/
    private boolean isCompleted;

    /**
     * Populates this TaskEntity with details from the given DTO.
     */
    public TaskEntity copyFrom(TaskDto task) {
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.isCompleted = task.isCompleted();
        return this;
    }
}

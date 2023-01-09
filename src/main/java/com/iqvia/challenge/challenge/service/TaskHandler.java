package com.iqvia.challenge.challenge.service;

import com.iqvia.challenge.challenge.model.Task;
import com.iqvia.challenge.challenge.persistence.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.iqvia.challenge.challenge.SchedulerConstants.TASK_STATUS_PROCESSED;
import static com.iqvia.challenge.challenge.SchedulerConstants.TASK_STATUS_PROCESSING;

public abstract class TaskHandler {
    private static final Logger LOG = LoggerFactory.getLogger(TaskHandler.class);
    @Autowired
    private TaskRepository taskRepository;

    public void startProcessing(Task task) {
        task.setStatus(TASK_STATUS_PROCESSING);
        taskRepository.save(task);

        handleTask(task);
    }

    public void endProcessing(Task task) {
        task.setStatus(TASK_STATUS_PROCESSED);
        taskRepository.save(task);
    }

    abstract void handleTask(Task task);
}

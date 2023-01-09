package com.iqvia.challenge.challenge.service;

import com.iqvia.challenge.challenge.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.iqvia.challenge.challenge.SchedulerConstants.TASK_HANDLER_RIGHT_AWAY;

/**
 * The task will be process immediately
 */
@Component
@Qualifier(value = TASK_HANDLER_RIGHT_AWAY)
public class TaskHandlerRightAwayImpl extends TaskHandler {
    private static final Logger LOG = LoggerFactory.getLogger(TaskHandlerRightAwayImpl.class);

    @Override
    public void handleTask(Task task) {
        LOG.debug("Task has been processed, content: {}", task.getContent());

        endProcessing(task);
    }
}

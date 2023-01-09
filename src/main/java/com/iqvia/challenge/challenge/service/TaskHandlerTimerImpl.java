package com.iqvia.challenge.challenge.service;

import com.iqvia.challenge.challenge.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.iqvia.challenge.challenge.SchedulerConstants.TASK_HANDLER_TIMER;

/**
 * This is the shell implementation which starts a timer to process a task, it makes sure the task can
 * be processed at the specific deliver time, however as every timer is actually a Thread,
 * when the number of tasks is increasing, the cpu resources will be a problem.
 */
@Component
@Qualifier(value = TASK_HANDLER_TIMER)
public class TaskHandlerTimerImpl extends TaskHandler {
    private static final Logger LOG = LoggerFactory.getLogger(TaskHandlerTimerImpl.class);

    @Override
    public void handleTask(Task task) {
        //Start a timer
    }
}

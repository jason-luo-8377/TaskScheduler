package com.iqvia.challenge.challenge.service;

import com.iqvia.challenge.challenge.model.Task;
import com.iqvia.challenge.challenge.persistence.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

import static com.iqvia.challenge.challenge.SchedulerConstants.TASK_HANDLER_RIGHT_AWAY;

/**
 * TaskConsumer scan the tasks from the database periodically, both the interval and deliverTime offset are adjustable.
 */
@Component
public class TaskConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(TaskConsumer.class);

    @Value("${task.scan.delivery_time.offset}")
    private int deliveryTimeOffset;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier(value = TASK_HANDLER_RIGHT_AWAY)
    private TaskHandler taskHandler;

    /**
     * The method will be run only once immediately after the bean's initialization to process the tasks with failure
     */
    @PostConstruct
    public void processTasksFailed() {
        LOG.debug("Scanning the database for tasks with status=2: {}");
        List<Task> taskList = taskRepository.listTasksInProcessing();
        LOG.debug("The count of tasks with status=1: {}", taskList.size());
        for(Task task: taskList) {
            taskHandler.startProcessing(task);
        }
    }

    @Scheduled(fixedRateString ="${task.scan.interval}")
    public void scanTasks() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MILLISECOND, deliveryTimeOffset);

        LOG.debug("Scanning the database for tasks with deliveryTime before {}", cal.getTime());
        List<Task> taskList = taskRepository.listApproachingTasks(cal.getTime());
        LOG.debug("The count of tasks: {}", taskList.size());
        for(Task task: taskList) {
            taskHandler.startProcessing(task);
        }
    }
}

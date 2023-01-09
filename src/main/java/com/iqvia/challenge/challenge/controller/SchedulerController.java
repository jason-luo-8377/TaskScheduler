package com.iqvia.challenge.challenge.controller;

import com.iqvia.challenge.challenge.exceptions.CustomValidationException;
import com.iqvia.challenge.challenge.exceptions.SchedulerServiceException;
import com.iqvia.challenge.challenge.model.TaskRequest;
import com.iqvia.challenge.challenge.model.RestResponse;
import com.iqvia.challenge.challenge.model.Task;
import com.iqvia.challenge.challenge.persistence.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/challenge")
@Validated
public class SchedulerController {
    private static final Logger LOG = LoggerFactory.getLogger(SchedulerController.class);

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/scheduleTask")
    public ResponseEntity scheduleTask(@Valid @RequestBody TaskRequest taskRequest) throws SchedulerServiceException, CustomValidationException {
        long start = System.currentTimeMillis();
        LOG.debug("Scheduling a task, content: {}, deliver_date: {}", taskRequest.getContent(), taskRequest.getDeliveryTime());

        //If annotations for validation don't meet the requirement, we need to validate the task manually
        taskRequest.validate();

        Task task = new Task();
        task.setContent(taskRequest.getContent());
        task.setDeliveryTime(taskRequest.getDeliveryTime());

        Task taskWithId = taskRepository.save(task);

        LOG.debug("Finished executing scheduleTask, time spent: {}, ", System.currentTimeMillis() - start);

        //The task will be attached to the data field in the response body
        return RestResponse.ok(taskWithId);
    }
}

package com.iqvia.challenge.challenge.model;

import com.iqvia.challenge.challenge.SchedulerConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Date;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    @Column(name = "delivery_time")
    private Date deliveryTime;

    /**
     * processing status of the task
     * 0: default
     * 1: processing
     * 2: processed
     */
    private Integer status = SchedulerConstants.TASK_STATUS_NEW;
}

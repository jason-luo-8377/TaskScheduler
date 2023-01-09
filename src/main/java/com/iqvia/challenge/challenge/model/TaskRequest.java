package com.iqvia.challenge.challenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iqvia.challenge.challenge.SchedulerUtil;
import com.iqvia.challenge.challenge.exceptions.CustomValidationException;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.iqvia.challenge.challenge.SchedulerConstants.DELIVER_TIME_FORMAT;

@Getter
@Setter
public class TaskRequest {
    @NotBlank
    private String content;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DELIVER_TIME_FORMAT, timezone="America/Halifax")
    private Date deliveryTime;

    /**
     * Custom validation for the task
     * @throws CustomValidationException
     */
    public void validate() throws CustomValidationException {
        if(!SchedulerUtil.isDeliveryTimeFuture(deliveryTime)) {
            throw new CustomValidationException("Can not schedule a task in the past");
        }
    }
}

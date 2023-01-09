package com.iqvia.challenge.challenge.persistence;

import com.iqvia.challenge.challenge.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT t FROM Task t where t.status=0 and t.deliveryTime <= ?1")
    List<Task> listApproachingTasks(Date deliveryTimeDue);

    @Query(value = "SELECT t FROM Task t where t.status=1")
    List<Task> listTasksInProcessing();
}
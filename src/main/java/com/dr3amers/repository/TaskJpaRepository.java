package com.dr3amers.repository;

import com.dr3amers.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TaskJpaRepository extends JpaRepository<Task, Integer> {

    Set<Task> findByAssigneeId(int id);
    List<Task> findByProjectId(int id);
}

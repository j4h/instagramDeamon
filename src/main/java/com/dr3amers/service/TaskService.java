package com.dr3amers.service;

import com.dr3amers.helper.Helper;
import com.dr3amers.validator.UpdatesValidator;
import com.dr3amers.model.Task;
import com.dr3amers.repository.ProjectJpaRepository;
import com.dr3amers.repository.TaskJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskJpaRepository taskJpaRepository;
    private ProjectJpaRepository projectJpaRepository;

    @Autowired
    public TaskService(TaskJpaRepository taskJpaRepository, ProjectJpaRepository projectJpaRepository) {
        this.taskJpaRepository = taskJpaRepository;
        this.projectJpaRepository = projectJpaRepository;
    }

    public List<Task> getAll(int projectId) {
        //validation if the project exists and we can access it
        Helper.getProjectById(projectJpaRepository,projectId);
        List<Task> tasks = taskJpaRepository.findByProjectId(projectId);
        tasks.removeIf(task -> task.getAssigneeId() != Helper.getCurrentUser().getId());
        return tasks;
    }

    public Task get(int projectId, int taskId) {
        return Helper.getTaskById(projectJpaRepository, taskJpaRepository, projectId, taskId);
    }

    public void delete(int projectId, int taskId) {
        get(projectId, taskId);
        taskJpaRepository.delete(taskId);
    }

    public Task create(int projectId, Task task) {
        Helper.getProjectById(projectJpaRepository, projectId);

        //fake code that won't be released
        task.setProjectId(projectId);
        return taskJpaRepository.saveAndFlush(task);
    }

    public Task update(int projectId, int taskId, Task task) {
        //validation process
        UpdatesValidator.checkTaskStatusUpdateValidity(projectJpaRepository, taskJpaRepository, projectId, taskId, task);

        //fake code that won't be released
        task.setProjectId(projectId);
        return taskJpaRepository.saveAndFlush(task);
    }

}

package com.dr3amers.service;

import com.dr3amers.helper.Helper;
import com.dr3amers.validator.UpdatesValidator;
import com.dr3amers.model.SubTask;
import com.dr3amers.repository.ProjectJpaRepository;
import com.dr3amers.repository.SubTaskJpaRepository;
import com.dr3amers.repository.TaskJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskService {

    private SubTaskJpaRepository subTaskJpaRepository;
    private ProjectJpaRepository projectJpaRepository;
    private TaskJpaRepository taskJpaRepository;

    @Autowired
    public SubTaskService(SubTaskJpaRepository subTaskJpaRepository, ProjectJpaRepository projectJpaRepository,
                          TaskJpaRepository taskJpaRepository) {
        this.subTaskJpaRepository = subTaskJpaRepository;
        this.projectJpaRepository = projectJpaRepository;
        this.taskJpaRepository = taskJpaRepository;
    }

    public List<SubTask> getAll(int projectId, int taskId) {
        return Helper.getTaskById(projectJpaRepository, taskJpaRepository, projectId, taskId).getSubTaskList();
    }

    public SubTask get(int projectId, int taskId, int subtaskId) {
        return Helper.getSubTaskByIdFromTask(projectJpaRepository, taskJpaRepository, projectId, taskId, subtaskId);
    }

    public void delete(int projectId, int taskId, int subtaskId) {
        get(projectId, taskId, subtaskId);
        subTaskJpaRepository.delete(subtaskId);
    }

    public SubTask create(int projectId, int taskId, SubTask subTask) {
        Helper.getTaskById(projectJpaRepository, taskJpaRepository, projectId, taskId);

        //fake code won't be released
        subTask.setTaskId(taskId);
        return subTaskJpaRepository.saveAndFlush(subTask);
    }

    public SubTask update(int projectId, int taskId, int subtaskId, SubTask subTask) {
        //validation process
        SubTask initialST = Helper.getSubTaskByIdFromTask(projectJpaRepository, taskJpaRepository, projectId, taskId, subtaskId);
        UpdatesValidator.checkStatusUpdateValidity(initialST.getStatus(), subTask.getStatus());

        //fake code won't be released
        subTask.setTaskId(taskId);
        return subTaskJpaRepository.saveAndFlush(subTask);
    }



}

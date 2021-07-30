package com.dr3amers.validator;

import com.dr3amers.exception.InvalidStatusUpdateException;
import com.dr3amers.helper.Helper;
import com.dr3amers.model.SubTask;
import com.dr3amers.model.Task;
import com.dr3amers.model.enumerated.Status;
import com.dr3amers.repository.ProjectJpaRepository;
import com.dr3amers.repository.TaskJpaRepository;

import java.util.List;

//Validation rules for Status updates
public class UpdatesValidator {


    //rules updating Status for Tasks
    public static Task checkTaskStatusUpdateValidity(ProjectJpaRepository projectJpaRepository, TaskJpaRepository taskJpaRepository,
                                                     int projectId, int taskId, Task task)
            throws RuntimeException {

        Task initialTask = Helper.getTaskById(projectJpaRepository, taskJpaRepository, projectId, taskId);
        checkStatusUpdateValidity(initialTask.getStatus(), task.getStatus());

        if (task.getStatus() == Status.DONE && !allSubTasksDone(projectJpaRepository, taskJpaRepository, projectId, taskId))
            throw new InvalidStatusUpdateException("You have some undone SubTasks in Task with ID:"+taskId);

        return task;
    }

    //rule updating Status for any Entity
    public static Status checkStatusUpdateValidity(Status initialStatus, Status updatedStatus)
            throws RuntimeException {

        if (updatedStatus == Status.DONE && initialStatus == Status.TODO)
            throw new InvalidStatusUpdateException(initialStatus, updatedStatus);

        return updatedStatus;
    }

    private static boolean allSubTasksDone(ProjectJpaRepository projectJpaRepository, TaskJpaRepository taskJpaRepository,
                                          int projectId, int taskId) {

        List<SubTask> subTasks = Helper.getTaskById(projectJpaRepository, taskJpaRepository, projectId, taskId).getSubTaskList();
        subTasks.removeIf(subTask -> subTask.getStatus() == Status.DONE);
        return subTasks.isEmpty();
    }
}

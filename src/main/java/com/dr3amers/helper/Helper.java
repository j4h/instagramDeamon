package com.dr3amers.helper;

import com.dr3amers.exception.NotFoundException;
import com.dr3amers.model.AuthenticatedUser;
import com.dr3amers.model.Project;
import com.dr3amers.model.SubTask;
import com.dr3amers.model.Task;
import com.dr3amers.repository.ProjectJpaRepository;
import com.dr3amers.repository.TaskJpaRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

//data access layer
public class Helper {

    public static AuthenticatedUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (AuthenticatedUser) auth.getPrincipal();
    }

    //get valid Project
    public static Project getProjectById(ProjectJpaRepository projectJpaRepository, int id)
            throws RuntimeException {

        //finding project in DB
        Project project = projectJpaRepository.findOne(id);
        if (project == null)
            throw new NotFoundException("Project with ID:"+id+" don't exists.");

        //checking accessibility
        Project comparingProject = findProjectById(getCurrentUser().getProjects(), id);
        if (comparingProject != null && comparingProject.getId() == project.getId())
            return project;

        throw new AccessDeniedException("You have no access to project with ID:"+id);
    }

    //same for Task Entity
    public static Task getTaskById(ProjectJpaRepository projectJpaRepository, TaskJpaRepository taskJpaRepository,
                                   int projectId, int taskId)
            throws RuntimeException {

        Project project = getProjectById(projectJpaRepository, projectId);
        Task task = taskJpaRepository.findOne(taskId);

        if (task == null)
            throw new NotFoundException("Task with ID:"+taskId+" don't exists.");

        Task comparingTask = findTaskById(project.getTasks(), taskId);
        if (comparingTask != null && comparingTask.getId() == task.getId() &&
                task.getAssigneeId() == getCurrentUser().getId())
            return task;

        throw new AccessDeniedException("You have no access to task with ID:"+taskId);
    }

    //same for SubTask Entity
    public static SubTask getSubTaskByIdFromTask(ProjectJpaRepository projectJpaRepository, TaskJpaRepository taskJpaRepository,
                                                 int projectId, int taskId, int subTaskId)
        throws RuntimeException {

        List<SubTask> subTasks = getTaskById(projectJpaRepository, taskJpaRepository, projectId, taskId).getSubTaskList();
        subTasks.removeIf(subTask1 -> subTask1.getId() != subTaskId);
        if (subTasks.isEmpty()) {
            throw new NotFoundException("SubTask with ID:"+subTaskId+" don't exists.");
        }
        return subTasks.get(0);
    }

    //utility methods
    private static Project findProjectById(List<Project> projects, int id) {
        List<Project> collectedProject = projects.stream().filter(project -> project.getId() ==id).collect(Collectors.toList());
        return collectedProject.isEmpty() ? null : collectedProject.get(0);
    }

    private static Task findTaskById(List<Task> tasks, int id) {
        List<Task> collectedTask = tasks.stream().filter(task -> task.getId() == id).collect(Collectors.toList());
        return collectedTask.isEmpty() ? null : collectedTask.get(0);
    }
}

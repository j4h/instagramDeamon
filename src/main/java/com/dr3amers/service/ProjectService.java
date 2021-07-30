package com.dr3amers.service;

import com.dr3amers.helper.Helper;
import com.dr3amers.model.Project;
import com.dr3amers.repository.ProjectJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    public Project create(Project project) {
        return projectJpaRepository.saveAndFlush(project);
    }

    public Project update(int id, Project project) {
        get(id);
        return projectJpaRepository.saveAndFlush(project);
    }

    public Project get(int id) {
        return Helper.getProjectById(projectJpaRepository,id);
    }

    public void delete(int id) {
        Project project = get(id);
        projectJpaRepository.delete(project);
    }

    public List<Project> getAll() {
        return Helper.getCurrentUser().getProjects();
    }
}

package com.dr3amers.controller;

import com.dr3amers.model.SubTask;
import com.dr3amers.service.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/projects/{projectId}/tasks/{taskId}/subtasks")
public class SubTaskController {

    @Autowired
    private SubTaskService subTaskService;

    @GetMapping
    public List<SubTask> getAll(@PathVariable("projectId") int projectId, @PathVariable("taskId") int taskId) {
        return subTaskService.getAll(projectId,taskId);
    }

    @GetMapping(value = "/{id}")
    public SubTask get(@PathVariable("projectId") int projectId, @PathVariable("taskId") int taskId,
                       @PathVariable("id") int id) {
        return subTaskService.get(projectId,taskId,id);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("projectId") int projectId, @PathVariable("taskId") int taskId,
                         @PathVariable("id") int id) {
        subTaskService.delete(projectId, taskId, id);
        return "SubTask with ID:" + id+ " was successfully deleted";
    }

    @PostMapping
    public SubTask create(@PathVariable("projectId") int projectId, @PathVariable("taskId") int taskId,
                          @RequestBody SubTask subtask) {
        return subTaskService.create(projectId, taskId, subtask);
    }

    @PutMapping(value = "/{id}")
    public SubTask update(@PathVariable("projectId") int projectId, @PathVariable("taskId") int taskId,
                          @PathVariable("id") int id, @RequestBody SubTask subtask) {
        return subTaskService.update(projectId, taskId, id, subtask);
    }

}

package com.dr3amers.controller;

import com.dr3amers.model.Task;
import com.dr3amers.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/projects/{projectId}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAll(@PathVariable ("projectId") int id){ return taskService.getAll(id); }

    @PostMapping
    public Task create(@PathVariable("projectId") int projectId, @RequestBody Task task) {
        return taskService.create(projectId, task);
    }

    @GetMapping(value = "/{id}")
    public Task get(@PathVariable("projectId")int projectId, @PathVariable("id")int id) {
        return taskService.get(projectId, id);
    }

    @PutMapping(value = "/{id}")
    public Task update(@PathVariable("projectId") int projectId, @PathVariable("id") int id, @RequestBody Task task) {
        return taskService.update(projectId, id, task);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable("projectId") int projectId, @PathVariable("id") int id) {
        taskService.delete(projectId, id);
        return "Task with ID:" + id+ " was successfully deleted";
    }
}
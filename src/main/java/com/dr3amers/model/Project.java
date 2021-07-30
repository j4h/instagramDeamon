package com.dr3amers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty
    @Column(unique = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "creationDate")
    @CreatedDate
    @Generated(GenerationTime.INSERT)
    private Timestamp creationDate;
    //todo QUESTION: "should we have this column in JSON or replace it with JsonIgnore Object User?
    @Column(name = "creator_id")
    private int creatorId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private List<Task> tasks;

    @ManyToMany(mappedBy = "projects")
    @JsonIgnore
    private Set<User> assignees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<User> getAssignees() {
        return assignees;
    }

    public void setAssignees(Set<User> assignees) {
        this.assignees = assignees;
    }

}

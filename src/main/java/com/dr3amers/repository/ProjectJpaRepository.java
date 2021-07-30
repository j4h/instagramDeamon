package com.dr3amers.repository;

import com.dr3amers.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<Project, Integer> {

}

package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Long>{

}

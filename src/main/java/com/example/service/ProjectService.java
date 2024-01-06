package com.example.service;

import java.util.List;

import com.example.Entity.Project;


public interface ProjectService {

	public List<Project> findAllProjects();

	public Project findProjectById(Long id);

	public void createProject(Project project);

	public void updateProject(Project project);

	public void deleteProject(Long id);

}

package com.example.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.Entity.Project;
import com.example.Repository.ProjectRepo;
import com.example.Exception.NotFoundException;
import com.example.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	private final ProjectRepo projectRepo;
	
	public ProjectServiceImpl(ProjectRepo projectRepo) {
		this.projectRepo = projectRepo;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Project> findAllProjects() {
		return projectRepo.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Project findProjectById(Long id) {
		return projectRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));
	}

	@Override
	public void createProject(Project project) {
		projectRepo.save(project);
		
	}

	@Override
	public void updateProject(Project project) {
		projectRepo.save(project);
		
	}

	@Override
	public void deleteProject(Long id) {
		final Project project = projectRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Author not found with ID %d", id)));

		projectRepo.deleteById(project.getId());
	}

}

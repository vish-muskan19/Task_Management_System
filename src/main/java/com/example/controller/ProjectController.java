package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Entity.Project;
import com.example.service.ProjectService;

@Controller
public class ProjectController {
	
	private final ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@RequestMapping("/projects")
	public String findAllProjects(Model model) {
		final List<Project> projects = projectService.findAllProjects();

		model.addAttribute("projects", projects);
		return "list-projects";
	}

	@RequestMapping("/project/{id}")
	public String findProjectById(@PathVariable("id") Long id, Model model) {
		final Project project = projectService.findProjectById(id);

		model.addAttribute("project", project);
		return "list-project";
	}

	@GetMapping("/addProject")
	public String showCreateForm(Project project) {
		return "add-project";
	}

	@RequestMapping("/add-project")
	public String createProject(Project project, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-project";
		}

		projectService.createProject(project);
		model.addAttribute("project", projectService.findAllProjects());
		return "redirect:/projects";
	}

	@GetMapping("/updateProject/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Project project = projectService.findProjectById(id);

		model.addAttribute("project", project);
		return "update-project";
	}

	@RequestMapping("/update-project/{id}")
	public String updateProject(@PathVariable("id") Long id, Project project, BindingResult result, Model model) {
		if (result.hasErrors()) {
			project.setId(id);
			return "update-project";
		}

		projectService.updateProject(project);
		model.addAttribute("project", projectService.findAllProjects());
		return "redirect:/projects";
	}

	@RequestMapping("/remove-project/{id}")
	public String deleteProject(@PathVariable("id") Long id, Model model) {
		projectService.deleteProject(id);

		model.addAttribute("project", projectService.findAllProjects());
		return "redirect:/projects";
	}


}

package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Entity.Department;
import com.example.service.DepartmentService;

@Controller
public class DepartmentController {
	
	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@RequestMapping("/departments")
	public String findAllDepartments(Model model) {
		final List<Department> departments = departmentService.findAllDepartments();

		model.addAttribute("departments", departments);
		return "list-departments";
	}
	
	@RequestMapping("/depatment/{id}")
	public String findDepartmentById(@PathVariable("id") Long id, Model model) {
		final Department department = departmentService.findDepartmentById(id);

		model.addAttribute("depatment", department);
		return "list-depatment";
	}
	
	@GetMapping("/addDepartment")
	public String showCreateForm(Department department) {
		return "add-department";
	}

	@RequestMapping("/add-department")
	public String createDepartment(Department department, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-department";
		}

		departmentService.createDepartment(department);
		model.addAttribute("department", departmentService.findAllDepartments());
		return "redirect:/departments";
	}

	@GetMapping("/updateDepartment/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Department department = departmentService.findDepartmentById(id);

		model.addAttribute("department", department);
		return "update-department";
	}

	@RequestMapping("/update-department/{id}")
	public String updateDepartment(@PathVariable("id") Long id, Department department, BindingResult result, Model model) {
		if (result.hasErrors()) {
			department.setId(id);
			return "update-departments";
		}

		departmentService.updateDepartment(department);
		model.addAttribute("department", departmentService.findAllDepartments());
		return "redirect:/departments";
	}

	@RequestMapping("/remove-department/{id}")
	public String deleteDepartment(@PathVariable("id") Long id, Model model) {
		departmentService.deleteDepartment(id);

		model.addAttribute("department", departmentService.findAllDepartments());
		return "redirect:/depatments";
	}
	

}

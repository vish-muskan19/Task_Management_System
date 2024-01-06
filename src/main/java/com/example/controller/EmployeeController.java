package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Entity.Employee;
import com.example.Entity.User;
import com.example.service.DepartmentService;
import com.example.service.EmployeeService;
import com.example.service.ProjectService;
import com.example.service.StatusService;
import com.example.service.UserService;

@Controller
public class EmployeeController {

	private final EmployeeService employeeService;
	private final DepartmentService departmentService;
	private final ProjectService projectService;
	private final StatusService statusService;
	private final UserService userService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService, DepartmentService departmentService,
			ProjectService projectService, StatusService statusService, UserService userService) {
		this.employeeService = employeeService;
		this.departmentService = departmentService;
		this.projectService = projectService;
		this.statusService = statusService;
		this.userService = userService;
	}
	
	
	@RequestMapping("/employees")
	public String findAllEmployees(Model model) {
		final List<Employee> employees = employeeService.findAllEmployees();

		model.addAttribute("employees", employees);
		return "list-employees";
	}

	@RequestMapping("/searchEmployee")
	public String searchEmployee(@Param("keyword") String keyword, Model model) {
		final List<Employee> employees = employeeService.searchEmployees(keyword);

		model.addAttribute("employees", employees);
		model.addAttribute("keyword", keyword);
		return "list-employees";
	}

	@RequestMapping("/employee/{id}")
	public String findEmployeeById(@PathVariable("id") Long id, Model model) {
		final Employee employee = employeeService.findEmployeeById(id);

		model.addAttribute("employee", employee);
		return "list-employee";
	}

	@GetMapping("/add")
	public String showCreateForm(Employee employee, Model model) {
		model.addAttribute("status", statusService.findAllStatus());
		model.addAttribute("projects", projectService.findAllProjects());
		model.addAttribute("departments",departmentService.findAllDepartments());
		return "add-employee";
	}

	@RequestMapping("/add-employee")
	public String createEmployee(Employee employee, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-employee";
		}

		employeeService.createEmployee(employee);
		model.addAttribute("employee", employeeService.findAllEmployees());
		return "redirect:/employees";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Employee employee = employeeService.findEmployeeById(id);

		model.addAttribute("employee", employee);
		return "update-employee";
	}

	@RequestMapping("/update-employee/{id}")
	public String updateEmployee(@PathVariable("id") Long id, Employee employee, BindingResult result, Model model) {
		if (result.hasErrors()) {
			employee.setId(id);
			return "update-employee";
		}

		employeeService.updateEmployee(employee);
		model.addAttribute("employee", employeeService.findAllEmployees());
		return "redirect:/employees";
	}

	@RequestMapping("/remove-employee/{id}")
	public String deleteEmployee(@PathVariable("id") Long id, Model model) {
		employeeService.deleteEmployee(id);

		model.addAttribute("employee", employeeService.findAllEmployees());
		return "redirect:/employees";
	}
	
//	@PostMapping()
//	public Employee createEmployee(@RequestBody Employee employee, @RequestHeader("Authorization") String jwt) throws Exception {
//		
//		User user = userService.findUserByJwt(jwt);
//		
//		Employee createEmployee = employeeService.createEmployee(employee, user);
//		return createEmployee;
//	}
	
}

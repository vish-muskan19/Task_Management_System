package com.example.Entity;

import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Employee_Id", length = 50, nullable = false, unique = true)
	private String Employee_Id;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "description", length = 250, nullable = false)
	private String description;
	
//	@ManyToOne
//	private User user;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "Employee_project", 
	    joinColumns = @JoinColumn(name = "employee_id"), 
	    inverseJoinColumns = @JoinColumn(name = "project_id"))
	private Set<Project> projects = new HashSet<Project>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "employees_departments", 
	    joinColumns = @JoinColumn(name = "employee_id"), 
	    inverseJoinColumns = @JoinColumn(name = "department_id"))
	private Set<Department> departments = new HashSet<Department>();


	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "Employees_status", joinColumns = { @JoinColumn(name = "employee_id") }, inverseJoinColumns = {
			@JoinColumn(name = "status_id") })
	private Set<Status> status = new HashSet<Status>();

//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//	@JoinTable(name = "employees_departments", joinColumns = { @JoinColumn(name = "department_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "department_id") })
//	private Set<Department> departments = new HashSet<Department>();

	public Employee(String Employee_Id, String name,  String description) {
		this.Employee_Id = Employee_Id;
		this.name = name;
		this.description = description;
	}

	public void addAuthors(Project project) {
		this.projects.add(project);
		project.getEmployees().add(this);
	}

	public void removeAuthors(Project project) {
		this.projects.remove(project);
		project.getEmployees().remove(this);
	}

	public void addCategories(Status status) {
		this.status.add(status);
		status.getEmployees().add(this);
	}

	public void removeCategories(Status status) {
		this.status.remove(status);
		status.getEmployees().remove(this);
	}

	public void addPublishers(Department department) {
		this.departments.add(department);
		department.getEmployees().add(this);
	}

	public void removePublishers(Department department) {
		this.departments.remove(department);
		department.getEmployees().remove(this);
	}

}

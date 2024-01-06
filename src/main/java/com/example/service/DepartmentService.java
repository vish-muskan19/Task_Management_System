package com.example.service;

import java.util.List;

import com.example.Entity.Department;


public interface DepartmentService {

	public List<Department> findAllDepartments();

	public Department findDepartmentById(Long id);

	public void createDepartment(Department department);

	public void updateDepartment(Department department);

	public void deleteDepartment(Long id);


}

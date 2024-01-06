package com.example.service;

import java.util.List;

import com.example.Entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAllEmployees();
	
	public List<Employee> searchEmployees(String keyword);

	public Employee findEmployeeById(Long id);

	public void createEmployee(Employee employee);

	public void updateEmployee(Employee employee);

	public void deleteEmployee(Long id);


}

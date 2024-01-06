package com.example.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.Entity.Employee;
import com.example.Repository.EmployeeRepo;
import com.example.Exception.NotFoundException;
import com.example.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private final EmployeeRepo employeeRepo;
	
	public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepo.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Employee> searchEmployees(String keyword) {
		if (keyword != null) {
			return employeeRepo.search(keyword);
		}
		return employeeRepo.findAll();
	}
	

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Employee findEmployeeById(Long id) {
		return employeeRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
	}

	@Override
	public void createEmployee(Employee employee) {
		employeeRepo.save(employee);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepo.save(employee);
		
	}

	@Override
	public void deleteEmployee(Long id) {
		final Employee employee = employeeRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));

		employeeRepo.deleteById(employee.getId());
	}

}

package com.example.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.Entity.Department;
import com.example.Repository.DepartmentRepo;
import com.example.Exception.NotFoundException;
import com.example.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	private final DepartmentRepo departmentRepo;
	
	public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
		this.departmentRepo = departmentRepo;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Department> findAllDepartments() {
		return departmentRepo.findAll();
	}

	@Override
	public Department findDepartmentById(Long id) {
		return departmentRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Department not found  with ID %d", id)));
	}

	@Override
	public void createDepartment(Department department) {
		departmentRepo.save(department);
		
	}

	@Override
	public void updateDepartment(Department department) {
		departmentRepo.save(department);
		
	}

	@Override
	public void deleteDepartment(Long id) {
		final Department department = departmentRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Department not found  with ID %d", id)));

		departmentRepo.deleteById(department.getId());
		
	}

}

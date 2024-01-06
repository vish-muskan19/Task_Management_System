package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.Entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	
//	@Query("SELECT b FROM Employee b WHERE b.name LIKE %?1%" + " OR b.isbn LIKE %?1%" + " OR b.Name LIKE %?1%")
//	public List<Employee> search(String keyword);

	@Query("SELECT e FROM Employee e WHERE e.name LIKE %?1%")
	public List<Employee> search(String keyword);

}

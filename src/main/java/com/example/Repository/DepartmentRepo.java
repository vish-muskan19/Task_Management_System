package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

}

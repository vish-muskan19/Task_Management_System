package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Status;

public interface StatusRepo extends JpaRepository<Status, Long>{

}

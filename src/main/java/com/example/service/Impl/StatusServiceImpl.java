package com.example.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.example.Entity.Status;
import com.example.Repository.StatusRepo;
import com.example.Exception.NotFoundException;
import com.example.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService{

	private final StatusRepo statusRepo;
	
	public StatusServiceImpl(StatusRepo statusRepo) {
		this.statusRepo = statusRepo;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Status> findAllStatus() {
		return statusRepo.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Status findStatusById(Long id) {
		return statusRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));
	}


	@Override
	public void createStatus(Status status) {
		statusRepo.save(status);
		
	}

	@Override
	public void updateStatus(Status status) {
		statusRepo.save(status);		
	}

	@Override
	public void deleteStatus(Long id) {
		final Status status = statusRepo.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));

		statusRepo.deleteById(status.getId());
	}

}

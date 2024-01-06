package com.example.service;

import java.util.List;

import com.example.Entity.Status;

public interface StatusService {

	public List<Status> findAllStatus();

	public Status findStatusById(Long id);

	public void createStatus(Status status);

	public void updateStatus(Status status);

	public void deleteStatus(Long id);
}

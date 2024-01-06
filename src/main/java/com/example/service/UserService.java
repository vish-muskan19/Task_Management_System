package com.example.service;

import com.example.Entity.User;

public interface UserService {

	public User saveUser(User user);

	public void removeSessionMessage();

}
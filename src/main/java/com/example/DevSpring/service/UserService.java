package com.example.DevSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DevSpring.dto.UserDTO;
import com.example.DevSpring.entity.User;
import com.example.DevSpring.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public User createUser(UserDTO request) {
		User user = new User();

		user.setUsername(request.getUsername());

		return userRepo.save(user);

	}
}

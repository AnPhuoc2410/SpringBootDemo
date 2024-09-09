package com.example.DevSpring.service;

import java.util.List;

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
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());

		return userRepo.save(user);

	}

	public List<User> getListUser() {

		return userRepo.findAll();
	}

	public User findUserByID(Long userID) {
		return userRepo.findById(userID).orElse(null);
	}

	public User updateUserByID(Long id, UserDTO request) {
		User user = findUserByID(id);
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		return userRepo.save(user);
	}
	
	public void deleteUserByID(Long id) {
		 userRepo.deleteById(id);
	}
}

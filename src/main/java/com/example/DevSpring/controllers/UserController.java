package com.example.DevSpring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DevSpring.dto.UserDTO;
import com.example.DevSpring.entity.User;
import com.example.DevSpring.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	User createUser(@RequestBody UserDTO request) {
		return userService.createUser(request);
	}

	@GetMapping
	List<User> getUsers() {
		return userService.getListUser();
	}

	@GetMapping("/{id}")
	String findUser(@PathVariable Long id) {
		User user = userService.findUserByID(id);
		if(user != null) {
			return "Found";
		}else {
			return "Not Found";
		}
	
	}
	
	@PutMapping("/{id}")
	User updateUser(@PathVariable Long id,@RequestBody UserDTO request) {
		return userService.updateUserByID(id,request);
	}
	
	@DeleteMapping("/{id}")
	String deleteUser(@PathVariable Long id) {
		userService.deleteUserByID(id);
		return "Delete User Success";
	}
	

}

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

import com.example.DevSpring.dto.ApiResponse;
import com.example.DevSpring.dto.UserDTO;
import com.example.DevSpring.entity.User;
import com.example.DevSpring.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	ApiResponse<User> createUser(@RequestBody @Valid UserDTO request) {
		ApiResponse<User> apiResponse = new ApiResponse<User>();
		
		apiResponse.setResult(userService.createUser(request));
		
		return apiResponse;
	}

	@GetMapping
	List<User> getUsers() {
		return userService.getListUser();
	}

	@GetMapping("/{id}")
	User findUser(@PathVariable Long id) {
		return userService.findUserByID(id);
	}

	@PutMapping("/{id}")
	User updateUser(@PathVariable Long id, @RequestBody UserDTO request) {
		return userService.updateUserByID(id, request);
	}

	@DeleteMapping("/{id}")
	String deleteUser(@PathVariable Long id) {
		userService.deleteUserByID(id);
		return "Delete User Success";
	}

}

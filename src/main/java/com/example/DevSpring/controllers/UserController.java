package com.example.DevSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DevSpring.dto.UserDTO;
import com.example.DevSpring.entity.User;
import com.example.DevSpring.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/users")
	User creatUser(@RequestBody UserDTO request) {
		return userService.createUser(request);
	}
}

package com.example.DevSpring.controllers;

import com.example.DevSpring.dto.request.ApiResponse;
import com.example.DevSpring.dto.request.UserDTO;
import com.example.DevSpring.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {

	 UserService userService;

	@PostMapping("/register")
	ApiResponse<UserDTO> createUser(@RequestBody @Valid UserDTO request) {
		ApiResponse<UserDTO> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(userService.createUser(request));
		
		return apiResponse;
	}

	@GetMapping
	List<UserDTO> getUsers() {
		return userService.getListUser();
	}

	@GetMapping("/{id}")
	UserDTO findUser(@PathVariable Long id) {
		return userService.findUserByID(id);
	}

	@PutMapping("/{id}")
	UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO request) {
		return userService.updateUserByID(id, request);
	}

	@DeleteMapping("/{id}")
	String deleteUser(@PathVariable Long id) {
		userService.deleteUserByID(id);
		return "Delete User Success";
	}

}

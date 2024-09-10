package com.example.DevSpring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDTO {

	Long id;
	@Size(min = 3, message = "USERNAME_INVALID")
	 String username;
	@Size(min = 8, message = "USER_PASSWORD")
	 String password;
	@Email
	 String email;

	

}

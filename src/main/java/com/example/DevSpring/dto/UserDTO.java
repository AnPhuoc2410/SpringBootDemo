package com.example.DevSpring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDTO {

	@Size(min = 3, message = "USERNAME_INVALID")
	 String username;
	@Size(min = 8, message = "USER_PASSWORD")
	 String password;
	@Email
	 String email;

	

}

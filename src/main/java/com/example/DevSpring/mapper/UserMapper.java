package com.example.DevSpring.mapper;

import com.example.DevSpring.dto.UserDTO;
import com.example.DevSpring.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User maptoUser(UserDTO userDTO);
    UserDTO maptoUserDTO(User user);
    void updateUser(@MappingTarget User user,UserDTO userDTO);
}

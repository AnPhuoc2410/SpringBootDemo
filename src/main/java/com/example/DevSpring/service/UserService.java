package com.example.DevSpring.service;

import com.example.DevSpring.dto.request.UserDTO;

import java.util.List;


public interface UserService {
    UserDTO createUser(UserDTO request);

    List<UserDTO> getListUser();

    UserDTO findUserByID(Long userID);

    UserDTO updateUserByID(Long id, UserDTO request);

    void deleteUserByID(Long id);
}

package com.example.DevSpring.service.imp;

import com.example.DevSpring.dto.UserDTO;
import com.example.DevSpring.entity.User;
import com.example.DevSpring.exception.AppException;
import com.example.DevSpring.exception.ErrorCode;
import com.example.DevSpring.mapper.UserMapper;
import com.example.DevSpring.repository.UserRepository;
import com.example.DevSpring.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepo;
    UserMapper userMapper;

    public UserDTO createUser(UserDTO request) {


        if (userRepo.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.maptoUser(request);

        return userMapper.maptoUserDTO(userRepo.save(user));

    }

    public List<UserDTO> getListUser() {
        return userRepo.findAll().stream()
                .map(userMapper::maptoUserDTO).toList();

    }

    public UserDTO findUserByID(Long userID) {
        return userMapper.maptoUserDTO(userRepo.findById(userID).orElseThrow(() -> new RuntimeException("User Not Found")));
    }

    public UserDTO updateUserByID(Long id, UserDTO request) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user,request);
        return userMapper.maptoUserDTO(userRepo.save(user));
    }

    public void deleteUserByID(Long id) {
        userRepo.deleteById(id);
    }
}

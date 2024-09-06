package com.example.DevSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.DevSpring.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

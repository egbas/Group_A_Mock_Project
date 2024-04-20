package com.group_A.MyTodo_App.repository;

import com.group_A.MyTodo_App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String username);
}

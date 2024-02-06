package com.trio.java.bikerentapi.repository;

import com.trio.java.bikerentapi.data.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUsername(String username);
}

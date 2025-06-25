package com.ttswebser.user_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ttswebser.user_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByNim(String nim);
    Optional<User> findByEmail(String email);
    List<User> findByRole(String role);
}
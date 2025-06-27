package com.ttswebser.user_service.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ttswebser.user_service.entity.User;
import com.ttswebser.user_service.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public ResponseEntity<?> register(User user) {
        if (userRepository.findById(user.getNim()).isPresent()) {
            return ResponseEntity.badRequest().body("NIM already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) user.setRole("mahasiswa");

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(convertToDTO(savedUser));
    }

    public ResponseEntity<?> login(String nim, String password) {
        Optional<User> userOpt = userRepository.findById(nim);

        if (userOpt.isEmpty() || !passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        User user = userOpt.get();
        String token = jwtService.generateToken(user.getNim(), user.getRole());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", convertToDTO(user));

        return ResponseEntity.ok(response);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public Optional<User> getUserByNim(String nim) {
        return userRepository.findById(nim);
    }

    public User updateUser(String nim, User user) {
        user.setNim(nim);
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    public void deleteUser(String nim) {
        userRepository.deleteById(nim);
    }

    public List<User> getUsersByRole(String role) {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if (role.equals(user.getRole())) {
                users.add(user);
            }
        });
        return users;
    }

    public ResponseEntity<?> changePassword(String nim, String oldPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findById(nim);

        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOpt.get();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid old password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return ResponseEntity.ok("Password changed successfully");
    }

    private User convertToDTO(User user) {
        User dto = new User();
        dto.setNim(user.getNim());
        dto.setNama(user.getNama());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}
// 
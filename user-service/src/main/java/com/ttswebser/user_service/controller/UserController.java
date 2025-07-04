package com.ttswebser.user_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttswebser.user_service.entity.User;
import com.ttswebser.user_service.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        return userService.login(loginData.get("nim"), loginData.get("password"));
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{nim}")
    public ResponseEntity<User> getUserByNim(@PathVariable String nim) {
        return userService.getUserByNim(nim)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{nim}")
    public User updateUser(@PathVariable String nim, @RequestBody User user) {
        return userService.updateUser(nim, user);
    }

    @DeleteMapping("/{nim}")
    public ResponseEntity<Void> deleteUser(@PathVariable String nim) {
        userService.deleteUser(nim);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        return userService.getUsersByRole(role);
    }

    @PostMapping("/{nim}/change-password")
    public ResponseEntity<?> changePassword(@PathVariable String nim, @RequestBody Map<String, String> passwordData) {
        return userService.changePassword(nim, passwordData.get("oldPassword"), passwordData.get("newPassword"));
    }
}
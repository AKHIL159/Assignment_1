package com.assignment.modules.controller;

import com.assignment.modules.model.User;
import com.assignment.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    // NEW: Get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // NEW: Update user by email
    @PutMapping("/email/{email}")
    public ResponseEntity<?> updateUserByEmail(@PathVariable String email, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUserByEmail(email, user));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // NEW: Delete user by email
    @DeleteMapping("/email/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
}

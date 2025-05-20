package com.assignment.modules.controller;

import com.assignment.modules.dto.UserDTO;
import com.assignment.modules.model.User;
import com.assignment.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
//@PostMapping
//    public ResponseEntity<?> createUser(@RequestBody User user) {
//        try {
//             ResponseEntity.ok(userService.createUser(user));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO user = userService.getUserByEmail(email);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/email/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User user) {
        User updated = userService.updateUser(email, user);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        boolean deleted = userService.deleteUser(email);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }

//    @PostMapping
//    public ResponseEntity<?> createUser(@RequestBody User user) {
//        try {
//            return ResponseEntity.ok(userService.createUser(user));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/email/{email}")
//    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
//        return userService.getUserByEmail(email)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/email/{email}")
//    public ResponseEntity<?> updateUserByEmail(@PathVariable String email, @RequestBody User user) {
//        try {
//            return ResponseEntity.ok(userService.updateUserByEmail(email, user));
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/email/{email}")
//    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email) {
//        userService.deleteUserByEmail(email);
//        return ResponseEntity.noContent().build();
//    }
}

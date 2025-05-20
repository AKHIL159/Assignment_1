package com.assignment.modules.service;

import com.assignment.modules.dto.UserDTO;
import com.assignment.modules.model.User;
import com.assignment.modules.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditService auditService;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAllProjectedBy();
    }

    public UserDTO getUserByEmail(String email) {
        return userRepository.findProjectedByEmail(email);
    }

    public User updateUser(String email, User user) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAge(user.getAge());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    public boolean deleteUser(String email) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            userRepository.deleteByEmail(email);
            return true;
        } else {
            return false;
        }
    }


    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return null;
        }

        User savedUser = userRepository.save(user);

//        // Run logging and audit in parallel
//        CompletableFuture.runAsync(() -> auditService.logToFile(savedUser));
//        CompletableFuture.runAsync(() -> auditService.saveToAuditTable(savedUser));
        auditService.logToFile(savedUser);
        auditService.saveToAuditTable(savedUser);
        return savedUser;
    }

//    public Optional<User> getUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    public User updateUserByEmail(String email, User updatedUser) {
//        User existing = userRepository.findByEmail(email)
//                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
//        updatedUser.setId(existing.getId());
//        return userRepository.save(updatedUser);
//    }
//
//    public void deleteUserByEmail(String email) {
//        Optional<User> userOpt = userRepository.findByEmail(email);
//        if (userOpt.isPresent()) {
//            userRepository.deleteById(userOpt.get().getId());
//        } else {
//            throw new NoSuchElementException("User not found with email: " + email);
//        }
//    }
}

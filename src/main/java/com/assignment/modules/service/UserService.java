package com.assignment.modules.service;

import com.assignment.modules.dto.UserDTO;
import com.assignment.modules.model.User;
import com.assignment.modules.repository.UserRepository;

import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditService auditService;

  // ----- new functions which return completable future -----
    @Async("taskExecutor")
    public CompletableFuture<List<UserDTO>> getAllUsers() {
        return CompletableFuture.completedFuture(userRepository.findAllProjectedBy());
    }

    @Async("taskExecutor")
    public CompletableFuture<UserDTO> getUserByEmail(String email) {
        return CompletableFuture.completedFuture(userRepository.findProjectedByEmail(email));
    }

    @Async("taskExecutor")
    public CompletableFuture<User> createUser(User user) {
        try {
            User savedUser = userRepository.save(user);
            CompletableFuture.runAsync(() -> auditService.logToFile(savedUser));
            CompletableFuture.runAsync(() -> auditService.saveToAuditTable(savedUser));
            return CompletableFuture.completedFuture(savedUser);
        } catch (DuplicateKeyException e) {
            return CompletableFuture.completedFuture(null);
        }
    }

    @Async("taskExecutor")
    public CompletableFuture<User> updateUser(String email, User user) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAge(user.getAge());
            return CompletableFuture.completedFuture(userRepository.save(existingUser));
        } else {
            return CompletableFuture.completedFuture(null);
        }
    }

    @Async("taskExecutor")
    public CompletableFuture<Boolean> deleteUser(String email) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser != null) {
            userRepository.deleteByEmail(email);
            return CompletableFuture.completedFuture(true);
        } else {
            return CompletableFuture.completedFuture(false);
        }
    }


// -------------This the DTO version of the code------------------
//    public List<UserDTO> getAllUsers() {
//        return userRepository.findAllProjectedBy();
//    }
//
//    public UserDTO getUserByEmail(String email) {
//        return userRepository.findProjectedByEmail(email);
//    }
//
//    public User updateUser(String email, User user) {
//        User existingUser = userRepository.findByEmail(email);
//        if (existingUser != null) {
//            existingUser.setName(user.getName());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setAge(user.getAge());
//            return userRepository.save(existingUser);
//        } else {
//            return null;
//        }
//    }
//
//    public boolean deleteUser(String email) {
//        User existingUser = userRepository.findByEmail(email);
//        if (existingUser != null) {
//            userRepository.deleteByEmail(email);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//
//    public User createUser(User user) {
//        if (userRepository.existsByEmail(user.getEmail())) {
//            return null;
//        }
//
//        User savedUser = userRepository.save(user);
//
////        // Run logging and audit in parallel
////        CompletableFuture.runAsync(() -> auditService.logToFile(savedUser));
////        CompletableFuture.runAsync(() -> auditService.saveToAuditTable(savedUser));
//        auditService.logToFile(savedUser);
//        auditService.saveToAuditTable(savedUser);
//        return savedUser;
//    }

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

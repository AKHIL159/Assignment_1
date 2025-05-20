package com.assignment.modules.service;

import com.assignment.modules.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    public void logToFile(User user) {
        try {
            // Simulate I/O delay
            Thread.sleep(100);
            System.out.println("[LOG] User created: " + user.getEmail());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void saveToAuditTable(User user) {
        try {
            // Simulate DB delay
            Thread.sleep(100);
            System.out.println("[AUDIT DB] Saved user: " + user.getEmail());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

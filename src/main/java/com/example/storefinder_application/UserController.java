package com.example.storefinder_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Document(collection = "users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}/favorites")
    public List<Store> getFavoriteStores(@PathVariable String userId) {
        return userService.getFavoriteStores(userId);
    }

    @PostMapping("/{userId}/favorites/{storeId}")
    public User saveFavoriteStore(@PathVariable String userId, @PathVariable String storeId) {
        return userService.saveFavoriteStore(userId, storeId);
    }

    @DeleteMapping("/{userId}/favorites/{storeId}")
    public ResponseEntity<?> removeFavoriteStore(@PathVariable String userId, @PathVariable String storeId) {
        try {
            userService.removeFavoriteStore(userId, storeId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error removing favorite store: " + e.getMessage());
        }
    }
}

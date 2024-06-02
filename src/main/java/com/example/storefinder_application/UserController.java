package com.example.storefinder_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@Document(collection = "users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already taken!");
        }
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody User loginRequest) {
        Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());
        if (userOptional.isPresent() && userOptional.get().getPasswordHash().equals(loginRequest.getPasswordHash())) {
            String userId = userOptional.get().getId(); // Get the userId from the retrieved user
            return ResponseEntity.ok(userId.toString()); // Send userId as the response
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<?> getFavoriteStores(@PathVariable String userId) {
        try {
            List<Store> favoriteStores = userService.getFavoriteStores(userId);
            return ResponseEntity.ok(favoriteStores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch favorite stores");
        }
    }

    @PostMapping("/{userId}/favorites/{storeId}")
    public ResponseEntity<?> saveFavoriteStore(@PathVariable String userId, @PathVariable String storeId) {
        try {
            User user = userService.saveFavoriteStore(userId, storeId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save favorite store");
        }
    }
}

package com.example.storefinder_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User saveFavoriteStore(String userId, String storeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("Store not found"));

        user.getFavoriteStoreIds().add(store.getId());
        return userRepository.save(user);
    }

    public List<Store> getFavoriteStores(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Set<String> favoriteStoreIds = user.getFavoriteStoreIds();
        return storeRepository.findAllById(favoriteStoreIds);
    }

    public void removeFavoriteStore(String userId, String storeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        user.removeFavoriteStore(storeId);
        userRepository.save(user);
    }
}

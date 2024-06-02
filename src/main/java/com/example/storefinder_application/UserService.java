package com.example.storefinder_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
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

//    @Transactional
//    public void deleteFavoriteStore(String userId, String storeId) throws Exception {
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            List<String> favoriteStores = user.getFavoriteStores();
//            if (favoriteStores.remove(storeId)) {
//                user.setFavoriteStores(favoriteStores);
//                userRepository.save(user);
//            } else {
//                throw new Exception("Store not found in user's favorites");
//            }
//        } else {
//            throw new Exception("User not found");
//        }
//    }
}

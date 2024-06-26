package com.example.storefinder_application;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String passwordHash;
    private Set<String> favoriteStoreIds = new HashSet<>(); // Store references by ID
    private List<String> favoriteStores;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<String> getFavoriteStoreIds() {
        return favoriteStoreIds;
    }

    public void setFavoriteStoreIds(Set<String> favoriteStoreIds) {
        this.favoriteStoreIds = favoriteStoreIds;
    }

    public List<String> getFavoriteStores() {
        return favoriteStores;
    }

    public void setFavoriteStores(List<String> favoriteStores) {
        this.favoriteStores = favoriteStores;
    }

    public void removeFavoriteStore(String storeId) {
        this.favoriteStoreIds.remove(storeId);
    }

}

package com.example.storefinder_application;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByStoreId(String storeId);
}


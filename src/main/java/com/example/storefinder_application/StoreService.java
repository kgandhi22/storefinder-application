package com.example.storefinder_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public List<Store> searchStores(String keyword) {
        return storeRepository.searchByKeyword(keyword);
    }

    public Store getStoreById(String id) {
        return storeRepository.findById(id).orElse(null);
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }
}

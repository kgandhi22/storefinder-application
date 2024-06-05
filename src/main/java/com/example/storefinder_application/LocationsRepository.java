package com.example.storefinder_application;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

//import java.util.Optional;

public interface LocationsRepository extends MongoRepository<Locations, String> {
//    Optional<Locations> findById(String id);
    Optional<Locations> findById(String id);
}

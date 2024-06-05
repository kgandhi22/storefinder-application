package com.example.storefinder_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationsService {
    @Autowired
    private LocationsRepository locationsRepository;

    public List<Locations> getAllLocations() {
        return locationsRepository.findAll();
    }

    public Optional<Locations> getLocationsById(String id) {
        return locationsRepository.findById(id);
    }

    public Location getLocationObjectById(String id) {
        Locations variable = locationsRepository.findById(id).orElse(null);
        return (variable != null && variable.getLocation() != null) ? variable.getLocation() : null;
    }

    public double getLatitudes(String id) {
        Locations variable = locationsRepository.findById(id).orElse(null);
        if (variable != null && variable.getLocation() != null)
            return variable.getLocation().getLatitude();
        return 0.0;
    }

    public double getLongitudes(String id) {
        Locations variable = locationsRepository.findById(id).orElse(null);
        if (variable != null && variable.getLocation() != null)
            return variable.getLocation().getLongitude();
        return 0.0;
    }
}

package com.example.storefinder_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:3000")
public class LocationsController {

    @Autowired
    private LocationsService service;

    @GetMapping
    public List<Locations> getAllLocationsAndIds() {
        return service.getAllLocations();
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable String id)
    {
        return service.getLocationObjectById(id);
    }

    @GetMapping("/{id}/latitude")
    public double getLatitudeById(@PathVariable String id)
    {
        return service.getLatitudes(id);
    }

    @GetMapping("/{id}/longitude")
    public double getLongitudeById(@PathVariable String id)
    {
        return service.getLongitudes(id);
    }
}

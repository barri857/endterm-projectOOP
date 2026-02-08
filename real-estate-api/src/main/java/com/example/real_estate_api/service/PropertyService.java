package com.example.real_estate_api.service;

import com.example.real_estate_api.model.Property;
import com.example.real_estate_api.patterns.PropertyFactory;
import com.example.real_estate_api.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.real_estate_api.exception.ResourceNotFoundException;

@Service
public class PropertyService {
    private final PropertyRepository repository;

    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public List<Property> getAllProperties() {
        return repository.getAll();
    }

    public void createProperty(String type, String address, double price, int extraValue) {

        Property property = PropertyFactory.createProperty(type, address, price, extraValue);
        if (property != null) {
            repository.add(property);
        }
    }


    public void deleteProperty(int id) {

        repository.getAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));

        repository.delete(id);
    }
}
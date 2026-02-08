package com.example.real_estate_api.repository;

import com.example.real_estate_api.model.Property;
import java.util.List;

public interface PropertyRepository {
    List<Property> getAll();
    void add(Property property);
    void update(int id, Property property);
    void delete(int id);
}
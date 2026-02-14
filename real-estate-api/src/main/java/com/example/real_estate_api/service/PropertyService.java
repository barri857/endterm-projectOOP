package com.example.real_estate_api.service;

import com.example.real_estate_api.dto.PropertyDTO;
import com.example.real_estate_api.patterns.PropertyCache;
import com.example.real_estate_api.patterns.PropertyFactory;
import com.example.real_estate_api.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository repository;
    private final PropertyCache cache = PropertyCache.getInstance();

    public List<PropertyDTO> findAll() {
        String key = "all_properties";
        if (cache.get(key) != null) {
            System.out.println("Returning from CACHE...");
            return cache.get(key);
        }

        System.out.println("Querying DATABASE...");
        List<PropertyDTO> properties = repository.getAll().stream()
                .map(p -> new PropertyDTO(p.getAddress(), p.getPrice(), p.getType()))
                .toList();

        cache.put(key, properties);
        return properties;
    }

    public void save(PropertyDTO dto) {
        repository.add(PropertyFactory.createProperty(
                dto.getType(),
                dto.getAddress(),
                dto.getPrice(),
                0
        ));
        cache.clear();
    }

    public void deleteProperty(int id) {
        repository.delete(id);
        cache.clear();
    }
}
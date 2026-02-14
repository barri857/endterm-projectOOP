package com.example.real_estate_api.patterns;

import com.example.real_estate_api.dto.PropertyDTO;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PropertyCache {

    private static PropertyCache instance;

    private final Map<String, List<PropertyDTO>> cache = new ConcurrentHashMap<>();

    private PropertyCache() {}

    public static synchronized PropertyCache getInstance() {
        if (instance == null) {
            instance = new PropertyCache();
        }
        return instance;
    }

    public void put(String key, List<PropertyDTO> data) {
        cache.put(key, data);
    }

    public List<PropertyDTO> get(String key) {
        return cache.get(key);
    }

    public void clear() {
        cache.clear();
        System.out.println("Cache cleared!");
    }
}
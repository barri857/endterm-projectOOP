package com.example.real_estate_api.controller;

import com.example.real_estate_api.model.Property;
import com.example.real_estate_api.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyService service;

    public PropertyController(PropertyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Property> getAll() {
        return service.getAllProperties();
    }

    @PostMapping
    public String add(@RequestParam String type,
                      @RequestParam String address,
                      @RequestParam double price,
                      @RequestParam int extraValue) {
        service.createProperty(type, address, price, extraValue);
        return "Мүлік сәтті қосылды!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.deleteProperty(id);
        return "Мүлік өшірілді!";
    }
}
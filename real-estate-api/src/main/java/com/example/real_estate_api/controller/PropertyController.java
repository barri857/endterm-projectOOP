package com.example.real_estate_api.controller;

import com.example.real_estate_api.dto.PropertyDTO;
import com.example.real_estate_api.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService service;

    @GetMapping
    public List<PropertyDTO> getAll() {
        return service.findAll();
    }

    @PostMapping
    public String add(@RequestBody PropertyDTO dto) {
        service.save(dto);
        return "Мүлік сәтті қосылды!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.deleteProperty(id);
        return "Мүлік өшірілді!";
    }
}
package com.example.real_estate_api.dto;

import lombok.Data;

@Data
public class PropertyDTO {
    private String address;
    private double price;
    private String type;
}
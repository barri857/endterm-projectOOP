package com.example.real_estate_api.patterns;

import com.example.real_estate_api.model.Property;
import com.example.real_estate_api.model.House;
import com.example.real_estate_api.model.Apartment;

public class PropertyFactory {

    public static Property createProperty(String type, String address, double price, int extraValue) {
        if (type.equalsIgnoreCase("house")) {
            return House.builder()
                    .address(address)
                    .price(price)
                    .type("house")
                    .yardSize(extraValue)
                    .build();
        } else if (type.equalsIgnoreCase("apartment")) {
            return Apartment.builder()
                    .address(address)
                    .price(price)
                    .type("apartment")
                    .floorNumber(extraValue)
                    .build();
        }
        return null;
    }
}
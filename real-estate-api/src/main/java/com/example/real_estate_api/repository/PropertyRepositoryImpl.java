package com.example.real_estate_api.repository;

import com.example.real_estate_api.model.*;
import com.example.real_estate_api.patterns.DatabaseConfig;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PropertyRepositoryImpl implements PropertyRepository {

    @Override
    public List<Property> getAll() {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT * FROM properties";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String type = rs.getString("type");
                if ("house".equalsIgnoreCase(type)) {
                    properties.add(House.builder()
                            .id(rs.getInt("id")).address(rs.getString("address"))
                            .price(rs.getDouble("price")).yardSize(rs.getInt("yard_size"))
                            .type("house").build());
                } else {
                    properties.add(Apartment.builder()
                            .id(rs.getInt("id")).address(rs.getString("address"))
                            .price(rs.getDouble("price")).floorNumber(rs.getInt("floor_number"))
                            .type("apartment").build());
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return properties;
    }

    @Override
    public void add(Property property) {
        String sql = "INSERT INTO properties (address, price, type, yard_size, floor_number) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, property.getAddress());
            pstmt.setDouble(2, property.getPrice());
            pstmt.setString(3, property.getType());
            if (property instanceof House) {
                pstmt.setInt(4, ((House) property).getYardSize());
                pstmt.setNull(5, Types.INTEGER);
            } else {
                pstmt.setNull(4, Types.INTEGER);
                pstmt.setInt(5, ((Apartment) property).getFloorNumber());
            }
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void update(int id, Property property) {
        String sql = "UPDATE properties SET address = ?, price = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, property.getAddress());
            pstmt.setDouble(2, property.getPrice());
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM properties WHERE id = ?";
        try (Connection conn = DatabaseConfig.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
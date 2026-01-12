package com.example.localfarmersemarket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rice")
public class RiceAndRiceProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String rice_name;
    private String rice_quantity;
    private double rice_price;
    private String rice_description;
    private String rice_image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRice_name() {
        return rice_name;
    }

    public void setRice_name(String rice_name) {
        this.rice_name = rice_name;
    }

    public String getRice_quantity() {
        return rice_quantity;
    }

    public void setRice_quantity(String rice_quantity) {
        this.rice_quantity = rice_quantity;
    }

    public double getRice_price() {
        return rice_price;
    }

    public void setRice_price(double rice_price) {
        this.rice_price = rice_price;
    }

    public String getRice_description() {
        return rice_description;
    }

    public void setRice_description(String rice_description) {
        this.rice_description = rice_description;
    }

    public String getRice_image() {
        return rice_image;
    }

    public void setRice_image(String rice_image) {
        this.rice_image = rice_image;
    }
}

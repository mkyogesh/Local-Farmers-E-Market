package com.example.localfarmersemarket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fruits")
public class FreshFruits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String fruits_name;
    private String fruits_quantity;
    private double fruits_price;
    private String fruits_description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFruits_name() {
        return fruits_name;
    }

    public void setFruits_name(String fruits_name) {
        this.fruits_name = fruits_name;
    }

    public String getFruits_quantity() {
        return fruits_quantity;
    }

    public void setFruits_quantity(String fruits_quantity) {
        this.fruits_quantity = fruits_quantity;
    }

    public double getFruits_price() {
        return fruits_price;
    }

    public void setFruits_price(double fruits_price) {
        this.fruits_price = fruits_price;
    }

    public String getFruits_description() {
        return fruits_description;
    }

    public void setFruits_description(String fruits_description) {
        this.fruits_description = fruits_description;
    }

    public String getFruits_image() {
        return fruits_image;
    }

    public void setFruits_image(String fruits_image) {
        this.fruits_image = fruits_image;
    }

    private String fruits_image;


}

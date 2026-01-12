package com.example.localfarmersemarket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exotic")
public class ExoticFruitsAndVeggies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String exotic_name;
    private String exotic_quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExotic_name() {
        return exotic_name;
    }

    public void setExotic_name(String exotic_name) {
        this.exotic_name = exotic_name;
    }

    public String getExotic_quantity() {
        return exotic_quantity;
    }

    public void setExotic_quantity(String exotic_quantity) {
        this.exotic_quantity = exotic_quantity;
    }

    public double getExotic_price() {
        return exotic_price;
    }

    public void setExotic_price(double exotic_price) {
        this.exotic_price = exotic_price;
    }

    public String getExotic_description() {
        return exotic_description;
    }

    public void setExotic_description(String exotic_description) {
        this.exotic_description = exotic_description;
    }

    public String getExotic_image() {
        return exotic_image;
    }

    public void setExotic_image(String exotic_image) {
        this.exotic_image = exotic_image;
    }

    private double exotic_price;
    private String exotic_description;
    private String exotic_image;


}

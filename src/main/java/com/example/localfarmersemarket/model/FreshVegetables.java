package com.example.localfarmersemarket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "veggies")
public class FreshVegetables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String veggies_name;
    private String veggies_quantity;
    private double veggies_price;
    private String veggies_description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVeggies_name() {
        return veggies_name;
    }

    public void setVeggies_name(String veggies_name) {
        this.veggies_name = veggies_name;
    }

    public String getVeggies_quantity() {
        return veggies_quantity;
    }

    public void setVeggies_quantity(String veggies_quantity) {
        this.veggies_quantity = veggies_quantity;
    }

    public double getVeggies_price() {
        return veggies_price;
    }

    public void setVeggies_price(double veggies_price) {
        this.veggies_price = veggies_price;
    }

    public String getVeggies_description() {
        return veggies_description;
    }

    public void setVeggies_description(String veggies_description) {
        this.veggies_description = veggies_description;
    }

    public String getVeggies_image() {
        return veggies_image;
    }

    public void setVeggies_image(String veggies_image) {
        this.veggies_image = veggies_image;
    }

    private String veggies_image;

}

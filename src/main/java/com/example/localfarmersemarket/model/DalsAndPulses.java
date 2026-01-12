package com.example.localfarmersemarket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dalsandpulses")
public class DalsAndPulses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String dals_name;
    private String dals_quantity;
    private double dals_price;
    private String dals_description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDals_name() {
        return dals_name;
    }

    public void setDals_name(String dals_name) {
        this.dals_name = dals_name;
    }

    public String getDals_quantity() {
        return dals_quantity;
    }

    public void setDals_quantity(String dals_quantity) {
        this.dals_quantity = dals_quantity;
    }

    public double getDals_price() {
        return dals_price;
    }

    public void setDals_price(double dals_price) {
        this.dals_price = dals_price;
    }

    public String getDals_description() {
        return dals_description;
    }

    public void setDals_description(String dals_description) {
        this.dals_description = dals_description;
    }

    public String getDals_image() {
        return dals_image;
    }

    public void setDals_image(String dals_image) {
        this.dals_image = dals_image;
    }

    private String dals_image;

}

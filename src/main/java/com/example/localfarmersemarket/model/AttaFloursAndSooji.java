package com.example.localfarmersemarket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "atta_flours")
public class AttaFloursAndSooji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String atta_name;
    private String atta_quantity;
    private double atta_price;
    private String atta_description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAtta_name() {
        return atta_name;
    }

    public void setAtta_name(String atta_name) {
        this.atta_name = atta_name;
    }

    public String getAtta_quantity() {
        return atta_quantity;
    }

    public void setAtta_quantity(String atta_quantity) {
        this.atta_quantity = atta_quantity;
    }

    public double getAtta_price() {
        return atta_price;
    }

    public void setAtta_price(double atta_price) {
        this.atta_price = atta_price;
    }

    public String getAtta_description() {
        return atta_description;
    }

    public void setAtta_description(String atta_description) {
        this.atta_description = atta_description;
    }

    public String getAtta_image() {
        return atta_image;
    }

    public void setAtta_image(String atta_image) {
        this.atta_image = atta_image;
    }

    private String atta_image;

}

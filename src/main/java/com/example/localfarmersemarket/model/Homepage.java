package com.example.localfarmersemarket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "home")
public class Homepage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String smart_basket_name;
    private String smart_basket_quantity;
    private double smart_basket_price;
    private String smart_basket_description;
    private String smart_basket_image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmart_basket_name() {
        return smart_basket_name;
    }

    public void setSmart_basket_name(String smart_basket_name) {
        this.smart_basket_name = smart_basket_name;
    }

    public String getSmart_basket_quantity() {
        return smart_basket_quantity;
    }

    public void setSmart_basket_quantity(String smart_basket_quantity) {
        this.smart_basket_quantity = smart_basket_quantity;
    }

    public double getSmart_basket_price() {
        return smart_basket_price;
    }

    public void setSmart_basket_price(double smart_basket_price) {
        this.smart_basket_price = smart_basket_price;
    }

    public String getSmart_basket_description() {
        return smart_basket_description;
    }

    public void setSmart_basket_description(String smart_basket_description) {
        this.smart_basket_description = smart_basket_description;
    }

    public String getSmart_basket_image() {
        return smart_basket_image;
    }

    public void setSmart_basket_image(String smart_basket_image) {
        this.smart_basket_image = smart_basket_image;
    }
}
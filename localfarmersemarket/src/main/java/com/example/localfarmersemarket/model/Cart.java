package com.example.localfarmersemarket.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cartId;               // product id
    private String cart_name;          // product name
    private String cart_weight;        // 250g / 500g / 1kg

    private int cart_quantity;         // number of packs
    private double cart_price;         // price per pack

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getCart_name() {
        return cart_name;
    }

    public void setCart_name(String cart_name) {
        this.cart_name = cart_name;
    }

    public String getCart_weight() {
        return cart_weight;
    }

    public void setCart_weight(String cart_weight) {
        this.cart_weight = cart_weight;
    }

    public int getCart_quantity() {
        return cart_quantity;
    }

    public void setCart_quantity(int cart_quantity) {
        this.cart_quantity = cart_quantity;
    }

    public double getCart_price() {
        return cart_price;
    }

    public void setCart_price(double cart_price) {
        this.cart_price = cart_price;
    }

    public double getCart_amount() {
        return cart_amount;
    }

    public void setCart_amount(double cart_amount) {
        this.cart_amount = cart_amount;
    }

    public String getCart_image() {
        return cart_image;
    }

    public void setCart_image(String cart_image) {
        this.cart_image = cart_image;
    }

    public String getCartCategory() {
        return cartCategory;
    }

    public void setCartCategory(String cartCategory) {
        this.cartCategory = cartCategory;
    }

    public String getCart_checkout() {
        return cart_checkout;
    }

    public void setCart_checkout(String cart_checkout) {
        this.cart_checkout = cart_checkout;
    }

    private double cart_amount;        // cart_price * cart_quantity

    private String cart_image;
    private String cartCategory;       // atta / dals / exotic / etc
    private String cart_checkout;      // YES / NO

}

package com.example.localfarmersemarket.repository;

import com.example.localfarmersemarket.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
    public Cart findByCartIdAndCartCategory(Long cartId, String cartCategory);
}
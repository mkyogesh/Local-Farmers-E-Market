package com.example.localfarmersemarket.repository;

import com.example.localfarmersemarket.model.FreshFruits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitsRepo extends JpaRepository<FreshFruits,Long> {
}

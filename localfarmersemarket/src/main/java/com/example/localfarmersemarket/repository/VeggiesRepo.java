package com.example.localfarmersemarket.repository;

import com.example.localfarmersemarket.model.FreshVegetables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeggiesRepo extends JpaRepository<FreshVegetables,Long> {
}

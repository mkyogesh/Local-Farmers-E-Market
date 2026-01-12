package com.example.localfarmersemarket.repository;

import com.example.localfarmersemarket.model.ExoticFruitsAndVeggies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExoticRepo extends JpaRepository<ExoticFruitsAndVeggies,Long> {
}

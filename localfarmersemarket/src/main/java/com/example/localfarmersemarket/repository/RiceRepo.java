package com.example.localfarmersemarket.repository;

import com.example.localfarmersemarket.model.RiceAndRiceProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiceRepo extends JpaRepository<RiceAndRiceProducts,Long> {
}

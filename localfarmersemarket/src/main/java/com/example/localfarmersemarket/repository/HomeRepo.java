package com.example.localfarmersemarket.repository;

import com.example.localfarmersemarket.model.Homepage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeRepo extends JpaRepository<Homepage,Long> {
}

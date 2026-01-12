package com.example.localfarmersemarket.repository;

import com.example.localfarmersemarket.model.DalsAndPulses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DalsRepo extends JpaRepository<DalsAndPulses,Long> {
}

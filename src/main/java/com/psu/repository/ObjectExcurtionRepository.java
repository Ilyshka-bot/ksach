package com.psu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.psu.entity.ObjectExcursion;

public interface ObjectExcurtionRepository extends JpaRepository<ObjectExcursion, Long> {
    ObjectExcursion findObjectExcursionById(Long id);
}

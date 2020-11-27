package com.psu.repository;

import com.psu.entity.ViewExcursion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewExcursionRepository extends JpaRepository<ViewExcursion, Long> {
    ViewExcursion findViewExcursionByTypeName(String name);
}
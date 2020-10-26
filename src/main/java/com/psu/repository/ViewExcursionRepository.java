package com.psu.repository;

import com.psu.entity.Employee;
import com.psu.entity.Role;
import com.psu.entity.User;
import com.psu.entity.ViewExcursion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ViewExcursionRepository extends JpaRepository<ViewExcursion, Long> {
    ViewExcursion findViewExcursionByTypeName(String name);
}
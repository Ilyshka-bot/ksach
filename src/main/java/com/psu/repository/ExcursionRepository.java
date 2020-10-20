package com.psu.repository;

import com.psu.entity.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExcursionRepository  extends JpaRepository<Excursion, Long> {
}

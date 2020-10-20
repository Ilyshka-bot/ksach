package com.psu.repository;

import com.psu.entity.GraphicEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphicRepository  extends JpaRepository<GraphicEmployee, Long> {
    GraphicEmployee findGraphicEmployeeById(Long graphicId);
}

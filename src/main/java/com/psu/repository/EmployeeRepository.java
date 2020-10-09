package com.psu.repository;

import com.psu.entity.Employee;
import com.psu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
    Employee findByUser(User user);
}

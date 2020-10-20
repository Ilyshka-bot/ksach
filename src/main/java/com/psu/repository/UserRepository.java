package com.psu.repository;

import com.psu.entity.Employee;
import com.psu.entity.Role;
import com.psu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findFirstByRoles(Role role);
    User findUserById(Long id);
}
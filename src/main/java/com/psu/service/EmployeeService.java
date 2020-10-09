package com.psu.service;

import com.psu.entity.Employee;
import com.psu.entity.User;
import com.psu.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee getEmployee(User user){
        return employeeRepository.findByUser(user);
    }

    public void deleteEmployee(Employee employee){
        employeeRepository.delete(employee);
    }

}

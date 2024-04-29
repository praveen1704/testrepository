package com.example.retailstoreschedule.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.retailstoreschedule.Entity.AvailabilityPattern;
import com.example.retailstoreschedule.Entity.Department;
import com.example.retailstoreschedule.Entity.Employee;
import com.example.retailstoreschedule.Entity.Role;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findById(Long id);
    
    List<Employee> findByDepartment(Department department);

    List<Employee> findByRoles(Role role);

    List<Employee> findByAvailabilityPatterns(AvailabilityPattern availabilityPattern);
}
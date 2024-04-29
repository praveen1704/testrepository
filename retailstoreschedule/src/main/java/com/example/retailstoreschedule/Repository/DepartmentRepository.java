package com.example.retailstoreschedule.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.retailstoreschedule.Entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
package com.example.retailstoreschedule.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.retailstoreschedule.Entity.AvailabilityPattern;

public interface AvailabilityPatternRepository extends JpaRepository<AvailabilityPattern, Long> {
    AvailabilityPattern findByPattern(String pattern);
}
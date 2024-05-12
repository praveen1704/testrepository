package com.example.retail.Dto;

import java.util.Set;

import com.example.retail.Entity.Role;

import lombok.Data;

@Data
public class EmployeeWithAvailabilitiesDTO {
    private String employeeName;
    private String department;
    private Role role;
    private Set<AvailabilityDTO> availabilities;
}

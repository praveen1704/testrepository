package com.example.retail.Service;

import com.example.retail.Dto.EmployeeWithAvailabilitiesDTO;
import com.example.retail.Entity.Employee;

public interface EmployeeService {
	
	public Employee updateEmployee(Long id, Employee employee);

    public Employee createEmployee(EmployeeWithAvailabilitiesDTO employeeDTO);

}

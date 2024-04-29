package com.example.retailstoreschedule.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.retailstoreschedule.Entity.Employee;
import com.example.retailstoreschedule.Repository.EmployeeRepository;
import com.example.retailstoreschedule.Service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
	    return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
	    return employeeRepository.findById(id).orElse(null);
	}

	@Override
	public Employee createEmployee(Employee employee) {
	    return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
	    Employee existingEmployee = getEmployeeById(id);
	    if (existingEmployee == null) {
	        return null;
	    }
	    existingEmployee.setName(employee.getName());
	    existingEmployee.setDepartment(employee.getDepartment());
	    existingEmployee.setRoles(employee.getRoles());
	    existingEmployee.setAvailabilityPatterns(employee.getAvailabilityPatterns());
	    existingEmployee.setRoles(employee.getRoles());
	    return employeeRepository.save(existingEmployee);
	}

	@Override
	public void deleteEmployee(Long id) {
	    employeeRepository.deleteById(id);
	}
	
}

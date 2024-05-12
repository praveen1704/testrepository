package com.example.retail.ServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import com.example.retail.Dto.AvailabilityDTO;
import com.example.retail.Dto.EmployeeWithAvailabilitiesDTO;
import com.example.retail.Entity.Availability;
import com.example.retail.Entity.Employee;
import com.example.retail.Entity.Role;
import com.example.retail.Repository.DepartmentRepository;
import com.example.retail.Repository.EmployeeRepository;
import com.example.retail.Service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	    @Autowired
	    private EmployeeRepository employeeRepository;
	    
	    @Autowired
	    private DepartmentRepository departmentRepository;
	    
	    
	    @Override
	    public Employee createEmployee(EmployeeWithAvailabilitiesDTO employeeDTO) {
	        validateEmployee(employeeDTO);
	        List<String> uniqueDaysOfWeek = new ArrayList<>();
	        Set<String> allDaysOfWeek = new HashSet<>(); // To track all days of week across employees

	        for (AvailabilityDTO availability : employeeDTO.getAvailabilities()) {
	            for (String dayOfWeek : availability.getDaysOfWeek()) {
	                if (uniqueDaysOfWeek.contains(dayOfWeek)) {
	                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate day of week found in availabilities");
	                }
	                
	                // Check if the day of the week has been seen across all employees
	                if (!allDaysOfWeek.add(dayOfWeek)) {
	                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate day of week found across employees");
	                }
	                
	                uniqueDaysOfWeek.add(dayOfWeek); // Add the day of the week to the unique set for this employee
	            }
	        }
	        uniqueDaysOfWeek.clear();
	        Employee employee = new Employee();
	        employee.setEmployeeName(employeeDTO.getEmployeeName());
	        employee.setDepartment(employeeDTO.getDepartment());
	        employee.setRole(employeeDTO.getRole());
	        List<Availability> availabilities = employeeDTO.getAvailabilities().stream()
	                .map(this::createAvailabilityFromDTO)
	                .collect(Collectors.toList());
	       
	        Employee employeesave= employeeRepository.save(employee);
	        for (Availability availability : availabilities) {
	            availability.setEmployee(employeesave);
	        }
	        employee.setAvailabilities(availabilities);
	        employeeRepository.save(employeesave);
	       return employeesave;
	    }
	    
	    private Availability createAvailabilityFromDTO(AvailabilityDTO availabilityDTO) {
	        Availability availability = new Availability();
	        availability.setStartTime(availabilityDTO.getStartTime());
	        availability.setEndTime(availabilityDTO.getEndTime());
	        availability.setDaysOfWeek(availabilityDTO.getDaysOfWeek());
	        return availability;
	    }

	    private void validateEmployee(EmployeeWithAvailabilitiesDTO employee) {
	        if (!ObjectUtils.isEmpty(employeeRepository.findEMployeeByName(employee.getEmployeeName()))) {
	            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "Employee with the Name already present");
	        }

	        if (ObjectUtils.isEmpty(departmentRepository.findDepartmentByName(employee.getDepartment()))) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Department");
	        }

	        Role role = employee.getRole();
	        if (role == null || role.equals("")) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Role");
	        }

	    }

	 
	   
	    @Override
	    public Employee updateEmployee(Long id, Employee employee) {
	    	Employee existingEmployee = employeeRepository.findById(id)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with the given Id is not found"));
	        
	    	if (departmentRepository.findDepartmentByName(employee.getDepartment()) == null) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Department");
	        }
	        
	        Role role = employee.getRole();
	        if (role == null || role.equals("")) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Role");
	        }
	        existingEmployee.setEmployeeName(employee.getEmployeeName());
	        existingEmployee.setDepartment(employee.getDepartment());
	        existingEmployee.setRole(employee.getRole());
	        List<Availability> newAvailabilities = employee.getAvailabilities();
	        existingEmployee.getAvailabilities().clear();
	        for (Availability availability : newAvailabilities) {
	            availability.setEmployee(existingEmployee);
	            existingEmployee.getAvailabilities().add(availability);
	        }
	        return employeeRepository.save(existingEmployee);
	    }

}



package com.example.retail.ServiceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.retail.Entity.Availability;
import com.example.retail.Entity.Employee;
import com.example.retail.Entity.Schedule;
import com.example.retail.Entity.Shift;
import com.example.retail.Repository.EmployeeRepository;
import com.example.retail.Repository.ScheduleRepository;
import com.example.retail.Service.ScheduleService;

@Service
public class ScheduleServicempl implements ScheduleService{

	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Schedule scheduleShift(Long employeeId, Shift shift, LocalDate date) {
	        Employee employee = employeeRepository.findById(employeeId)
	                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"For the given employee ID there is not Employee present") );

	        if (!isEmployeeAvailable(employee, shift, date)) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Employee is not available for the given shift timing and date");
	        }

       if( scheduleRepository.findEmployeeForTimingOfDate(date,shift.getStartTime() , shift.getEndTime(), employeeId)!=null) {
    	   throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Employee schedule for the date and time is already present");
       }
	        Schedule schedule = new Schedule();
	        schedule.setEmployeeId(employee.getId());
	        schedule.setDate(date);
	        schedule.setStartTime(shift.getStartTime());
	        schedule.setEndTime(shift.getEndTime());

	        return scheduleRepository.save(schedule);
	}
	
	   private boolean isEmployeeAvailable(Employee employee, Shift shift, LocalDate date) {
	        List<Availability> availabilities = employee.getAvailabilities();
	        for (Availability availability : availabilities) {
	            if (availability.getDaysOfWeek().contains(date.getDayOfWeek().name()) &&
	                availability.getStartTime().isBefore(shift.getEndTime()) &&
	                availability.getEndTime().isAfter(shift.getStartTime())) {
	                return true; // Employee is available
	            }
	        }
	        return false; 
	    }

	@Override
	public List<Schedule> getEmployeeSchedule(Long employeeId, LocalDate startDate, LocalDate endDate) {
		
		return scheduleRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate);
	}

	@Override
	public List<Schedule> findSchedulesForEmployee(Long employeeId) {
		if(employeeRepository.findById(employeeId).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The Employee Id given is not present");
		}
		return scheduleRepository.findEmployeeSchedulesById(employeeId);
	}


}

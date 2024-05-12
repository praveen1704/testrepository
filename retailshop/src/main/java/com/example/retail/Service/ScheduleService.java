package com.example.retail.Service;

import java.time.LocalDate;
import java.util.List;

import com.example.retail.Entity.Schedule;
import com.example.retail.Entity.Shift;

public interface ScheduleService {
	
	public Schedule scheduleShift(Long employeeId,Shift shift,LocalDate date);
	public List<Schedule> getEmployeeSchedule(Long employeeId,LocalDate startDate,LocalDate endDate);
    public List<Schedule> findSchedulesForEmployee(Long employeeId);
}

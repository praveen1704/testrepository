package com.example.retailstoreschedule.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.retailstoreschedule.Entity.Schedule;

@Service
public interface ScheduleService {


    Schedule assignShiftToEmployee(Long employeeId, Long shiftId, LocalDate date);

    List<Schedule> getEmployeeSchedule(Long employeeId, LocalDate startDate, LocalDate endDate);
}

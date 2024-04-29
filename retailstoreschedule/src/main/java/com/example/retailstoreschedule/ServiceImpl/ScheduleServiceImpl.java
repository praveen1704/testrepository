package com.example.retailstoreschedule.ServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.retailstoreschedule.Entity.Employee;
import com.example.retailstoreschedule.Entity.Schedule;
import com.example.retailstoreschedule.Entity.Shift;
import com.example.retailstoreschedule.Repository.EmployeeRepository;
import com.example.retailstoreschedule.Repository.ScheduleRepository;
import com.example.retailstoreschedule.Repository.ShiftRepository;
import com.example.retailstoreschedule.Service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    ShiftRepository shiftRepository;

    @Override
    public Schedule assignShiftToEmployee(Long employeeId, Long shiftId, LocalDate date) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        Optional<Shift> shiftOptional = shiftRepository.findById(shiftId);

        if (employeeOptional.isPresent() && shiftOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            Shift shift = shiftOptional.get();

            Schedule schedule = scheduleRepository.findByEmployeeAndShiftAndDate(employee, shift, date);

            if (schedule == null) {
                schedule = new Schedule();
                schedule.setEmployee(employee);
                schedule.setShift(shift);
                schedule.setDate(date);

                return scheduleRepository.save(schedule);
            }
        }

        return null;
    }

    @Override
    public List<Schedule> getEmployeeSchedule(Long employeeId, LocalDate startDate, LocalDate endDate) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            return scheduleRepository.findAllByEmployeeAndDateGreaterThanEqualAndDateLessThanEqual(employee, startDate, endDate);
        }

        return null;
    }
}
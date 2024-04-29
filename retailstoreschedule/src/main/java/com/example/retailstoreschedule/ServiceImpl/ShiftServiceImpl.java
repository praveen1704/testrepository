package com.example.retailstoreschedule.ServiceImpl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.retailstoreschedule.Entity.Shift;
import com.example.retailstoreschedule.Repository.EmployeeRepository;
import com.example.retailstoreschedule.Repository.ShiftRepository;
import com.example.retailstoreschedule.Service.ShiftService;

@Service
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    @Override
    public Shift getShiftById(Long id) {
        return shiftRepository.findById(id).orElse(null);
    }

    @Override
    public Shift createShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public Shift updateShift(Long id, Shift shift) {
        Shift existingShift = getShiftById(id);
        if (existingShift == null) {
            return null;
        }
        existingShift.setStartTime(shift.getStartTime());
        existingShift.setEndTime(shift.getEndTime());
        existingShift.setDepartment(shift.getDepartment());
        existingShift.setRequiredSkills(shift.getRequiredSkills());
        return shiftRepository.save(existingShift);
    }

    @Override
    public void deleteShift(Long id) {
        shiftRepository.deleteById(id);
    }

 
    @Override
    public List<Shift> getShiftForSpecificPeriod(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startTime = startDate.atStartOfDay();
        LocalDateTime endTime = endDate.plusDays(1).atStartOfDay();
        return shiftRepository.findShiftsByPeriod(startTime, endTime);
    }

	
}
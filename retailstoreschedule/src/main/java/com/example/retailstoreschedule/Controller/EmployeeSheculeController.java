package com.example.retailstoreschedule.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.retailstoreschedule.Entity.AssignShiftRequest;
import com.example.retailstoreschedule.Entity.Schedule;
import com.example.retailstoreschedule.Service.EmployeeService;
import com.example.retailstoreschedule.Service.ScheduleService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeSheculeController {

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/{employeeId}/assign-shift")
    public Schedule assignShiftToEmployee(@PathVariable Long employeeId, @RequestBody AssignShiftRequest request) {
        return scheduleService.assignShiftToEmployee(employeeId, request.getShiftId(), request.getDate());
    }
  

    // ... other methods
}
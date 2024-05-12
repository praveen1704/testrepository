package com.example.retail.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.retail.Entity.Schedule;
import com.example.retail.Entity.Shift;
import com.example.retail.Service.ScheduleService;

//ShiftScheduleController.java
@RestController
@RequestMapping("/shift-schedule")
public class ScheduleController {

 @Autowired
 private ScheduleService shiftScheduleService;

 @PostMapping("/{employeeId}")
 public ResponseEntity<?> scheduleShift(
         @PathVariable Long employeeId,
         @RequestBody Shift shift,
         @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
     Schedule schedule = shiftScheduleService.scheduleShift(employeeId, shift, date);
     return ResponseEntity.ok(schedule);
 }

 @GetMapping("/{employeeId}/schedule")
 public ResponseEntity<List<Schedule>> getEmployeeSchedule(
         @PathVariable Long employeeId,
         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
     List<Schedule> schedule = shiftScheduleService.getEmployeeSchedule(employeeId, startDate, endDate);
     return ResponseEntity.ok(schedule);
 }
 
 @GetMapping("/getSchedhulesForEmployee")
 public List<Schedule> getListOfScheduleForEmployee(@RequestParam("id") Long Id){
	return shiftScheduleService.findSchedulesForEmployee(Id);
 }
}

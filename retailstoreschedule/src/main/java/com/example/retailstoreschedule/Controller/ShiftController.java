package com.example.retailstoreschedule.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.retailstoreschedule.Entity.Shift;
import com.example.retailstoreschedule.Service.ShiftService;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @PostMapping
    public ResponseEntity<Shift> createShift(@RequestBody Shift shift) {
        Shift createdShift = shiftService.createShift(shift);
        return new ResponseEntity<>(createdShift, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Shift>> getShifts(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                  @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Shift> shifts = shiftService.getShiftForSpecificPeriod(startDate, endDate);
        return new ResponseEntity<>(shifts, HttpStatus.OK);
    }

}
package com.example.retail.Controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.retail.Entity.Shift;
import com.example.retail.Service.ShiftService;

@RestController
public class ShiftController {
	
	@Autowired
	ShiftService shiftService;
	
	@PostMapping("/addShift")
	public Shift addShift(@RequestBody Shift shift) {
		return shiftService.addShift(shift);
	}
	
	@PutMapping("/updateShift")
	public Shift updateShift(@RequestBody Shift shift) {
	return shiftService.updateshift(shift);
	}
	
	
	@GetMapping("/getShiftByTimings")
	public List<Shift> getShiftByStartEndTime(@RequestParam String starttime,@RequestParam String endTime) {
		LocalTime start=LocalTime.parse(starttime);
		LocalTime end=LocalTime.parse(endTime);
		return shiftService.getShiftByStartEndTIme(start, end);
	}
	
	

}

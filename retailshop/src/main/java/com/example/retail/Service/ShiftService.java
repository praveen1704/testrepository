package com.example.retail.Service;

import java.time.LocalTime;
import java.util.List;

import com.example.retail.Entity.Shift;

public interface ShiftService {
	
	 public Shift addShift(Shift shift);
	 
	 public Shift updateshift(Shift shift);
	 
	 public List<Shift> getShiftByStartEndTIme(LocalTime startTime,LocalTime endTime);

}

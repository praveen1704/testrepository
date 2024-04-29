package com.example.retailstoreschedule.Service;

import java.time.LocalDate;
import java.util.List;

import com.example.retailstoreschedule.Entity.Shift;

public interface ShiftService {
	  List<Shift> getAllShifts();
	    Shift getShiftById(Long id);
	    Shift createShift(Shift shift);
	    Shift updateShift(Long id, Shift shift);
	    void deleteShift(Long id);
	    List<Shift> getShiftForSpecificPeriod(LocalDate startdate,LocalDate endDate);
	  
	
}

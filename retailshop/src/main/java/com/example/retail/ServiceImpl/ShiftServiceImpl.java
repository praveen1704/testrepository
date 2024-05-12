package com.example.retail.ServiceImpl;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.retail.Entity.Shift;
import com.example.retail.Repository.DepartmentRepository;
import com.example.retail.Repository.ShiftRepository;
import com.example.retail.Service.ShiftService;

@Service
public class ShiftServiceImpl implements ShiftService {

	
	@Autowired
	ShiftRepository shiftRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public Shift addShift(Shift shift) {
		if(departmentRepository.findDepartmentByName(shift.getDepartment()) == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the Given  department is not Present !! ");
		}
		if(shiftRepository.getShiftIfPresent(shift.getStartTime(), shift.getEndTime(),shift.getDepartment())==null) {
			shiftRepository.save(shift);
		}else{
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"THe Shift for the department is already present!!");
		}
		return shift;
		
	}

	@Override
	public Shift updateshift(Shift shift) {
		Shift shiftser=shiftRepository.findById(shift.getId()).get();
		if(shiftser!=null) {
			shiftser.setDepartment(shift.getDepartment());
			shiftser.setEndTime(shift.getEndTime());
			shiftser.setStartTime(shift.getStartTime());
			return shiftser;
		}else
		 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Shift Id given is not present !! ");
	}

	@Override
	public List<Shift> getShiftByStartEndTIme(LocalTime startTime, LocalTime endTime) {
		return shiftRepository.getShiftByStartAndEndTime(startTime, endTime);
	}
	
	

}

package com.example.retail.Repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.retail.Entity.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long>{

	@Query(value="Select s from Shift s where s.StartTime >= :starttime AND s.EndTime <= :endTime")
	public List<Shift> getShiftByStartAndEndTime(LocalTime starttime ,LocalTime endTime);

@Query(value="Select s from Shift s where s.StartTime = :starttime AND s.EndTime = :endTime AND s.department= :department")
public Shift getShiftIfPresent(LocalTime starttime ,LocalTime endTime,String department);
	
}
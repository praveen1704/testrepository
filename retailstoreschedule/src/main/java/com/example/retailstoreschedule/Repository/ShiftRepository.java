package com.example.retailstoreschedule.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.retailstoreschedule.Entity.Shift;
@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long>{
	
	@Query("SELECT s FROM Shift s WHERE s.startTime >= :startDate AND s.endTime <= :endDate")
	List<Shift> findShiftsByPeriod(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


	   List<Shift> findByDepartment(String department);

	    List<Shift> findByRequiredSkills(String requiredSkills);
}

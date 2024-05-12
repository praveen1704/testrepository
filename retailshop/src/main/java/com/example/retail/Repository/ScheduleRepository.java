package com.example.retail.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.retail.Entity.Schedule;

//ShiftScheduleRepository.java
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query(value="SELECT e FROM Schedule e WHERE e.date = :scheduleDate AND e.startTime = :starttime AND e.endTime = :endTime AND e.employeeId = :employeeId")
	Schedule findEmployeeForTimingOfDate(@Param("scheduleDate") LocalDate scheduleDate, @Param("starttime") LocalTime starttime, @Param("endTime") LocalTime endTime, @Param("employeeId") Long employeeId);

	
    List<Schedule> findByEmployeeIdAndDateBetween(Long employeeId, LocalDate startDate, LocalDate endDate);
    
    @Query(value="Select e FROM Schedule e where e.employeeId= :id")
    List<Schedule> findEmployeeSchedulesById(Long id);
}

package com.example.retailstoreschedule.Repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.retailstoreschedule.Entity.Employee;
import com.example.retailstoreschedule.Entity.Schedule;
import com.example.retailstoreschedule.Entity.Shift;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findByEmployeeAndShiftAndDate(Employee employee, Shift shift, LocalDate date);

    @Query("SELECT s FROM Schedule s WHERE s.employee = :employee AND s.date >= :startDate AND s.date <= :endDate")
    List<Schedule> findAllByEmployeeAndDateGreaterThanEqualAndDateLessThanEqual(@Param("employee") Employee employee,@Param("startDate") LocalDate startDate, 
            @Param("endDate") LocalDate endDate); 
           
}
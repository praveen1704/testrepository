package com.example.retail.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data 
@Entity
public class Schedule {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
   
    private Long employeeId;
    
    @Column(name="date")
    private LocalDate date;
    
    private LocalTime startTime;
    
    private LocalTime endTime;
	
	
	

}

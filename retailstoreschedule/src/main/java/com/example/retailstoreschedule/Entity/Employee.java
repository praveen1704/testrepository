package com.example.retailstoreschedule.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Employee {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	    private String name;
	    
	    @ManyToOne
	    private Department department;
	    
	    @ManyToMany
	    private List<AvailabilityPattern> availabilityPatterns;
	    
	    @ManyToOne
	    private Role roles;
	    
	    @ManyToMany
	    @JoinTable(name = "employee_shift",
	        joinColumns = @JoinColumn(name = "employee_id"),
	        inverseJoinColumns = @JoinColumn(name = "shift_id"))
	    private List<Shift> shifts;
}
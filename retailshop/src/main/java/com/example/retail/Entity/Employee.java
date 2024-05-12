package com.example.retail.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	private String employeeName;
	
	
	private String department;

    @Enumerated(EnumType.STRING)
	private Role role;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Availability> availabilities;

}

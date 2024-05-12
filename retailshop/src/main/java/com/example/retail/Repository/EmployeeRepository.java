package com.example.retail.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.retail.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	@Query("Select e from Employee e where e.employeeName =:employeeName")
	public Employee findEMployeeByName(@Param(value="employeeName")String employeeName);
	

//	@Query("Select e from Employee e where e.employeeName =:employeeName AND e.department= : department")
//	public Employee findByNameAndDept(@Param("employeeName")String employeename,@Param("department")String department);
	
}

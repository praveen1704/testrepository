package com.example.retail.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.retail.Entity.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	@Query(value="Select d from Department d where d.departmentName= :departmentName")
	Department findDepartmentByName(@Param(value = "departmentName")String departmentName);

}

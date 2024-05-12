package com.example.retail.Service;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.retail.Entity.Department;

public interface DepartmentService {
	

	public Department saveDepartment(@RequestBody Department  department);

}

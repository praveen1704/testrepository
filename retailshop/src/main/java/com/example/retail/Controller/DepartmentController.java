package com.example.retail.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.retail.Entity.Department;
import com.example.retail.Service.DepartmentService;

@RestController
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@PostMapping("/saveDepartment")
	public Department saveDepartment(@RequestBody Department  department) {
		return departmentService.saveDepartment(department);
	}

}

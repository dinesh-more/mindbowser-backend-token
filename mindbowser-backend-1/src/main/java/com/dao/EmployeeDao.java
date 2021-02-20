package com.dao;

import java.util.List;

import com.model.Employee;

public interface EmployeeDao {

	public Employee save(Employee e) throws Exception;
	
	public Employee update(Employee e) throws Exception;
	
	public void delete(Long empid) throws Exception;
	
	public Employee getEmployeeById(Long empid) throws Exception;
	
	public List<Employee> getAllEmployee() throws Exception;

}

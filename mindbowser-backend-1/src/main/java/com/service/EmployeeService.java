package com.service;

import java.util.List;

import com.model.Employee;

public interface EmployeeService {

	public Employee addEmployee(Employee employee) throws Exception;

	public List<Employee> listEmployee() throws Exception;
	
	public Employee getEmployee(Long empid) throws Exception;

	public Employee updateEmployee(Employee employee) throws Exception;

	public void deleteEmployee(Long empid) throws Exception;

}

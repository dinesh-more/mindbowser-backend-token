package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EmployeeDao;
import com.model.Employee;
import com.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee addEmployee(Employee employee) throws Exception {
		return employeeDao.save(employee);
	}

	@Override
	public List<Employee> listEmployee() throws Exception {
		return employeeDao.getAllEmployee();
	}

	@Override
	public Employee getEmployee(Long empid) throws Exception {
		return (Employee) employeeDao.getEmployeeById(empid);
	}

	@Override
	public Employee updateEmployee(Employee employee) throws Exception {
		return employeeDao.update(employee);
	}

	@Override
	public void deleteEmployee(Long empid) throws Exception {
		employeeDao.delete(empid);
	}

}

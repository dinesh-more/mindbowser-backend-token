package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Employee;
import com.model.Manager;
import com.model.UserTokens;
import com.service.EmployeeService;
import com.service.ManagerService;
import com.utility.ErrorConstants;
import com.utility.Response;
import com.utility.Utils;

@RestController
@RequestMapping("/v1")
public class HomeController {

	private static final Logger logger = LogManager.getLogger(HomeController.class);

	@Autowired
	private ManagerService managerService;

	@Autowired
	private EmployeeService employeeService;

	@CrossOrigin
	@PostMapping(value = "/register")
	public Response registerProfile(@RequestBody Manager manager) {
		Response response = new Response();
		try {
			response.setStatus("200");
			response.setMessage("Profile Register Successfully");
			response.setResult(managerService.signup(manager));
			return response;
		} catch (Exception e) {
			logger.error("ERROR IN registerProfile(): ", e);
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(ErrorConstants.SERVER_ERROR);
			return response;
		}
	}

	@CrossOrigin
	@PostMapping(value = "/login")
	public Response loginProfile(@RequestBody Manager manager) {
		Response response = new Response();
		try {
			response.setStatus("200");
			response.setMessage("Profile login Successfully");
			response.setResult(managerService.signin(manager));
			return response;
		} catch (Exception e) {
			logger.error("ERROR IN loginProfile(): ", e);
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(ErrorConstants.SERVER_ERROR);
			return response;
		}
	}

	@CrossOrigin
	@PostMapping(value = "/add-employee")
	public Response addEmployee(@RequestBody Employee employee) {
		Response response = new Response();
		try {
			response.setStatus("200");
			response.setMessage("Registered Successfully");
			response.setResult(employeeService.addEmployee(employee));
			return response;
		} catch (Exception e) {
			logger.error("ERROR IN addEmployee(): ", e);
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
			response.setMessage(ErrorConstants.SERVER_ERROR);
		}
		return response;
	}

	@CrossOrigin
	@GetMapping(value = "/list-employee")
	public Response listEmployee() throws Exception {
		Response response = new Response();
		try {
			response.setResult(employeeService.listEmployee());
			response.setMessage("Employee List");
			response.setStatus("200");
		} catch (Exception e) {
			logger.error("ERROR IN listProfile():", e);
			response.setMessage(ErrorConstants.SERVER_ERROR);
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
		}
		return response;

	}

	@CrossOrigin
	@GetMapping(value = "/employee/{empid}")
	public Response getEmployee(@PathVariable Long empid) {
		Response response = new Response();
		try {
			response.setStatus("200");
			response.setMessage("Employee updated successfully");
			response.setResult(employeeService.getEmployee(empid));
		} catch (Exception e) {
			logger.error("ERROR IN updateEmployee():", e);
			response.setMessage(ErrorConstants.SERVER_ERROR);
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@CrossOrigin
	@PutMapping("/update-employee")
	public Response updateEmployee(@RequestBody Employee employee) {
		Response response = new Response();
		try {
			response.setStatus("200");
			response.setMessage("Employee updated successfully");
			response.setResult(employeeService.updateEmployee(employee));
		} catch (Exception e) {
			logger.error("ERROR IN updateEmployee():", e);
			response.setMessage(ErrorConstants.SERVER_ERROR);
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@CrossOrigin
	@DeleteMapping("/delete-employee/{empid}")
	private Response deleteEmployee(@PathVariable Long empid) {
		Response response = new Response();
		try {
			employeeService.deleteEmployee(empid);
			response.setMessage("Employee deleted successfully");
			response.setStatus("200");
		} catch (Exception e) {
			logger.error("ERROR IN deleteEmployee():", e);
			response.setMessage(ErrorConstants.SERVER_ERROR);
			response.setStatus(ErrorConstants.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}

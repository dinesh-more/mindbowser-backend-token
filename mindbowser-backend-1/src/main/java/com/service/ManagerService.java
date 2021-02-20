package com.service;

import com.model.Manager;

public interface ManagerService {

	public Manager signup(Manager manager) throws Exception;

	public Manager signin(Manager manager) throws Exception;

}

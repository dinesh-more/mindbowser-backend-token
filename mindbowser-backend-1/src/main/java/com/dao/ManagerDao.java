package com.dao;

import java.util.List;

import com.model.Manager;

public interface ManagerDao {

	public Manager save(Manager m) throws Exception;

	public Manager update(Manager m) throws Exception;

	public void delete(Long manid) throws Exception;

	public Manager getManagerById(Long manid) throws Exception;

	public List<Manager> getAllManager() throws Exception;

}

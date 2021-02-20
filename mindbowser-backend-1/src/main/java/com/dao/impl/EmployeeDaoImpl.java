package com.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.dao.EmployeeDao;
import com.model.Employee;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mindbowser_db","root","root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	@Override
	public Employee save(Employee e) throws Exception {
		try {
			Connection con = EmployeeDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into employee(address,city,dob,firstname,lastname,mobile) values (?,?,?,?,?,?)");
			ps.setString(1, e.getAddress());
			ps.setString(2, e.getCity());
			ps.setString(3, e.getDob());
			ps.setString(4, e.getFirstname());
			ps.setString(5, e.getLastname());
			ps.setString(6, e.getMobile());

			ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public Employee update(Employee e) throws Exception {
		try {
			Connection con = EmployeeDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"update employee set address=?,city=?,dob=?,firstname=?,lastname=?,mobile=? where empid=?");
			ps.setString(1, e.getAddress());
			ps.setString(2, e.getCity());
			ps.setString(3, e.getDob());
			ps.setString(4, e.getFirstname());
			ps.setString(5, e.getLastname());
			ps.setString(6, e.getMobile());
			ps.setLong(7, e.getEmpid());

			ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return e;
	}

	@Override
	public void delete(Long empid) throws Exception {
		try {
			Connection con = EmployeeDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from employee where empid=?");
			ps.setLong(1, empid);
			ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Employee getEmployeeById(Long empid) throws Exception {
		Employee e = new Employee();
		try {
			Connection con = EmployeeDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from employee where empid=?");
			ps.setLong(1, empid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e.setEmpid(rs.getLong("empid"));
				e.setFirstname(rs.getString("firstname"));
				e.setLastname(rs.getString("lastname"));
				e.setMobile(rs.getString("mobile"));
				e.setDob(rs.getString("dob"));
				e.setAddress(rs.getString("address"));
				e.setCity(rs.getString("city"));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public List<Employee> getAllEmployee() throws Exception {
		List<Employee> list = new ArrayList<Employee>();
		try {
			Connection con = EmployeeDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from employee");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee e = new Employee();
				e.setEmpid(rs.getLong("empid"));
				e.setFirstname(rs.getString("firstname"));
				e.setLastname(rs.getString("lastname"));
				e.setMobile(rs.getString("mobile"));
				e.setDob(rs.getString("dob"));
				e.setAddress(rs.getString("address"));
				e.setCity(rs.getString("city"));
				list.add(e);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}

package com.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.dao.ManagerDao;
import com.model.Manager;

@Repository
@Transactional
public class ManagerDaoImpl implements ManagerDao {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mindbowser_db", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	@Override
	public Manager save(Manager m) throws Exception {
		Manager man = new Manager();
		try {
			Connection con = ManagerDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into manager(email,firstname,lastname,password,address,dob,company,manid) values (?,?,?,?,?,?,?,?)");
			ps.setString(1, m.getEmail());
			ps.setString(2, m.getFirstname());
			ps.setString(3, m.getLastname());
			ps.setString(4, m.getPassword());
			ps.setString(5, m.getAddress());
			ps.setString(6, m.getDob());
			ps.setString(7, m.getCompany());
			ps.setLong(8, man.getManid());

			ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return m;
	}

	@Override
	public Manager update(Manager m) throws Exception {
		try {
			Connection con = ManagerDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"update employee set email=?,firstname=?,lastname=?,password=?,address=?,dob=?,company=? where manid=?");
			ps.setString(1, m.getEmail());
			ps.setString(2, m.getFirstname());
			ps.setString(3, m.getLastname());
			ps.setString(4, m.getPassword());
			ps.setString(5, m.getAddress());
			ps.setString(6, m.getDob());
			ps.setString(7, m.getCompany());
			ps.setLong(8, m.getManid());

			ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return m;
	}

	@Override
	public void delete(Long manid) throws Exception {
		try {
			Connection con = ManagerDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from manager where manid=?");
			ps.setLong(1, manid);
			ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Manager getManagerById(Long manid) throws Exception {
		Manager m = new Manager();
		try {
			Connection con = ManagerDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from manager where manid=?");
			ps.setLong(1, manid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				m.setEmail(rs.getString("email"));
				m.setFirstname(rs.getString("firstname"));
				m.setLastname(rs.getString("lastname"));
				m.setPassword(rs.getString("password"));
				m.setAddress(rs.getString("address"));
				m.setDob(rs.getString("dob"));
				m.setCompany(rs.getString("company"));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return m;
	}

	@Override
	public List<Manager> getAllManager() throws Exception {
		List<Manager> list = new ArrayList<Manager>();
		try {
			Connection con = ManagerDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from employee");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Manager m = new Manager();
				m.setManid(rs.getLong("manid"));
				m.setEmail(rs.getString("email"));
				m.setFirstname(rs.getString("firstname"));
				m.setLastname(rs.getString("lastname"));
				m.setPassword(rs.getString("password"));
				m.setAddress(rs.getString("address"));
				m.setDob(rs.getString("dob"));
				m.setCompany(rs.getString("company"));
				list.add(m);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}

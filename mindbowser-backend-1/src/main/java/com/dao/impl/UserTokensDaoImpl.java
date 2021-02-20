package com.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.dao.UserTokensDao;
import com.model.UserTokens;

@Repository
@Transactional
public class UserTokensDaoImpl implements UserTokensDao {

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
	public void save(UserTokens tokens) throws Exception {
		try {
			Connection con = UserTokensDaoImpl.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into user_tokens(user_no,token) values (?,?)");
			ps.setLong(1, tokens.getUserNo());
			ps.setString(1, tokens.getToken());

			ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public UserTokens getTokens(String userNo) throws Exception {
		UserTokens u = new UserTokens();
		try {
			Connection con = UserTokensDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from user_tokens where user_no=?");
			ps.setString(1, userNo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u.setUserNo(rs.getLong("user_no"));
				u.setToken(rs.getString("token"));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return u;
	}
	
	@Override
	public UserTokens getToken(String token) throws Exception {
		UserTokens u = new UserTokens();
		try {
			Connection con = UserTokensDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from user_tokens where token=?");
			ps.setString(1, token);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u.setUserNo(rs.getLong("user_no"));
				u.setToken(rs.getString("token"));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return u;
	}

}

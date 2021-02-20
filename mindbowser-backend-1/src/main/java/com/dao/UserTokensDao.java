package com.dao;

import com.model.UserTokens;

public interface UserTokensDao {
	
	public void save(UserTokens tokens) throws Exception;
	
	public UserTokens getTokens(String token) throws Exception;
	
	public UserTokens getToken(String token) throws Exception;
}

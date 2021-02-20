package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ManagerDao;
import com.dao.UserTokensDao;
import com.model.Manager;
import com.model.UserTokens;
import com.service.ManagerService;
import com.utility.Utils;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerDao managerDao;

	@Autowired
	private UserTokensDao userToken;

	@Override
	public Manager signup(Manager manager) throws Exception {
		return managerDao.save(manager);
	}

	@Override
	public Manager signin(Manager manager) throws Exception {
		System.out.println(manager.getEmail() + manager.getPassword());
		Manager checkLogin = new Manager();
		try {
			if (null != checkLogin) {
				managerDao.save(manager);

				String sToken = String.valueOf(manager.getManid());
				UserTokens token = userToken.getTokens(sToken);
				if (null == token) {
					token = new UserTokens();
					token.setUserNo(checkLogin.getManid());
					token.setToken(Utils.randomStr(128));
					userToken.save(token);
				} else {
					token.setToken(Utils.randomStr(128));
					userToken.save(token);
				}
				checkLogin.setToken(token);
//					return checkLogin;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkLogin;
	}

}

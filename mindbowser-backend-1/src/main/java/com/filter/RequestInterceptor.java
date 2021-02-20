package com.filter;

import java.util.Arrays;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dao.UserTokensDao;
import com.model.UserTokens;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	public static final String AUTHENTICATION_HEADER = "Authorization";

	@Autowired
	private UserTokensDao tokensDao;

	String[] allowedUrls = { "/v1/register", "/v1/login", "/v1/add-employee", "/v1/update-employee",
			"/v1/list-employee" };

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept, Authorization");

		if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
			return true;
		}
		String path = request.getServletPath();
		if (Arrays.asList(allowedUrls).indexOf(path) > -1 || path.startsWith("/v1/employee/")
				|| path.startsWith("/v1/delete-employee/")) {

			return true;
		} else {
			String authCredentials = request.getHeader(AUTHENTICATION_HEADER);
			if (null != authCredentials && !authCredentials.isEmpty()) {
				final String encodedUserPassword = authCredentials.replaceFirst("Bearer" + " ", "");
				byte[] decodedBytes = Base64.decodeBase64(encodedUserPassword);
				String usernameAndPassword = new String(decodedBytes, "UTF-8");
				final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
				final String token = tokenizer.nextToken();
				UserTokens user = tokensDao.getToken(token);
				if (null != user) {
					return true;
				} else {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return false;
				}
			} else {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return false;
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg)
			throws Exception {
	}
}

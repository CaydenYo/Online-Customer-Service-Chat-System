package com.pentaKill.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pentaKill.service.CustomerServiceService;




public class CustomerSvcLoginFilter implements Filter {
	private static final String LOGIN_KEYWORD = "login", LOGIN_PAGE = "customerService/Login.jsp";

	public static final String ATTR_ADMINUSER = "customerService";
	public static final String ATTR_NEXTURL = "login.nextUrl";

	public void init(FilterConfig filterConfig) {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		String requestURI = req.getRequestURI();
		String uri = requestURI.substring(requestURI.indexOf("/customerService/"));
		if (uri.indexOf(LOGIN_KEYWORD) == -1) {
			CustomerServiceService user = (CustomerServiceService) session.getAttribute(ATTR_ADMINUSER);
			if (user == null) {
				session.setAttribute(ATTR_NEXTURL, uri);
				String loginUri = req.getContextPath() +"/" +LOGIN_PAGE;
				((HttpServletResponse) response).sendRedirect(loginUri);
				return;
			}
		}

		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}
package com.hoangmn.filter;

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

import com.hoangmn.model.User;

public class AuthenticationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hr = (HttpServletRequest) request;
		HttpServletResponse hs = (HttpServletResponse) response;
		HttpSession session = hr.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null && user.getRole() != null &&
				(user.getRole().equals("user") || user.getRole().equals("admin"))) {
			chain.doFilter(request, response);
		} else {
			session.setAttribute("requestURI", hr.getRequestURI());
			hs.sendRedirect(hr.getContextPath() + "/login");
		}

	}

	public void init(FilterConfig fConfig) {

	}

	public void destroy() {

	}

}

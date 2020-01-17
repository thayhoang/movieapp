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

import com.hoangmn.model.User;
import org.springframework.stereotype.Component;

@Component
public class HomeFilter implements Filter {

	public HomeFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hr = (HttpServletRequest) request;
		HttpServletResponse hs = (HttpServletResponse) response;

		User user = (User) hr.getSession().getAttribute("user");

		if (user != null) {
			if (user.getRole() != null) {
				if (user.getRole().equals("user")) {
					hs.sendRedirect(hr.getContextPath() + "/user");
					return;
				} else if (user.getRole().equals("admin")) {
					hs.sendRedirect(hr.getContextPath() + "/admin");
					return;
				}
			}
		}
		
		hs.sendRedirect(hr.getContextPath() + "/login");
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

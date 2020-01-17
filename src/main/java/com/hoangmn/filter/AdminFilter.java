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
public class AdminFilter implements Filter {

	public AdminFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// System.out.println("Authenticate admin ..........");
		HttpServletRequest hr = (HttpServletRequest) request;
		HttpServletResponse hs = (HttpServletResponse) response;

		User user = (User) hr.getSession().getAttribute("user");

		if (user != null) {
			if (user.getRole() != null) {
				if (user.getRole().equals("admin")) {
					chain.doFilter(request, response);
					return;
				}
			}
		}

		if ("XMLHttpRequest".equals(hr.getHeader("X-Requested-With"))) {
			response.getWriter().write("You are not currently logged in");
			return;
		}
		// System.out.println("Redirect login..........");
		hs.sendRedirect(hr.getContextPath() + "/login");

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

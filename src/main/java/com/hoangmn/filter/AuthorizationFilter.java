package com.hoangmn.filter;

import com.hoangmn.model.User;
import com.hoangmn.util.Util;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hr = (HttpServletRequest) request;
		HttpServletResponse hs = (HttpServletResponse) response;

		User user = (User) hr.getSession().getAttribute("user");
		if ((hr.getServletPath().startsWith("/admin") && Util.isAdmin(user)) ||
				(hr.getServletPath().startsWith("/app") && Util.isUser(user))) {
			chain.doFilter(request, response);
		} else {
			hs.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not Allow");
		}
	}

	public void init(FilterConfig fConfig) {

	}

	public void destroy() {

	}

}

package com.movieswatch.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.movieswatch.entities.User;

/**
 * Servlet Filter implementation class AccountingFilter
 * 
 * @author Younes Moumtaz
 * @version 1.0
 */

@WebFilter("/accounting/*")
public class AccountingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AccountingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp= (HttpServletResponse) response;
		HttpSession session= req.getSession();


		User user = new User();


		if((user= (User) session.getAttribute("currentUser")) !=null) {
			if(user.getRole().getName().equals("Admin") || user.getRole().getName().equals("Accounting")) {
				chain.doFilter(req, resp);
			}else {
				resp.sendRedirect(req.getContextPath() + "/accesDenied.xhtml");
			}
		}else {
			resp.sendRedirect(req.getContextPath() + "/accesDenied.xhtml");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

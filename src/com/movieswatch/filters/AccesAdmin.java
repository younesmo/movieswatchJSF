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

import org.apache.log4j.Logger;

import com.movieswatch.entities.User;


/**
 * 
 * @author Younes Moumtaz
 * @version 1.0
 */
@WebFilter("/admin/*")
public class AccesAdmin implements Filter {
	private static Logger log = Logger.getLogger(AccesAdmin.class);

    /**
     * Default constructor.
     */
    public AccesAdmin() {
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
			if(user.getRole().getName().equals("Admin")) {
				chain.doFilter(req, resp);
			}else {
				log.debug(user.getRole().getName());
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

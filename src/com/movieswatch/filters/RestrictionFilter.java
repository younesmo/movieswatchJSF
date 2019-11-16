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

@WebFilter("/restricted/*")
public class RestrictionFilter implements Filter {


	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response= (HttpServletResponse) resp;


	       /* Non-filtrage des ressources statiques */
        String chemin = request.getRequestURI().substring( request.getContextPath().length() );
        if ( chemin.startsWith( "/resources" ) ) {
            chain.doFilter( request, response );
            return;
        }

		HttpSession session= request.getSession();

		if(session.getAttribute("currentUser") !=null) {
			chain.doFilter(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/login.xhtml");
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}

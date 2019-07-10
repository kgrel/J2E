package com.fis.drzewiecki.wojciech.lbd.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// filter that executes at every request to the server
@WebFilter(filterName = "Request",
			urlPatterns= {"/*"})
public class RequestFilter implements Filter {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		// get headers info and log them
		String sessionId = req.getSession().getId();
		logger.info("session id: " + sessionId);
		
		String userLanguage = req.getHeader("Accept-Language");
		logger.info("Languages accepted: " + userLanguage);
		
		String responseFormat = req.getHeader("Accept");
		logger.info("Accepted responses: " + responseFormat);
		
		
		// measure time that took for request processing
		long start = System.nanoTime();
		
		// do filter has servlet service as last link
		// then it recurrently comes back after sending response to the client
		chain.doFilter(request, response);
		
		long end = System.nanoTime();
		
		logger.info("elapsed request processing time: " + (end-start) + "ns");
	}

}

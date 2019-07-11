package com.fis.drzewiecki.wojciech.lbd.servlet.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet{

	// inject value from properties file
	// microprofile-config
	@Inject
	@ConfigProperty(name = "helloMessage")
	private String message;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		// writer to the response
		final PrintWriter writer = resp.getWriter();
		
		writer.write("Session id");
		writer.write(" : ");
		
		// get session from request, always create if doesn't exist for testing
		HttpSession session = req.getSession();
		if(session == null) {
			writer.write("Session does not exist");
		}
		else {
			writer.write(session.getId());
			// for testing
			session.invalidate();
		}
		writer.write('\n');
		
		// enumeration of all header names in request
		Enumeration<String> headerEnum = req.getHeaderNames();
		
		// until there are headers in request
		while(headerEnum.hasMoreElements()) {
			
			String headerName = headerEnum.nextElement();
			
			// write header to response body
			writer.write(headerName);
			writer.write(" : ");
			writer.write(req.getHeader(headerName));
			writer.write('\n');
		}
		
		writer.flush();
		writer.close();
		
		// Set status code to 200(ok)
		resp.setStatus(HttpServletResponse.SC_OK);
		
	}
}

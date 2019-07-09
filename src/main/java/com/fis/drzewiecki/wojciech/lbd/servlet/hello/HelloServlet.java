package com.fis.drzewiecki.wojciech.lbd.servlet.hello;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		// Set status code to 200(ok) and send message from field 
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.getWriter().write(message);
		resp.getWriter().flush();
		resp.getWriter().close();
		
	}
}

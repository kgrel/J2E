package com.fis.drzewiecki.wojciech.lbd.servlet.survey;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("surveys")
public class SurveyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		// redirect to html file
//		resp.sendRedirect("survey.html");
//		resp.setStatus(HttpServletResponse.SC_FOUND);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		
	}
	
}

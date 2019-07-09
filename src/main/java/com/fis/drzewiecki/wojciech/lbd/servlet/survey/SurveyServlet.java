package com.fis.drzewiecki.wojciech.lbd.servlet.survey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fis.drzewiecki.wojciech.lbd.model.survey.StudyDegree;
import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;
import com.fis.drzewiecki.wojciech.lbd.servlet.commons.Constants;

@WebServlet("surveys")
public class SurveyServlet extends HttpServlet {
	
	@Inject
	private ServletContext context;
	

	@Override
	public void init() throws ServletException{
		context.setAttribute(Constants.listAttributeName, new ArrayList<Survey>());
		
		// Add Counter
		context.setAttribute(Constants.listCounterName, Integer.valueOf(0));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		// redirect to html file
		resp.sendRedirect("surveys.html");
		resp.setStatus(HttpServletResponse.SC_FOUND);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException {
		// construct survey from request attributes
		Survey s = fromAttributesToSurvey(req);
		
		// add survey to list of surveys in context
		List<Survey> surveyList = (List<Survey>)context.getAttribute(Constants.listAttributeName);
		surveyList.add(s);
		
		// increment counter
		Integer counter = (Integer)context.getAttribute(Constants.listCounterName);
		context.setAttribute(Constants.listCounterName, ++counter);
		
		// send string as response
		resp.getWriter().append(s.toString());
		resp.getWriter().append("Counter: " + counter);
		resp.getWriter().flush();
		resp.getWriter().close();
	}
	
	private Survey fromAttributesToSurvey(HttpServletRequest req) {
		Survey returner = new Survey();
		
		String name = (String)req.getParameter("name");
		returner.setName(name);
		
		String surname = (String)req.getParameter("surname");
		returner.setSurname(surname);
		
		String uni = (String)req.getParameter("uni");
		returner.setUniversityName(uni);
		
		String faculty = (String)req.getParameter("faculty");
		returner.setFacultyName(faculty);
		
		// TODO: exception handling
		String degree = (String)req.getParameter("degree");
		// TODO: in setter?
		// Name of enum has to be the same as attribute in surveys.html
		if(degree.equals(StudyDegree.Bachelor.name().toLowerCase())) {
			returner.setStudyDegree(StudyDegree.Bachelor);
		}
		else if(degree.equals(StudyDegree.Master.name().toLowerCase())) {
			returner.setStudyDegree(StudyDegree.Master);
		}
		
		String year = (String)req.getParameter("year");
		returner.setYearOfStudy(Integer.parseInt(year));
		
		String quality = (String)req.getParameter("quality");
		returner.setQualityVote(Integer.parseInt(quality));
		
		String contact = (String)req.getParameter("contact");
		returner.setContactVote(Integer.parseInt(contact));
		
		String inclusion = (String)req.getParameter("inclusion");
		returner.setInclusionVote(Integer.parseInt(inclusion));
		
		return returner;
	}
	
}

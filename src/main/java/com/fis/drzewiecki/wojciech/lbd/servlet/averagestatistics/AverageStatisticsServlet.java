package com.fis.drzewiecki.wojciech.lbd.servlet.averagestatistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;
import com.fis.drzewiecki.wojciech.lbd.model.survey.course.Course;
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysAverageStatistics;
import com.fis.drzewiecki.wojciech.lbd.service.surveys.statistics.SurveysStatisticsService;
import com.fis.drzewiecki.wojciech.lbd.servlet.commons.Constants;

@WebServlet("average-statistics")
public class AverageStatisticsServlet extends HttpServlet{

	@Inject
	private ServletContext context;
	
	@Inject
	private SurveysStatisticsService surveysStatisticsService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// TODO: check for null of list in context
		// when someone tries to perform this GET before init of surveyServlet, list is null
		
		// get uni statistics for surveys in memory
		Map<String, SurveysAverageStatistics> uniStatistics = 
				surveysStatisticsService.
				universityAverageStatistics(
						(List<Survey>)context.getAttribute(Constants.listAttributeName)
						);
		
		resp.getWriter().append(universitiesStatistics(uniStatistics));
		
		resp.getWriter().flush();
		resp.getWriter().close();
	}
	
	private String universitiesStatistics(Map<String, SurveysAverageStatistics> uniStatistics) {
		StringBuilder sb = new StringBuilder();
		
		for(Map.Entry<String, SurveysAverageStatistics> entry : uniStatistics.entrySet()) {
			sb.append(entry.getKey());
			sb.append(":\n");
			sb.append("Average quality vote: ");
			sb.append(entry.getValue().getAverageQualityVote());
			sb.append('\n');
			
			sb.append("Average contact vote: ");
			sb.append(entry.getValue().getAverageContactVote());
			sb.append('\n');
			
			sb.append("Average inclusion vote: ");
			sb.append(entry.getValue().getAverageInclusionVote());
			sb.append('\n');
			
			sb.append('\n');
		}
		
		return sb.toString();
	}

	
}

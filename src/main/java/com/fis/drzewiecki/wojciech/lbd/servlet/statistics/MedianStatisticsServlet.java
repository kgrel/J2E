package com.fis.drzewiecki.wojciech.lbd.servlet.statistics;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysMedianStatistics;
import com.fis.drzewiecki.wojciech.lbd.service.surveys.statistics.SurveysMedianStatisticsService;
import com.fis.drzewiecki.wojciech.lbd.servlet.commons.Constants;

@WebServlet("median-statistics")
public class MedianStatisticsServlet extends HttpServlet{

	@Inject
	private ServletContext context;
	
	@Inject
	private SurveysMedianStatisticsService surveysMedianStatisticsService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// TODO: check for null of list in context
		// when someone tries to perform this GET before init of surveyServlet, list is null
		
		// get uni statistics for surveys in memory
		Map<String, SurveysMedianStatistics> uniStatistics = 
				surveysMedianStatisticsService.
				universityMedianStatistics(
						(List<Survey>)context.getAttribute(Constants.listAttributeName)
						);
		
		// sort by overall Median of uni
		
		
		resp.setContentType("text/html charset=utf-8");
		
		resp.getWriter().append(universitiesStatistics(uniStatistics));
		
		resp.getWriter().flush();
		resp.getWriter().close();
	}
	
	private String universitiesStatistics(Map<String, SurveysMedianStatistics> uniStatistics) {
		StringBuilder sb = new StringBuilder();
		
		for(Map.Entry<String, SurveysMedianStatistics> entry : uniStatistics.entrySet()) {
			sb.append(entry.getKey());
			sb.append(":<br>");
			sb.append("Median quality vote: ");
			sb.append(entry.getValue().getStatisticQualityVote());
			sb.append("<br>");
			
			sb.append("Median contact vote: ");
			sb.append(entry.getValue().getStatisticContactVote());
			sb.append("<br>");
			
			sb.append("Median inclusion vote: ");
			sb.append(entry.getValue().getStatisticInclusionVote());
			sb.append("<br>");
			
			sb.append("<br>");
		}
		
		return sb.toString();
	}

	
}

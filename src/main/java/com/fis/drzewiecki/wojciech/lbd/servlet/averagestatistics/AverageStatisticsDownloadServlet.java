package com.fis.drzewiecki.wojciech.lbd.servlet.averagestatistics;

import java.io.IOException;
import java.io.OutputStream;
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
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysAverageStatistics;
import com.fis.drzewiecki.wojciech.lbd.service.surveys.statistics.SurveysAverageStatisticsService;
import com.fis.drzewiecki.wojciech.lbd.servlet.commons.Constants;

@WebServlet("average-statistics/download")
public class AverageStatisticsDownloadServlet extends HttpServlet{

	@Inject
	private ServletContext context;
	
	@Inject
	private SurveysAverageStatisticsService surveysStatisticsService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/csv");
	    resp.setHeader("Content-Disposition", "attachment; filename=\"averageStatistics.csv\"");
	  
	    OutputStream outputStream = resp.getOutputStream();
	    outputStream.write(csvAverageStatistics().getBytes());
	    outputStream.flush();
	    outputStream.close();
	}
	
	// Return csv string of average results
	private String csvAverageStatistics() {
		
		StringBuilder sb = new StringBuilder();
		
		// header
		sb.append("University Name,Quality of courses & teaching?(Average),Contact with teachers? (Average),Inclusion of work/practical experience?(Average),Uni average,Overall average\n");
				
		// TODO: calculates averages for the second time
		
		// TODO: check for null of list in context
		// when someone tries to perform this GET before init of surveyServlet, list is null
				
		Double overallAverageSum = 0D;
		
		// get uni statistics for surveys in memory
		Map<String, SurveysAverageStatistics> uniStatistics = 
					surveysStatisticsService.
					universityAverageStatistics(
						(List<Survey>)context.getAttribute(Constants.listAttributeName)
					);		

		for(Map.Entry<String, SurveysAverageStatistics> e : uniStatistics.entrySet()) {
			sb.append(e.getKey());
			sb.append(',');
			sb.append(e.getValue().getStatisticQualityVote());
			sb.append(',');
			sb.append(e.getValue().getStatisticContactVote());
			sb.append(',');
			sb.append(e.getValue().getStatisticInclusionVote());
			sb.append(',');
			sb.append(e.getValue().getOverallAverage());
			sb.append('\n');
			
			overallAverageSum += e.getValue().getOverallAverage();
		}
		
		// print overall average
		sb.append(",,,,,");
		sb.append(overallAverageSum/uniStatistics.size());
		
		return sb.toString();
	}
}

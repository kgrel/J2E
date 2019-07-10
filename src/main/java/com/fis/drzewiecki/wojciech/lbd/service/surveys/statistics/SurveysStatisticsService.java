package com.fis.drzewiecki.wojciech.lbd.service.surveys.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;
import com.fis.drzewiecki.wojciech.lbd.model.survey.calculators.SurveyCalculator;
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysAverageStatistics;

@Stateless 
@Local
public class SurveysStatisticsService {
	
	@Inject
	private SurveyCalculator surveyCalculator;
	
	// get average statistics for every university
	public Map<String, SurveysAverageStatistics> universityAverageStatistics(List<Survey> surveys){
		
		// divide surveys by university
		Map<String, List<Survey>> uniSurveysMap = new HashMap();
		for(Survey s : surveys) {
			// get list of all surveys for uni just like in survey s
			List<Survey> uniSurveys = uniSurveysMap.getOrDefault(s.getUniversityName(), new ArrayList<Survey>());
			
			// add this survey
			uniSurveys.add(s);
			
			// Put updated survey list to map
			uniSurveysMap.put(s.getUniversityName(), uniSurveys);
		}
				
		// calculate average for each university
		Map<String, SurveysAverageStatistics> uniStatisticsMap = new HashMap();
		
		// calculate statistics and put to map
		uniSurveysMap.entrySet().forEach(e -> 
			uniStatisticsMap.put(e.getKey(), surveyCalculator.calculateAverageStatistics(e.getValue()))
		);
		
		return uniStatisticsMap;
	}
	

}

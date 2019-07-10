package com.fis.drzewiecki.wojciech.lbd.model.survey.calculators;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysAverageStatistics;

@Stateless
@Local
public class SurveyCalculator {
	
	public SurveyCalculator() {
		
	}
	
	// Get average statistics of all surveys passed in parameter
	public SurveysAverageStatistics calculateAverageStatistics(List<Survey> surveys) {
		SurveysAverageStatistics returner = new SurveysAverageStatistics();
		
		surveys.forEach(s -> returner.addSurvey(s));
		
		return returner;
	}
}

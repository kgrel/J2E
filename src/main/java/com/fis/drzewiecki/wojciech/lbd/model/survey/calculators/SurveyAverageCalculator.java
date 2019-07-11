package com.fis.drzewiecki.wojciech.lbd.model.survey.calculators;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysAverageStatistics;
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysStatistics;

// implementation of surveyCalculator for average statistic
@Stateless
@Local
@Average
public class SurveyAverageCalculator implements SurveyCalculator {
	
	public SurveyAverageCalculator() {
	}
	
	// Get average statistics of all surveys passed in parameter
	@Override
	public SurveysStatistics calculateStatistic(List<Survey> surveys) {
		SurveysStatistics returner = new SurveysAverageStatistics();
		
		surveys.forEach(s -> returner.addSurvey(s));
		
		return returner;
	}
}
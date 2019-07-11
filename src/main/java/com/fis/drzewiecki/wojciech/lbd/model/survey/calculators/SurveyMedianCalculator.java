package com.fis.drzewiecki.wojciech.lbd.model.survey.calculators;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysMedianStatistics;
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysStatistics;

@Stateless
@Local
@Median
public class SurveyMedianCalculator implements SurveyCalculator {

	public SurveyMedianCalculator() {
	}
	
	// Get average statistics of all surveys passed in parameter
	@Override
	public SurveysStatistics calculateStatistic(List<Survey> surveys) {
		SurveysStatistics returner = new SurveysMedianStatistics();
		
		surveys.forEach(s -> returner.addSurvey(s));
		
		return returner;
	}
}

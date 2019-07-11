package com.fis.drzewiecki.wojciech.lbd.model.survey.calculators;

import java.util.List;

import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysStatistics;

// Interface for calculating statistic of surveys
public interface SurveyCalculator {

	// calculate desired statistic for given list of surveys
	SurveysStatistics calculateStatistic(List<Survey> surveys);
}

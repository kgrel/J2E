package com.fis.drzewiecki.wojciech.lbd.service.surveys.statistics;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;
import com.fis.drzewiecki.wojciech.lbd.model.survey.calculators.Median;
import com.fis.drzewiecki.wojciech.lbd.model.survey.calculators.SurveyCalculator;
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysMedianStatistics;

@Dependent //¯\_(ツ)_/¯
public class SurveysMedianStatisticsService extends SurveysStatisticsService {
	@Inject
	public SurveysMedianStatisticsService(@Median SurveyCalculator calculator) {
		super(calculator);
	}
	
	// get median statistics for every university
	public Map<String, SurveysMedianStatistics> universityMedianStatistics(List<Survey> surveys){
		return this.universityStatistics(surveys).entrySet().
				stream().
				collect(Collectors.
						toMap(
								e -> e.getKey(), 
								e -> (SurveysMedianStatistics)e.getValue()
								)
						);
	}
}

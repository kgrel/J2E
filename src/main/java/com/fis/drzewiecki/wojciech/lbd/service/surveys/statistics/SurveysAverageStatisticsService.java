package com.fis.drzewiecki.wojciech.lbd.service.surveys.statistics;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;
import com.fis.drzewiecki.wojciech.lbd.model.survey.calculators.Average;
import com.fis.drzewiecki.wojciech.lbd.model.survey.calculators.SurveyCalculator;
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysAverageStatistics;

//@Stateless 
//@Local
@Dependent
public class SurveysAverageStatisticsService extends SurveysStatisticsService {
	
	@Inject
	public SurveysAverageStatisticsService(@Average SurveyCalculator calculator) {
		super(calculator);
	}
	
	// get average statistics for every university
	public Map<String, SurveysAverageStatistics> universityAverageStatistics(List<Survey> surveys){
		return this.universityStatistics(surveys).entrySet().
				stream().
				collect(Collectors.
						toMap(
								e -> e.getKey(), 
								e -> (SurveysAverageStatistics)e.getValue()
								)
						);
	}
}

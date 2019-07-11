package com.fis.drzewiecki.wojciech.lbd.service.surveys.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;
import com.fis.drzewiecki.wojciech.lbd.model.survey.calculators.SurveyCalculator;
import com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics.SurveysStatistics;

// base class for filtering and applying calculations based on what child class injects
public abstract class SurveysStatisticsService {

	protected SurveysStatisticsService(SurveyCalculator calculator) {
		this.calculator = calculator;
	}
	
	protected SurveyCalculator calculator;
	
	protected Map<String, SurveysStatistics> universityStatistics(List<Survey> surveys){

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
				
		// calculate statistic for each university
		Map<String, SurveysStatistics> uniStatisticsMap = new HashMap();
		
		// calculate statistics and put to map
		uniSurveysMap.entrySet().forEach(e -> 
			uniStatisticsMap.put(e.getKey(), calculator.calculateStatistic(e.getValue()))
		);
		
		return sortByValue(uniStatisticsMap);
	}
	
	private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}

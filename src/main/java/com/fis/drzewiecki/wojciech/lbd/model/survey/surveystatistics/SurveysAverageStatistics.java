package com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics;

import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;

// Class that calculates average statistics of surveys passed in 'on the go'
public class SurveysAverageStatistics {
	private Double averageQualityVote;
	private Double averageContactVote;
	private Double averageInclusionVote;
	
	private Integer surveysCount;
	
	public SurveysAverageStatistics() {
		averageContactVote = 0D;
		averageInclusionVote = 0D;
		averageQualityVote = 0D;
		
		surveysCount = 0;
	}

	public Double getAverageQualityVote() {
		return averageQualityVote;
	}

	public Double getAverageContactVote() {
		return averageContactVote;
	}

	public Double getAverageInclusionVote() {
		return averageInclusionVote;
	}

	public void addSurvey(Survey s) {
		// Calculate new average for previous average and new survey
		averageContactVote = ((surveysCount * averageContactVote) + s.getContactVote())/(surveysCount + 1);
		averageInclusionVote = ((surveysCount * averageInclusionVote) + s.getInclusionVote())/(surveysCount + 1);
		averageQualityVote = ((surveysCount * averageQualityVote) + s.getQualityVote())/(surveysCount + 1);
		
		// new survey added
		surveysCount++;
	}
	
}

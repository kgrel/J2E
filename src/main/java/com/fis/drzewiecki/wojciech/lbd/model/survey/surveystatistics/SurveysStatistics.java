package com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics;

import com.fis.drzewiecki.wojciech.lbd.model.survey.Survey;

// Class that calculates average statistics of surveys passed in 'on the go'
public abstract class SurveysStatistics implements Comparable<SurveysStatistics> {
	protected Double statisticQualityVote;
	protected Double statisticContactVote;
	protected Double statisticInclusionVote;
	
	protected Integer surveysCount;
	
	public SurveysStatistics() {
		statisticContactVote = 0D;
		statisticInclusionVote = 0D;
		statisticQualityVote = 0D;
		
		surveysCount = 0;
	}
	
	// return current values of desired statistics
	protected abstract Double calculateStatisticQualityVote();
	protected abstract Double calculateStatisticContactVote(); 
	protected abstract Double calculateStatisticInclusionVote();
	
	// add next value to statistic
	// remember that survey count is added automatically after method call!!!
	protected abstract void addQualityVote(Integer quality);
	protected abstract void addContactVote(Integer contact);
	protected abstract void addInclusionVote(Integer inclusion);

	public Double getStatisticQualityVote() {
		return calculateStatisticQualityVote();
	}

	public Double getStatisticContactVote() {
		return calculateStatisticContactVote();
	}

	public Double getStatisticInclusionVote() {
		return calculateStatisticInclusionVote();
	}
	
	public Double getOverallAverage() {
		return (getStatisticContactVote() + getStatisticInclusionVote() + getStatisticQualityVote()) / 3;
	}

	public void addSurvey(Survey s) {
		// Calculate new average for previous average and new survey
		addContactVote(s.getContactVote());
		addInclusionVote(s.getInclusionVote());
		addQualityVote(s.getQualityVote());
		
		// new survey added
		surveysCount++;
	}

	// return inverse (descending sort)
	@Override
	public int compareTo(SurveysStatistics o) {
		return -this.getOverallAverage().compareTo(o.getOverallAverage());
	}
	
}

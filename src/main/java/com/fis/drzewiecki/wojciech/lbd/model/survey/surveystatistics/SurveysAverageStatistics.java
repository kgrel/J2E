package com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics;

// holds average statistics of surveys
public class SurveysAverageStatistics extends SurveysStatistics {
	
	public SurveysAverageStatistics() {
	}

	@Override
	protected Double calculateStatisticQualityVote() {
		return statisticQualityVote;
	}

	@Override
	protected Double calculateStatisticContactVote() {
		return statisticContactVote;
	}

	@Override
	protected Double calculateStatisticInclusionVote() {
		return statisticInclusionVote;
	}

	@Override
	protected void addQualityVote(Integer quality) {
		statisticQualityVote = ((statisticQualityVote * surveysCount) + quality)/(surveysCount + 1);
	}

	@Override
	protected void addContactVote(Integer contact) {
		statisticContactVote = ((statisticContactVote * surveysCount) + contact)/(surveysCount + 1);
	}

	@Override
	protected void addInclusionVote(Integer inclusion) {
		statisticInclusionVote = ((statisticInclusionVote * surveysCount) + inclusion)/(surveysCount + 1);
	}

}

package com.fis.drzewiecki.wojciech.lbd.model.survey.surveystatistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SurveysMedianStatistics extends SurveysStatistics {
	
	List<Integer> qualities;
	List<Integer> contacts;
	List<Integer> inclusions;
	
	public SurveysMedianStatistics() {
		qualities = new ArrayList();
		contacts = new ArrayList();
		inclusions = new ArrayList();
	}

	@Override
	protected Double calculateStatisticQualityVote() {
		return median(qualities);
	}

	@Override
	protected Double calculateStatisticContactVote() {
		return median(contacts);
	}

	@Override
	protected Double calculateStatisticInclusionVote() {
		return median(inclusions);
	}

	@Override
	protected void addQualityVote(Integer quality) {
		qualities.add(quality);
	}

	@Override
	protected void addContactVote(Integer contact) {
		contacts.add(contact);
	}

	@Override
	protected void addInclusionVote(Integer inclusion) {
		inclusions.add(inclusion);
	}

	private Double median(List<Integer> list) {
		Collections.sort(list);
		double median;
		if (list.size() % 2 == 0)
		    median = ((double)list.get(list.size()/2) + (double)list.get(list.size()/2 - 1))/2;
		else
		    median = (double) list.get(list.size()/2);
		
		return median;
	}
}

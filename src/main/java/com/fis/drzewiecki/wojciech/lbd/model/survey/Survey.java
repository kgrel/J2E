package com.fis.drzewiecki.wojciech.lbd.model.survey;

import com.fis.drzewiecki.wojciech.lbd.model.survey.course.Course;

public class Survey {
	private String name;
	private String surname;
	private Course course;
	private Integer yearOfStudy;
	private Integer qualityVote;
	private Integer contactVote;
	private Integer inclusionVote;
	
	
	public Survey() {
		this.course = new Course();
	}
	
	@Override
	public String toString() {
		// construct response
		StringBuilder sb = new StringBuilder();
			
		sb.append("Thanks ");
		sb.append(this.getName());
		sb.append(" ");
		sb.append(this.getSurname());
		sb.append(" for participation in our university survey.\n");
		sb.append("Your answers:\n");
		sb.append(this.getName());
		sb.append(" ");
		sb.append(this.getSurname());
		sb.append("\n");
		sb.append(this.getUniversityName());
		sb.append("\n");
		sb.append(this.getFacultyName());
		sb.append("\n");
		sb.append(this.getStudyDegree().name());
		sb.append("\n");
		sb.append("Quality of courses & teaching: ");
		sb.append(this.getQualityVote());
		sb.append("\n");
		sb.append("Contact with teachers: ");
		sb.append(this.getContactVote());
		sb.append("\n");
		sb.append("Inclusion of work/practical teaching: ");
		sb.append(this.getInclusionVote());
		sb.append("\n");
		
		return sb.toString();
				
	}
	
	public Integer getInclusionVote() {
		return inclusionVote;
	}
	public void setInclusionVote(Integer inclusionVote) {
		this.inclusionVote = inclusionVote;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUniversityName() {
		return this.course.getUniversityName();
	}
	public void setUniversityName(String universityName) {
		this.course.setUniversityName(universityName);
	}
	public String getFacultyName() {
		return this.course.getFacultyName();
	}
	public void setFacultyName(String facultyName) {
		this.course.setFacultyName(facultyName);
	}
	public Integer getYearOfStudy() {
		return yearOfStudy;
	}
	public void setYearOfStudy(Integer yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	public Integer getQualityVote() {
		return qualityVote;
	}
	public void setQualityVote(Integer qualityVote) {
		this.qualityVote = qualityVote;
	}
	public Integer getContactVote() {
		return contactVote;
	}
	public void setContactVote(Integer contactVote) {
		this.contactVote = contactVote;
	}
	public StudyDegree getStudyDegree() {
		return this.course.getStudyDegree();
	}
	public void setStudyDegree(StudyDegree studyDegree) {
		this.course.setStudyDegree(studyDegree);
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}

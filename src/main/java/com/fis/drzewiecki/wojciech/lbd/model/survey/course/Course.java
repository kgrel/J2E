package com.fis.drzewiecki.wojciech.lbd.model.survey.course;

import com.fis.drzewiecki.wojciech.lbd.model.survey.StudyDegree;

public class Course {
	private String universityName;
	private String facultyName;
	private StudyDegree studyDegree;
	
	public Course() {
		
	}
	
	public Course(String universityName, String facultyName, StudyDegree studyDegree) {
		super();
		this.universityName = universityName;
		this.facultyName = facultyName;
		this.studyDegree = studyDegree;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) 
            return true; 
          
        if(obj == null || obj.getClass()!= this.getClass()) 
            return false; 
        
        Course c = (Course)obj;
        
        return 	this.facultyName.equals(c.getFacultyName()) &&
        		this.studyDegree.equals(c.getStudyDegree()) &&
        		this.universityName.equals(c.getUniversityName());
	}


	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public StudyDegree getStudyDegree() {
		return studyDegree;
	}
	public void setStudyDegree(StudyDegree studyDegree) {
		this.studyDegree = studyDegree;
	}
	
	
}

package com.senoops.model;

public class ESection {
	private Integer id;

	private Integer courseId;

	private String sectionName;

	public Integer getId() {
		return id;
	}

	public ESection setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public ESection setCourseId(Integer courseId) {
		this.courseId = courseId;
		return this;
	}

	public String getSectionName() {
		return sectionName;
	}

	public ESection setSectionName(String sectionName) {
		this.sectionName = sectionName == null ? null : sectionName.trim();
		return this;
	}
}
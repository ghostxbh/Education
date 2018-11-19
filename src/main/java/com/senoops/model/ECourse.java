package com.senoops.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ECourse {
	private Integer id;

	private String courseName;

	private String courseInfo;

	private String photoUrl;

	private String thumPhotoUrl;

	private String lecturer;

	private String lecturerPortraitUrl;

	private String period;

	private Double price;

	private String free;

	private String status;

	private Integer salesVolume;

	private Date insertTime;

	private String createPerson;

	private Integer categoryId;

	public Integer getId() {
		return id;
	}

	public ECourse setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getCourseName() {
		return courseName;
	}

	public ECourse setCourseName(String courseName) {
		this.courseName = courseName == null ? null : courseName.trim();
		return this;
	}

	public String getCourseInfo() {
		return courseInfo;
	}

	public ECourse setCourseInfo(String courseInfo) {
		this.courseInfo = courseInfo == null ? null : courseInfo.trim();
		return this;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public ECourse setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl == null ? null : photoUrl.trim();
		return this;
	}

	public String getThumPhotoUrl() {
		return thumPhotoUrl;
	}

	public ECourse setThumPhotoUrl(String thumPhotoUrl) {
		this.thumPhotoUrl = thumPhotoUrl == null ? null : thumPhotoUrl.trim();
		return this;
	}

	public String getLecturer() {
		return lecturer;
	}

	public ECourse setLecturer(String lecturer) {
		this.lecturer = lecturer == null ? null : lecturer.trim();
		return this;
	}

	public String getLecturerPortraitUrl() {
		return lecturerPortraitUrl;
	}

	public ECourse setLecturerPortraitUrl(String lecturerPortraitUrl) {
		this.lecturerPortraitUrl = lecturerPortraitUrl == null ? null : lecturerPortraitUrl.trim();
		return this;
	}

	public String getPeriod() {
		return period;
	}

	public ECourse setPeriod(String period) {
		this.period = period == null ? null : period.trim();
		return this;
	}

	public Double getPrice() {
		return price;
	}

	public ECourse setPrice(Double price) {
		this.price = price;
		return this;
	}

	public String getFree() {
		return free;
	}

	public ECourse setFree(String free) {
		this.free = free == null ? null : free.trim();
		return this;
	}

	public String getStatus() {
		return status;
	}

	public ECourse setStatus(String status) {
		this.status = status == null ? null : status.trim();
		return this;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public ECourse setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
		return this;
	}

	public String getInsertTime() {
		if(insertTime != null){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
			return time.format(insertTime);
		}
		return null;
	}

	public ECourse setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
		return this;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public ECourse setCreatePerson(String createPerson) {
		this.createPerson = createPerson == null ? null : createPerson.trim();
		return this;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public ECourse setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	@Override
	public String toString() {
		return "ECourse [id=" + id + ", courseName=" + courseName + ", courseInfo=" + courseInfo + ", photoUrl="
				+ photoUrl + ", thumPhotoUrl=" + thumPhotoUrl + ", lecturer=" + lecturer + ", lecturerPortraitUrl="
				+ lecturerPortraitUrl + ", period=" + period + ", price=" + price + ", free=" + free + ", status="
				+ status + ", salesVolume=" + salesVolume + ", insertTime=" + insertTime + ", createPerson="
				+ createPerson + ", categoryId=" + categoryId + "]";
	}
	
	
}
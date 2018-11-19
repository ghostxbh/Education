package com.senoops.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EVideo {
	private Integer id;

	private Integer sectionId;

	private String videoName;

	private String videoUrl;

	private String coverUrl;

	private Date insertTime;

	public Integer getId() {
		return id;
	}

	public EVideo setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public EVideo setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
		return this;
	}

	public String getVideoName() {
		return videoName;
	}

	public EVideo setVideoName(String videoName) {
		this.videoName = videoName == null ? null : videoName.trim();
		return this;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public EVideo setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl == null ? null : videoUrl.trim();
		return this;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public EVideo setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl == null ? null : coverUrl.trim();
		return this;
	}

	public String getInsertTime() {
		if(insertTime != null){
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
			return time.format(insertTime);
		}
		return null;
	}

	public EVideo setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
		return this;
	}

	@Override
	public String toString() {
		return "EVideo [id=" + id + ", sectionId=" + sectionId + ", videoName=" + videoName + ", videoUrl=" + videoUrl
				+ ", coverUrl=" + coverUrl + ", insertTime=" + insertTime + "]";
	}
	
}
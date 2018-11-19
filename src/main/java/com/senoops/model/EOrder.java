package com.senoops.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EOrder {
	private Integer id;

	private String code;

	private String receiver;

	private String mobile;

	private String address;

	private String payWay;

	private String status;

	private Date insertTime;

	private Integer courseId;

	private Integer userId;

	public Integer getId() {
		return id;
	}

	public EOrder setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getCode() {
		return code;
	}

	public EOrder setCode(String code) {
		this.code = code == null ? null : code.trim();
		return this;
	}

	public String getReceiver() {
		return receiver;
	}

	public EOrder setReceiver(String receiver) {
		this.receiver = receiver == null ? null : receiver.trim();
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public EOrder setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
		return this;
	}

	public String getAddress() {
		return address;
	}

	public EOrder setAddress(String address) {
		this.address = address == null ? null : address.trim();
		return this;
	}

	public String getPayWay() {
		return payWay;
	}

	public EOrder setPayWay(String payWay) {
		this.payWay = payWay == null ? null : payWay.trim();
		return this;
	}

	public String getStatus() {
		return status;
	}

	public EOrder setStatus(String status) {
		this.status = status == null ? null : status.trim();
		return this;
	}

	public String getInsertTime() {
		if (insertTime != null) {
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
			return time.format(insertTime);
		}
		return null;
	}

	public EOrder setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
		return this;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public EOrder setCourseId(Integer courseId) {
		this.courseId = courseId;
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public EOrder setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
}
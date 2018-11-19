package com.senoops.model;

public class ERole {
	private Integer id;

	private Integer userId;

	private String role;

	private String userCode;

	private String userName;

	private String userPhone;
	
	public Integer getId() {
		return id;
	}

	public ERole setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getUserId() {
		return userId;
	}

	public ERole setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public String getRole() {
		return role;
	}

	public ERole setRole(String role) {
		this.role = role == null ? null : role.trim();
		return this;
	}

	public String getUserCode() {
		return userCode;
	}

	public ERole setUserCode(String userCode) {
		this.userCode = userCode;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public ERole setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public ERole setUserPhone(String userPhone) {
		this.userPhone = userPhone;
		return this;
	}

}
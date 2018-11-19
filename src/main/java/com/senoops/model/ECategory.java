package com.senoops.model;

public class ECategory {
	private Integer id;

	private String name;

	public Integer getId() {
		return id;
	}

	public ECategory setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public ECategory setName(String name) {
		this.name = name == null ? null : name.trim();
		return this;
	}
}
package com.user.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Motorbike {
	private String brand;
	private String model;
	private int userId;
	public Motorbike() {
		super();
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}

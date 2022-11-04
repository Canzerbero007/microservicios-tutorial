package com.user.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {

	private String brand;
	private String model;
	private int userId;
	public Car() {
		super();
	}
}

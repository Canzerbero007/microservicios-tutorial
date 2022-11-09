package com.car.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.car.service.entity.Car;
import com.car.service.service.CarService;

@RefreshScope
@Controller
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;
	
	@GetMapping
	public ResponseEntity<List<Car>> listCar() {
		List<Car> listCar = carService.getAll();
		if (listCar.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listCar);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Car> findCarById(@PathVariable("id") int id) {
		Car car = carService.getCarById(id);
		if (car == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(car);
	}

	@PostMapping
	public ResponseEntity<Car> saveCar(@RequestBody Car car) {
		Car newCar = carService.save(car);
		return ResponseEntity.ok(newCar);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Car>> listCarByUserId(@PathVariable("id") int id){
		List<Car> listCar = carService.byUserId(id);
		if (listCar.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listCar);
	}
}

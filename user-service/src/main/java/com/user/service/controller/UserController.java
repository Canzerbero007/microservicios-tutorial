package com.user.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.entity.User;
import com.user.service.model.Car;
import com.user.service.model.Motorbike;
import com.user.service.service.UserService;

@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> listUser() {
		List<User> listUser = userService.getAll();
		if (listUser.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listUser);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findUserById(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User newUser = userService.save(user);
		return ResponseEntity.ok(newUser);
	}

	@GetMapping("/car/{id}")
	public ResponseEntity<List<Car>> getListCar(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		List<Car> listCar = userService.getCar(id);
		return ResponseEntity.ok(listCar);
	}

	@GetMapping("/motorbike/{id}")
	public ResponseEntity<List<Motorbike>> getListMotorbike(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		List<Motorbike> listMotorbike = userService.getMotorbike(id);
		return ResponseEntity.ok(listMotorbike);
	}

	@PostMapping("/car/{userId}")
	public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car) {
		Car newCar = userService.saveCar(userId, car);
		return ResponseEntity.ok(newCar);
	}
	
	@PostMapping("/motorbike/{userId}")
	public ResponseEntity<Motorbike> saveMotorbike(@PathVariable("userId") int userId, @RequestBody Motorbike motorbike) {
		Motorbike newMotorbike = userService.saveMotorbike(userId, motorbike);
		return ResponseEntity.ok(newMotorbike);
	}
	
	@GetMapping("/all/{userId}")
	public ResponseEntity<Map<String, Object>> listAllVehicle(@PathVariable("userId") int userId){
		Map<String, Object> result = userService.getUserAndVehicle(userId);
		return ResponseEntity.ok(result);
	}
	
}

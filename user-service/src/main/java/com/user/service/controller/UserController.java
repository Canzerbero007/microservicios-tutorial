package com.user.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

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

	@CircuitBreaker(name = "carCB", fallbackMethod = "fallBackGetCar")
	@GetMapping("/car/{id}")
	public ResponseEntity<List<Car>> getListCar(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		List<Car> listCar = userService.getCar(id);
		return ResponseEntity.ok(listCar);
	}

	@CircuitBreaker(name = "motorbikeCB", fallbackMethod = "fallBackGetMotorbike")
	@GetMapping("/motorbike/{id}")
	public ResponseEntity<List<Motorbike>> getListMotorbike(@PathVariable("id") int id) {
		User user = userService.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		List<Motorbike> listMotorbike = userService.getMotorbike(id);
		return ResponseEntity.ok(listMotorbike);
	}

	@CircuitBreaker(name = "motorbikeCB", fallbackMethod = "fallBackSaveCar")
	@PostMapping("/car/{userId}")
	public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car) {
		Car newCar = userService.saveCar(userId, car);
		return ResponseEntity.ok(newCar);
	}

	@CircuitBreaker(name = "motorbikeCB", fallbackMethod = "fallBackSaveMotorbike")
	@PostMapping("/motorbike/{userId}")
	public ResponseEntity<Motorbike> saveMotorbike(@PathVariable("userId") int userId,
			@RequestBody Motorbike motorbike) {
		Motorbike newMotorbike = userService.saveMotorbike(userId, motorbike);
		return ResponseEntity.ok(newMotorbike);
	}

	@CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAll")
	@GetMapping("/all/{userId}")
	public ResponseEntity<Map<String, Object>> listAllVehicle(@PathVariable("userId") int userId) {
		Map<String, Object> result = userService.getUserAndVehicle(userId);
		return ResponseEntity.ok(result);
	}

	private ResponseEntity<List<Car>> fallBackGetCar(@PathVariable("userId") int userId, RuntimeException exception) {
		return new ResponseEntity("El usuario : " + userId + " tiene los carros en el taller", HttpStatus.OK);
	}

	private ResponseEntity<Car> fallBackSaveCar(@PathVariable("userId") int userId, @RequestBody Car car,
			RuntimeException exception) {
		return new ResponseEntity("El usuario : " + userId + " no tiene dinero para los carros en el taller",
				HttpStatus.OK);
	}

	private ResponseEntity<List<Motorbike>> fallBackGetMotorbike(@PathVariable("userId") int userId,
			RuntimeException exception) {
		return new ResponseEntity("El usuario : " + userId + " tiene las motocicletas en el taller", HttpStatus.OK);
	}

	private ResponseEntity<Motorbike> fallBackSaveMotorbike(@PathVariable("userId") int userId,
			@RequestBody Motorbike motorbike, RuntimeException exception) {
		return new ResponseEntity("El usuario : " + userId + " no tiene dinero para las motocicletas en el taller",
				HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> fallBackGetAll(@PathVariable("userId") int userId, RuntimeException exception) {
		return new ResponseEntity("El usuario : " + userId + " tiene los vehiculos en el taller", HttpStatus.OK);
	}
}

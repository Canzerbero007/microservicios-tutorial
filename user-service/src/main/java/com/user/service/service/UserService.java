package com.user.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entity.User;
import com.user.service.feignclient.CarFeignClient;
import com.user.service.feignclient.MotorbikeFeignClient;
import com.user.service.model.Car;
import com.user.service.model.Motorbike;
import com.user.service.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarFeignClient carFeignClient;

	@Autowired
	private MotorbikeFeignClient motorbikeFeignClient;

	public List<Car> getCar(int id) {
		List<Car> listCar = restTemplate.getForObject("http://car-service/car/user/" + id, List.class);
		return listCar;
	}

	public List<Motorbike> getMotorbike(int id) {
		List<Motorbike> listMotorbike = restTemplate.getForObject("http://motorbike-service/motorbike/user/" + id,
				List.class);
		return listMotorbike;
	}

	public Car saveCar(int userId, Car car) {
		car.setUserId(userId);
		Car newCar = carFeignClient.save(car);
		return newCar;
	}

	public Motorbike saveMotorbike(int userId, Motorbike motorbike) {
		motorbike.setUserId(userId);
		Motorbike newMotorbike = motorbikeFeignClient.save(motorbike);
		return newMotorbike;
	}

	public Map<String, Object> getUserAndVehicle(int userId) {
		Map<String, Object> result = new HashMap<>();
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			result.put("Mensaje", "El usuario no existe");
			return result;
		}

		result.put("User", user);
		List<Car> listCar = carFeignClient.getListCar(userId);
		if (listCar.isEmpty()) {
			result.put("listCar", "El usuario no tiene carros");
		} else {
			result.put("listCar", listCar);
		}

		List<Motorbike> listMotorbike = motorbikeFeignClient.getListMotorbike(userId);
		if (listMotorbike.isEmpty()) {
			result.put("listMotorbike", "El usuario no tiene motocicletas");
		} else {
			result.put("listMotorbike", listMotorbike);
		}

		return result;
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	public User save(User user) {
		User newUser = userRepository.save(user);
		return newUser;
	}
}

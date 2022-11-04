package com.user.service.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.service.model.Car;

@FeignClient(name = "car-service",url = "http://localhost:8082")
@RequestMapping("/car")
public interface CarFeignClient {
	@PostMapping
	public Car save(@RequestBody Car car);
	
	@GetMapping("/user/{userId}")
	public List<Car> getListCar(@PathVariable("userId") int userId);
}

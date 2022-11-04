package com.user.service.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.service.model.Motorbike;

@FeignClient(name="motorbike-service", url="http://localhost:8083")
@RequestMapping("/motorbike")
public interface MotorbikeFeignClient {

		@PostMapping()
		public Motorbike save(@RequestBody Motorbike motorbike);
		
		@GetMapping("/user/{userId}")
		public List<Motorbike> getListMotorbike(@PathVariable("userId") int userId);
}

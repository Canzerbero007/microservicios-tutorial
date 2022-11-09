package com.motorbike.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.motorbike.service.entity.Motorbike;
import com.motorbike.service.service.MotorbikeService;

@RefreshScope
@RestController
@RequestMapping("/motorbike")
public class MotorbikeController {

	@Autowired
	private MotorbikeService motorbikeService;
	
	@GetMapping
	public ResponseEntity<List<Motorbike>> listCar() {
		List<Motorbike> listMotorbike = motorbikeService.getAll();
		if (listMotorbike.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listMotorbike);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Motorbike> findMotorbikeById(@PathVariable("id") int id) {
		Motorbike motorbike = motorbikeService.getMotorbikeById(id);
		if (motorbike == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(motorbike);
	}

	@PostMapping
	public ResponseEntity<Motorbike> saveMotorbike(@RequestBody Motorbike motorbike) {
		Motorbike newMotorbike = motorbikeService.save(motorbike);
		return ResponseEntity.ok(newMotorbike);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Motorbike>> listMotorbikeByUserId(@PathVariable("id") int id){
		List<Motorbike> listMotorbike = motorbikeService.byUserId(id);
		if (listMotorbike.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listMotorbike);
	}
}

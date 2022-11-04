package com.motorbike.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.motorbike.service.entity.Motorbike;
import com.motorbike.service.repository.MotorbikeRepository;

@Service
public class MotorbikeService {

	 @Autowired
	 private MotorbikeRepository motorbikeRepository;
	 
	 public List<Motorbike> getAll(){
			return motorbikeRepository.findAll();		
		}
		 
		public Motorbike getMotorbikeById(int id) {
			return motorbikeRepository.findById(id).orElse(null);
		}
		
		public Motorbike save(Motorbike motorbike) {
			Motorbike newMotorbike = motorbikeRepository.save(motorbike);
			return newMotorbike;
		} 
		
		public List<Motorbike> byUserId(int userId){
			return motorbikeRepository.findByUserId(userId);
		}
}

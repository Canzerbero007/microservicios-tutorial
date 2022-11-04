package com.motorbike.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motorbike.service.entity.Motorbike;

@Repository
public interface MotorbikeRepository extends JpaRepository<Motorbike, Integer> {
	List<Motorbike> findByUserId(int userId);
}

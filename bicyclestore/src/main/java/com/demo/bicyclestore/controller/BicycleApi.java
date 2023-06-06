package com.demo.bicyclestore.controller;

import com.demo.bicyclestore.dto.BicycleDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BicycleApi {
    ResponseEntity<Object> saveBicycle(BicycleDto bicycleDto);
    ResponseEntity<Object> saveBicycles(List<BicycleDto> bicycleDto);
    ResponseEntity<Object> deleteBicycle(String id);
    ResponseEntity<Object> updateBicycle(String id, BicycleDto bicycleDto);
    ResponseEntity<Object> findBicycles(BicycleDto bicycleDto);
    ResponseEntity<Object> getBicycles(int pageNumber, int pageSize);
}

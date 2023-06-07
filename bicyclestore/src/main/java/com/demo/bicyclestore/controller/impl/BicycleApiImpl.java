package com.demo.bicyclestore.controller.impl;

import com.demo.bicyclestore.controller.BicycleApi;
import com.demo.bicyclestore.dto.BicycleDto;
import com.demo.bicyclestore.service.BicycleCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bicycle")
@RequiredArgsConstructor
@Slf4j
public class BicycleApiImpl implements BicycleApi {
    private final BicycleCrudService bicycleCrudService;
    @Override
    @GetMapping("/all")
    public ResponseEntity<Object> getBicycles(@RequestParam(defaultValue="0")  int pageNumber,
                                              @RequestParam(defaultValue="5") int pageSize) {
        return ResponseEntity.ok(bicycleCrudService.findAllBicyclesPaginated(pageNumber, pageSize));
    }

    @Override
    @PostMapping("/find")
    public ResponseEntity<Object> findBicycles(@RequestBody BicycleDto bicycleDto) {
        return ResponseEntity.ok(bicycleCrudService.findByFilter(bicycleDto));
    }

    @Override
    @PutMapping("/save-many")
    public ResponseEntity<Object> saveBicycles(@RequestBody List<BicycleDto> bicycleDto) {
        bicycleCrudService.saveAll(bicycleDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @Override
    @PostMapping("/update/{id}")
    public ResponseEntity<Object> updateBicycle(@PathVariable String id, @RequestBody BicycleDto bicycleDto) {
        bicycleCrudService.update(id, bicycleDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @Override
    @PutMapping("/save")
    public ResponseEntity<Object> saveBicycle(@RequestBody BicycleDto bicycleDto) {
        var entity = bicycleCrudService.save(bicycleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity.getId());
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBicycle(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(bicycleCrudService.delete(Long.valueOf(id)));
    }
}

package com.demo.BicycleStore.service;

import com.demo.bicyclestore.dto.BicycleDto;
import com.demo.bicyclestore.model.BicycleEntity;
import com.demo.bicyclestore.repository.BicycleEntityRepository;
import com.demo.bicyclestore.service.BicycleCrudService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class BicycleCrudServiceTests {
    @Mock
    public BicycleEntityRepository bicycleEntityRepository;

    @InjectMocks
    public BicycleCrudService bicycleCrudService;

    @Test
    public void findByFilter_test() {
        bicycleCrudService.findByFilter(getDummyDto());
        Mockito.verify(bicycleEntityRepository, Mockito.times(1)).findAll(Mockito.any(Specification.class));
    }
    @Test
    public void delete_test() {
        var entity = new BicycleEntity();
        entity.setId(1L);
        Mockito.doReturn(Optional.of(entity)).when(bicycleEntityRepository).findById(1L);
        bicycleCrudService.delete(1L);
        Mockito.verify(bicycleEntityRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(bicycleEntityRepository, Mockito.times(1)).deleteById(1L);
    }

    private BicycleDto getDummyDto() {
        var dto = new BicycleDto();
        dto.setId(10);
        dto.setBrand("BSA");
        dto.setPrice(50.99);
        dto.setConfigId(0);
        dto.setFrameSize(20);
        dto.setWheelSize(29);
        dto.setColor("black");
        return dto;
    }
}

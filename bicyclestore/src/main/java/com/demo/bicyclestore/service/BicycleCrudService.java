package com.demo.bicyclestore.service;

import com.demo.bicyclestore.dto.BicycleDto;
import com.demo.bicyclestore.model.BicycleEntity;
import com.demo.bicyclestore.repository.BicycleEntityRepository;
import com.demo.bicyclestore.repository.specs.BicycleSpecs;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BicycleCrudService {
    private final BicycleEntityRepository bicycleEntityRepository;

    /**
     * It's a find all method where the amount of data returned is controlled by pagination.
     *
     * @param pageNumber .
     * @param pageSize .
     * @return .
     */
    public List<BicycleDto> findAllBicyclesPaginated(int pageNumber, int pageSize) {
        return bicycleEntityRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("id")))
                .stream().map(BicycleDto::toDto).collect(Collectors.toList());
    }

    public BicycleEntity save(BicycleDto bicycleDto) {
        return bicycleEntityRepository.save(BicycleDto.toEntity(bicycleDto));
    }

    @CachePut(cacheNames="bicycles", key="#id")
    public void update(String id, BicycleDto bicycleDto) {
        var entity = bicycleEntityRepository.findById(Long.valueOf(id));
        entity.ifPresent(e -> {
            e.setBrand(bicycleDto.getBrand());
            e.setBrand(bicycleDto.getBrand());
            e.setPrice(bicycleDto.getPrice());
            e.getConfiguration().setWheelSize(bicycleDto.getWheelSize());
            e.getConfiguration().setFrameSize(bicycleDto.getFrameSize());
            e.getConfiguration().setColor(bicycleDto.getColor());
            bicycleEntityRepository.save(e);
        });
    }

    public void saveAll(List<BicycleDto> bicycleDto) {
        if (CollectionUtils.isEmpty(bicycleDto))
            return;
        bicycleEntityRepository.saveAll(bicycleDto.stream().map(BicycleDto::toEntity).collect(Collectors.toList()));
    }

    @Cacheable(cacheNames="bicycles", key="#bicycleDto.id")
    public Object findByFilter(BicycleDto bicycleDto) {
        return bicycleEntityRepository.findAll(
                BicycleSpecs.like("brand", bicycleDto.getBrand())
                        .or(BicycleSpecs.equals("id", String.valueOf(bicycleDto.getId())))
                        .or(BicycleSpecs.equalsJoin("wheelSize", String.valueOf(bicycleDto.getWheelSize()), "configuration"))
                        .or(BicycleSpecs.equalsJoin("frameSize", String.valueOf(bicycleDto.getFrameSize()), "configuration"))
                        .or(BicycleSpecs.equalsJoin("color", bicycleDto.getColor(), "configuration")));
    }

    @CacheEvict(cacheNames="bicycles", key="#id")
    public boolean delete(Long id) {
        Optional<BicycleEntity> bicyle = bicycleEntityRepository.findById(id);
        if (bicyle.isPresent()) {
            bicycleEntityRepository.deleteById(bicyle.get().getId());
            return true;
        }
        return false;
    }
}

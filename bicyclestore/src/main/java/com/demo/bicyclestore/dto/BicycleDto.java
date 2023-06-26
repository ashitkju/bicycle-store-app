package com.demo.bicyclestore.dto;

import com.demo.bicyclestore.model.BicycleEntity;
import com.demo.bicyclestore.model.Configuration;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BicycleDto {
    private long id;
    private String brand;
    private double price;
    private long configId;
    private double wheelSize;
    private double frameSize;
    private String color;

    public static BicycleEntity toEntity(BicycleDto bicycleDto) {
        var entity = new BicycleEntity();
        entity.setId(bicycleDto.getId() == 0 ? null : bicycleDto.getId());
        entity.setBrand(bicycleDto.getBrand());
        entity.setPrice(bicycleDto.getPrice());
        var config = new Configuration();
        config.setId(bicycleDto.getConfigId() == 0 ? null : bicycleDto.getConfigId());
        config.setWheelSize(bicycleDto.getWheelSize());
        config.setFrameSize(bicycleDto.getFrameSize());
        config.setColor(bicycleDto.getColor());
        entity.setConfiguration(config);
        return entity;
    }

    public static BicycleDto toDto(BicycleEntity bicycleEntity) {
        return BicycleDto.builder().id(bicycleEntity.getId()).brand(bicycleEntity.getBrand()).price(bicycleEntity.getPrice())
                .configId(bicycleEntity.getConfiguration().getId()).wheelSize(bicycleEntity.getConfiguration().getWheelSize())
                .frameSize(bicycleEntity.getConfiguration().getFrameSize()).color(bicycleEntity.getConfiguration().getColor()).build();
    }
    
    public int hash() {
        final int PRIME = 59;
        int result = 1;
        result = (int) (result * PRIME + this.getId());
        result = (int) (result * PRIME + this.getFrameSize());
        result = (int) (result * PRIME + this.getWheelSize());
        result = result * PRIME + (this.getBrand() == null ? 43 : this.getBrand().hashCode());
        result = result * PRIME + (this.getColor() == null ? 43 : this.getColor().hashCode());
        return result;
    }
}

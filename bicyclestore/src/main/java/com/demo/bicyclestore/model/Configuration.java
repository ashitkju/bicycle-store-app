package com.demo.bicyclestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Configuration {
    @Id
    @GeneratedValue
    private Long id;
    private double wheelSize;
    private double frameSize;
    private String color;

    @OneToOne(mappedBy = "configuration")
    @JsonIgnore
    private BicycleEntity bicycle;

}

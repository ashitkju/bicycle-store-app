package com.demo.bicyclestore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class BicycleEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String brand;
    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "config_id")
    private Configuration configuration;
}

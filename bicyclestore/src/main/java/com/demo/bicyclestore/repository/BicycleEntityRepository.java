package com.demo.bicyclestore.repository;

import com.demo.bicyclestore.model.BicycleEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicycleEntityRepository extends PagingAndSortingRepository<BicycleEntity, Long>, CrudRepository<BicycleEntity, Long>, JpaSpecificationExecutor<BicycleEntity> {}

package com.demo.bicyclestore.repository.specs;

import com.demo.bicyclestore.model.BicycleEntity;
import org.springframework.data.jpa.domain.Specification;

public class BicycleSpecs {
    private static final String LIKE_OPERATOR = "%";
    public static Specification<BicycleEntity> like(String column, String value){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(column), LIKE_OPERATOR + value + LIKE_OPERATOR);
    }

    public static Specification<BicycleEntity> equals(String column, String value){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(column), value);
    }

    public static Specification<BicycleEntity> equalsJoin(String column, String value, String joinClass){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(joinClass).get(column), value);
    }
}

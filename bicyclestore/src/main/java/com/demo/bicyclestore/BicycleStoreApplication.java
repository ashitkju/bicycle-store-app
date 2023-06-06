package com.demo.bicyclestore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCaching
public class BicycleStoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(BicycleStoreApplication.class, args);
	}
}

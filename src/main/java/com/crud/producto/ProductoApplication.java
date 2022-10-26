package com.crud.producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.crud.producto"})
public class ProductoApplication{

	public static void main(String[] args) {
		SpringApplication.run(ProductoApplication.class, args);
	}

}

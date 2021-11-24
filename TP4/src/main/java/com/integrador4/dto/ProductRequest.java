package com.integrador4.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.integrador4.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema( name = "Product Request", description = "Product request for add/update a product")
public class ProductRequest {

	private final String name;
	private final Double price;
	private final Integer stock;

	public ProductRequest(String name, Double price, Integer stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public Product toProduct(Integer id) {
		return new Product(id, this.getName(), this.getPrice(), this.getStock());
	}

	public Product toProduct() {
		return new Product(this.getName(), this.getPrice(), this.getStock());
	}

}

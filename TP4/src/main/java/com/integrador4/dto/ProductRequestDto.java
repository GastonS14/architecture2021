package com.integrador4.dto;

import com.integrador4.entity.Product;

import java.math.BigDecimal;

public class ProductRequestDto {

	private final String name;
	private final Double price;
	private final Integer stock;

	public ProductRequestDto(String name, Double price, Integer stock) {
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

	public Product toProduct(BigDecimal id) {
		return new Product(id, this.name, this.price, this.stock);
	}

	public Product toProduct() {
		return new Product(this.name, this.price, this.stock);
	}

}

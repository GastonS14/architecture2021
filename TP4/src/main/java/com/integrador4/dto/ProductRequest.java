package com.integrador4.dto;

import com.integrador4.entity.Product;

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

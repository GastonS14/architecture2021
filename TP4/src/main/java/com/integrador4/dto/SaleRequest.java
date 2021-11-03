package com.integrador4.dto;

import com.integrador4.entity.Client;
import com.integrador4.entity.Sale;
import com.integrador4.entity.SaleProduct;
import java.util.List;

public class SaleRequest {

	private final Client client;
	private List<SaleProduct> productsSold;

	public SaleRequest(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public List<SaleProduct> getProductsSold() {
		return productsSold;
	}

	public Sale toSale(Integer id) {
		return new Sale(id, this.getClient());
	}

	public Sale toSale() {
		return new Sale(this.getClient());
	}
}

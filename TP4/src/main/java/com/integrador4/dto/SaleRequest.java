package com.integrador4.dto;

import com.integrador4.entity.Client;
import com.integrador4.entity.Sale;
import com.integrador4.entity.SaleProduct;
import java.sql.Date;
import java.util.List;

public class SaleRequest {

	private final Date date;
	private final Client client;
	private List<SaleProduct> productsSold;

	public SaleRequest(Date date, Client client) {
		this.date = date;
		this.client = client;
	}

	public Date getDate() {
		return this.date;
	}

	public Client getClient() {
		return client;
	}

	public List<SaleProduct> getProductsSold() {
		return productsSold;
	}

	public Sale toSale(Integer id) {
		return new Sale(id, this.getClient(), this.getDate());
	}

	public Sale toSale() {
		return new Sale(this.getClient(), this.getDate());
	}
}

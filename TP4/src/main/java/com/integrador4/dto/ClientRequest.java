package com.integrador4.dto;

import com.integrador4.entity.Client;

public class ClientRequest {

	private final String name;
	private final String surname;

	public ClientRequest(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Client toClient(Integer id) {
		return new Client(id, this.getName(), this.getSurname());
	}

	public Client toClient() {
		return new Client(this.getName(), this.getSurname());
	}
}

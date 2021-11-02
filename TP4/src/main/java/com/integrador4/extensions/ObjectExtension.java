package com.integrador4.extensions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectExtension {

	private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	public static String toJson(Object body) {

		try {
			if(body != null) return objectMapper.writeValueAsString(body);
			return "{}";
		} catch(Exception exception) {
			return "{}";
		}

	}
}

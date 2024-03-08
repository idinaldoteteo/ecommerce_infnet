package com.ecommerce.order.util;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	public static Map<String, Object> convertToObject(String jsonS) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(jsonS, Map.class);

			return map;

		} catch (JsonProcessingException e) {
			return null;
		}
	}
}

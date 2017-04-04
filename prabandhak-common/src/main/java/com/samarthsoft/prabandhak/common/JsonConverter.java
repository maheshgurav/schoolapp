package com.samarthsoft.prabandhak.common;

import com.google.gson.Gson;

public class JsonConverter {
	
	public static String toJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	public static Object fromJson(String json,Class<?> clazz) {
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}
}

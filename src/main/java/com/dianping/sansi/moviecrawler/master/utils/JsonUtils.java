package com.dianping.sansi.moviecrawler.master.utils;

import com.google.gson.Gson;

public class JsonUtils {
	private static final Gson gson=new Gson();
	
	public static <T> T fromJson(String json, Class<T> type){
		return gson.fromJson(json, type);
	}
	
	public static String toJson(Object o){
		String json = gson.toJson(o);
		return json;
	}	
}

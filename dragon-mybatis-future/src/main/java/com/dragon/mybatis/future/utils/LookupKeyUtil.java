package com.dragon.mybatis.future.utils;

public class LookupKeyUtil {

	private static final ThreadLocal<String> LOOKUP_KEY = new ThreadLocal<String>();

	public static void setLookupKey(String datasourceId) {
		LOOKUP_KEY.set(datasourceId);
	}

	public static String getLookupKey() {
		return LOOKUP_KEY.get();
	}

}

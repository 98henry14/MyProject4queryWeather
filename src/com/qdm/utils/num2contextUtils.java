package com.qdm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.sun.security.ntlm.Client;


public class num2contextUtils {
	private static Properties prop = new Properties();
	/**
	 * 从天气中取出对应值
	 * @param str
	 * @return
	 * @throws IOException 
	 * @throws  
	 */
	public static String turn2weather(String str) throws IOException {
		String property = "";
			prop.load(new InputStreamReader(Object.class.getResourceAsStream("/file/weather.properties"), "UTF-8"));
			property = prop.getProperty(str);
		return property;
	}
	/**
	 * 从风力中取出对应值
	 * @param str
	 * @return
	 * @throws IOException 
	 * @throws  
	 */
	public static String turn2power(String str) throws IOException {
		String property = "";
			prop.load(new InputStreamReader(Object.class.getResourceAsStream("/file/windPower.properties"), "UTF-8"));
			property = prop.getProperty(str);

		return property;
	}
	/**
	 * 从风向中取出对应值
	 * @param str
	 * @return
	 * @throws IOException 
	 * @throws  
	 */
	public static String turn2direction(String str) throws IOException {
		String property = "";
			prop.load(new InputStreamReader(Object.class.getResourceAsStream("/file/windDirection.properties"), "UTF-8"));
			property = prop.getProperty(str);

		return property;
	}
	
/*	public static void main(String[] args) {
		Properties prop = new Properties();   
		try {
			prop.load(new InputStreamReader(Object.class.getResourceAsStream("/file/weather.properties"), "UTF-8"));
			String property = prop.getProperty("20");
			System.out.println(property);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("出错了!");
		}
	}*/
}

package com.qdm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.qdm.dao.ForecastDao;
import com.sun.security.ntlm.Client;


/**
 * @author lenovo
 *
 */
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

	public static List<String> queryAreain(){
		ForecastDao fd =  new ForecastDao();
		List<String> strArrList = new ArrayList<String>();
		try {
			List<Object> list = fd.queryAreain();
				String str = "";
				int index = 1;
				for (Object object : list) {
					if(Math.floorMod(index, 20) == 0 ) {
						str += object;
						strArrList.add(str);
						index++;
						str="";
					}else if(index != list.size()){
						str += object+"|";
						index++;
						}else{
							str += object;
							strArrList.add(str);
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return strArrList;
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

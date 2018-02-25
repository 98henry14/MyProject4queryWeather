package com.qdm.test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.management.StringValueExp;

import org.junit.Test;

import com.qdm.dao.ForecastDao;

public class test {
	/*	public static void main(String[] args) {
		String area = "101280101|101280102|101280103|101280104|101280105|101280106|101280107|101280108|101280109|101280110|101280111|101280112";
//		area = new Array("101280101","101280102","101280103","101280104","101280105","101280106","101280107","101280108","101280109","101280110","101280111","101280112");
		String[] areaArray = area.split("\\|");
//		System.out.println(areaArray.length);
		for (String string : areaArray) {
			System.out.println(string);
		}
		*/ 
/*		ForecastDao fd = new ForecastDao();
		try {
			fd.showUser();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
/*		Calendar cd =  new GregorianCalendar();
//		cd.setTime(new Date());
		cd.add(cd.DATE, 1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(format.format(cd.getTime()));
		
		try {
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(format1.parse("2018-02-25"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String[] strArr= new String[7];
		List<String> strList = new ArrayList<String>();
		strList.add("101280101|101280102|101280103|101280104|101280105|101280106|101280107|101280108|101280109|101280110|101280111|101280112|101280201|101280202|101280203|101280204|101280205|101280206|101280207|101280208");
		strList.add("101280209|101280210|101280211|101280301|101280302|101280303|101280304|101280305|101280306|101280401|101280402|101280403|101280404|101280405|101280406|101280407|101280408|101280409|101280501|101280502");
		strList.add("101280503|101280504|101280505|101280506|101280507|101280508|101280601|101280602|101280603|101280604|101280605|101280606|101280607|101280701|101280702|101280703|101280704|101280800|101280801|101280802");
		strList.add("101280803|101280804|101280805|101280901|101280902|101280903|101280904|101280905|101280906|101280907|101280908|101280909|101281001|101281002|101281003|101281004|101281005|101281006|101281007|101281008");
		strList.add("101281009|101281010|101281101|101281103|101281104|101281105|101281106|101281107|101281108|101281109|101281201|101281202|101281203|101281204|101281205|101281206|101281207|101281301|101281302|101281303");
		strList.add("101281304|101281305|101281306|101281307|101281308|101281309|101281401|101281402|101281403|101281404|101281405|101281406|101281501|101281502|101281503|101281504|101281601|101281701|101281801|101281802");
		strList.add("101281803|101281804|101281805|101281901|101281902|101281903|101281904|101281905|101281906|101282001|101282002|101282003|101282004|101282005|101282007|101282101|101282102|101282103|101282104");
		
		System.out.println(strList.size()+"duoshaoge:->>>>"+strList.get(0));
		
	}*/
	
	
	@Test
	public void testQueryCol() {
		ForecastDao fd =  new ForecastDao();
		List<String> strArrList = new ArrayList<String>();
		try {
			List<Object> list = fd.queryAreain();
				String str = "";
				int index = 1;
				for (Object object : list) {
//					System.out.println(object.toString()+"-----"+object);
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
							break;
					}
				}
				for (String strr : strArrList) {
					System.out.println(strr);
				}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
}

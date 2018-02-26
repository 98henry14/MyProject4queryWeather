package com.qdm.main;

import java.sql.SQLException;

import org.junit.Test;

import com.qdm.dao.ForecastDao;

public class TestPro {
	//测试删除方法的使用
	@Test
	public void testDelect() throws SQLException {
		ForecastDao fd =  new ForecastDao();
		int info = fd.deleteForecast();
		System.out.println(info);
	}
	/**
	
	//根据地区编码获取省市区
	@Test
	public void queryArea() throws SQLException {
		ForecastDao fd =  new ForecastDao();
		Object[] info = fd.queryLocation("101280101");
		System.out.println(info[0]+"----"+info[1]+"----"+info[2]);
	}
	 * 
	 */
	
}

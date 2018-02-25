package com.qdm.main;

import java.sql.SQLException;

import org.junit.Test;

import com.qdm.dao.ForecastDao;

public class TestPro {
	@Test
	public void testDelect() throws SQLException {
		ForecastDao fd =  new ForecastDao();
		int info = fd.deleteForecast();
		System.out.println(info);
	}
	
	@Test
	public void queryArea() throws SQLException {
		ForecastDao fd =  new ForecastDao();
		Object[] info = fd.queryLocation("101280101");
		System.out.println(info.toString());
	}
}

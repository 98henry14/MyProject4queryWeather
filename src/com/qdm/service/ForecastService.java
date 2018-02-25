package com.qdm.service;

import java.sql.SQLException;
import java.util.List;

import com.qdm.dao.ForecastDao;
import com.qdm.domain.Forecast;
import com.qdm.utils.DataSourceUtils;

public class ForecastService {
public void addWeather(List<Forecast> fclist) {
		
		ForecastDao dao = new ForecastDao();
		
		try {
			//1、开启事务
			DataSourceUtils.startTransaction();
			//2、调用dao存储order表数据的方法
			dao.addWeather(fclist);

			
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}

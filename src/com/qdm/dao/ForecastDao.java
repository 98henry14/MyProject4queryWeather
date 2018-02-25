package com.qdm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.qdm.domain.Forecast;
import com.qdm.utils.DataSourceUtils;

public class ForecastDao {

	//insert weather condition to DB
	public void addWeather(List<Forecast> fclist) throws SQLException{
		QueryRunner runner = new QueryRunner();
		String sql = "insert into forecast values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = DataSourceUtils.getConnection();
		for (Forecast fc : fclist) {
			runner.update(conn, sql, fc.areaid,fc.bttq001,fc.wstq002,
					fc.zgqw003,fc.zdqw004,fc.btfl005,fc.btfx007,
					fc.wsfl006,fc.wsfx008,fc.fdate,this.queryLocation(fc.areaid)[0],
					this.queryLocation(fc.areaid)[1],this.queryLocation(fc.areaid)[2]); 
		}
		
	}
	//queryAreain
	public List<Object> queryAreain() throws SQLException{
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select aid from areain";
		List<Object> collist = qr.query(sql, new ColumnListHandler("aid"));
		return collist;
		
	}
	
	//delete weather data some forecast weather data
	public int deleteForecast() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "DELETE FROM forecast WHERE 预测时间 > NOW()";
		return qr.update(sql);
	}
	
	//from areaid to find area
	public Object[] queryLocation(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select province,city,district from areain where aid=?";
		Object[] query = qr.query(sql, new ArrayHandler(),id);
//		String a =Arrays.toString(query).replace("[", "").replace("]", "");
		return query;
		
	}
}

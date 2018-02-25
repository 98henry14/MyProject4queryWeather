package com.qdm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import com.qdm.domain.Forecast;
import com.qdm.utils.DataSourceUtils;

public class ForecastDao {

	//insert weather condition to DB
	public void addWeather(List<Forecast> fclist) throws SQLException{
		QueryRunner runner = new QueryRunner();
		String sql = "insert into forecast values(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = DataSourceUtils.getConnection();
		for (Forecast fc : fclist) {
			runner.update(conn, sql, fc.fid,fc.bttq001,fc.wstq002,fc.zgqw003,fc.zzqw004,fc.btfl005,fc.btfx007,fc.wsfl006,fc.wsfx008,fc.fdate); 
		}
		
	}

	//queryAreain
	public List<Object> queryAreain() throws SQLException{
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select aid from areain";
		List<Object> collist = qr.query(sql, new ColumnListHandler("aid"));
		return collist;
		
	}
}

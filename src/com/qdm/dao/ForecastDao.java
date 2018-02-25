package com.qdm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.qdm.domain.Forecast;
import com.qdm.utils.DataSourceUtils;

public class ForecastDao {

/*	//向orderitem表插入数据
	public void addOrderItem(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		Connection conn = DataSourceUtils.getConnection();
		List<OrderItem> orderItems = order.getOrderItems();
		for(OrderItem item : orderItems){
			runner.update(conn,sql,item.getItemid(),item.getCount(),item.getSubtotal(),item.getProduct().getPid(),item.getOrder().getOid());
		}
	}*/
	public void addWeather(List<Forecast> fclist) throws SQLException{
		QueryRunner runner = new QueryRunner();
		String sql = "insert into forecast values(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = DataSourceUtils.getConnection();
		for (Forecast fc : fclist) {
			runner.update(conn, sql, fc.fid,fc.bttq001,fc.wstq002,fc.zgqw003,fc.zzqw004,fc.btfl005,fc.btfx007,fc.wsfl006,fc.wsfx008,fc.fdate); 
		}
		
	}
	
	//test
/*	public void showUser() throws SQLException{
		QueryRunner runner = new QueryRunner();
		String sql = "select * from user";
		Connection conn = DataSourceUtils.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			String field = rs.getString("host");
			String user = rs.getString("user"); 
			System.out.println(field+"~----"+user);
		}
	}*/
	
	//queryArea
	public List<String> queryAll() throws SQLException{
		QueryRunner runner  = new QueryRunner();
		String sql ="select aid from areain";
		Connection conn = DataSourceUtils.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return null;
	}
}

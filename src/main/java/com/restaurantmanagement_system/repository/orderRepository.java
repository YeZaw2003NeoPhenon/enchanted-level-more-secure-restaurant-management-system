package com.restaurantmanagement_system.repository;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.restaurantmanagement_system.model.Order;
import com.restaurantmanagement_system.model.userDetailModel;

@Repository
public class orderRepository {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<Order> getAllOrders(){
		return jdbcTemplate.query( QueryData.getSELECT_ALL() , new TableRowMapper());
	}
	
  public static class TableRowMapper implements RowMapper<Order>{
		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order  = new Order();
			order.setOrderId(rs.getInt("orderId"));
			order.setTableNumber(rs.getInt("tableNumber"));
			order.setItemName(rs.getString("itemName"));
			order.setQuantity(rs.getInt("quantity"));
			order.setTotalPrices(rs.getDouble("totalPrices"));
			return order;
		}
	}

  
	public int addOrder(Order order) {

		return jdbcTemplate.update( QueryData.getCREATE_ORDER() , new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, order.getTableNumber());
				ps.setString(2, order.getItemName());
				ps.setInt(3, order.getQuantity());
				ps.setDouble(4, order.getTotalPrices());
			}
		});
	}
		
		public Order getOrderById( int orderId ) {
			return  jdbcTemplate.query(QueryData.getGET_ORDER_BY_ID(), new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					
					ps.setInt(1, orderId);
				}
				
			} , new ResultSetExtractor<Order>() {

				@Override
				public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
					Order order  = new Order();
					while( rs.next()) {
						order.setOrderId(rs.getInt("orderId"));
						order.setTableNumber(rs.getInt("tableNumber"));
						order.setItemName(rs.getString("itemName"));
						order.setQuantity(rs.getInt("quantity"));
						order.setTotalPrices(rs.getDouble("totalPrices"));
					}
					return order;
				}
			});
		}
		
		public int deleteOrder( int orderId ) {
			
			return jdbcTemplate.update( QueryData.getDELETE_ORDER() , new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, orderId);
				}
				
			});
		}
		
		public int updateOrder( Order order ) {
			return jdbcTemplate.update( QueryData.getUPDATE_ORDER(), new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, order.getTableNumber());
					ps.setString(2, order.getItemName());
					ps.setInt(3, order.getQuantity());
					ps.setDouble(4, order.getTotalPrices());
					ps.setInt(5, order.getOrderId());
				}
			});
		}
		
		
		public List<Order> getOrdersFromTable(int tableNumber) {
		    return jdbcTemplate.query(QueryData.getGET_ORDER_TABLE(), new Object[]{tableNumber} , new BeanPropertyRowMapper<>(Order.class));
		}
		

}

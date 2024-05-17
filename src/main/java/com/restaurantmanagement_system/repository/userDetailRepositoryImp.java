package com.restaurantmanagement_system.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.restaurantmanagement_system.model.userDetailModel;

@Repository
public class userDetailRepositoryImp implements userDetailRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public userDetailModel loginProcess( String username ) {
		
		return jdbcTemplate.query( QueryData.getACCOUNT_LOGIN_PROCESS() , new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, username);
				}
			
		} , new ResultSetExtractor<userDetailModel>() {

			@Override
			public userDetailModel extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				while( rs.next() ) {
					userDetailModel userDetailModel = new userDetailModel();
					userDetailModel.setUsername(rs.getString("username"));
					userDetailModel.setPassword(rs.getString("password"));
					userDetailModel.setEnabled(rs.getBoolean("enabled"));
					userDetailModel.setAuthority(rs.getString("authority"));
					return userDetailModel;
				}
				
				return null;
			}
		});
	}
	
}

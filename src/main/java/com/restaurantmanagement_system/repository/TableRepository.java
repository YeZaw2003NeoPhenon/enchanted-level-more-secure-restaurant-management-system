package com.restaurantmanagement_system.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.restaurantmanagement_system.model.Table;

@Repository
public class TableRepository {

	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	
	public List<Table> getAllTables(){
		
	return	jdbcTemplate.query(QueryData.getGET_TABLE_DATAS(),  new RowMapper<Table>() {

			@Override
			public Table mapRow(ResultSet rs, int rowNum) throws SQLException {
				Table table = new Table();
				table.setTableNumber(rs.getInt("tablNumber"));
				table.setOccupied(rs.getBoolean("occupied"));
				return table;
			}
			// in this case, empirically, using new BeanPropertyRowMapper<>(Table.class) can beneficially break down codes lines and can be more well-orgnized
		});	
	} // just refers to generic class
	
	public void updateTableStatus(  int tableNumber , boolean occupied ) {
		jdbcTemplate.update( QueryData.getUPDATE_TABLE_STAUS() , occupied , tableNumber ); // to just virtously change the status of table in database
	}
}

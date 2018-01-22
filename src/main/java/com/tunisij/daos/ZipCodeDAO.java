package com.tunisij.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.tunisij.businessObjects.ZipCodeBO;

public class ZipCodeDAO {

	private DataSource dataSource;
	
	public ZipCodeDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ZipCodeBO getZipCode(Integer zipCode) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ZipCodeBO zipCodeBO = new ZipCodeBO(zipCode);
		
		String sql = "SELECT * FROM routemining.zip_code WHERE zip_code = ?";
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, zipCode);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				zipCodeBO.setAvgHouseValue(rs.getInt("avg_house_value"));
				zipCodeBO.setHouseholdIncome(rs.getInt("household_income"));
				zipCodeBO.setMedianAge(rs.getDouble("median_age"));
				zipCodeBO.setNumBusinesses(rs.getInt("num_business"));
				zipCodeBO.setNumEmployees(rs.getInt("num_employee"));
				zipCodeBO.setPopulation(rs.getInt("population"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return zipCodeBO;
	}

}
package com.tunisij.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.tunisij.businessObjects.ZipCodeBO;

public class ZipCodeDAO extends JdbcDaoSupport {

	private DataSource dataSource;
	
	public ZipCodeDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ZipCodeBO getZipCode(Integer zipCode) {
		ZipCodeBO zipCodeBO = new ZipCodeBO(zipCode);
		
		String sql = "SELECT * FROM routemining.zip_code";
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
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
package com.tunisij.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.tunisij.businessObjects.ZipCodeBO;

public class ZipCodeDAO extends BaseDAO {

	private DataSource dataSource;
	
	public ZipCodeDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(ZipCodeBO zipCodeBO) {
		Connection connection = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO routemining.zip_code(zip_code, population, avg_house_value, household_income, median_age, num_business, num_employee) VALUES (?,?,?,?,?,?,?)";
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setString(1, zipCodeBO.getZipCode() != null ? zipCodeBO.getZipCode().toString() : null);
			ps.setString(2, zipCodeBO.getPopulation() != null ? zipCodeBO.getPopulation().toString() : null);
			ps.setString(3, zipCodeBO.getAvgHouseValue() != null ? zipCodeBO.getAvgHouseValue().toString() : null);
			ps.setString(4, zipCodeBO.getHouseholdIncome() != null ? zipCodeBO.getHouseholdIncome().toString() : null);
			ps.setString(5, zipCodeBO.getMedianAge() != null ? zipCodeBO.getMedianAge().toString() : null);
			ps.setString(6, zipCodeBO.getNumBusinesses() != null ? zipCodeBO.getNumBusinesses().toString() : null);
			ps.setString(7, zipCodeBO.getNumEmployees() != null ? zipCodeBO.getNumEmployees().toString() : null);
			
			ps.execute();
		} catch (SQLException e) {
			logger.log(e.getMessage(), e);
		} finally {
			closeResources(ps, connection);
		}
		
	}

	public ZipCodeBO getZipCode(Integer zipCode) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ZipCodeBO zipCodeBO = null;
		
		String sql = "SELECT * FROM routemining.zip_code WHERE zip_code = ?";
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(sql);
			ps.setInt(1, zipCode);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				zipCodeBO = ZipCodeBO.builder()
						.zipCode(zipCode)
						.avgHouseValue(rs.getInt("avg_house_value"))
						.householdIncome(rs.getInt("household_income"))
						.medianAge(rs.getDouble("median_age"))
						.numBusinesses(rs.getInt("num_business"))
						.numEmployees(rs.getInt("num_employee"))
						.population(rs.getInt("population"))
						.build();
			}
		} catch (SQLException e) {
			logger.log(e.getMessage(), e);
		} finally {
			closeResources(rs, ps, connection);
		}
		
		return zipCodeBO;
	}
	
}
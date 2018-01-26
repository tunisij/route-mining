package com.tunisij.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.tunisij.businessObjects.RouteBO;

public class RouteDAO extends BaseDAO {

	private DataSource dataSource;

	public RouteDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insertRoutes(List<RouteBO> routes) {
		for (RouteBO routeBO : routes) {
			insertRoute(routeBO);
		}
	}

	public void insertRoute(RouteBO route) {
		Connection connection = null;
		PreparedStatement ps = null;

		String sql = "INSERT INTO routemining.route "
				+ "(route, zip_code, type, county_code, business_count, apartment_count, po_box_count, residential_count, total_deliveries, avg_household_income, avg_property_value) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?) ";

		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(sql);

			ps.setString(1, route.getRoute());
			ps.setInt(2, route.getZipCode());
			ps.setString(3, route.getType());
			ps.setString(4, route.getCountyCode());
			ps.setInt(4, route.getBusinessCount());
			ps.setInt(6, route.getApartmentCount());
			ps.setInt(7, route.getPoBoxCount());
			ps.setInt(8, route.getResidentialCount());
			ps.setInt(9, route.getTotalDeliveries());
			ps.setInt(10, route.getAvgHouseholdIncome());
			ps.setInt(11, route.getAvgPropertyValue());
			
			ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			logger.log(e.getMessage());
		} finally {
			closeResources(ps, connection);
		}
	}

	public List<RouteBO> getRoutes(Integer zipCode) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RouteBO> routes = new ArrayList<>();

		String sql = "SELECT * FROM routemining.route WHERE zip_code = ?";

		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(sql);

			ps.setInt(1, zipCode);
			rs = ps.executeQuery();

			while (rs.next()) {
				RouteBO routeBO = new RouteBO(zipCode);
				routeBO.setApartmentCount(rs.getInt("apartment_count"));
				routeBO.setAvgHouseholdIncome(rs.getInt("avg_household_income"));
				routeBO.setAvgPropertyValue(rs.getInt("avg_property_value"));
				routeBO.setBusinessCount(rs.getInt("business_count"));
				routeBO.setCountyCode(rs.getString("county_code"));
				routeBO.setPoBoxCount(rs.getInt("po_box_count"));
				routeBO.setResidentialCount(rs.getInt("residential_count"));
				routeBO.setRoute(rs.getString("route"));
				routeBO.setTotalDeliveries(rs.getInt("total_deliveries"));
				routeBO.setType(rs.getString("type"));
				routes.add(routeBO);
			}
		} catch (SQLException e) {
			logger.log(e.getMessage());
		} finally {
			closeResources(rs, ps, connection);
		}

		return routes;
	}
}

package com.tunisij.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {
	
	public static void closeResources(PreparedStatement ps, Connection connection) {
		closeResources(null, ps, connection);
	}

	public static void closeResources(ResultSet rs, PreparedStatement ps, Connection connection) {
		try {
			if (rs != null) { rs.close(); }
			if (ps != null) { ps.close(); }
			if (connection != null) { connection.close(); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

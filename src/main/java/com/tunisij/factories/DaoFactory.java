package com.tunisij.factories;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunisij.common.Strings;
import com.tunisij.daos.BaseDAO;
import com.tunisij.daos.RouteDAO;
import com.tunisij.daos.ZipCodeDAO;

/**
 * This is a factory to get the correct DAO 
 */
@Service
public class DaoFactory {
	
	@Autowired
	private DataSource dataSource;

	public BaseDAO getDAO(String daoType) {
		if (Strings.ROUTE_DAO.equals(daoType)) {
			return new RouteDAO(dataSource);
		} else if (Strings.ZIP_CODE_DAO.equals(daoType)) {
			return new ZipCodeDAO(dataSource);
		} else {
			return null;
		}
	}
}

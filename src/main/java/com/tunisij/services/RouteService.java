package com.tunisij.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.common.Strings;
import com.tunisij.daos.RouteDAO;
import com.tunisij.factories.DaoFactory;

@Service
public class RouteService {
	
	@Autowired
	private DaoFactory factory;
	
	public List<RouteBO> getRoutes(Integer zipCode) {
		return ((RouteDAO) factory.getDAO(Strings.ROUTE_DAO)).getRoutes(zipCode);
	}

}

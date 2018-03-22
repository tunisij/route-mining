package com.tunisij.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.businessObjects.ZipCodeBO;
import com.tunisij.common.Strings;
import com.tunisij.daos.RouteDAO;
import com.tunisij.factories.DaoFactory;
import com.tunisij.scrapers.RouteScraper;

@Service
public class RouteService {
	
	@Autowired
	private DaoFactory factory;
	
	public List<RouteBO> getRoutes(Integer zipCode) {
		RouteDAO routeDAO = ((RouteDAO) factory.getDAO(Strings.ROUTE_DAO));
		List<RouteBO> routes = routeDAO.getRoutes(zipCode);
		
		if (routes.isEmpty()) {
			routes = new RouteScraper().getRoutesForZipCode(zipCode);
			routeDAO.insertRoutes(routes);
		}
		
		return routes;
	}
	
	public List<ZipCodeBO> populateRoutesForZipCodes(List<ZipCodeBO> zipCodes) {
		for (ZipCodeBO zipCode : zipCodes) {
			zipCode.setRoutes(getRoutes(zipCode.getZipCode()));
		}
		
		return zipCodes;
	}

}

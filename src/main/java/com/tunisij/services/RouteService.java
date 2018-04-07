package com.tunisij.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.businessObjects.ZipCodeBO;
import com.tunisij.common.Strings;
import com.tunisij.daos.RouteDAO;
import com.tunisij.factories.DaoFactory;
import com.tunisij.strategies.route.ExternalRouteStrategy;
import com.tunisij.strategies.route.LocalRouteStrategy;
import com.tunisij.strategies.route.RouteContext;

@Service
public class RouteService {
	
	@Autowired
	private DaoFactory factory;
	
	@Autowired
	private LocalRouteStrategy localRouteStrategy;
	
	@Autowired
	private ExternalRouteStrategy externalRouteStrategy;
	
	public List<ZipCodeBO> populateRoutesForZipCodes(List<ZipCodeBO> zipCodes) throws IOException {
		for (ZipCodeBO zipCode : zipCodes) {
			zipCode.setRoutes(getRoutes(zipCode.getZipCode()));
		}
		
		return zipCodes;
	}
	
	public List<RouteBO> getAllRoutesByKey(List<String> selectedKeys, List<ZipCodeBO> zipCodes) {
		List<RouteBO> routes = new ArrayList<>();
		
		for (ZipCodeBO zipCodeBO : zipCodes) {
			for (RouteBO routeBO : zipCodeBO.getRoutes()) {
				if (selectedKeys.contains(routeBO.getKey())) {
					routes.addAll(zipCodeBO.getRoutes());
					break;
				}
			}
		}
		
		return routes;
	}
	
	public List<RouteBO> getSelectedRoutesByKey(List<String> selectedKeys, List<ZipCodeBO> zipCodes) {
		List<RouteBO> routes = new ArrayList<>();
		
		for (ZipCodeBO zipCodeBO : zipCodes) {
			for (RouteBO routeBO : zipCodeBO.getRoutes()) {
				if (selectedKeys.contains(routeBO.getKey())) {
					routes.add(routeBO);
				}
			}
		}
		
		return routes;
	}
	
	//Tries local strategy first. If local returns no data, executes external strategy
	private List<RouteBO> getRoutes(Integer zipCode) throws IOException {
		RouteDAO routeDAO = ((RouteDAO) factory.getDAO(Strings.ROUTE_DAO));
		RouteContext localContext = new RouteContext(localRouteStrategy);
		RouteContext externalContext = new RouteContext(externalRouteStrategy);
		List<RouteBO> routes = localContext.executeFetch(zipCode);
		
		if (routes.isEmpty()) {
			routes = externalContext.executeFetch(zipCode);
			routeDAO.insertRoutes(routes);
		}
		
		return routes;
	}

}

package com.tunisij.strategies.route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.common.Strings;
import com.tunisij.daos.RouteDAO;
import com.tunisij.factories.DaoFactory;

@Service
public class LocalRouteStrategy implements RouteStrategy {

	@Autowired
	private DaoFactory factory;
	
	@Override
	public List<RouteBO> fetch(Integer zipCode) {
		return ((RouteDAO) factory.getDAO(Strings.ROUTE_DAO)).getRoutes(zipCode);
	}

}

package com.tunisij.strategies.route;

import java.io.IOException;
import java.util.List;

import com.tunisij.businessObjects.RouteBO;

public class RouteContext {

	private RouteStrategy strategy;
	
	public RouteContext(RouteStrategy strategy) {
		this.strategy = strategy;
	}
	
	public List<RouteBO> executeFetch(Integer zipCode) throws IOException {
		return strategy.fetch(zipCode);
	}
}

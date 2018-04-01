package com.tunisij.strategies.route;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.scrapers.RouteScraper;

@Service
public class ExternalRouteStrategy implements RouteStrategy {

	@Override
	public List<RouteBO> fetch(Integer zipCode) throws IOException {
		return new RouteScraper().getRoutesForZipCode(zipCode);
	}

}

package com.tunisij.strategies.route;

import java.io.IOException;
import java.util.List;

import com.tunisij.businessObjects.RouteBO;

public interface RouteStrategy {

	public List<RouteBO> fetch (Integer zipCode) throws IOException;
}

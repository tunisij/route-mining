package com.tunisij.forms;

import java.util.List;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.businessObjects.ZipCodeBO;

public class ZipCodeForm {

	private Integer zipCode;
	private Integer distance;
	private List<ZipCodeBO> zipCodes;
	private List<RouteBO> routes;
	private Double price;
	private Double budget;

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public List<ZipCodeBO> getZipCodes() {
		return zipCodes;
	}

	public void setZipCodes(List<ZipCodeBO> zipCodes) {
		this.zipCodes = zipCodes;
	}

	public List<RouteBO> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteBO> routes) {
		this.routes = routes;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

}

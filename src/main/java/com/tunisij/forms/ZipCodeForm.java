package com.tunisij.forms;

import java.util.List;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.businessObjects.ZipCodeBO;

public class ZipCodeForm {

	private Integer zipCode;
	private Integer distance;
	private List<ZipCodeBO> zipCodes;
	private List<RouteBO> routes;
	private List<String> selectedZipCodes;
	private List<String> selectedRoutes;
	private Double price;
	private Double budget;
	private Integer houseValueLower;
	private Integer houseValueUpper;
	private Integer incomeLower;
	private Integer incomeUpper;

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

	public List<RouteBO> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteBO> routes) {
		this.routes = routes;
	}

	public List<String> getSelectedZipCodes() {
		return selectedZipCodes;
	}

	public void setSelectedZipCodes(List<String> selectedZipCodes) {
		this.selectedZipCodes = selectedZipCodes;
	}
	
	public List<String> getSelectedRoutes() {
		return selectedRoutes;
	}

	public void setSelectedRoutes(List<String> selectedRoutes) {
		this.selectedRoutes = selectedRoutes;
	}

	public Integer getHouseValueLower() {
		return houseValueLower;
	}

	public void setHouseValueLower(Integer houseValueLower) {
		this.houseValueLower = houseValueLower;
	}

	public Integer getHouseValueUpper() {
		return houseValueUpper;
	}

	public void setHouseValueUpper(Integer houseValueUpper) {
		this.houseValueUpper = houseValueUpper;
	}

	public Integer getIncomeLower() {
		return incomeLower;
	}

	public void setIncomeLower(Integer incomeLower) {
		this.incomeLower = incomeLower;
	}

	public Integer getIncomeUpper() {
		return incomeUpper;
	}

	public void setIncomeUpper(Integer incomeUpper) {
		this.incomeUpper = incomeUpper;
	}

}

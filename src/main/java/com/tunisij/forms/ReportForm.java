package com.tunisij.forms;

import java.util.List;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.businessObjects.ZipCodeBO;

public class ReportForm {

	private List<ZipCodeBO> zipCodes;
	private List<RouteBO> routes;
	private Double price;
	private Double budget;
	private Integer totalDeliveries;
	private Integer houseValueLower;
	private Integer houseValueUpper;
	private Integer incomeLower;
	private Integer incomeUpper;

	public Double getTotalCost() {
		return totalDeliveries * price;
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

	public Integer getTotalDeliveries() {
		return totalDeliveries;
	}

	public void setTotalDeliveries(Integer totalDeliveries) {
		this.totalDeliveries = totalDeliveries;
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

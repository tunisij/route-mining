package com.tunisij.forms;

import java.util.List;

import com.tunisij.businessObjects.ZipCodeBO;

public class ZipCodeForm {

	private Integer zipCode;
	private Integer distance;
	private List<ZipCodeBO> zipCodes;
	private List<String> selectedRoutes;
	private List<String> selectedZipCodes;
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

	public List<String> getSelectedRoutes() {
		return selectedRoutes;
	}

	public void setSelectedRoutes(List<String> selectedRoutes) {
		this.selectedRoutes = selectedRoutes;
	}

	public List<String> getSelectedZipCodes() {
		return selectedZipCodes;
	}

	public void setSelectedZipCodes(List<String> selectedZipCodes) {
		this.selectedZipCodes = selectedZipCodes;
	}

}

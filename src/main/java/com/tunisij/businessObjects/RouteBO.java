package com.tunisij.businessObjects;

public class RouteBO {

	private Integer zipCode;
	private String route;
	private String type;
	private String countyCode;
	private Integer businessCount;
	private Integer apartmentCount;
	private Integer poBoxCount;
	private Integer residentialCount;
	private Integer totalDeliveries;
	private Integer avgHouseholdIncome;
	private Integer avgPropertyValue;

	public RouteBO(Integer zipCode) {
		this.zipCode = zipCode;
	}
	
	public Integer getZipCode() {
		return zipCode;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public Integer getBusinessCount() {
		return businessCount;
	}

	public void setBusinessCount(Integer businessCount) {
		this.businessCount = businessCount;
	}

	public Integer getApartmentCount() {
		return apartmentCount;
	}

	public void setApartmentCount(Integer apartmentCount) {
		this.apartmentCount = apartmentCount;
	}

	public Integer getPoBoxCount() {
		return poBoxCount;
	}

	public void setPoBoxCount(Integer poBoxCount) {
		this.poBoxCount = poBoxCount;
	}

	public Integer getResidentialCount() {
		return residentialCount;
	}

	public void setResidentialCount(Integer residentialCount) {
		this.residentialCount = residentialCount;
	}

	public Integer getTotalDeliveries() {
		return totalDeliveries;
	}

	public void setTotalDeliveries(Integer totalDeliveries) {
		this.totalDeliveries = totalDeliveries;
	}

	public Integer getAvgHouseholdIncome() {
		return avgHouseholdIncome;
	}

	public void setAvgHouseholdIncome(Integer avgHouseholdIncome) {
		this.avgHouseholdIncome = avgHouseholdIncome;
	}

	public Integer getAvgPropertyValue() {
		return avgPropertyValue;
	}

	public void setAvgPropertyValue(Integer avgPropertyValue) {
		this.avgPropertyValue = avgPropertyValue;
	}
	
	public String toString() {
		return "Zip Code: " + zipCode +
				"\nRoute: " + route +
				"\nType: " + type +
				"\nCounty Code: " + countyCode +
				"\nBusiness Count: " + businessCount +
				"\nApartment Count: " + apartmentCount +
				"\nPO Box: " + poBoxCount +
				"\nResidential Count: " + residentialCount +
				"\nTotal Deliveries: " + totalDeliveries + 
				"\nAverage Household Income: " + avgHouseholdIncome +
				"\nAverage Property Value: " + avgPropertyValue + "\n";
	}
}

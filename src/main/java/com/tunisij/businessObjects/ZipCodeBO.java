package com.tunisij.businessObjects;

public class ZipCodeBO {

	private Integer zipCode;
	private Integer population;
	private Integer avgHouseValue;
	private Integer householdIncome;
	private Double medianAge;
	private Integer numBusinesses;
	private Integer numEmployees;

	public ZipCodeBO(Integer zipCode) {
		this.zipCode = zipCode;
	}
	
	public Integer getZipCode() {
		return zipCode;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Integer getAvgHouseValue() {
		return avgHouseValue;
	}

	public void setAvgHouseValue(Integer avgHouseValue) {
		this.avgHouseValue = avgHouseValue;
	}

	public Integer getHouseholdIncome() {
		return householdIncome;
	}

	public void setHouseholdIncome(Integer householdIncome) {
		this.householdIncome = householdIncome;
	}

	public Double getMedianAge() {
		return medianAge;
	}

	public void setMedianAge(Double medianAge) {
		this.medianAge = medianAge;
	}

	public Integer getNumBusinesses() {
		return numBusinesses;
	}

	public void setNumBusinesses(Integer numBusinesses) {
		this.numBusinesses = numBusinesses;
	}

	public Integer getNumEmployees() {
		return numEmployees;
	}

	public void setNumEmployees(Integer numEmployees) {
		this.numEmployees = numEmployees;
	}
	
	public String toString() {
		return "Zip Code: " + zipCode +
				"\nAverage House Value: " + avgHouseValue +
				"\nHousehold Income: " + householdIncome +
				"\nMedian Age: " + medianAge +
				"\nNum Businesses: " + numBusinesses + 
				"\nNum Employees: " + numEmployees +
				"\nPopulation: " + population + "\n";
	}
	
}

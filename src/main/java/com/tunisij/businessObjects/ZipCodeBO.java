package com.tunisij.businessObjects;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ZipCodeBO implements Comparable<ZipCodeBO> {

	@NonNull private Integer zipCode;
	private Integer population;
	private Integer avgHouseValue;
	private Integer householdIncome;
	private Double medianAge;
	private Integer numBusinesses;
	private Integer numEmployees;
	private List<RouteBO> routes;

	@Override
	public int compareTo(ZipCodeBO zipCodeBO) {
		return this.getZipCode().compareTo(zipCodeBO.getZipCode());
	}
	
}

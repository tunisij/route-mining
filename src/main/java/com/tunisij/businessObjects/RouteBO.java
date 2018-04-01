package com.tunisij.businessObjects;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class RouteBO {

	@NonNull private Integer zipCode;
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

	public String getKey() {
		return zipCode + ":" + route;
	}
	
}

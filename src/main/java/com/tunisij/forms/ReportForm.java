package com.tunisij.forms;

import java.util.List;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.businessObjects.ZipCodeBO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	private String errors;

	public Double getTotalCost() {
		return totalDeliveries * price;
	}

}

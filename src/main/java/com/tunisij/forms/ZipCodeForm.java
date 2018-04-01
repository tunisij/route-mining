package com.tunisij.forms;

import java.util.List;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.businessObjects.ZipCodeBO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	private String errors;

}

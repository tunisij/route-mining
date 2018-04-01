package com.tunisij.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.businessObjects.ZipCodeBO;
import com.tunisij.common.Strings;
import com.tunisij.forms.ZipCodeForm;
import com.tunisij.services.RouteService;
import com.tunisij.services.ZipCodeService;

@Controller
public class HomeController {
	
	@Autowired
	private RouteService routeService;
	
	@Autowired
	private ZipCodeService zipCodeService;
	
	@RequestMapping("/")
	public String welcomeHome(Model model) {
		model.addAttribute("zipCodeForm", new ZipCodeForm());
		return "welcome";
	}
	
	@RequestMapping(value = "/processForm", method=RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute("zipCodeForm") ZipCodeForm form) {
		List<ZipCodeBO> zipCodes = new ArrayList<>();
		try {
			zipCodes = routeService.populateRoutesForZipCodes(zipCodeService.getZipCodes(form.getZipCode(), form.getDistance()));
		} catch (IOException e) {
			form.setErrors(Strings.MELISSA_DATA_ERROR);
		}
		
		Map<String, List<RouteBO>> separatedRoutes = separateSelectedRoutes(form.getSelectedZipCodes(), zipCodes);
		
		Collections.sort(zipCodes);
		form.setZipCodes(zipCodes);
		form.setRoutes(separatedRoutes.get(Strings.SELECTED));
		return new ModelAndView("welcome", "zipCodeForm", form);
	}
	
	@RequestMapping(value = "/processForm", method=RequestMethod.POST, params="rightArrow")
	public ModelAndView rightArrow(@ModelAttribute("zipCodeForm") ZipCodeForm form) {
		List<ZipCodeBO> zipCodes = new ArrayList<>();
		try {
			zipCodes = routeService.populateRoutesForZipCodes(zipCodeService.getZipCodes(form.getZipCode(), form.getDistance()));
		} catch (IOException e) {
			form.setErrors(Strings.MELISSA_DATA_ERROR);
		}
		
		Map<String, List<RouteBO>> separatedRoutes = separateSelectedRoutes(form.getSelectedZipCodes(), zipCodes);
		
		Collections.sort(zipCodes);
		form.setZipCodes(zipCodes);
		form.setRoutes(separatedRoutes.get(Strings.SELECTED));
		return new ModelAndView("welcome", "zipCodeForm", form);
	}
	
	@RequestMapping(value = "/processForm", method=RequestMethod.POST, params="autoSelect")
	public ModelAndView autoSelect(@ModelAttribute("zipCodeForm") ZipCodeForm form) {
		List<ZipCodeBO> zipCodes = new ArrayList<>();
		try {
			zipCodes = routeService.populateRoutesForZipCodes(zipCodeService.getZipCodes(form.getZipCode(), form.getDistance()));
		} catch (IOException e) {
			form.setErrors(Strings.MELISSA_DATA_ERROR);
		}
		List<String> selectedKeys = autoSelectRoutes(form, zipCodes);
		
		//maybe use the builder pattern for autoSelectRoutes?
		//maybe use decorator for house value range and income range have value?
		
		Collections.sort(zipCodes);
		form.setZipCodes(zipCodes);
		form.setRoutes(routeService.getAllRoutesByKey(selectedKeys, zipCodes));
		form.setSelectedZipCodes(zipCodeService.getSelectedZipCodesByKey(selectedKeys));
		form.setSelectedRoutes(selectedKeys);
		
		return new ModelAndView("welcome", "zipCodeForm", form);
	}
	
	private List<String> autoSelectRoutes(ZipCodeForm form, List<ZipCodeBO> zipCodes) {
		List<String> selectedRoutes = new ArrayList<>();
		Double selectedPriceTotal = 0.0;
		Double budget = form.getBudget();
		Double price = form.getPrice();
		
		if (budget == null || price == null) {
			return selectedRoutes;
		}
		
		for (ZipCodeBO zipCodeBO : zipCodes) {
			for (RouteBO routeBO : zipCodeBO.getRoutes()) {
				if (isIncomeValid(form, routeBO.getAvgHouseholdIncome()) && isPropertyValueValid(form, routeBO.getAvgPropertyValue())) {
					if (Double.compare(budget - (selectedPriceTotal + (price * routeBO.getTotalDeliveries())), 0.0) >= 0) {
						selectedRoutes.add(routeBO.getKey());
						selectedPriceTotal += price * routeBO.getTotalDeliveries();
					}
				}
			}
		}
		
		return selectedRoutes;
		
	}
	
	private boolean isIncomeValid(ZipCodeForm form, Integer avgHouseholdIncome) {
		Integer incomeLower = form.getIncomeLower();
		Integer incomeUpper = form.getIncomeUpper();
		
		if (incomeLower == null && incomeUpper == null) {
			return true;
		} else if (incomeLower != null && incomeUpper != null) {
			return incomeLower <= avgHouseholdIncome && incomeUpper >= avgHouseholdIncome;
		} else if (incomeLower != null) {
			return incomeLower <= avgHouseholdIncome;
		} else if (incomeUpper != null) {
			return incomeUpper >= avgHouseholdIncome;
		}
		
		return false;
	}
	
	private boolean isPropertyValueValid(ZipCodeForm form, Integer avgHouseholdValue) {
		Integer houseValueLower = form.getHouseValueLower();
		Integer houseValueUpper = form.getHouseValueUpper();
		
		if (houseValueLower == null && houseValueUpper == null) {
			return true;
		} else if (houseValueLower != null && houseValueUpper != null) {
			return houseValueLower <= avgHouseholdValue && houseValueUpper >= avgHouseholdValue;
		} else if (houseValueLower != null) {
			return houseValueLower <= avgHouseholdValue;
		} else if (houseValueUpper != null) {
			return houseValueUpper >= avgHouseholdValue;
		}
		
		return false;
	}
	
	private Map<String, List<RouteBO>> separateSelectedRoutes(List<String> selectedZipCodes, List<ZipCodeBO> zipCodes) {
		Map<String, List<RouteBO>> separatedRoutes = new HashMap<>();
		List<RouteBO> unselected = new ArrayList<>();
		List<RouteBO> selected = new ArrayList<>();
		
		if (selectedZipCodes == null) {
			return separatedRoutes;
		}

		for (ZipCodeBO zipCodeBO : zipCodes) {
			if (selectedZipCodes.contains(zipCodeBO.getZipCode().toString())) {
				selected.addAll(zipCodeBO.getRoutes());
			} else {
				unselected.addAll(zipCodeBO.getRoutes());
			}
		}
		
		separatedRoutes.put(Strings.SELECTED, selected);
		separatedRoutes.put(Strings.UNSELECTED, unselected);
		
		return separatedRoutes;
	}
	
}

package com.tunisij.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.businessObjects.ZipCodeBO;
import com.tunisij.forms.ZipCodeForm;
import com.tunisij.services.RouteService;
import com.tunisij.services.ZipCodeService;

@Controller
public class HomeController {
	
	@Autowired
	private RouteService routeService;
	
	@Autowired
	private ZipCodeService zipCodeService;
	
	private static final String SELECTED = "SELECTED";
	private static final String UNSELECTED = "UNSELECTED";
	
	@RequestMapping("/")
	public String welcomeHome(Model model) {
		model.addAttribute("zipCodeForm", new ZipCodeForm());
		return "welcome";
	}
	
	@RequestMapping(value = "/processForm", method=RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute("zipCodeForm") ZipCodeForm form) {
		List<ZipCodeBO> zipCodes = routeService.populateRoutesForZipCodes(zipCodeService.getZipCodes(form.getZipCode(), form.getDistance()));
		
		Map<String, List<RouteBO>> separatedRoutes = separateSelectedRoutes(form.getSelectedZipCodes(), zipCodes);
		
		Collections.sort(zipCodes);
		form.setZipCodes(zipCodes);
		form.setSelectedRoutesList(separatedRoutes.get(SELECTED));
		return new ModelAndView("welcome", "zipCodeForm", form);
	}
	
	@RequestMapping(value = "/processForm", method=RequestMethod.POST, params="rightArrow")
	public ModelAndView rightArrow(@ModelAttribute("zipCodeForm") ZipCodeForm form) {
		List<ZipCodeBO> zipCodes = routeService.populateRoutesForZipCodes(zipCodeService.getZipCodes(form.getZipCode(), form.getDistance()));
		
		Map<String, List<RouteBO>> separatedRoutes = separateSelectedRoutes(form.getSelectedZipCodes(), zipCodes);
		
		Collections.sort(zipCodes);
		form.setZipCodes(zipCodes);
		form.setSelectedRoutesList(separatedRoutes.get(SELECTED));
		return new ModelAndView("welcome", "zipCodeForm", form);
	}
	
	@RequestMapping(value = "/processForm", method=RequestMethod.POST, params="autoSelect")
	public ModelAndView autoSelect(@ModelAttribute("zipCodeForm") ZipCodeForm form) {
		List<ZipCodeBO> zipCodes = routeService.populateRoutesForZipCodes(zipCodeService.getZipCodes(form.getZipCode(), form.getDistance()));
		
		Map<String, List<RouteBO>> separatedRoutes = separateSelectedRoutes(form.getSelectedZipCodes(), zipCodes);
		
		Collections.sort(zipCodes);
		form.setZipCodes(zipCodes);
		form.setSelectedRoutesList(separatedRoutes.get(SELECTED));
		return new ModelAndView("welcome", "zipCodeForm", form);
	}
	
	@RequestMapping(value = "/processForm", method=RequestMethod.POST, params="generateReport")
	public ModelAndView generateReport(@ModelAttribute("zipCodeForm") ZipCodeForm form) {
		List<ZipCodeBO> zipCodes = routeService.populateRoutesForZipCodes(zipCodeService.getZipCodes(form.getZipCode(), form.getDistance()));
		
		Map<String, List<RouteBO>> separatedRoutes = separateSelectedRoutes(form.getSelectedZipCodes(), zipCodes);
		
		Collections.sort(zipCodes);
		form.setZipCodes(zipCodes);
		form.setSelectedRoutesList(separatedRoutes.get(SELECTED));
		return new ModelAndView("welcome", "zipCodeForm", form);
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
		
		separatedRoutes.put(SELECTED, selected);
		separatedRoutes.put(UNSELECTED, unselected);
		
		return separatedRoutes;
	}
	
}

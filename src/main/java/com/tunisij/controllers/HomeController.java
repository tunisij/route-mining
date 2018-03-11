package com.tunisij.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping("/")
	public String welcomeHome(Model model) {
		model.addAttribute("zipCodeForm", new ZipCodeForm());
		return "welcome";
	}
	
	@RequestMapping(value = "/processForm", method=RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute("zipCodeForm") ZipCodeForm form) {
		List<ZipCodeBO> zipCodes = zipCodeService.getZipCodes(form.getZipCode(), form.getDistance());
		Collections.sort(zipCodes);
		
		form.setZipCodes(zipCodes);
		form.setRoutes(routeService.getRoutes(zipCodes));
		return new ModelAndView("welcome", "zipCodeForm", form);
	}
	
}

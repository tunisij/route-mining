package com.tunisij.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		form.setZipCodes(Arrays.asList(zipCodeService.getZipCode(form.getZipCode())));
		form.setRoutes(routeService.getRoutes(form.getZipCode()));
		return new ModelAndView("welcome", "zipCodeForm", form);
	}
}

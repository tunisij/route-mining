package com.tunisij.controllers;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tunisij.daos.RouteDAO;
import com.tunisij.daos.ZipCodeDAO;
import com.tunisij.forms.ZipCodeForm;

@Controller
public class HomeController {
	
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping("/")
	public String welcomeHome(Model model) {
		model.addAttribute("zipCodeForm", new ZipCodeForm());
		return "welcome";
	}
	
	@RequestMapping(value = "/processForm", method=RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute("zipCodeForm") ZipCodeForm form) {
		form.setZipCodes(Arrays.asList(new ZipCodeDAO(dataSource).getZipCode(form.getZipCode())));
		form.setRoutes(new RouteDAO(dataSource).getRoutes(form.getZipCode()));
		
		return new ModelAndView("welcome", "zipCodeForm", form);
	}
}

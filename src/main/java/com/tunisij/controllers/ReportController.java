package com.tunisij.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.businessObjects.ZipCodeBO;
import com.tunisij.forms.ReportForm;
import com.tunisij.forms.ZipCodeForm;
import com.tunisij.services.RouteService;
import com.tunisij.services.ZipCodeService;

@Controller
public class ReportController {

	@Autowired
	private RouteService routeService;

	@Autowired
	private ZipCodeService zipCodeService;

	@RequestMapping(value = "/processForm", method = RequestMethod.POST, params = "generateReport")
	public ModelAndView generateReport(@ModelAttribute("zipCodeForm") ZipCodeForm form) {
		ReportForm reportForm = new ReportForm();
		List<ZipCodeBO> zipCodes = routeService.populateRoutesForZipCodes(zipCodeService.getZipCodes(form.getZipCode(), form.getDistance(), form.getSelectedZipCodes()));
		List<RouteBO> routes = routeService.getSelectedRoutesByKey(form.getSelectedRoutes(), zipCodes);
		
		reportForm.setBudget(form.getBudget());
		reportForm.setPrice(form.getPrice());
		reportForm.setTotalDeliveries(getTotalDeliveries(routes));
		reportForm.setZipCodes(zipCodes);
		reportForm.setRoutes(routes);
		return new ModelAndView("report", "reportForm", reportForm);
	}
	
	private Integer getTotalDeliveries(List<RouteBO> routes) {
		Integer piecesDelivered = 0;
		
		for (RouteBO routeBO : routes) {
			piecesDelivered += routeBO.getTotalDeliveries();
		}
		
		return piecesDelivered;
	}
}

package com.tunisij.controllers;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tunisij.businessObjects.ZipCodeBO;
import com.tunisij.daos.ZipCodeDAO;

@Controller
public class HomeController {
	
//	@Autowired
//	private ZipCodeDAO dao;
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
//		ZipCodeBO zipCodeBO = dao.getZipCode(48082);
		ZipCodeBO zipCodeBO = new ZipCodeDAO(dataSource).getZipCode(48082);
		model.put("zipCode", zipCodeBO.getZipCode());
		model.put("avgHouseholdValue", zipCodeBO.getAvgHouseValue());
		model.put("householdIncome", zipCodeBO.getHouseholdIncome());
		model.put("medianAge", zipCodeBO.getMedianAge());
		model.put("numBusiness", zipCodeBO.getNumBusinesses());
		model.put("numEmployee", zipCodeBO.getNumEmployees());
		model.put("population", zipCodeBO.getPopulation());
		return "welcome";
	}
}

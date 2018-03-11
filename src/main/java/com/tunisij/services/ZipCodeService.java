package com.tunisij.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunisij.businessObjects.ZipCodeBO;
import com.tunisij.common.Strings;
import com.tunisij.daos.ZipCodeDAO;
import com.tunisij.factories.DaoFactory;
import com.tunisij.scrapers.ZipCodeScraper;

@Service
public class ZipCodeService {

	@Autowired
	private DaoFactory factory;
	
	public List<ZipCodeBO> getZipCodes(Integer zipCode, Integer distance) {
		List<ZipCodeBO> zipCodes = new ArrayList<>();
		ZipCodeDAO zipCodeDAO = (ZipCodeDAO) factory.getDAO(Strings.ZIP_CODE_DAO);
		
		for (Integer zip : getZipCodesInRadius(zipCode, distance)) {
			ZipCodeBO z = zipCodeDAO.getZipCode(zip);
			
			if (z == null) {
				z = getZipCodeData(zip);
				zipCodeDAO.insert(z);
			}
			
			zipCodes.add(z);
		}
		
		return zipCodes;
	}
	
	private ZipCodeBO getZipCodeData(Integer zipCode) {
		return new ZipCodeScraper().getZipCodeData(zipCode);
	}
	
	private List<Integer> getZipCodesInRadius(Integer zipCode, Integer distance) {
		return new ZipCodeScraper().getZipCodesInRadius(zipCode, distance);
	}
	
}

package com.tunisij.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunisij.businessObjects.ZipCodeBO;
import com.tunisij.common.Strings;
import com.tunisij.daos.ZipCodeDAO;
import com.tunisij.factories.DaoFactory;
import com.tunisij.scrapers.ZipCodeScraper;
import com.tunisij.strategies.zipCode.ExternalZipCodeStrategy;
import com.tunisij.strategies.zipCode.LocalZipCodeStrategy;
import com.tunisij.strategies.zipCode.ZipCodeContext;

@Service
public class ZipCodeService {

	@Autowired
	private DaoFactory factory;
	
	@Autowired
	private LocalZipCodeStrategy localZipCodeStrategy;
	
	@Autowired
	private ExternalZipCodeStrategy externalZipCodeStrategy;
	
	public List<ZipCodeBO> getZipCodes(Integer zipCode, Integer distance) throws IOException {
		return getZipCodes(zipCode, distance, null);
	}
	
	//Tries local strategy first. If local returns no data, executes external strategy
	public List<ZipCodeBO> getZipCodes(Integer zipCode, Integer distance, List<String> selectedZipCodes) throws IOException {
		List<ZipCodeBO> zipCodes = new ArrayList<>();
		ZipCodeContext localContext = new ZipCodeContext(localZipCodeStrategy);
		ZipCodeContext externalContext = new ZipCodeContext(externalZipCodeStrategy);
		ZipCodeDAO zipCodeDAO = (ZipCodeDAO) factory.getDAO(Strings.ZIP_CODE_DAO);
		
		for (Integer zip : getZipCodesInRadius(zipCode, distance)) {
			if (selectedZipCodes == null || selectedZipCodes.isEmpty() || selectedZipCodes.contains(zip.toString())) {
				ZipCodeBO z = localContext.executeFetch(zip);
				
				if (z == null) {
					z = externalContext.executeFetch(zip);
					zipCodeDAO.insert(z);
				}
				
				zipCodes.add(z);
			}
		}
		
		return zipCodes;
	}
	
	public List<String> getSelectedZipCodesByKey(List<String> selectedKeys) {
		List<String> zipCodes = new ArrayList<>();
		
		for (String key : selectedKeys) {
			String zipCode = key.substring(0, key.indexOf(":"));
			if (!zipCodes.contains(zipCode)) {
				zipCodes.add(zipCode);
			}
		}
		
		return zipCodes;
	}
	
	private List<Integer> getZipCodesInRadius(Integer zipCode, Integer distance) {
		return new ZipCodeScraper().getZipCodesInRadius(zipCode, distance);
	}
	
}

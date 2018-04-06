package com.tunisij.strategies.zipCode;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.tunisij.businessObjects.ZipCodeBO;
import com.tunisij.scrapers.ZipCodeScraper;

//Implementation of zipCodeStrategy
@Service
public class ExternalZipCodeStrategy implements ZipCodeStrategy {

	@Override
	public ZipCodeBO fetch(Integer zipCode) throws IOException {
		return new ZipCodeScraper().getZipCodeData(zipCode);
	}
}

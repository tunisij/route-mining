package com.tunisij.strategies.zipCode;

import java.io.IOException;

import com.tunisij.businessObjects.ZipCodeBO;

public class ZipCodeContext {
	private ZipCodeStrategy strategy;
	
	public ZipCodeContext(ZipCodeStrategy strategy) {
		this.strategy = strategy;
	}
	
	public ZipCodeBO executeFetch(Integer zipCode) throws IOException {
		return strategy.fetch(zipCode);
	}

}

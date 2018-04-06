package com.tunisij.strategies.zipCode;

import java.io.IOException;

import com.tunisij.businessObjects.ZipCodeBO;

//interface for strategy pattern
public interface ZipCodeStrategy {
	public ZipCodeBO fetch (Integer zipCode) throws IOException;
}

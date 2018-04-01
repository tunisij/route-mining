package com.tunisij.strategies.zipCode;

import java.io.IOException;

import com.tunisij.businessObjects.ZipCodeBO;

public interface ZipCodeStrategy {
	public ZipCodeBO fetch (Integer zipCode) throws IOException;
}

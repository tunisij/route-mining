package com.tunisij.strategies.zipCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunisij.businessObjects.ZipCodeBO;
import com.tunisij.common.Strings;
import com.tunisij.daos.ZipCodeDAO;
import com.tunisij.factories.DaoFactory;

//Implementation of zipCodeStrategy
@Service
public class LocalZipCodeStrategy implements ZipCodeStrategy {

	@Autowired
	private DaoFactory factory;
	
	@Override
	public ZipCodeBO fetch(Integer zipCode) {
		return ((ZipCodeDAO) factory.getDAO(Strings.ZIP_CODE_DAO)).getZipCode(zipCode);
	}

}

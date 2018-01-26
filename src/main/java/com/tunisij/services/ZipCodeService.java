package com.tunisij.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunisij.businessObjects.ZipCodeBO;
import com.tunisij.common.Strings;
import com.tunisij.daos.ZipCodeDAO;
import com.tunisij.factories.DaoFactory;

@Service
public class ZipCodeService {

	@Autowired
	private DaoFactory factory;
	
	public ZipCodeBO getZipCode(Integer zipCode) {
		return ((ZipCodeDAO) factory.getDAO(Strings.ZIP_CODE_DAO)).getZipCode(zipCode);
	}
}

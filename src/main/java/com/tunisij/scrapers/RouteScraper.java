package com.tunisij.scrapers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tunisij.businessObjects.RouteBO;
import com.tunisij.businessObjects.ZipCodeBO;

public class RouteScraper extends BaseScraper {
	
	private static final String SITE_URL = "http://www.melissadata.com/lookups/MapCartS.asp?zip=";

	public List<RouteBO> getRoutesForZipCodes(List<ZipCodeBO> zipCodes) {
		List<RouteBO> routes = new ArrayList<>();
		for (ZipCodeBO zipCodeBO : zipCodes) {
			routes.addAll(getRoutesForZipCode(zipCodeBO.getZipCode()));
		}
		return routes;
	}
	
	public List<RouteBO> getRoutesForZipCode(Integer zipCode) {
		List<RouteBO> routeList = new ArrayList<>();
		Document document = null;
		
		try {
			document = Jsoup.connect(SITE_URL + zipCode).timeout(0).get();
			Element element = document.getElementsByClass("Tableresultborder").first();
			
			if (element == null) {
				return routeList;
			}
			
			Elements elements = element.getElementsByTag("tr");

			for (int i = 4; i < elements.size()-2; i++) {
				Elements routeElements = elements.get(i).children();
				
				RouteBO routeBO = new RouteBO(zipCode);
				routeBO.setRoute(routeElements.get(0).text());
				routeBO.setType(routeElements.get(1).text());
				routeBO.setCountyCode(routeElements.get(2).text().replaceAll("[^0-9]",""));
				routeBO.setBusinessCount(Integer.parseInt(routeElements.get(3).text().replaceAll("[^0-9]","")));
				routeBO.setApartmentCount(Integer.parseInt(routeElements.get(4).text().replaceAll("[^0-9]","")));
				routeBO.setPoBoxCount(Integer.parseInt(routeElements.get(5).text().replaceAll("[^0-9]","")));
				routeBO.setResidentialCount(Integer.parseInt(routeElements.get(6).text().replaceAll("[^0-9]","")));
				routeBO.setTotalDeliveries(Integer.parseInt(routeElements.get(7).text().replaceAll("[^0-9]","")));
				routeBO.setAvgHouseholdIncome(Integer.parseInt(routeElements.get(8).text().replaceAll("[^0-9]","")));
				routeBO.setAvgPropertyValue(Integer.parseInt(routeElements.get(9).text().replaceAll("[^0-9]","")));
				routeList.add(routeBO);
			}
		} catch (IOException e) {
			logger.log(e.getMessage(), e);
		}
		
		return routeList;
	}
}

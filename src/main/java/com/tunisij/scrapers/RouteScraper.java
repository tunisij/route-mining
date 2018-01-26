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

//	public static void main(String[] args) {
//		ZipCodeScraper zs = new ZipCodeScraper();
//		RouteScraper rs = new RouteScraper();
//		for (RouteBO route : rs.getRoutesForZipCodes(zs.getZipCodesInRadiusOfZipCode(48082, 10))) {
//			System.out.println(route);
//		}
//	}
	
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
			
			Elements elements = element.getElementsByTag("tr");
			for (int i = 4; i < elements.size()-2; i++) {
				String[] routeInfo = elements.get(i).text().split(" ");
				
				RouteBO routeBO = new RouteBO(zipCode);
				routeBO.setRoute(routeInfo[0]);
				routeBO.setType(routeInfo[1]);
				routeBO.setCountyCode(routeInfo[2].replaceAll("[^0-9]",""));
				routeBO.setBusinessCount(Integer.parseInt(routeInfo[3].replaceAll("[^0-9]","")));
				routeBO.setApartmentCount(Integer.parseInt(routeInfo[4].replaceAll("[^0-9]","")));
				routeBO.setPoBoxCount(Integer.parseInt(routeInfo[5].replaceAll("[^0-9]","")));
				routeBO.setResidentialCount(Integer.parseInt(routeInfo[6].replaceAll("[^0-9]","")));
				routeBO.setTotalDeliveries(Integer.parseInt(routeInfo[7].replaceAll("[^0-9]","")));
				routeBO.setAvgHouseholdIncome(Integer.parseInt(routeInfo[8].replaceAll("[^0-9]","")));
				routeBO.setAvgPropertyValue(Integer.parseInt(routeInfo[9].replaceAll("[^0-9]","")));
				routeList.add(routeBO);
				System.out.println(routeBO.toString());
			}
		} catch (IOException e) {
			logger.log(e.getMessage());
		}
		
		return routeList;
	}
}

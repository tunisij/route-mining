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
import com.tunisij.common.Strings;

public class RouteScraper extends BaseScraper {
	
	public List<RouteBO> getRoutesForZipCodes(List<ZipCodeBO> zipCodes) throws IOException {
		List<RouteBO> routes = new ArrayList<>();
		for (ZipCodeBO zipCodeBO : zipCodes) {
			routes.addAll(getRoutesForZipCode(zipCodeBO.getZipCode()));
		}
		return routes;
	}
	
	public List<RouteBO> getRoutesForZipCode(Integer zipCode) throws IOException {
		List<RouteBO> routeList = new ArrayList<>();
		Document document = null;
		
		document = Jsoup.connect(Strings.SITE_URL + zipCode).timeout(0).get();
		Element element = document.getElementsByClass("Tableresultborder").first();
		
		if (element == null) {
			return routeList;
		}
		
		Elements elements = element.getElementsByTag("tr");

		for (int i = 4; i < elements.size()-2; i++) {
			Elements routeElements = elements.get(i).children();
			
			RouteBO routeBO = RouteBO.builder()
					.zipCode(zipCode)
					.route(routeElements.get(0).text())
					.type(routeElements.get(1).text())
					.countyCode(routeElements.get(2).text().replaceAll("[^0-9]",""))
					.businessCount(Integer.parseInt(routeElements.get(3).text().replaceAll("[^0-9]","")))
					.apartmentCount(Integer.parseInt(routeElements.get(4).text().replaceAll("[^0-9]","")))
					.poBoxCount(Integer.parseInt(routeElements.get(5).text().replaceAll("[^0-9]","")))
					.residentialCount(Integer.parseInt(routeElements.get(6).text().replaceAll("[^0-9]","")))
					.totalDeliveries(Integer.parseInt(routeElements.get(7).text().replaceAll("[^0-9]","")))
					.avgHouseholdIncome(Integer.parseInt(routeElements.get(8).text().replaceAll("[^0-9]","")))
					.avgPropertyValue(Integer.parseInt(routeElements.get(9).text().replaceAll("[^0-9]","")))
					.build();
			routeList.add(routeBO);
		}
		
		return routeList;
	}
}

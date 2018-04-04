package com.tunisij.scrapers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.tunisij.businessObjects.ZipCodeBO;
import com.tunisij.common.Strings;

public class ZipCodeScraper extends BaseScraper {
	
	public ZipCodeBO getZipCodeData(Integer zipCodeInt) throws IOException {
		ZipCodeBO zipCodeBO = ZipCodeBO.builder().zipCode(zipCodeInt).build();
		String fullUrl = Strings.ZIP_CODE_LOOKUP_URL + Strings.TYPE_ZIP_2_CITY + Strings.ZIP_SEARCH + zipCodeInt;
		Document document = null;
		
		document = Jsoup.connect(fullUrl).timeout(0).get();
		Elements elements = document.getElementsByTag("table").get(14).getElementsByTag("tr");
		
		Element popElement = elements.select("tr:contains(Population)").first();
		zipCodeBO.setPopulation(popElement != null ? Integer.parseInt(popElement.getElementsByIndexEquals(1).text().replaceAll("[^0-9]","")) : null);
		
		Element ahvElement = elements.select("tr:contains(Average House Value)").first();
		zipCodeBO.setAvgHouseValue(ahvElement != null ? Integer.parseInt(ahvElement.getElementsByIndexEquals(1).text().replaceAll("[^0-9]","")) : null);
		
		Element hiElement = elements.select("tr:contains(Income Per Household)").first();
		zipCodeBO.setHouseholdIncome(hiElement != null ? Integer.parseInt(hiElement.getElementsByIndexEquals(1).text().replaceAll("[^0-9]","")) : null);
		
		Element maElement = elements.select("tr:contains(Median Age)").first();
		zipCodeBO.setMedianAge(maElement != null ? Double.parseDouble(maElement.getElementsByIndexEquals(1).text().replaceAll("[^0-9\\.]","")) : null);
		
		Element nbElement = elements.select("tr:contains(Number Of Businesses)").first();
		zipCodeBO.setNumBusinesses(nbElement != null ? Integer.parseInt(nbElement.getElementsByIndexEquals(1).text().replaceAll("[^0-9]","")) : null);
		
		Element neElement = elements.select("tr:contains(Number Of Employees)").first();
		zipCodeBO.setNumEmployees(neElement != null ? Integer.parseInt(neElement.getElementsByIndexEquals(1).text().replaceAll("[^0-9]","")) : null);
		
		return zipCodeBO;
	}
	
	public List<Integer> getZipCodesInRadius(Integer zipCode, Integer distance) {
		List<Integer> zipCodes = new ArrayList<>();
		String fullUrl = Strings.ZIP_RADIUS_URL + Strings.TYPE_ZIP_RADIUS + Strings.ZIP_SEARCH + zipCode + Strings.DIST_SEARCH + distance;
		Document document = null;
		try {
			document = Jsoup.connect(fullUrl).timeout(0).get();
			Elements elements = document.getElementsByClass("Data");
			elements.forEach(element -> zipCodes.add(Integer.parseInt(((TextNode) element.getElementsByTag("a").first().childNode(0)).text())));
		} catch (IOException e) {
			logger.log(e.getMessage(), e);
		}
		
		return zipCodes;
	}
	
}

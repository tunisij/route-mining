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

public class ZipCodeScraper {
	
	private static final String ZIP_RADIUS_URL = "https://www.searchbug.com/tools/zip-radius.aspx?";
	private static final String ZIP_CODE_LOOKUP_URL = "https://www.searchbug.com/tools/zip-code-lookup.aspx?";
	private static final String TYPE_ZIP_RADIUS = "TYPE=zipradius";
	private static final String TYPE_ZIP_2_CITY = "TYPE=zip2city";
	private static final String ZIP_SEARCH = "&ZIP=";
	private static final String DIST_SEARCH = "&DIST=";
	
//	public static void main(String args[]) {
//		ZipCodeScraper sc = new ZipCodeScraper();
//		for (Integer zipCode : sc.getZipCodesInRadius(48082, 10)) {
//			System.out.println(sc.getZipCodeData(zipCode).toString());
//		}
//	}
	
	public List<ZipCodeBO> getZipCodesInRadiusOfZipCode(Integer zipCode, Integer range) {
		List<ZipCodeBO> zipCodeList = new ArrayList<>();
		for (Integer zipCodes : getZipCodesInRadius(zipCode, range)) {
			zipCodeList.add(getZipCodeData(zipCodes));
		}
		return zipCodeList;
	}
	
	public ZipCodeBO getZipCodeData(Integer zipCodeInt) {
		ZipCodeBO zipCodeBO = new ZipCodeBO(zipCodeInt);
		String fullUrl = ZIP_CODE_LOOKUP_URL + TYPE_ZIP_2_CITY + ZIP_SEARCH + zipCodeInt;
		Document document = null;
		
		try {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return zipCodeBO;
	}
	
	public List<Integer> getZipCodesInRadius(Integer zipCode, Integer distance) {
		List<Integer> zipCodes = new ArrayList<>();
		String fullUrl = ZIP_RADIUS_URL + TYPE_ZIP_RADIUS + ZIP_SEARCH + zipCode + DIST_SEARCH + distance;
		Document document = null;
		try {
			document = Jsoup.connect(fullUrl).get();
			Elements elements = document.getElementsByClass("Data");
			elements.forEach(element -> zipCodes.add(Integer.parseInt(((TextNode) element.getElementsByTag("a").first().childNode(0)).text())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return zipCodes;
	}
	
}

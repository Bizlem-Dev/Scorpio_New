package com.subjectheaders;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

public class subjectHeadersFinalResult {

	public static void main(String[] args) {
		
		String testString="EASTPORT CLEAN MR POSITION BSS S.KOREA AS AT 15th MAR 2019"; // regrex // TH
//		String testString="TNC Evening Dirty Report - 03.15.19";// regrex
//		String testString="DIETZE CPP WEEKLY FIXTURE REPORT";
//		String testString="CLARKSONS PLATOU - 15 MARCH 2019- USA CPP MARKET REPORT"; // working 
//		String testString="PANAMAX DPP CMED TONNAGE LIST UPDATED 15/03/19 *AMENDED*";
		
		JSONObject data=SubjectHeadersAllMethods.getUrlByStanbol(testString, null);
		//System.out.println(data);
		JSONArray finalArray=new JSONArray();
		finalArray.put(data);
		
		System.out.println(finalArray);
		
		
		
	}

}

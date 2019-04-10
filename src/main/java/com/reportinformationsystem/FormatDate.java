package com.reportinformationsystem;  

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;

  

public class FormatDate {	

	public static void main(String[] args) {
		FormatDate fd= new FormatDate();
		JSONArray arr= new JSONArray();
		
		fd.formatDateMain(); 		
		
	}

	public JSONArray formatDateMain() {
		JSONArray finalArr=null;

		try {
			String formarArr[]= {"dd MMM yyyy", "dd.MM.yy", "dd-MMM", "MMM-dd", "dd/MMM", "MMM/dd", "dd-MMM-yyyy", "dd-MM-yyyy"};
			JSONArray arr= new JSONArray();
			finalArr= new JSONArray();

			for(int i=0; i<formarArr.length; i++) {
				arr= new FormatDate().formatDate(formarArr[i]); 		
				for(int j=0; j<arr.length(); j++) {
					finalArr.put(arr.get(j));				
				}
			}
			System.out.println(finalArr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalArr;
	}
	public JSONArray formatDate(String date_format) {
		JSONArray dateArr = null;
		try {
			String format_date="";
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 2019);
			cal.set(Calendar.DAY_OF_YEAR, 1);    
			Date start = cal.getTime();

			//set date to last day of 2014
			cal.set(Calendar.YEAR, 2019);
			cal.set(Calendar.MONTH, 11); // 11 = december
			cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve

			Date end = cal.getTime();

			//Iterate through the two dates 
			GregorianCalendar gcal = new GregorianCalendar();
			gcal.setTime(start);
			dateArr= new JSONArray();
				format_date= getFormatedDate(date_format, gcal.getTime());
			    
			    dateArr.put(format_date);		    
			    while (gcal.getTime().before(end)) {
				    gcal.add(Calendar.DAY_OF_YEAR, 1);	    
				    
				    format_date= getFormatedDate(date_format, gcal.getTime());
				    
				    dateArr.put(format_date);
				    /*sdf = new SimpleDateFormat("dd MMM yyyy");
				    sdf = new SimpleDateFormat("dd.MM.yy");		    
				    sdf = new SimpleDateFormat("dd-MMM");		    
				    sdf = new SimpleDateFormat("MMM-dd");		    
				    sdf = new SimpleDateFormat("dd/MMM");		    
				    sdf = new SimpleDateFormat("MMM/dd");		    
				    sdf = new SimpleDateFormat("dd-MMM-yyyy");		    
				    sdf = new SimpleDateFormat("dd-MM-yyyy");*/
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateArr;
	}

	public String getFormatedDate(String date_format, Date date) {
		String format_date = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat(date_format);
	    format_date = sdf.format(date);  
	    System.out.println(date+"\n"+format_date);
		return format_date;
	}
}

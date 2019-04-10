package com.subjectheaders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

public class CombinePortAndDateSeparateLogic {
	
	public static void main(String[] args) {
		portIdentifiedJoint("3/USG /01/2019");
	}

	public static String portIdentifiedJoint(String JsonString){
		JSONObject data=null;
		String finalportanddatedata="";
		try {
			
			  Pattern id=null;
			  Matcher m=null;
			  String date="";
			  String portOnly="";
			  
			  id = Pattern.compile("(\\d{1,}\\/[A-Z]{1,}\\s?[A-Z]{1,}|[A-Z]{1,})");
			   m = id.matcher(JsonString);
		    	if (m.find()){
		    		portOnly = m.group(1);
		    		portOnly=portOnly.replaceAll("\\d", "" ).replace("/", "");
		    	     System.out.println("port:: "+portOnly);
		    	    date= dateIdentifiedJoint(JsonString);
		    	    
		    	     data=new JSONObject();
		    	     data.put("port", portOnly);
		    	     data.put("date", date);
		    	     finalportanddatedata=data.toString();
		    	     System.out.println("finalportanddatedata:: "+finalportanddatedata);
		    	}
			
			
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return "false";
		}
		return finalportanddatedata;
	}

	public static String dateIdentifiedJoint(String JsonString){
		 String dateOnly="";
		try {
			
			  Pattern id=null;
			  Matcher m=null;
			 
			  
			  id = Pattern.compile("(\\d{1,}[/,-]\\d{1,})");
			   m = id.matcher(JsonString);
		    	if (m.find()){
		    		dateOnly = m.group(1);
		    	    // System.out.println("date:: "+dateOnly);
		    	    
		    	}else{
		    		id = Pattern.compile("(\\d{1,}[/,-]\\d{1,}[/,-]\\d{2,4})");
		 		   m = id.matcher(JsonString);
		 	    	if (m.find()){
		 	    		dateOnly = m.group(1);
		 	    	    // System.out.println("date:: "+dateOnly);
		 	    	    
		 	    	}
		    	}
			
			
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return "false";
		}
		return dateOnly;
	}
	
}

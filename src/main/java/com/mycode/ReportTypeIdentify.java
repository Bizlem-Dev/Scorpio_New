package com.mycode;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.abhishek.ConvertDate;
import com.readGmail.GmailMethods;

public class ReportTypeIdentify {

	public static void main(String[] args) throws ParseException {
		    
		   /* SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		    String dateInString = "07/06/2013";
		  String s=  before(dateInString, "/");
		  System.out.println(s);*/
		  boolean numeric = true;
            String f="1,000";
		  
	        try {
	            Integer num = Integer.parseInt(f);
	            
	        } catch (NumberFormatException e) {
	            numeric = false;
	            System.out.println(e.getMessage());
	        }
		  
	        if(numeric){
	        	System.out.println("numeric:: "+f);
	        }
		 /*   Date date = formatter.parse(dateInString);
		    System.out.println(date);
		    System.out.println(formatter.format(date));*/
		   
	}
	
	public static String before(String value, String a) {
        // Return substring containing all characters before a string.
        int posA = value.indexOf(a);
        if (posA == -1) {
            return "";
        }
        return value.substring(0, posA);
    }
	
	public static String ReportTypeAndFinalJson(String myjson, PrintWriter out, String subjectLinePass){
		String finalJsonString="";
		try {
			//System.out.println("json my");
			JSONObject jsonStringInput=new JSONObject(myjson);
			Iterator<String> jsonObjkeys=jsonStringInput.keys();
			while(jsonObjkeys.hasNext()){
				String keyjsonObjkeys=jsonObjkeys.next();
				Object jsonObjkeyValueString=jsonStringInput.get(keyjsonObjkeys);
				if (jsonObjkeyValueString instanceof JSONObject) {
					JSONArray jsonObjArray=new JSONArray();
					JSONObject tabledataJsonObj=null;
					tabledataJsonObj = (JSONObject)jsonObjkeyValueString;
					 if(tabledataJsonObj.has("table_data")){
						 JSONArray table_dataArray=tabledataJsonObj.getJSONArray("table_data");
	                	 JSONObject tableinsideDataOnlyJonObj=null;
	                	 if(table_dataArray.length()>0){
	                		 
	                		 int headerCount=0;
	                		 for(int i=0;i<table_dataArray.length();i++){
	                			 headerCount++;
	                			 tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
	                			 JSONObject dataJSonObj=null;
	                    		 if(tableinsideDataOnlyJonObj.has("data")){
	                    			dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
	                    			 
	                    		 } // data close
	                    		 JSONObject headerJSonObj=null;
	                    		 JSONObject newHeader=new JSONObject();
	                    		 if(tableinsideDataOnlyJonObj.has("new_header_data")){
	                    			 
	                    			 headerJSonObj= tableinsideDataOnlyJonObj.getJSONObject("new_header_data");
	                    		 } // header close
	                    		 boolean vesselNameCheckBlank=false;
	                    		 if(headerJSonObj!=null){
	                    		
	                    		 Iterator<String>itrtemp= headerJSonObj.keys();
	                    		 while(itrtemp.hasNext()){
	                    			 String headerKeyKey=itrtemp.next(); //0
	                    			 String headerValue=headerJSonObj.getString(headerKeyKey); //key
	                    			 
	                    			 if(headerValue.equalsIgnoreCase("VesselName") || headerValue.equalsIgnoreCase("Vessel")){
	    	         						vesselNameCheckBlank=true;
//	    	         						System.out.println("check:: "+vesselNameCheckBlank);
	    	         						break;
	    	         					}
	                    			 
	                    		 }// while close first vesselname check
	                    			if(vesselNameCheckBlank==true){
	                    				//System.out.println("vesselname entered blank");
	                    			HashMap<String, String> g=new HashMap<>();
	                    			 Iterator<String>itr= headerJSonObj.keys();
	                    		 while(itr.hasNext()){
	                    			 String headerKeyKey=itr.next(); //0
	                    			 String headerValue=headerJSonObj.getString(headerKeyKey); //key
	                    			 newHeader.put(headerKeyKey, headerValue);
	                    			 
	                    			g.put(headerKeyKey, headerValue);
	                    		 } // while keyheader dyanamic 
	                    		
	                    		// System.out.println("g:: "+g);
	                    		
	                    		 if(g!=null && !g.isEmpty() && g.size()>0){
	                    			 
                                  
	                    			 String VesselNamePos="";
	                    				String LCStartPos="";
	                    				String LoadPortPos="";
	                    				String RatePos="";
	                    				String datePos="";
	                    				String PortPos="";
	                    				String LCEndPos="";
	                    				String DiscPortPos="";
	                    				
	                    				String OpenDatePos="";
	                    				String OpenPortPos="";
	                    				String ETABasisPos="";
	                    				String ChartererPos="";
	                    				String ReportTypePos="";
	                    				String OPENPos="";
	                    				String RateTypePos="";
	                    				
	                    				boolean vesselHas=false;
	                    				boolean LCStartHas=false;
	                    				boolean LoadPortHas=false;
	                    				boolean RateHas=false;
	                    				boolean DateHas=false;
	                    				boolean PortHas=false;
	                    				boolean LCEndHas=false;
	                    				boolean DiscPortHas=false;
	                    				
	                    				boolean OpenDatePoss=false;
	                    				boolean OpenPortPoss=false;
	                    				boolean ETABasisPoss=false;
	                    				boolean ChartererPoss=false;
	                    				boolean ReportTypePoss=false;
	                    				boolean OPENPoss=false;
	                    				boolean RateTypePoss=false;
	                    				
	                    			 
	                    		 for (Map.Entry<String, String> entry : g.entrySet())
								  {
	                    			// newHeader.put(entry.getKey(), entry.getValue());
	                    				
	                    				if(entry.getValue().equalsIgnoreCase("VesselName")){
	                    					VesselNamePos=entry.getKey();
	                    					//System.out.println("VesselNamePos:: "+VesselNamePos);
	                    					 vesselHas=true;
	                    				}if(entry.getValue().equalsIgnoreCase("LCStart")){
	                    					LCStartPos=entry.getKey();
	                    					//System.out.println("LCStartPos:: "+LCStartPos);
	                    					LCStartHas=true;
	                    				}if(entry.getValue().equalsIgnoreCase("LoadPort")){
	                    					LoadPortPos=entry.getKey();
	                    					//System.out.println("LoadPortPos:: "+LoadPortPos);
	                    					LoadPortHas=true;
	                    				}if(entry.getValue().equalsIgnoreCase("Rate")){
	                    					RatePos=entry.getKey();
	                    					//System.out.println("RatePos:: "+RatePos);
	                    					RateHas=true;
	                    				}if(entry.getValue().equalsIgnoreCase("Date")){
	                    					datePos=entry.getKey();
	                    					//System.out.println("datePos:: "+datePos);
	                    					DateHas=true;
	                    				}if(entry.getValue().equalsIgnoreCase("Port")){
	                    					PortPos=entry.getKey();
	                    					PortHas=true;
	                    				}if(entry.getValue().equalsIgnoreCase("LCEnd")){
	                    					LCEndPos=entry.getKey();
	                    					LCEndHas=true;
	                    				}if(entry.getValue().equalsIgnoreCase("DiscPort")){
	                    					DiscPortPos=entry.getKey();
	                    					DiscPortHas=true;
	                    				}if(entry.getValue().equalsIgnoreCase("OpenDate")){
	                    					OpenDatePos=entry.getKey();
	                    					OpenDatePoss=true;
	                    				}if(entry.getValue().equalsIgnoreCase("OpenPort")){
	                    					OpenPortPos=entry.getKey();
	                    					OpenPortPoss=true;
	                    				}if(entry.getValue().equalsIgnoreCase("ETABasis")){
	                    					ETABasisPos=entry.getKey();
	                    					ETABasisPoss=true;
	                    				}if(entry.getValue().equalsIgnoreCase("Charterer")){
	                    					ChartererPos=entry.getKey();
	                    					ChartererPoss=true;
	                    				}if(entry.getValue().equalsIgnoreCase("ReportType")){
	                    					ReportTypePos=entry.getKey();
	                    					ReportTypePoss=true;
	                    				}if(entry.getValue().equalsIgnoreCase("OPEN")){
	                    					OPENPos=entry.getKey();
	                    					OPENPoss=true;
	                    				}if(entry.getValue().equalsIgnoreCase("RateType")){
	                    					RateTypePos=entry.getKey();
	                    					RateTypePoss=true;
	                    				}
	                    				
	                    			
								  } // map for loop close
	                    		 
	                    		 JSONObject countAll=countRateAndPortAndDate(g);
	                    		// System.out.println(":: "+countAll);
	                    		 
	                    		    int dateCount=0;
	                    			int portCount=0;
	                    			int rateCount=0;
	                    			
	                    			 if(countAll!=null){
	                    				 if(countAll.has("Date")){
	                    					 dateCount= countAll.getInt("Date");
	                    					// System.out.println("singlejsondate: "+dateCount);
	                    				 }if(countAll.has("Port")){
	                    					 portCount=countAll.getInt("Port");
	                    				 }if(countAll.has("Rate")){
	                    					 rateCount=countAll.getInt("Rate");
	                    				 }
	                    				
	                    			 }
	                    			// dateGreaterLesser(g, dataJSonObj);
	                    			 
	                    			String RT= OnlyCheckFourJsonToReportType.checkHeaderDataAndSubjectData(tabledataJsonObj, headerJSonObj, dataJSonObj);
	                    			//System.out.println("RTCHECK:: "+RT);
	                    			// ................... header and table reporttype check
	                    			if( !GmailMethods.isNullString(RT) ){
	                    				 if("Tonnage".equalsIgnoreCase(RT) ){
	                    					 
	                    					 if(ReportTypePoss){
	                    						 
	                    						 
	                    					 }else{
	                    						 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	    	                    				 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Tonnage");
	                    					 }
	                    					 
	                    					 if(PortHas){
	                    						 newHeader.put(PortPos, "OpenPort");
	                    				 } // port check 
	                    				 if(ChartererPoss){
	                    						 newHeader.put(ChartererPos, "Operators");
	                    				 } //  Operators
	                    				 if(DateHas){
	                    					JSONObject StandardAndNonStandardDate= dateGreaterLesser(g, dataJSonObj);
	                    					if(StandardAndNonStandardDate!=null && StandardAndNonStandardDate.length()!=0){
	                    						String ETABasisFound="";
	                    						String OpenDateFound="";
	                    						String ETABasisNotFound="";
	                    						String OpenDateNotFound="";
	                    						
	                    						if(StandardAndNonStandardDate.has("DateStandardFound")){
	                    							JSONArray DateStandardFound=StandardAndNonStandardDate.getJSONArray("DateStandardFound");
	                    							System.out.println("DateStandardFound: "+DateStandardFound);
	                    							for(int k=0;k<DateStandardFound.length();k++){
		                    							JSONObject DateStandardFoundOBj= DateStandardFound.getJSONObject(k);
		                    							if(DateStandardFoundOBj.has("ETABasis")){
		                    								ETABasisFound=DateStandardFoundOBj.getString("ETABasis");
		                    							}if(DateStandardFoundOBj.has("OpenDate")){
		                    								OpenDateFound=DateStandardFoundOBj.getString("OpenDate");
		                    							}
		                    						} // standard date
	                    						}if(StandardAndNonStandardDate.has("DateNotStandardFound")){
	                    							JSONArray DateNotStandardFound=StandardAndNonStandardDate.getJSONArray("DateNotStandardFound");
	                    							for(int j=0;j<DateNotStandardFound.length();j++){
		                    							JSONObject DateStandardNotFoundOBj= DateNotStandardFound.getJSONObject(j);
	                                                         if(DateStandardNotFoundOBj.has("ETABasis")){
	                                                        	 ETABasisNotFound= DateStandardNotFoundOBj.getString("ETABasis");
		                    							}
	                                                          if(DateStandardNotFoundOBj.has("OpenDate")){
	                                                        	  OpenDateNotFound= DateStandardNotFoundOBj.getString("OpenDate");
	                                                        }
		                    						} // not standard date
	                    						}
	                    						
	                    						if( !GmailMethods.isNullString(ETABasisFound) ){
	                    							newHeader.put(ETABasisFound, "ETABasis");
	                    						}else{
	                    							if( !GmailMethods.isNullString(ETABasisNotFound) ){
	                    							     newHeader.put(ETABasisNotFound, "ETABasis");
	                    							}
	                    						}
	                    						if( !GmailMethods.isNullString(OpenDateFound) ){
	                    							newHeader.put(OpenDateFound, "OpenDate");
	                    						}else{
	                    							if( !GmailMethods.isNullString(OpenDateNotFound) ){
	                    								newHeader.put(OpenDateNotFound, "OpenDate");
	                    							}
	                    						}
	                    						
	                    						
	                    					} // json null check
	                    				 }
	                    				 
	                    				 if(DateHas){
	                    					 if(!OpenDatePoss){
	                    						 newHeader.put(datePos, "OpenDate");
	                    					 }else if(!ETABasisPoss){
	                    						 newHeader.put(datePos, "ETABasis");
	                    					 }
	                    				 } // date check 
	                    				 
	                    				/* if(OPENPoss && ETABasisPoss && OpenPortPoss){
	                    					 newHeader.put(OPENPos, "OpenDate");
	                    				 }*/
	                    				 
	                    				  if( portCount>=1 && ETABasisPoss && !RateHas ){
	                    					 if(PortHas){
		                    					 if(!OpenPortPoss){
		                    						 newHeader.put(PortPos, "OpenPort");
		                    					 }
		                    				 } // port check
	                    					 
	                    					 if(DateHas){
	                    						 //System.out.println("date inside:: "+DateHas+"date pos::  "+datePos);
		                    					 if(!ETABasisPoss){
		                    						 newHeader.put(datePos, "ETABasis");
		                    					 }else if(!OpenDatePoss){
		                    						 newHeader.put(datePos, "OpenDate");
		                    					 }
		                    				 } // date check 
	                    					 else{
	                    						 newHeader.put(OPENPos, "OpenDate");
	                    					 }
	                    					 
	                    					 if(ChartererPoss){
	                    						 newHeader.put(ChartererPos, "Operators");
	                    				 } //  Operators
	                    					 
	                    					 
	                    				 }
	                    				 /*if(PortHas){
	                    					 if(!OpenPortPoss){
	                    						 newHeader.put(PortPos, "OpenPort");
	                    					 }
	                    				 } // port check 
	                    				 if(ChartererPoss){
	                    						 newHeader.put(ChartererPos, "Operators");
	                    				 } //  Operators
*/	                    				 
	                    					 
	                    				 }
	                    				 
//	                    				 else {
	                    				 else  if("Spot".equalsIgnoreCase(RT) ){
	                    						 if(ReportTypePoss){
		                    						// System.out.println("header rt found");
		                    						 
		                    					 }else{
		                    						 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
		    	                    				 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Spot");
		                    					 }
	                    						 
	                    						 JSONObject comparePortAndDate=null;
	    	                    				 if(DateHas){ // if date has
	    	                    					 comparePortAndDate=new JSONObject();
	    	    	                    			 comparePortAndDate=PortAndDatePositionCompare(g);
	    	    	                    			// System.out.println(comparePortAndDate);
	    	    	                    			 
	    	    	                    			 if(comparePortAndDate!=null){
	    	    	                    				 if(comparePortAndDate.has("LCStart")){
	    	    	                    					String LCStartPoss= comparePortAndDate.getString("LCStart");
	    	    	                    					 newHeader.put(LCStartPoss, "LCStart");
	    	    	                    				 }if(comparePortAndDate.has("LCEnd")){
	    	    	                    					String LCEndPoss= comparePortAndDate.getString("LCEnd");
	    	    	                    					 newHeader.put(LCEndPoss, "LCEnd");
	    	    	                    				 }
	    	    	                    			 }
	    	                    					 
	    	                    				 } // date check
	    	                    				 
	    	                    				 if(PortHas){
	    	                    					 if(comparePortAndDate!=null){
	    	                    						 if(comparePortAndDate.has("LoadPort")){
	    	    	                    					String LoadPortPoss= comparePortAndDate.getString("LoadPort");
	    	    	                    					 newHeader.put(LoadPortPoss, "LoadPort");
	    	    	                    				 }if(comparePortAndDate.has("DiscPort")){
	    	    	                    					String DiscPortPoss=comparePortAndDate.getString("DiscPort");
	    	    	                    					 newHeader.put(DiscPortPoss, "DiscPort");
	    	    	                    				 }
	    	                    					 }
	    	                    				 } // port check
	    	                    				 
	    	                    				 if(DateHas){ // if date has
	    	                    					 if(!LCStartHas){ // if not else lcstart
	    	                    						 newHeader.put(datePos, "LCStart");
	    	                    					 }else if(!LCEndHas){ //if not else lcend
	    	                    						 newHeader.put(datePos, "LCEnd");
	    	                    					 }
	    	                    				 } // date check 
	    	                    				 if(PortHas){ // if date has
	    	                    					 if(!LoadPortHas){ //if not else lloadport
	    	                    						 newHeader.put(LoadPortPos, "LoadPort");
	    	                    					 }else if(!DiscPortHas){    //if not else discport
	    	                    						 newHeader.put(LoadPortPos, "DiscPort");
	    	                    					 }
	    	                    				 } // port check
	    	                    				 
	    	                    				 
	                    						 
	                    					 } // spot equal check
//	                    				 }
	                    			 }
	                    			 // ................... header and table reporttype check
	                    			
	                    			// ..............not in header and table reporttype......
	                    			 else{
	                    			// System.out.println("else spot inside");
	                    			// System.out.println(1>=1);
	                    			// System.out.println("dateCount:: "+dateCount);
	                    			 if( dateCount>=1 && portCount>=1 && rateCount>=1){
	                    				// System.out.println("onecount spot true");
	                    				 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    				 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Spot");
	                    				 
	                    				 JSONObject comparePortAndDate=null;
	                    				 if(DateHas){ // if date has
	                    					 comparePortAndDate=new JSONObject();
	    	                    			 comparePortAndDate=PortAndDatePositionCompare(g);
	    	                    			 //System.out.println(comparePortAndDate);
	    	                    			 
	    	                    			 if(comparePortAndDate!=null){
	    	                    				 if(comparePortAndDate.has("LCStart")){
	    	                    					String LCStartPoss= comparePortAndDate.getString("LCStart");
	    	                    					 newHeader.put(LCStartPoss, "LCStart");
	    	                    				 }if(comparePortAndDate.has("LCEnd")){
	    	                    					String LCEndPoss= comparePortAndDate.getString("LCEnd");
	    	                    					 newHeader.put(LCEndPoss, "LCEnd");
	    	                    				 }
	    	                    			 }
	                    					 
	                    				 } // date check
	                    				 
	                    				 if(PortHas){
	                    					 if(comparePortAndDate!=null){
	                    						 if(comparePortAndDate.has("LoadPort")){
	    	                    					String LoadPortPoss= comparePortAndDate.getString("LoadPort");
	    	                    					 newHeader.put(LoadPortPoss, "LoadPort");
	    	                    				 }if(comparePortAndDate.has("DiscPort")){
	    	                    					String DiscPortPoss=comparePortAndDate.getString("DiscPort");
	    	                    					 newHeader.put(DiscPortPoss, "DiscPort");
	    	                    				 }
	                    					 }
	                    				 } // port check
	                    				 
	                    			 }

	                    			 else if( LCStartHas && LoadPortHas && RateHas ){
	                    				// System.out.println("entered spot");
	                    				 if(DateHas){ // if date has
	                    					 if(!LCStartHas){ // if not else lcstart
	                    						 newHeader.put(datePos, "LCStart");
	                    					 }else if(!LCEndHas){ //if not else lcend
	                    						 newHeader.put(datePos, "LCEnd");
	                    					 }
	                    				 } // date check 
	                    				 if(PortHas){ // if date has
	                    					 if(!LoadPortHas){ //if not else lloadport
	                    						 newHeader.put(LoadPortPos, "LoadPort");
	                    					 }else if(!DiscPortHas){    //if not else discport
	                    						 newHeader.put(LoadPortPos, "DiscPort");
	                    					 }
	                    				 } // port check
	                    				 
	                    				 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    				 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Spot");
	                    				 
	                    			 }else if( LCEndHas && DiscPortHas && RateHas){
	                    				 if(DateHas){ // if date has
	                    					 if(!LCStartHas){ // if not else lcstart
	                    						 newHeader.put(datePos, "LCStart");
	                    					 }else if(!LCEndHas){ //if not else lcend
	                    						 newHeader.put(datePos, "LCEnd");
	                    					 }
	                    				 } // date check 
	                    				 if(PortHas){ // if date has
	                    					 if(!LoadPortHas){ //if not else lloadport
	                    						 newHeader.put(LoadPortPos, "LoadPort");
	                    					 }else if(!DiscPortHas){    //if not else discport
	                    						 newHeader.put(LoadPortPos, "DiscPort");
	                    					 }
	                    				 } // port check
	                    				 
	                    				 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    				 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Spot");
	                    			 }// spot 
	                    			 else if( dateCount>=1 && DiscPortHas && RateHas){
	                    				 if(DateHas){ // if date has
	                    					 if(!LCStartHas){ // if not else lcstart
	                    						 newHeader.put(datePos, "LCStart");
	                    					 }else if(!LCEndHas){ //if not else lcend
	                    						 newHeader.put(datePos, "LCEnd");
	                    					 }
	                    				 } // date check 
	                    				 if(PortHas){ // if date has
	                    					 if(!LoadPortHas){ //if not else lloadport
	                    						 newHeader.put(LoadPortPos, "LoadPort");
	                    					 }else if(!DiscPortHas){    //if not else discport
	                    						 newHeader.put(LoadPortPos, "DiscPort");
	                    					 }
	                    				 } // port check
	                    				 
	                    				 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    				 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Spot");
	                    			 }// spot
	                    			 else if( dateCount>=1 && LoadPortHas && RateHas){
	                    				 if(DateHas){ // if date has
	                    					 if(!LCStartHas){ // if not else lcstart
	                    						 newHeader.put(datePos, "LCStart");
	                    					 }else if(!LCEndHas){ //if not else lcend
	                    						 newHeader.put(datePos, "LCEnd");
	                    					 }
	                    				 } // date check 
	                    				 if(PortHas){ // if date has
	                    					 if(!LoadPortHas){ //if not else lloadport
	                    						 newHeader.put(LoadPortPos, "LoadPort");
	                    					 }else if(!DiscPortHas){    //if not else discport
	                    						 newHeader.put(LoadPortPos, "DiscPort");
	                    					 }
	                    				 } // port check
	                    				 
	                    				 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    				 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Spot");
	                    			 }// spot
	                    			 
	                    			 else if( RateHas && PortHas && RateTypePoss ){
	                    				 if(DateHas){ // if date has
	                    					 if(!LCStartHas){ // if not else lcstart
	                    						 newHeader.put(datePos, "LCStart");
	                    					 }else if(!LCEndHas){ //if not else lcend
	                    						 newHeader.put(datePos, "LCEnd");
	                    					 }
	                    				 } // date check 
	                    				 if(PortHas){ // if date has
	                    					 
	                    					 System.out.println("porthas:: "+PortHas);
	                    					 if(!LoadPortHas){ //if not else lloadport
	                    						 System.out.println("entered");
	                    						 newHeader.put(LoadPortPos, "LoadPort");
	                    					 }else if(!DiscPortHas){    //if not else discport
	                    						 newHeader.put(LoadPortPos, "DiscPort");
	                    					 }
	                    				 } // port check
	                    				 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    				 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Spot");
	                    				 
	                    			 }
	                    			 
//	                    			 else {
	                    				 
	                    			 else if( dateCount>=1 && portCount>=1 && !RateHas){
	                    					 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    					 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Tonnage");
	                    					 
	                    					 if(PortHas){
		                    						 newHeader.put(PortPos, "OpenPort");
		                    				 } // port check 
		                    				 if(ChartererPoss){
		                    						 newHeader.put(ChartererPos, "Operators");
		                    				 } //  Operators
		                    				 if(DateHas){
		                    					JSONObject StandardAndNonStandardDate= dateGreaterLesser(g, dataJSonObj);
		                    					//System.out.println("StandardAndNonStandardDate:: "+StandardAndNonStandardDate);
		                    					if(StandardAndNonStandardDate!=null && StandardAndNonStandardDate.length()!=0){
		                    						String ETABasisFound="";
		                    						String OpenDateFound="";
		                    						String ETABasisNotFound="";
		                    						String OpenDateNotFound="";
		                    						
		                    						if(StandardAndNonStandardDate.has("DateStandardFound")){
		                    							JSONArray DateStandardFound=StandardAndNonStandardDate.getJSONArray("DateStandardFound");
		                    							//System.out.println("DateStandardFound:: "+DateStandardFound);
		                    							if(DateStandardFound.length()>0){
		                    							for(int k=0;k<DateStandardFound.length();k++){
			                    							JSONObject DateStandardFoundOBj= DateStandardFound.getJSONObject(k);
			                    							//System.out.println("DateStandardFoundOBj:: "+DateStandardFoundOBj);
			                    							if(DateStandardFoundOBj.has("ETABasis")){
			                    								ETABasisFound=DateStandardFoundOBj.getString("ETABasis");
			                    							}if(DateStandardFoundOBj.has("OpenDate")){
			                    								OpenDateFound=DateStandardFoundOBj.getString("OpenDate");
			                    							}
			                    						} // standard date
		                    							}
		                    						}if(StandardAndNonStandardDate.has("DateNotStandardFound")){
		                    							JSONArray DateNotStandardFound=StandardAndNonStandardDate.getJSONArray("DateNotStandardFound");
		                    							for(int j=0;j<DateNotStandardFound.length();j++){
			                    							JSONObject DateStandardNotFoundOBj= DateNotStandardFound.getJSONObject(j);
		                                                         if(DateStandardNotFoundOBj.has("ETABasis")){
		                                                        	 ETABasisNotFound= DateStandardNotFoundOBj.getString("ETABasis");
			                    							}
		                                                          if(DateStandardNotFoundOBj.has("OpenDate")){
		                                                        	  OpenDateNotFound= DateStandardNotFoundOBj.getString("OpenDate");
		                                                        }
			                    						} // not standard date
		                    						}
		                    						
		                    						if( !GmailMethods.isNullString(ETABasisFound) ){
		                    							newHeader.put(ETABasisFound, "ETABasis");
		                    						}else{
		                    							if( !GmailMethods.isNullString(ETABasisNotFound) ){
		                    							     newHeader.put(ETABasisNotFound, "ETABasis");
		                    							}
		                    						}
		                    						if( !GmailMethods.isNullString(OpenDateFound) ){
		                    							newHeader.put(OpenDateFound, "OpenDate");
		                    						}else{
		                    							if( !GmailMethods.isNullString(OpenDateNotFound) ){
		                    								newHeader.put(OpenDateNotFound, "OpenDate");
		                    							}
		                    						}
		                    						
		                    						
		                    					} // json null check
		                    				 }
		                    				 
	                    					 
	                    				 }else if( OpenDatePoss && OpenPortPoss && !RateHas){
	                    					 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    					 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Tonnage");
	                    					 
	                    					 if(DateHas){
		                    					 if(!ETABasisPoss){
		                    						 newHeader.put(datePos, "ETABasis");
		                    					 }else if(!OpenDatePoss){
		                    						 newHeader.put(datePos, "OpenDate");
		                    					 }
		                    				 } // date check 
		                    				 if(PortHas){
		                    					 if(!OpenPortPoss){
		                    						 newHeader.put(PortPos, "OpenPort");
		                    					 }
		                    				 } // port check 
		                    				 if(ChartererPoss){
		                    						 newHeader.put(ChartererPos, "Operators");
		                    				 } //  Operators
		                    				 
	                    				 }else if(  OpenDatePoss && OpenPortPoss && ETABasisPoss ){
	                    					 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    					 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Tonnage");
	                    					 
	                    					 if(DateHas){
		                    					 if(!ETABasisPoss){
		                    						 newHeader.put(datePos, "ETABasis");
		                    					 }else if(!OpenDatePoss){
		                    						 newHeader.put(datePos, "OpenDate");
		                    					 }
		                    				 } // date check 
		                    				 if(PortHas){
		                    					 if(!OpenPortPoss){
		                    						 newHeader.put(PortPos, "OpenPort");
		                    					 }
		                    				 } // port check 
		                    				 if(ChartererPoss){
		                    						 newHeader.put(ChartererPos, "Operators");
		                    				 } //  Operators
		                    				 
	                    				 }
	                    				 
	                    				 else if( OpenPortPoss && ETABasisPoss && !RateHas){
	                    					 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    					 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Tonnage");
	                    					 
	                    					 if(DateHas){
	                    						 System.out.println("date inside:: "+DateHas+"date pos::  "+datePos);
		                    					 if(!ETABasisPoss){
		                    						 newHeader.put(datePos, "ETABasis");
		                    					 }else if(!OpenDatePoss){
		                    						 newHeader.put(datePos, "OpenDate");
		                    					 }
		                    				 } // date check 
		                    				 if(PortHas){
		                    					 if(!OpenPortPoss){
		                    						 newHeader.put(PortPos, "OpenPort");
		                    					 }
		                    				 } // port check 
		                    				 if(ChartererPoss){
		                    						 newHeader.put(ChartererPos, "Operators");
		                    				 } //  Operators
		                    				 
		                    				/* if(OPENPoss && ETABasisPoss && OpenPortPoss){
		                    					 newHeader.put(OPENPos, "OpenDate");
		                    				 }*/
		                    				 
	                    				 }
	                    				 else if( OpenPortPoss && OpenDatePoss){
	                    					 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    					 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Tonnage");
	                    					 
	                    					 if(DateHas){
	                    						 //System.out.println("date inside:: "+DateHas+"date pos::  "+datePos);
		                    					 if(!ETABasisPoss){
		                    						 newHeader.put(datePos, "ETABasis");
		                    					 }else if(!OpenDatePoss){
		                    						 newHeader.put(datePos, "OpenDate");
		                    					 }
		                    				 } // date check 
		                    				 if(PortHas){
		                    					 if(!OpenPortPoss){
		                    						 newHeader.put(PortPos, "OpenPort");
		                    					 }
		                    				 } // port check 
		                    				 if(ChartererPoss){
		                    						 newHeader.put(ChartererPos, "Operators");
		                    				 } //  Operators
		                    				 
	                    				 }
	                    				 else if( OpenPortPoss && dateCount>=1 && !RateHas){
	                    					 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    					 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Tonnage");
	                    					 
	                    					 if(DateHas){
	                    						// System.out.println("if two date  balnks in tonnage:: "+DateHas+"date pos::  "+datePos);
	                    						 if(!ETABasisPoss && !OpenDatePoss){
	                    							 
	     		                    					JSONObject StandardAndNonStandardDate= dateGreaterLesser(g, dataJSonObj);
	     		                    					if(StandardAndNonStandardDate!=null && StandardAndNonStandardDate.length()!=0){
	     		                    						//System.out.println(StandardAndNonStandardDate);
	     		                    						String ETABasisFound="";
	     		                    						String OpenDateFound="";
	     		                    						String ETABasisNotFound="";
	     		                    						String OpenDateNotFound="";
	     		                    						
	     		                    						if(StandardAndNonStandardDate.has("DateStandardFound")){
	     		                    							JSONArray DateStandardFound=StandardAndNonStandardDate.getJSONArray("DateStandardFound");
	     		                    							for(int k=0;k<DateStandardFound.length();k++){
	     			                    							JSONObject DateStandardFoundOBj= DateStandardFound.getJSONObject(k);
	     			                    							if(DateStandardFoundOBj.has("ETABasis")){
	     			                    								ETABasisFound=DateStandardFoundOBj.getString("ETABasis");
	     			                    							}if(DateStandardFoundOBj.has("OpenDate")){
	     			                    								OpenDateFound=DateStandardFoundOBj.getString("OpenDate");
	     			                    							}
	     			                    						} // standard date
	     		                    						}if(StandardAndNonStandardDate.has("DateNotStandardFound")){
	     		                    							JSONArray DateNotStandardFound=StandardAndNonStandardDate.getJSONArray("DateNotStandardFound");
	     		                    							for(int j=0;j<DateNotStandardFound.length();j++){
	     			                    							JSONObject DateStandardNotFoundOBj= DateNotStandardFound.getJSONObject(j);
	     		                                                         if(DateStandardNotFoundOBj.has("ETABasis")){
	     		                                                        	 ETABasisNotFound= DateStandardNotFoundOBj.getString("ETABasis");
	     			                    							}
	     		                                                          if(DateStandardNotFoundOBj.has("OpenDate")){
	     		                                                        	  OpenDateNotFound= DateStandardNotFoundOBj.getString("OpenDate");
	     		                                                        }
	     			                    						} // not standard date
	     		                    						}
	     		                    						
	     		                    						if( !GmailMethods.isNullString(ETABasisFound) ){
	     		                    							newHeader.put(ETABasisFound, "ETABasis");
	     		                    						}else{
	     		                    							if( !GmailMethods.isNullString(ETABasisNotFound) ){
	     		                    							     newHeader.put(ETABasisNotFound, "ETABasis");
	     		                    							}
	     		                    						}
	     		                    						if( !GmailMethods.isNullString(OpenDateFound) ){
	     		                    							newHeader.put(OpenDateFound, "OpenDate");
	     		                    						}else{
	     		                    							if( !GmailMethods.isNullString(OpenDateNotFound) ){
	     		                    								newHeader.put(OpenDateNotFound, "OpenDate");
	     		                    							}
	     		                    						}
	     		                    						
	     		                    						
	     		                    					} // json null check
	     		                    				 
	                    							 
	                    						 }else{
		                    					 if(!ETABasisPoss){
		                    						 newHeader.put(datePos, "ETABasis");
		                    					 }else if(!OpenDatePoss){
		                    						 newHeader.put(datePos, "OpenDate");
		                    					 }
		                    				 } 
	                    				 }  // date check
	                    						 
		                    				 if(PortHas){
		                    					 if(!OpenPortPoss){
		                    						 newHeader.put(PortPos, "OpenPort");
		                    					 }
		                    				 } // port check 
		                    				 if(ChartererPoss){
		                    						 newHeader.put(ChartererPos, "Operators");
		                    				 } //  Operators
		                    				 
	                    				 }else if( portCount>=1 && ETABasisPoss && !RateHas ){
	                    					 if(PortHas){
		                    					 if(!OpenPortPoss){
		                    						 newHeader.put(PortPos, "OpenPort");
		                    					 }
		                    				 } // port check
	                    					 
	                    					 if(DateHas){
	                    						 //System.out.println("date inside:: "+DateHas+"date pos::  "+datePos);
		                    					 if(!ETABasisPoss){
		                    						 newHeader.put(datePos, "ETABasis");
		                    					 }else if(!OpenDatePoss){
		                    						 newHeader.put(datePos, "OpenDate");
		                    					 }
		                    				 } // date check 
	                    					 else{
	                    						 newHeader.put(OPENPos, "OpenDate");
	                    					 }
	                    					 
	                    					 if(ChartererPoss){
	                    						 newHeader.put(ChartererPos, "Operators");
	                    				 } //  Operators
	                    					 
	                    					 newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
	                    					 dataJSonObj.put(String.valueOf(dataJSonObj.length()), "Tonnage");
	                    					 
	                    				 }else if( ETABasisPoss && OPENPoss && !RateHas && !PortHas ){
	                    					 if(DateHas){
	                    						 //System.out.println("date inside:: "+DateHas+"date pos::  "+datePos);
		                    					 if(!ETABasisPoss){
		                    						 newHeader.put(datePos, "ETABasis");
		                    					 }else if(!OpenDatePoss){
		                    						 newHeader.put(datePos, "OpenDate");
		                    					 }
		                    				 } // date check 
	                    					 else{
	                    						 newHeader.put(OPENPos, "OpenDate");
	                    					 }
	                    					 
	                    					 
	                    				 }
	                    				 
	                    				 
//	                    			 } // tonnage
                 				
	                    			
	                    		 }// check null map
	                    			
	                    			// ..............not in header and table reporttype......
	                    		 }// reporttype check
	                    		 table_dataArray.getJSONObject(i).put("new_header_data", newHeader);
	                    		} // vesselname check blank
	                    		 
	                    		 }
	                    		
	                    		 
	                    		 
	                    		// jsonObjArray.put(jsonObj);
	                		 } // for loop close
	                		 finalJsonString=jsonStringInput.toString();
	                		
	                	 } // length check tablearray
					 } // table_data has check
					 
					 
					 
				} // jsonobj check
			} // while close dyanamic table1 etc
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalJsonString;
	}
	
	public static boolean datecheck(String dateValue){
		boolean dateStandardFound=false;
		try {
			
			if( !GmailMethods.isNullString(dateValue) ){
				//System.out.println("datecheckdatevalue: "+dateValue);
			  String dateStandardFormate= new ConvertDate().convertDateFormat(dateValue);
			  //System.out.println("dateStandardFormate: "+dateStandardFormate);
			  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			  Date date = formatter.parse(dateStandardFormate);
		     if(date!=null){
		    	 dateStandardFound=true;
		    	// System.out.println("dateStandardFound: "+dateStandardFormate);
		     }
			}
		  // 
			
		} catch (Exception e) {
			return false;
		}
		return dateStandardFound;
	}
	
	public static JSONObject dateGreaterLesser(HashMap<String, String> g, JSONObject dataJSonObj){
		JSONObject allArrayDataDAteTonnage=new JSONObject();
		try {
			
			HashMap<String, String > dateList=new HashMap<>();
			HashMap<String, String > notRecognizedList=new HashMap<>();
			
			 for (Map.Entry<String, String> entry : g.entrySet())
			  {
 				
 				if(entry.getValue().equalsIgnoreCase("Date")){
 					 boolean dateStandardFormate=false;
 					// System.out.println("entry.getKey():: "+entry.getKey());
 					 
 					String dateValue=dataJSonObj.getString(entry.getKey());
 					//System.out.println("dateValue:: "+dateValue);
 					if( !GmailMethods.isNullString(dateValue) ){
 						dateStandardFormate=datecheck(dateValue);
 						String dateStandard="";
    					if(dateStandardFormate){
    						//System.out.println("boolean :: "+dateStandardFormate);
    						dateStandard= new ConvertDate().convertDateFormat(dateValue);
    						//System.out.println("boolean1 :: "+dateStandard);
    						String addSingleDateList=before(dateStandard, "/");
    						//System.out.println("boolean_slash :: "+addSingleDateList);
    						
    						dateList.put(entry.getKey(), addSingleDateList);
    						//System.out.println("dateList:: "+dateList);
    						
    						
    					}else{
    						//dateStandard= new ConvertDate().convertDateFormat(dateValue);
//    						notRecognizedList.put(entry.getKey(), "Date");
    						notRecognizedList.put(entry.getKey(), dateValue);
    						//System.out.println("else:: "+notRecognizedList);
    					}
 					}
 					
 				}
 			
			  } // map for loop close
			 
			 
			 JSONArray datearray=new JSONArray();
			 JSONArray notdatearray=new JSONArray();
			 
			if(dateList!=null && dateList.size()>0 && !dateList.isEmpty()){
				 Comparator<String> strLenCmp = new Comparator<String>() {
					    @Override
					    public int compare(String o1, String o2) {
					        return Integer.compare(o1.length(), o2.length());
					    }
					};
					String maxKey = Collections.max(dateList.keySet());
//				    String longKey = Collections.max(dateList.keySet(),strLenCmp );
				    String minKey = Collections.min(dateList.keySet());
				 
//				 System.out.println("longKey:: "+longKey);
				// System.out.println("maxkey:: "+maxKey);
				 //System.out.println("minKey:: "+minKey);
				 JSONObject dateStandard=new JSONObject();
				 
				 if(maxKey.equals(minKey)){
					 dateStandard.put("OpenDate", minKey);
				 }else{
					 
					 String maxValueInMap=(Collections.max(dateList.values()));
					   // System.out.println("maxValueInMap: "+maxValueInMap);
					    
					    for (Map.Entry<String, String> entry : dateList.entrySet())
						  {
					    	if (entry.getValue()==maxValueInMap) {
				              //  System.out.println(entry.getKey());     // Print the key with max value
				                dateStandard.put("ETABasis", entry.getKey());
				            }else{
				            	dateStandard.put("OpenDate", entry.getKey());
				            }
					    	
						  }
					 
				 }
				 
				 datearray.put(dateStandard);
				 
			}
			if(notRecognizedList!=null && notRecognizedList.size()>0 && !notRecognizedList.isEmpty()){
				 Comparator<String> strLenCmp = new Comparator<String>() {
					    @Override
					    public int compare(String o1, String o2) {
					        return Integer.compare(o1.length(), o2.length());
					    }
					};
					String maxKey = Collections.max(notRecognizedList.keySet());
				    String minKey = Collections.min(notRecognizedList.keySet());
//				    String longKey = Collections.max(dateList.keySet(),strLenCmp );
				 
				// System.out.println("maxkey:: "+maxKey);
				 JSONObject dateNotStandard=new JSONObject();
				 
				 if(maxKey.equals(minKey)){
					 dateNotStandard.put("OpenDate", maxKey);
				 }else{
					 
					 Map.Entry<String, String> maxEntry = null;
					 for (Map.Entry<String, String> entry : notRecognizedList.entrySet()){
						 if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
					            maxEntry =  entry;
					           // System.out.println("string max:: "+maxEntry.getValue());
					            dateNotStandard.put("ETABasis", maxEntry.getKey());
					        }
					        
					    }
					 
					 for (Map.Entry<String, String> entry : notRecognizedList.entrySet()){
						 if(entry.getValue().equals(maxEntry.getValue())){
							 
						 }else{
							 dateNotStandard.put("OpenDate", entry.getKey());
						 }
					        
					    }// min
					 
				 }
				 
				 notdatearray.put(dateNotStandard);
				 
			}

			if(datearray.length()>0){
				allArrayDataDAteTonnage.put("DateStandardFound", datearray);
			}if(notdatearray.length()>0){
				allArrayDataDAteTonnage.put("DateNotStandardFound", notdatearray);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allArrayDataDAteTonnage;
	}
	
	public static JSONObject PortAndDatePositionCompare(HashMap<String, String> g){
		JSONObject countObj=new JSONObject();
		try {
		//	System.out.println("tata:: "+g);
			ArrayList<String> date=new ArrayList<>(); 
			ArrayList<String> port=new ArrayList<>();
			
			 for (Map.Entry<String, String> entry : g.entrySet())
			  {
  				
  				if(entry.getValue().equalsIgnoreCase("Date")){

  					date.add(entry.getKey());
  					
  				}if(entry.getValue().equalsIgnoreCase("Port")){
  					port.add(entry.getKey());
  				}
  			
			  } // map for loop close
			 
			 
			 if(date!=null && date.size()>0){
				// System.out.println("max: " + Collections.max(date));
				// System.out.println("min: " + Collections.min(date));
				 
				 if(Collections.min(date).equals(Collections.max(date))){
					 countObj.put("LCStart", Collections.min(date));
				 }else{
					 countObj.put("LCStart", Collections.min(date));
					 countObj.put("LCEnd", Collections.max(date));
				 }
				 
			 }if(port!=null && port.size()>0){
				// System.out.println("max: " + Collections.max(port));
				// System.out.println("min: " + Collections.min(port));
				 
				 if(Collections.min(port).equals(Collections.max(port))){
					 countObj.put("LoadPort", Collections.min(port));
				 }else{
					 countObj.put("LoadPort", Collections.min(port));
					 countObj.put("DiscPort", Collections.max(port));
				 }
				 
				 
				 
				 
			 }
			 
    			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countObj;
	}

	
	public static JSONObject countRateAndPortAndDate(HashMap<String, String> g){
		JSONObject countObj=new JSONObject();
		try {
			
			int dateCount=0;
			int portCount=0;
			int rateCount=0;
			
			 for (Map.Entry<String, String> entry : g.entrySet())
			  {
   				
   				if(entry.getValue().equalsIgnoreCase("Rate")){
   					rateCount++; // minimum one
   				}if(entry.getValue().equalsIgnoreCase("Date")){
   					dateCount++; // minimum one
   					
   				}if(entry.getValue().equalsIgnoreCase("Port")){
   					portCount++;  // minimum one
   				}
   			
			  } // map for loop close
			
			 countObj.put("Date", dateCount);
			 countObj.put("Port", portCount);
			 countObj.put("Rate", rateCount);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countObj;
	}

}

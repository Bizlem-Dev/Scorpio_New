package com.mycode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;

public class RemoveMasterPropertiesFromComments {

	public static void main(String[] args) {
		/*RemoveMasterPropertiesFromComments rn= new RemoveMasterPropertiesFromComments();
		boolean flag = rn.reportCorrectionMethod("ATLANTIC OLIVE", "08");
		System.out.println("Final Flag "+flag);*/
		
		boolean numeric=removeNumericData("49.9999");
		boolean floatCheck=removeFloatData("49.9999");
		
		/*if(numeric==true){
			System.out.println("found int");
		}else if(floatCheck==true){
			System.out.println("found float");
		}else{
			System.out.println("not found");
		}*/
		
		splitUnknownToCheckMaster("25 abhishek 34 98 aalok 45  67.34");
		
	}
	
	public  boolean reportCorrectionMethod(String vesselNamePass, String UnkownData){

		boolean reportChange = false;
		try {

			if(!isNullString(vesselNamePass)){
				String vesselPropertiesString=vesselPropertiesDetailsScript(vesselNamePass);
				boolean checkvessel= isJSONValid(vesselPropertiesString);
				if(checkvessel){
					System.out.println(vesselPropertiesString);
					JSONObject vesselObj=new JSONObject(vesselPropertiesString);

					double cubics = 0.0;
					double dwt= 0.0;
					double loa = 0.0;
					String built="";
					
					if(vesselObj.has("cubics")){
						cubics=Math.ceil(Double.parseDouble(vesselObj.getString("cubics"))/1000);
						System.out.println("cubics "+(int)cubics);
					}
					if(vesselObj.has("dwt")){
						dwt=Math.ceil(Double.parseDouble(vesselObj.getString("dwt"))/1000);
						System.out.println("dwt "+(int)dwt);
					}
					if(vesselObj.has("loa")){
						loa=Math.ceil(Double.parseDouble(vesselObj.getString("loa")));
						System.out.println("loa "+(int)loa);
					}
					if(vesselObj.has("built")){
						built=vesselObj.getString("built");
						System.out.println("built: "+built);
					}
					
					String pattern = "[^a-zA-Z.//$//?//_//|]";
					String unVal="";
                            
							Pattern pattern1 = Pattern.compile(pattern);
							Matcher matcher = pattern1.matcher(UnkownData);
                           
                            System.out.println("UnkownData "+UnkownData);
                            
							while (matcher.find()) {
								unVal+=matcher.group();
								System.out.println("unVal: "+unVal);
								
		                    } // while close here	
							
							if(!isNullString(unVal)){
								System.out.println((Math.ceil(Double.parseDouble(unVal))));
								boolean cubicflag = ((Math.ceil(Double.parseDouble(unVal))- cubics) == 0.0)?true:false;
								if(cubicflag){
									reportChange = true;
								}
								boolean dwtflag = ((Math.ceil(Double.parseDouble(unVal))- dwt) == 0.0)?true:false;
								if(dwtflag){
									System.out.println("found");
									reportChange = true;
								}
								boolean loaflag = ((Math.ceil(Double.parseDouble(unVal))- loa) == 0.0)?true:false;
								if(loaflag){
									reportChange = true;
								}
								
								boolean builtflag=(built.equals("20"+unVal))?true:false;
								if(builtflag){
									reportChange = true;
								}
								
							} // unkown value check here						
						

				} // jsonValid Check close

			} else{
				System.out.println("vesselName if blank in main json from pallavi code");
				// blank check close
			}





		} catch (Exception e) {
			e.printStackTrace();
//          System.out.println(e.getMessage());
		}
		return reportChange;
	}
	
	public static boolean isNullString (String p_text){
		if(p_text != null && p_text.trim().length() > 0 && !"null".equalsIgnoreCase(p_text.trim())){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean isJSONValid(String test) {
		try {
			new JSONObject(test);
		} catch (JSONException ex) {
			
			try {
				new JSONArray(test);
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}
	
	public static String vesselPropertiesDetailsScript(String vesselNamePass){

		StringBuffer response1=null;

		try {

			String url = "";
			url="https://dev.bizlem.io:8082/scorpio/checkVesselDetails?vesselName="+vesselNamePass;

			url = url.replace(" ", "%20");

			URL url1 = new URL(url);
			System.setProperty("http.keepAlive","false");
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			//			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			in.close();
			con.disconnect();

		} catch (Exception e) {
           System.out.println(e.getMessage());
		}

		return response1.toString();

	}
	
	public static String urlValue(String text){
		String[] s = {};
		String valueReturn = null;
		try {
			String regex = "^http://.*";

			boolean matches = Pattern.matches(regex, text);

			if(matches){
				s= text.split("#");
				//System.out.println(s[1]);
				valueReturn= s[1];
			}else{
				valueReturn= text;
			}
		} catch (Exception e) {
			valueReturn= "";
		}
		return valueReturn;
	}
	
	public static boolean removeNumericData(String passUnknownData){
		
			boolean numeric = true;
			
			try {
	           Integer.parseInt(passUnknownData);
	           numeric=true;
	            
	        } catch (Exception e) {
	            numeric = false;
	            
	        }
			return numeric;
			
	}
	
public static boolean removeFloatData(String passUnknownData){
		
		
			boolean numeric = true;
			
			try {
				Float.parseFloat(passUnknownData);
	            numeric=true; 
	           
	        } catch (Exception e) {
	            numeric = false;
	            
	        }
			return numeric;
		
	}

public static void splitUnknownToCheckMaster(String splitData){
	
	try {
		if(splitData.contains(" ")){
			
			String[] splited = splitData.split("\\s+");
			for(int i=0;i<splited.length;i++){
				String data=splited[i];
				if( !GmailMethods.isNullString(data) ){
//					 System.out.println("data: "+data);
					 
					 boolean numeric=removeNumericData(data);
					 boolean floatCheck=removeFloatData(data);
					 
					 if(numeric==true){
						
					 }else if(floatCheck==true){
						 
					 }else {
						 System.out.println("elsedata: "+data);
					 }
				}
				
			} // for close here
			
		} // space check here
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	

}

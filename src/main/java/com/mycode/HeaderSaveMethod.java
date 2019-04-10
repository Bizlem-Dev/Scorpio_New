package com.mycode;

import java.io.PrintWriter;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

public class HeaderSaveMethod {

	public static void main(String[] args) {

	}
	
	public static String headersDataExcel(PrintWriter out, JSONObject tabledataJsonObj){
		JSONObject dataAll=null;
		String dataall="";
		try {
			
			
			//...... for headers data .........
			if(tabledataJsonObj.has("header_data")){
				JSONArray header_data_jsonarray=tabledataJsonObj.getJSONArray("header_data");
				
				if(header_data_jsonarray.length()>0){
					
				for(int i=0;i<header_data_jsonarray.length();i++){
					JSONObject headerJsonobj=header_data_jsonarray.getJSONObject(i);
					
					String check_Port_repositionRegion_id="";					
					if(headerJsonobj.has("RepositionRegion")){
						 check_Port_repositionRegion_id=headerJsonobj.getString("RepositionRegion");
					}
					String check_CargoType_cargotype_id="";
					if(headerJsonobj.has("CargoType")){
						check_CargoType_cargotype_id=headerJsonobj.getString("CargoType");
					}
					String vesselType_id="";
					if(headerJsonobj.has("VesselType")){
					   vesselType_id=headerJsonobj.getString("VesselType");
					} 
					
					String ETABasisString="";
					if(headerJsonobj.has("ETABasis")){
					   ETABasisString=headerJsonobj.getString("ETABasis");
					}
					String Status="";
					if(headerJsonobj.has("Status")){
						Status=headerJsonobj.getString("Status");
					}
					String ReportType="";
					if(headerJsonobj.has("ReportType")){
						ReportType=headerJsonobj.getString("ReportType");
					}
					
					 dataAll=new JSONObject();
					 dataAll.put("RG", check_Port_repositionRegion_id);
					 dataAll.put("CT", check_CargoType_cargotype_id);
					 dataAll.put("VT", vesselType_id);
					 dataAll.put("EB", ETABasisString);
					 
					 dataAll.put("StatusHeader", Status);
					 dataAll.put("RT", ReportType);
					 
					 dataall=dataAll.toString();
				}
			}// length check here
			}
	        // ................. close headers data..........
			
		} catch (Exception e) {
			
		}
		return dataall;
	}

}

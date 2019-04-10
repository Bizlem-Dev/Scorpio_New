package com.subjectheaders;

import java.util.Iterator;
import java.util.Stack;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;

public class PdfSubjectHeaders {

	public static void main(String[] args) {

		String data="{\r\n" + 
				"\"headerdata\":[\r\n" + 
				"{\r\n" + 
				"\"header_data\":{\r\n" + 
				"\"ETABasis\":\"29/10/2018\",\r\n" + 
				"\"RepositionRegion\":\"BALTIC/UKC\",\r\n" + 
				"\"unknown_0\":\"BRS\",\r\n" + 
				"\"unknown_1\":\"DAILY DPP\",\r\n" + 
				"\"unknown_10\":\"30\",\r\n" + 
				"\"unknown_11\":\"WS 220\",\r\n" + 
				"\"unknown_12\":\"=\",\r\n" + 
				"\"unknown_13\":\"HANDY/MR\",\r\n" + 
				"\"unknown_14\":\"0\",\r\n" + 
				"\"unknown_15\":\"days\",\r\n" + 
				"\"unknown_16\":\"0\",\r\n" + 
				"\"unknown_17\":\"days\",\r\n" + 
				"\"unknown_2\":\"30-55KT\",\r\n" + 
				"\"unknown_3\":\"ROUTE\",\r\n" + 
				"\"unknown_4\":\"QTY\",\r\n" + 
				"\"unknown_5\":\"RATE\",\r\n" + 
				"\"unknown_6\":\"TREND\",\r\n" + 
				"\"unknown_7\":\"T/S DELAYS\",\r\n" + 
				"\"unknown_8\":\"N/B\",\r\n" + 
				"\"unknown_9\":\"S/B\"\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"}\r\n" + 
				"]\r\n" + 
				"}";
		
		JSONObject pdfData=pdfData(data);
		if(pdfData!=null && pdfData.length()!=0){
			//System.out.println(pdfData);
			 String q="USG CPP LIST BASIS HOUSTON";
			JSONObject d= SolrLogicSubjectHeaders.SubjectData(q, null);
			JSONObject dataFinal=finalHeaderPdfData(d, pdfData);
			System.out.println(dataFinal);
			
		}
		
	}
	
	
	public static JSONObject finalHeaderPdfData(JSONObject myJSonSubject, JSONObject expertScriptJSon){
		JSONObject finalHeaderData=new JSONObject();
		try {
			
			JSONObject header_data=null;
			
			String RepositionRegionExpert="";
			String vesselTypeExpert="";
			String cargoTypeExpert="";
			String DateExpert="";
			
			if(expertScriptJSon.has("RepositionRegion")){
				JSONObject RepositionRegionExpertjsonobj=expertScriptJSon.getJSONObject("RepositionRegion");
				
				if(RepositionRegionExpertjsonobj.has("RepositionRegion")){
					RepositionRegionExpert=RepositionRegionExpertjsonobj.getString("RepositionRegion");
				}
			}
			if(expertScriptJSon.has("VesselType")){
				JSONObject vesselTypeExpertjsonobj=expertScriptJSon.getJSONObject("VesselType");
				if(vesselTypeExpertjsonobj.has("VesselType")){
					vesselTypeExpert=vesselTypeExpertjsonobj.getString("VesselType");
				}
			}
			if(expertScriptJSon.has("CargoType")){
				JSONObject cargoTypeExpertjsonobj=expertScriptJSon.getJSONObject("CargoType");
				if(cargoTypeExpertjsonobj.has("CargoType")){
					cargoTypeExpert=cargoTypeExpertjsonobj.getString("CargoType");
				}
			}
			if(expertScriptJSon.has("ETABasis")){
				JSONObject DateExpertjsonobj=expertScriptJSon.getJSONObject("ETABasis");
				if(DateExpertjsonobj.has("ETABasis")){
					DateExpert=DateExpertjsonobj.getString("ETABasis");
				}
			}
			
			
			//System.out.println(DateExpert);
			
			String RepositionRegionMyJson="";
			String VesselTypeMyJson="";
			String Cargo_TypeMyJson="";
			String ETABasisMyJson="";
			
			if(myJSonSubject.has("RepositionRegion")){
				RepositionRegionMyJson=myJSonSubject.getString("RepositionRegion");
			}
			if(myJSonSubject.has("VesselType")){
				VesselTypeMyJson=myJSonSubject.getString("VesselType");
			}
			if(myJSonSubject.has("Cargo_Type")){
				Cargo_TypeMyJson=myJSonSubject.getString("Cargo_Type");
			}else if(myJSonSubject.has("CargoType")){
				Cargo_TypeMyJson=myJSonSubject.getString("CargoType");
			}
			if(myJSonSubject.has("ETABasis")){
				ETABasisMyJson=myJSonSubject.getString("ETABasis");
			}
			
			if(GmailMethods.isNullString(RepositionRegionMyJson)){
				finalHeaderData.put("RepositionRegion", RepositionRegionExpert);
			}else{
				finalHeaderData.put("RepositionRegion", RepositionRegionMyJson);
			}
			if(GmailMethods.isNullString(VesselTypeMyJson)){
				finalHeaderData.put("VesselType", vesselTypeExpert);
			}else{
				finalHeaderData.put("VesselType", VesselTypeMyJson);
			}
			if(GmailMethods.isNullString(Cargo_TypeMyJson)){
				finalHeaderData.put("CargoType", cargoTypeExpert);
			}else{
				finalHeaderData.put("CargoType", Cargo_TypeMyJson);
			}
			if(GmailMethods.isNullString(ETABasisMyJson)){
				finalHeaderData.put("ETABasis", DateExpert);
			}else{
				finalHeaderData.put("ETABasis", ETABasisMyJson);
			}
			
			
		} catch (Exception e) {
			
		}
		return finalHeaderData;
	}
	
	
	public static JSONObject pdfData(String vinayaJSonPass){
		JSONObject finaldata=new JSONObject();
		//Set <JSONObject> mapData= new HashSet<JSONObject>();
		Stack <JSONObject> stRep=new Stack<JSONObject>();
		Stack <JSONObject> stCargo=new Stack<JSONObject>();
		Stack <JSONObject> stVt=new Stack<JSONObject>();
		Stack <JSONObject> etaBasis=new Stack<JSONObject>();
		try {
			JSONObject jsonOutputData=new JSONObject(vinayaJSonPass);
			if(jsonOutputData.has("headerdata")){
				
				JSONArray headerdata=jsonOutputData.getJSONArray("headerdata");
//				JSONObject f=null;
				for(int i=0;i<headerdata.length();i++){
					JSONObject zeroHeaderData=headerdata.getJSONObject(0);
					if(zeroHeaderData.has("header_data")){
						JSONObject header_data=zeroHeaderData.getJSONObject("header_data");
						Iterator<String>itr=header_data.keys();
						while(itr.hasNext()){
							String dynamicKeys=itr.next();
							//System.out.println(dynamicKeys);
							String values=header_data.getString(dynamicKeys);
							String data=SolrLogicSubjectHeaders.fetchsolrdata(values);
							JSONObject eta = new JSONObject();
							if(dynamicKeys.equals("ETABasis")){
							   eta.put("ETABasis",values);
							   etaBasis.push(eta);
							}
							
							JSONObject JSonObj=parsesolrresp(data);
							//System.out.println("Json before add "+JSonObj);
							if(JSonObj != null && JSonObj.length()>0){
								//System.out.println("Json Retrieved is ::"+JSonObj);
							//RepositionRegion CargoType VesselType 
								if(JSonObj.has("RepositionRegion")){
									stRep.push(JSonObj);	
									System.out.println("Reposition Stack Size :: "+stRep.size());
								}else if(JSonObj.has("CargoType")){
									stCargo.push(JSonObj);
								}else if(JSonObj.has("VesselType")){
									stVt.push(JSonObj);
								}
								
								
							}
							//finaldata.put(JSonObj);
							//mapData.add(JSonObj);
						}
						if(!stRep.empty()){
							finaldata.put("RepositionRegion",stRep.pop());
						}
						if(!stCargo.empty()){
							finaldata.put("CargoType",stCargo.pop());
						}
						if(!stVt.empty()){
							finaldata.put("VesselType",stVt.pop());
						}
						if(!etaBasis.empty()){
							finaldata.put("ETABasis",etaBasis.pop());
						}
							
					}
					
				}
				
//				System.out.println(finaldata);
			}
			
		} catch (Exception e) {
			
		}
		return finaldata;
	}
	
	public static JSONObject parsesolrresp(String solrResp) {
//		String allData="";
		JSONObject keyValue=null;
		//System.out.println("resp "+resp);
		try {
			JSONObject sorrespobj = new JSONObject(solrResp);
			// JSONArray arrayKeyValueData=new JSONArray();
			if (sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore")
					&& sorrespobj.getJSONObject("response").has("docs")) {

				JSONObject Subsorrespobj = sorrespobj.getJSONObject("response");
				JSONArray docs = Subsorrespobj.getJSONArray("docs");
//              if(docs.length()>0){
				for (int i = 0; i < docs.length(); i++) {
					 JSONObject subdocs = docs.getJSONObject(i);
					 String keyString="";
					 String valueString="";
					 if(subdocs.has("Key")){
						 JSONArray key= subdocs.getJSONArray("Key");
						  keyString= key.getString(0);
					 }
					 if(subdocs.has("value")){
						 JSONArray value= subdocs.getJSONArray("value");
						 valueString= value.getString(0);
					 }
					 keyValue=new JSONObject();
					 keyValue.put(keyString, valueString);
					 
					/* arrayKeyValueData.put(keyValue);*/
					
					
//					 System.out.println("key: "+ keyString +"  "+"value: "+ valueString);
				} // for close
				/*if(arrayKeyValueData.length()>0){
				   allData= arrayKeyValueData.toString();
				}*/
//			}// check length
			}

		} catch (Exception e) {
			//return allData;
                //e.printStackTrace();
		}

		return keyValue;
	}
	
	

}

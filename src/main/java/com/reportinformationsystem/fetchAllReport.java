package com.reportinformationsystem;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.readGmail.SlingMethods;
import com.subjectheaders.CombinePortAndDateSeparateLogic;

import ChangedStructureCurrent.ChangedStructureCurrent_Methods;
import ChangedStructureCurrent.FetchData;

public class fetchAllReport {

	
	public static void insertUnknownInSling(PrintWriter out, Session session, String DynamicJson, String BrokerName, String timestampDateAndTime, String reportcomesFrom, String emailUrl, String subjectNodePath, String textSentMailTime, String timestampDate){
		
try {
	
			
			JSONObject objectjson = new JSONObject(DynamicJson);
	        
            Iterator<String> keys = objectjson.keys();
            while (keys.hasNext()) {
             String key = keys.next();
             Object keyValue = objectjson.get(key);
             if (keyValue instanceof JSONArray) {
            	   
            		JSONArray allReportJsonArray=null;
        			ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();
        			
        			allReportJsonArray = (JSONArray)keyValue;
        				for(int i=0;i<allReportJsonArray.length();i++){
        					JSONObject allReportJsonArrayInsideJSonobject=allReportJsonArray.getJSONObject(i);
        					String check_ReportType_id="";
        					String ReportTypeString="";
        					
        					if(allReportJsonArrayInsideJSonobject.has("ReportType")){
        						ReportTypeString=allReportJsonArrayInsideJSonobject.getString("ReportType");
        						String returnReportType = SaveReportDataClass.urlValue(ReportTypeString);
        						
        						check_ReportType_id=CSC.check_ReportType(session, returnReportType, out);
        					}
        					
        					String check_Port_repositionRegion_id="";
        					out.println("before RepositionRegion: ");
        					if(allReportJsonArrayInsideJSonobject.has("RepositionRegion")){
        						String RepositionRegionString=allReportJsonArrayInsideJSonobject.getString("RepositionRegion");
        						String returnRepositionRegion = SaveReportDataClass.urlValue(RepositionRegionString);
        						
        						if(!GmailMethods.isNullString(returnRepositionRegion)){
        							if(returnRepositionRegion.contains("_")){
        								returnRepositionRegion=  returnRepositionRegion.replace("_", " ");
        								//out.println("returnDiscPortString: "+returnCargoType);
        						      }
        						  String check_Port_repositionRegion_id1=CSC.check_Port(session, returnRepositionRegion, out);
        						   boolean checkjsonString=SaveReportDataClass.isJSONValid(check_Port_repositionRegion_id1);
        						   if(checkjsonString==true){
        							   JSONObject data=new JSONObject(check_Port_repositionRegion_id1);
        							   if(data.has("port_id")){
        								   check_Port_repositionRegion_id=data.getString("port_id");
        							   }
        							   
        							   
        						   }
        						}/*else{
        							check_Port_repositionRegion_id="";
        						}*/
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion")){
        						String RepositionRegionString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion");
        						String returnRepositionRegion = SaveReportDataClass.urlValue(RepositionRegionString);
        						
        						if(!GmailMethods.isNullString(returnRepositionRegion)){
        							if(returnRepositionRegion.contains("_")){
        								returnRepositionRegion=  returnRepositionRegion.replace("_", " ");
        								//out.println("returnDiscPortString: "+returnCargoType);
        						      }
        						  String check_Port_repositionRegion_id1=CSC.check_Port(session, returnRepositionRegion, out);
        						   boolean checkjsonString=SaveReportDataClass.isJSONValid(check_Port_repositionRegion_id1);
        						   if(checkjsonString==true){
        							   JSONObject data=new JSONObject(check_Port_repositionRegion_id1);
        							   if(data.has("port_id")){
        								   check_Port_repositionRegion_id=data.getString("port_id");
        							   }
        							   
        							   
        						   }
        						}
        					}
        					
        					out.println("before CargoType: ");
        					String check_CargoType_cargotype_id="";
        					if(allReportJsonArrayInsideJSonobject.has("CargoType")){
        						String CargoTypeString=allReportJsonArrayInsideJSonobject.getString("CargoType");
        						String returnCargoType = SaveReportDataClass.urlValue(CargoTypeString);
        						if(returnCargoType.contains("_")){
        							returnCargoType=  returnCargoType.replace("_", " ");
    								//out.println("returnDiscPortString: "+returnCargoType);
    						      }
        						check_CargoType_cargotype_id=CSC.check_CargoType(session, returnCargoType,out);
        						out.println("CargoTypeString: "+returnCargoType);
        						out.println("check_CargoType_cargotype_id: "+check_CargoType_cargotype_id);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type")){
        						String CargoTypeString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type");
        						String returnCargoType = SaveReportDataClass.urlValue(CargoTypeString);
        						if(returnCargoType.contains("_")){
        							returnCargoType=  returnCargoType.replace("_", " ");
    								//out.println("returnDiscPortString: "+returnCargoType);
    						      }
        						check_CargoType_cargotype_id=CSC.check_CargoType(session, returnCargoType,out);
        						out.println("CargoTypeString_url: "+returnCargoType);
        						out.println("check_CargoType_cargotype_id_url: "+check_CargoType_cargotype_id);
        					}
        					
        					String check_vesselNameProperties="";
        					String vessel_Id="";
        					String built="";
        					String cubics="";
        					String dwt="";
        					String ice="";
        					String loa="";
        					String owners_id="";
        					String sternline="";
        					String vesselType_id="";
        					String status_id="";
        					Node scorpioDataBase=null;
        					Node vessel=null;
        					String flag="";
        					
        					String check_Operators_id="";
        					
        					if(session.getRootNode().hasNode("scorpioDataBase")){
        						scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
        					}
        					if(scorpioDataBase.hasNode("vessel")){
        						vessel=scorpioDataBase.getNode("vessel");
        					}
        					
        					if(allReportJsonArrayInsideJSonobject.has("VesselName")){
        						Node vesselSubNodeName=null;
        						String VesselNameString=allReportJsonArrayInsideJSonobject.getString("VesselName");
        						String returnVesselName = SaveReportDataClass.urlValue(VesselNameString);
        						
        						if(!GmailMethods.isNullString(returnVesselName)){
        							out.println("returnVesselName: "+returnVesselName);
        							
        							if(returnVesselName.contains("_")){
        								returnVesselName=  returnVesselName.replace("_", " ");
        								out.println("returnVesselName_after: "+returnVesselName);
        						      }
        							
        						   check_vesselNameProperties=CSC.check_vesselName(session, returnVesselName, out);
       						    //out.println("nullwala: "+check_vesselNameProperties);
        						if(!GmailMethods.isNullString(check_vesselNameProperties)){
        							
//        						out.println("check_vesselNameProperties_test: "+check_vesselNameProperties);
        						
        						boolean checkjsonString=SaveReportDataClass.isJSONValid(check_vesselNameProperties);
        						JSONObject check_vesselNamePropertiesJsonobject=null;
        						if(checkjsonString==true){
        							check_vesselNamePropertiesJsonobject=new JSONObject(check_vesselNameProperties);
        						
        							if(check_vesselNamePropertiesJsonobject.has("Flag")){
        								flag=check_vesselNamePropertiesJsonobject.getString("Flag");
        							}
//        						if(check_vesselNamePropertiesJsonobject.length()>0){
//        						out.println("check_vesselNameProperties_test1: "+check_vesselNamePropertiesJsonobject);
        						if(check_vesselNamePropertiesJsonobject.has("vessel_Id")){
        							vessel_Id=check_vesselNamePropertiesJsonobject.getString("vessel_Id");
//        							out.println("check_vesselNameProperties_test2: "+vessel_Id);
        						}
        						
        						} // json string check is valid wala
        						else{
//        							out.println("else");
        							if(vessel.hasNode(check_vesselNameProperties)){
        								vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        								vessel_Id=vesselSubNodeName.getProperty("vessel_Id").getString();
        							}
        						}
        						
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("built")){
        							built=check_vesselNamePropertiesJsonobject.getString("built");
        						}
        					} //valid json
        						else{
        							if(allReportJsonArrayInsideJSonobject.has("Built")){
        								built=allReportJsonArrayInsideJSonobject.getString("Built");
        								String returnBuilt = SaveReportDataClass.urlValue(built);
//        								out.println("data s");
        								if(!GmailMethods.isNullString(returnBuilt)){
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("built", returnBuilt);
        									session.save();
        								}
        							}
        								
        							}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt")){
        								built=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt");
        								String returnBuilt = SaveReportDataClass.urlValue(built);
//        								out.println("data s");
        								if(!GmailMethods.isNullString(returnBuilt)){
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("built", returnBuilt);
        									session.save();
        								}
        							}
        							} // url built
        							
        						}
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("cubics")){
        							cubics=check_vesselNamePropertiesJsonobject.getString("cubics");
        						}
        					}// isjsonvalid
        						else{
        							if(allReportJsonArrayInsideJSonobject.has("Cubics")){
        								cubics=allReportJsonArrayInsideJSonobject.getString("Cubics");
        								String returncubics = SaveReportDataClass.urlValue(cubics);
//        								out.println("data sq");
        								if(!GmailMethods.isNullString(returncubics)){
        								if(vessel.hasNode(check_vesselNameProperties)){
//        									out.println("data s2");
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("cubics", returncubics);
        									session.save();
        								}
        							}
        							}
        							else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub")){
        								cubics=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub");	
        								String returncubics = SaveReportDataClass.urlValue(cubics);
//        								out.println("data sq");
        								if(!GmailMethods.isNullString(returncubics)){
        								if(vessel.hasNode(check_vesselNameProperties)){
//        									out.println("data s2");
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("cubics", returncubics);
        									session.save();
        								}
        							}
        							
        							}
        							
        						}
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("dwt")){
        							dwt=check_vesselNamePropertiesJsonobject.getString("dwt");
        						}
        						
        						}else{
        							if(allReportJsonArrayInsideJSonobject.has("DWT")){
        								dwt=allReportJsonArrayInsideJSonobject.getString("DWT");
        								String returndwt = SaveReportDataClass.urlValue(dwt);
//        								out.println("data dwt");
        								if(!GmailMethods.isNullString(returndwt)){
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("dwt", returndwt);
        									session.save();
        								}
        							}
        							}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT")){
        								dwt=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT");
        								String returndwt = SaveReportDataClass.urlValue(dwt);
        								if(!GmailMethods.isNullString(returndwt)){
            								if(vessel.hasNode(check_vesselNameProperties)){
            									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
            									vesselSubNodeName.setProperty("dwt", returndwt);
            									session.save();
            								}
            							}
        							}
        						}
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("ice")){
        							ice=check_vesselNamePropertiesJsonobject.getString("ice");
        						}
        						}else{
        							if(allReportJsonArrayInsideJSonobject.has("ICE")){
        								ice=allReportJsonArrayInsideJSonobject.getString("ICE");
        								String returnice = SaveReportDataClass.urlValue(ice);
//        								out.println("data ice");
        								if(!GmailMethods.isNullString(returnice)){
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("ice", returnice);
        									session.save();
        								}
        							}
        							}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ICE")){
        								ice=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ICE");
        								String returnice = SaveReportDataClass.urlValue(ice);
        								if(!GmailMethods.isNullString(returnice)){
            								if(vessel.hasNode(check_vesselNameProperties)){
            									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
            									vesselSubNodeName.setProperty("ice", returnice);
            									session.save();
            								}
            							}
        							}
        						}
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("loa")){
        							loa=check_vesselNamePropertiesJsonobject.getString("loa");
        							
        						}}else{
        							if(allReportJsonArrayInsideJSonobject.has("LOA")){
        								loa=allReportJsonArrayInsideJSonobject.getString("LOA");
        								String returloa = SaveReportDataClass.urlValue(loa);
//        								out.println("data loa");
        								if(!GmailMethods.isNullString(returloa)){	
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("loa", returloa);
        									session.save();
        								}
        							}
        							}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA")){
        								loa=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA");
        								String returloa = SaveReportDataClass.urlValue(loa);
        								if(!GmailMethods.isNullString(returloa)){	
            								if(vessel.hasNode(check_vesselNameProperties)){
            									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
            									vesselSubNodeName.setProperty("loa", returloa);
            									session.save();
            								}
            							}
        							}
        						}
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("owners_id")){
        							owners_id=check_vesselNamePropertiesJsonobject.getString("owners_id");
        						}}else{
        							if(allReportJsonArrayInsideJSonobject.has("Owners")){
        								String owners_Name=allReportJsonArrayInsideJSonobject.getString("Owners");
        								String returnowners_Name = SaveReportDataClass.urlValue(owners_Name);
        								
        								owners_id=ChangedStructureCurrent_Methods.check_Owners(session, returnowners_Name,out);
//        								out.println("data owner");
        								if(!GmailMethods.isNullString(owners_id)){
        									
        									String vesselNamequery="";
        									if(checkjsonString==true){
        									if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
        										vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
        									   //out.println("vesselNamequery_owners: "+vesselNamequery);
        									
        									}}
        									if(vessel.hasNode(vesselNamequery)){
        										vesselSubNodeName=vessel.getNode(vesselNamequery);
        										//out.println("noode: "+vesselSubNodeName);
        										vesselSubNodeName.setProperty("owners_id", owners_id);
        										session.save();
        										
        									}else{
        										//out.println("vesselNamequery_owners_else: ");
        								if(vessel.hasNode(check_vesselNameProperties)){
        									//out.println("vesselNamequery_owners_if: ");
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("owners_id", owners_id);
        									session.save();
        									//out.println("vesselNamequery_owners_last: ");
        								}
        							}
        							}
        							}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners")){
        								String owners_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners");
        								String returnowners_Name = SaveReportDataClass.urlValue(owners_Name);
        								owners_id=ChangedStructureCurrent_Methods.check_Owners(session, returnowners_Name,out);
//        								out.println("data owner");
        								if(!GmailMethods.isNullString(owners_id)){
        									
        									String vesselNamequery="";
        									if(checkjsonString==true){
        									if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
        										vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
        									}}
        									if(vessel.hasNode(vesselNamequery)){
        										vesselSubNodeName=vessel.getNode(vesselNamequery);
//        										out.println("noode: "+vesselSubNodeName);
        										vesselSubNodeName.setProperty("owners_id", owners_id);
        										session.save();
        									}else{
        									
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("owners_id", owners_id);
        									session.save();
        								}
        							}
        							}
        								
        							}
        						}
        						
//        						String operatorId="";
        						if(checkjsonString==true){
            						if(check_vesselNamePropertiesJsonobject.has("operatorId")){
            							check_Operators_id=check_vesselNamePropertiesJsonobject.getString("operatorId");
            						}}else{
            							if(allReportJsonArrayInsideJSonobject.has("Operators")){
            								String opertaor_Name=allReportJsonArrayInsideJSonobject.getString("Operators");
            								String returnopertaor_Name = SaveReportDataClass.urlValue(opertaor_Name);
            								
            								check_Operators_id=CSC.check_Operators(session, returnopertaor_Name,out);
//            								out.println("data owner");
            								if(!GmailMethods.isNullString(check_Operators_id)){
            									
            									String vesselNamequery="";
            									if(checkjsonString==true){
            									if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
            										vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
            									}}
            									if(vessel.hasNode(vesselNamequery)){
            										vesselSubNodeName=vessel.getNode(vesselNamequery);
//            										out.println("noode: "+vesselSubNodeName);
            										vesselSubNodeName.setProperty("operatorId", check_Operators_id);
            										session.save();
            									}else{
            									
            								if(vessel.hasNode(check_vesselNameProperties)){
            									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
            									vesselSubNodeName.setProperty("operatorId", check_Operators_id);
            									session.save();
            								}
            							}
            							}
            							}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators")){
            								String opertaor_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators");
            								String returnopertaor_Name = SaveReportDataClass.urlValue(opertaor_Name);
            								check_Operators_id=CSC.check_Operators(session, returnopertaor_Name,out);
//            								out.println("data owner");
            								if(!GmailMethods.isNullString(check_Operators_id)){
            									
            									String vesselNamequery="";
            									if(checkjsonString==true){
            									if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
            										vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
            									}}
            									if(vessel.hasNode(vesselNamequery)){
            										vesselSubNodeName=vessel.getNode(vesselNamequery);
//            										out.println("noode: "+vesselSubNodeName);
            										vesselSubNodeName.setProperty("operatorId", check_Operators_id);
            										session.save();
            									}else{
            									
            								if(vessel.hasNode(check_vesselNameProperties)){
            									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
            									vesselSubNodeName.setProperty("operatorId", check_Operators_id);
            									session.save();
            								}
            							}
            							}
            							}
            						}
        						
        						
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("sternline")){
        							sternline=check_vesselNamePropertiesJsonobject.getString("sternline");
        							
        						}}else{
        							if(allReportJsonArrayInsideJSonobject.has("SternLine")){
        								sternline=allReportJsonArrayInsideJSonobject.getString("SternLine");
        								String returnsternline = SaveReportDataClass.urlValue(sternline);
//        								out.println("data sl");
        								if(!GmailMethods.isNullString(returnsternline)){
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("sternline", returnsternline);
        									session.save();
        								}
        							}
        							}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselSternLine")){
        								sternline=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselSternLine");
        								String returnsternline = SaveReportDataClass.urlValue(sternline);
        								if(!GmailMethods.isNullString(returnsternline)){
            								if(vessel.hasNode(check_vesselNameProperties)){
            									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
            									vesselSubNodeName.setProperty("sternline", returnsternline);
            									session.save();
            								}
            							}
        							}
        						}
        					
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("vesselType_id")){
        							vesselType_id=check_vesselNamePropertiesJsonobject.getString("vesselType_id");
        							
        							
        						}}else{
        							if(allReportJsonArrayInsideJSonobject.has("VesselType")){
        								String VesselType_Name=allReportJsonArrayInsideJSonobject.getString("VesselType");
        								String returnVesselType_Name = SaveReportDataClass.urlValue(VesselType_Name);
        								vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);
//        								out.println("data type");
        								if(!GmailMethods.isNullString(vesselType_id)){
//        									VesselNameString=VesselNameString.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
        								/*if(vessel.hasNode(check_vesselNameProperties) || vessel.hasNode(VesselNameString)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        									session.save();
        								}*/
        									String vesselNamequery="";
        									if(checkjsonString==true){
        									if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
        										vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
        										
        									}}
        									
        									
//        									out.println("vesseltyped: "+VesselNameString);
        									if(vessel.hasNode(vesselNamequery)){
        										vesselSubNodeName=vessel.getNode(vesselNamequery);
//        										out.println("noode: "+vesselSubNodeName);
        										vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        										session.save();
        									}else{
        										if(vessel.hasNode(check_vesselNameProperties)){
        											vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        											vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        											session.save();
        										}
        									}
        									
        									/*vesselSubNodeName=(vessel.hasNode(check_vesselNameProperties)) ? vessel.getNode(check_vesselNameProperties) : vessel.getNode(VesselNameString);
        									out.println("g: "+vesselSubNodeName);
        									vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        									session.save();*/
        							}else{
        								vesselType_id="";
        							}
        							} else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){
        								
        								String VesselType_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType");
        								String returnVesselType_Name = SaveReportDataClass.urlValue(VesselType_Name);
        								vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);
//        								out.println("data type");
        								if(!GmailMethods.isNullString(vesselType_id)){
//        									VesselNameString=VesselNameString.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
        								/*if(vessel.hasNode(check_vesselNameProperties) || vessel.hasNode(VesselNameString)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        									session.save();
        								}*/
        									String vesselNamequery="";
        									if(checkjsonString==true){
        									if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
        										vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
        										
        									}}
        									
        									
//        									out.println("vesseltyped: "+VesselNameString);
        									if(vessel.hasNode(vesselNamequery)){
        										vesselSubNodeName=vessel.getNode(vesselNamequery);
//        										out.println("noode: "+vesselSubNodeName);
        										vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
//        										session.save();
        									}else{
        										if(vessel.hasNode(check_vesselNameProperties)){
        											vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        											vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
//        											session.save();
        										}
        									}
        									
        									/*vesselSubNodeName=(vessel.hasNode(check_vesselNameProperties)) ? vessel.getNode(check_vesselNameProperties) : vessel.getNode(VesselNameString);
        									out.println("g: "+vesselSubNodeName);
        									vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        									session.save();*/
        							}else{
        								vesselType_id="";
        							}
        								
        							}
        						}
//        						out.println("end");
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("status_id")){
        							status_id=check_vesselNamePropertiesJsonobject.getString("status_id");
        						}}
        						
//        					}
        				} // null check when vesselname is not there
        			}// vesselname check from json
        						else{
        							if(allReportJsonArrayInsideJSonobject.has("VesselType")){
        								String VesselType_Name=allReportJsonArrayInsideJSonobject.getString("VesselType");
        								String returnVesselType_Name = SaveReportDataClass.urlValue(VesselType_Name);
        								vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);
        								
        							} // this condition used in brokertcrate report only bcz there is not vessel name given
        							else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){
        								String VesselType_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType");
        								String returnVesselType_Name = SaveReportDataClass.urlValue(VesselType_Name);
        								vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);
        								
        							}
        						
        						}
        					out.println("vesselnameProperty is set to null");
        		}// all vessel property
        					
        					
        					else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselName")){
        						
        						Node vesselSubNodeName=null;
        						String VesselNameString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselName");
        						String returnVesselName = SaveReportDataClass.urlValue(VesselNameString);
        						
        						if(!GmailMethods.isNullString(returnVesselName)){
        							if(returnVesselName.contains("_")){
        								returnVesselName=  returnVesselName.replace("_", " ");
        								out.println("returnVesselName_after_url: "+returnVesselName);
        						      }
        							
        						   check_vesselNameProperties=CSC.check_vesselName(session, returnVesselName, out);
//        						out.println("nullwala: "+check_vesselNameProperties);
        						if(!GmailMethods.isNullString(check_vesselNameProperties)){
        							
//        						out.println("check_vesselNameProperties_test: "+check_vesselNameProperties);
        						
        						boolean checkjsonString=SaveReportDataClass.isJSONValid(check_vesselNameProperties);
        						JSONObject check_vesselNamePropertiesJsonobject=null;
        						if(checkjsonString==true){
        							check_vesselNamePropertiesJsonobject=new JSONObject(check_vesselNameProperties);
        						
        							if(check_vesselNamePropertiesJsonobject.has("Flag")){
        								flag=check_vesselNamePropertiesJsonobject.getString("Flag");
        							}
        							
//        						if(check_vesselNamePropertiesJsonobject.length()>0){
//        						out.println("check_vesselNameProperties_test1: "+check_vesselNamePropertiesJsonobject);
        						if(check_vesselNamePropertiesJsonobject.has("vessel_Id")){
        							vessel_Id=check_vesselNamePropertiesJsonobject.getString("vessel_Id");
//        							out.println("check_vesselNameProperties_test2: "+vessel_Id);
        						}
        						
        						} // json string check is valid wala
        						else{
//        							out.println("else");
        							if(vessel.hasNode(check_vesselNameProperties)){
        								vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        								vessel_Id=vesselSubNodeName.getProperty("vessel_Id").getString();
        							}
        						}
        						
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("built")){
        							built=check_vesselNamePropertiesJsonobject.getString("built");
        						}
        					} //valid json
        						else{
        							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt")){
        								built=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt");
        								String returnBuilt = SaveReportDataClass.urlValue(built);
//        								out.println("data s");
        								if(!GmailMethods.isNullString(returnBuilt)){
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("built", returnBuilt);
//        									session.save();
        								}
        							}
        								
        							}
        							
        						}
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("cubics")){
        							cubics=check_vesselNamePropertiesJsonobject.getString("cubics");
        						}
        					}// isjsonvalid
        						else{
        							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub")){
        								cubics=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub");
        								String returncubics = SaveReportDataClass.urlValue(cubics);
//        								out.println("data sq");
        								if(!GmailMethods.isNullString(returncubics)){
        								if(vessel.hasNode(check_vesselNameProperties)){
//        									out.println("data s2");
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("cubics", returncubics);
//        									session.save();
        								}
        							}
        							}
        						}
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("dwt")){
        							dwt=check_vesselNamePropertiesJsonobject.getString("dwt");
        						}
        						
        						}else{
        							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT")){
        								dwt=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT");
        								String returndwt = SaveReportDataClass.urlValue(dwt);
//        								out.println("data dwt");
        								if(!GmailMethods.isNullString(returndwt)){
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("dwt", returndwt);
//        									session.save();
        								}
        							}
        							}
        						}
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("ice")){
        							ice=check_vesselNamePropertiesJsonobject.getString("ice");
        						}
        						}else{
        							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ICE")){
        								ice=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ICE");
        								String returnice = SaveReportDataClass.urlValue(ice);
//        								out.println("data ice");
        								if(!GmailMethods.isNullString(returnice)){
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("ice", returnice);
//        									session.save();
        								}
        							}
        							}
        						}
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("loa")){
        							loa=check_vesselNamePropertiesJsonobject.getString("loa");
        							
        						}}else{
        							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA")){
        								loa=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA");
        								String returloa = SaveReportDataClass.urlValue(loa);
//        								out.println("data loa");
        								if(!GmailMethods.isNullString(returloa)){	
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("loa", returloa);
//        									session.save();
        								}
        							}
        							}
        						}
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("owners_id")){
        							owners_id=check_vesselNamePropertiesJsonobject.getString("owners_id");
        						}}else{
        							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners")){
        								String owners_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners");
        								String returnowners_Name = SaveReportDataClass.urlValue(owners_Name);
        								
        								owners_id=ChangedStructureCurrent_Methods.check_Owners(session, returnowners_Name,out);
//        								out.println("data owner");
        								if(!GmailMethods.isNullString(owners_id)){
        									
        									String vesselNamequery="";
        									if(checkjsonString==true){
        									if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
        										vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
        									}}
        									if(vessel.hasNode(vesselNamequery)){
        										vesselSubNodeName=vessel.getNode(vesselNamequery);
//        										out.println("noode: "+vesselSubNodeName);
        										vesselSubNodeName.setProperty("owners_id", owners_id);
//        										session.save();
        									}else{
        									
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("owners_id", owners_id);
//        									session.save();
        								}
        							}
        							}
        							}
        						}
        						
//        						String operatorId="";
        						if(checkjsonString==true){
            						if(check_vesselNamePropertiesJsonobject.has("operatorId")){
            							check_Operators_id=check_vesselNamePropertiesJsonobject.getString("operatorId");
            						}}else{
            							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators")){
            								String opertaor_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators");
            								String returnopertaor_Name = SaveReportDataClass.urlValue(opertaor_Name);
            								
            								check_Operators_id=CSC.check_Operators(session, returnopertaor_Name,out);
//            								out.println("data owner");
            								if(!GmailMethods.isNullString(check_Operators_id)){
            									
            									String vesselNamequery="";
            									if(checkjsonString==true){
            									if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
            										vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
            									}}
            									if(vessel.hasNode(vesselNamequery)){
            										vesselSubNodeName=vessel.getNode(vesselNamequery);
//            										out.println("noode: "+vesselSubNodeName);
            										vesselSubNodeName.setProperty("operatorId", check_Operators_id);
//            										session.save();
            									}else{
            									
            								if(vessel.hasNode(check_vesselNameProperties)){
            									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
            									vesselSubNodeName.setProperty("operatorId", check_Operators_id);
//            									session.save();
            								}
            							}
            							}
            							}
            						}
        						
        						
        						
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("sternline")){
        							sternline=check_vesselNamePropertiesJsonobject.getString("sternline");
        							
        						}}else{
        							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselSternLine")){
        								sternline=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselSternLine");
        								String returnsternline = SaveReportDataClass.urlValue(sternline);
//        								out.println("data sl");
        								if(!GmailMethods.isNullString(returnsternline)){
        								if(vessel.hasNode(check_vesselNameProperties)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("sternline", returnsternline);
//        									session.save();
        								}
        							}
        							}
        						}
        					
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("vesselType_id")){
        							vesselType_id=check_vesselNamePropertiesJsonobject.getString("vesselType_id");
        							
        							
        						}}else{
        							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){
        								String VesselType_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType");
        								String returnVesselType_Name = SaveReportDataClass.urlValue(VesselType_Name);
        								vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);
//        								out.println("data type");
        								if(!GmailMethods.isNullString(vesselType_id)){
//        									VesselNameString=VesselNameString.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
        								/*if(vessel.hasNode(check_vesselNameProperties) || vessel.hasNode(VesselNameString)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        									session.save();
        								}*/
        									String vesselNamequery="";
        									if(checkjsonString==true){
        									if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
        										vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
        										
        									}}
        									
        									
//        									out.println("vesseltyped: "+VesselNameString);
        									if(vessel.hasNode(vesselNamequery)){
        										vesselSubNodeName=vessel.getNode(vesselNamequery);
//        										out.println("noode: "+vesselSubNodeName);
        										vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
//        										session.save();
        									}else{
        										if(vessel.hasNode(check_vesselNameProperties)){
        											vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        											vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
//        											session.save();
        										}
        									}
        									
        									/*vesselSubNodeName=(vessel.hasNode(check_vesselNameProperties)) ? vessel.getNode(check_vesselNameProperties) : vessel.getNode(VesselNameString);
        									out.println("g: "+vesselSubNodeName);
        									vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        									session.save();*/
        							}else{
        								vesselType_id="";
        							}
        							}
        						}
//        						out.println("end");
        						if(checkjsonString==true){
        						if(check_vesselNamePropertiesJsonobject.has("status_id")){
        							status_id=check_vesselNamePropertiesJsonobject.getString("status_id");
        						}}
        						
//        					}
        				} // null check when vesselname is not there
        			}// vesselname check from json
        						else{
        							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){
        								String VesselType_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType");
        								String returnVesselType_Name = SaveReportDataClass.urlValue(VesselType_Name);
        								vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);
        								
        							} // this condition used in brokertcrate report only bcz there is not vessel name given
        							else if(allReportJsonArrayInsideJSonobject.has("VesselType")){
        								   String VesselType_Name=allReportJsonArrayInsideJSonobject.getString("VesselType");
        								   String returnVesselType_Name = SaveReportDataClass.urlValue(VesselType_Name);
           								   vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);
           								
        							}
        						}
        						
        					} // vessel else if check
        					
        					
        					else{
    							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){
    								String VesselType_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType");
    								String returnVesselType_Name = SaveReportDataClass.urlValue(VesselType_Name);
    								vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);
    								
    							} // this condition used in brokertcrate report only bcz there is not vessel name given
    							else if(allReportJsonArrayInsideJSonobject.has("VesselType")){
 								   String VesselType_Name=allReportJsonArrayInsideJSonobject.getString("VesselType");
 								   String returnVesselType_Name = SaveReportDataClass.urlValue(VesselType_Name);
    								   vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);
    								
 							}
    						}
        					
        					// extra for vesseltype
        						
        					/*String check_Operators_id="";
        					if(allReportJsonArrayInsideJSonobject.has("Operators")){
        						String OperatorsString=allReportJsonArrayInsideJSonobject.getString("Operators");
        						check_Operators_id=CSC.check_Operators(session, OperatorsString,out);
        					}*/
        					
        					String check_EmployementStatus="";
        					if(allReportJsonArrayInsideJSonobject.has("Status")){
        						String EmploymentStatusString=allReportJsonArrayInsideJSonobject.getString("Status");
        						String returnEmploymentStatusString = SaveReportDataClass.urlValue(EmploymentStatusString);
        						check_EmployementStatus=CSC.check_EmployementStatus(session, returnEmploymentStatusString,out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#EmploymentStatus")){
        						String EmploymentStatusString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#EmploymentStatus");
        						String returnEmploymentStatusString = SaveReportDataClass.urlValue(EmploymentStatusString);
        						check_EmployementStatus=CSC.check_EmployementStatus(session, returnEmploymentStatusString,out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status")){
        						String EmploymentStatusString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status");
        						String returnEmploymentStatusString = SaveReportDataClass.urlValue(EmploymentStatusString);
        						check_EmployementStatus=CSC.check_EmployementStatus(session, returnEmploymentStatusString,out);
        					}
        					
        					String check_OpenPort_id="";
        					String flagPortOpen="";
        					if(allReportJsonArrayInsideJSonobject.has("OpenPort")){
        						String OpenPortString=allReportJsonArrayInsideJSonobject.getString("OpenPort");
        						String returnOpenPortString = SaveReportDataClass.urlValue(OpenPortString);
        						
        						if(returnOpenPortString.contains("_")){
        							returnOpenPortString=  returnOpenPortString.replace("_", " ");
    								out.println("returnVesselName_after: "+returnOpenPortString);
    						      }
        						
        						check_OpenPort_id=CSC.check_Port(session, returnOpenPortString, out);
        						 boolean checkjsonString=SaveReportDataClass.isJSONValid(check_OpenPort_id);
      						   if(checkjsonString==true){
      							   JSONObject data=new JSONObject(check_OpenPort_id);
      							   if(data.has("port_id")){
      								 check_OpenPort_id=data.getString("port_id");
      							   }
      							   if(data.has("Area")){
//      								 check_Port_repositionRegion_id=data.getString("Area");
      								    check_Port_repositionRegion_id=check_OpenPort_id;
      							   }if(data.has("flag")){
      								 flagPortOpen=data.getString("flag");
      							   }
      							   
      							   
      						   }
      						   
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort")){
        						String OpenPortString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort");
        						String returnOpenPortString = SaveReportDataClass.urlValue(OpenPortString);
        						if(returnOpenPortString.contains("_")){
        							returnOpenPortString=  returnOpenPortString.replace("_", " ");
    								out.println("returnVesselName_: "+returnOpenPortString);
    						      }
        						check_OpenPort_id=CSC.check_Port(session, returnOpenPortString, out);
        						 boolean checkjsonString=SaveReportDataClass.isJSONValid(check_OpenPort_id);
      						   if(checkjsonString==true){
      							   JSONObject data=new JSONObject(check_OpenPort_id);
      							   if(data.has("port_id")){
      								 check_OpenPort_id=data.getString("port_id");
      							   }
      							   if(data.has("Area")){
//      								 check_Port_repositionRegion_id=data.getString("Area");
      								    check_Port_repositionRegion_id=check_OpenPort_id;
      							   }if(data.has("flag")){
      								 flagPortOpen=data.getString("flag");
      							   }
      							   
      							   
      						   }
        					}
        					
        					String OpenDateString="";
        					if(allReportJsonArrayInsideJSonobject.has("OpenDate")){
        						 OpenDateString=allReportJsonArrayInsideJSonobject.getString("OpenDate");
        						 OpenDateString = SaveReportDataClass.urlValue(OpenDateString);
        						 
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate")){
        						OpenDateString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate");
       						    OpenDateString = SaveReportDataClass.urlValue(OpenDateString);
        					}
        					
        					String ETABasisString="";
        					if(allReportJsonArrayInsideJSonobject.has("ETABasis")){
        						 ETABasisString=allReportJsonArrayInsideJSonobject.getString("ETABasis");
        						 ETABasisString = SaveReportDataClass.urlValue(ETABasisString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS")){
        						 ETABasisString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS");
        						 ETABasisString = SaveReportDataClass.urlValue(ETABasisString);
        					}
        					String CommentString="";
        					if(allReportJsonArrayInsideJSonobject.has("Comment")){
        						 CommentString=allReportJsonArrayInsideJSonobject.getString("Comment");
        						 CommentString = SaveReportDataClass.urlValue(CommentString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Comment")){
        						CommentString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Comment");
       						    CommentString = SaveReportDataClass.urlValue(CommentString);
        					}
        					String check_Source="";
        					if(allReportJsonArrayInsideJSonobject.has("Source")){
        						String SourceString=allReportJsonArrayInsideJSonobject.getString("Source");
        						SourceString = SaveReportDataClass.urlValue(SourceString);
        						check_Source=CSC.check_Source(session, SourceString,out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Source")){
        						String SourceString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Source");
        						SourceString = SaveReportDataClass.urlValue(SourceString);
        						check_Source=CSC.check_Source(session, SourceString,out);
        					}
        					else{
        						check_Source=CSC.check_Source(session, BrokerName,out);
        						
        					}
        					
//        					String combinedatetimestamp="";
        					String ReportTimestampString="";
        					JSONObject dateJson=null;
        					if(allReportJsonArrayInsideJSonobject.has("ReportTimestamp")){
        						 ReportTimestampString=allReportJsonArrayInsideJSonobject.getString("ReportTimestamp");
        						 ReportTimestampString = SaveReportDataClass.urlValue(ReportTimestampString);
        						/*if(ReportTimestampString!=""){
        							Date date=GmailMethods.parseDate(ReportTimestampString);
        							 dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);
        							 
        							    int day=0;
        								int month=0;
        								int year=0;
        								int hours=0;
        								int Minute=0;
        								String s="";
        								if(dateJson.has("day")){
        									 day=dateJson.getInt("day");
        									 String dayString=String.valueOf(day);
        									 boolean b3 = Pattern.matches("(\\d{1}$)", dayString); 
        									 if(b3==true){
        										  s="0"+day;
        									 }else{
        										 s=String.valueOf(day);
        									 }
        									 
        								}if(dateJson.has("month")){
        									 month=dateJson.getInt("month");
        								}
        								if(dateJson.has("year")){
        									 year=dateJson.getInt("year");
        								}
        								if(dateJson.has("hours")){
        									 hours=dateJson.getInt("hours");
        								}
        								if(dateJson.has("Minute")){
        									Minute=dateJson.getInt("Minute");
        								}
        							
        							 combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year)+" "+hours+":"+Minute;
        							 
        						}*/
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportTimeStamp")){
        						 ReportTimestampString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportTimeStamp");
        						 ReportTimestampString = SaveReportDataClass.urlValue(ReportTimestampString);
        					}
        					else {
        						ReportTimestampString=timestampDateAndTime;
        					}
        					
        					String check_Information="";
        					if(allReportJsonArrayInsideJSonobject.has("Information")){
        						String InformationString=allReportJsonArrayInsideJSonobject.getString("Information");
        						InformationString = SaveReportDataClass.urlValue(InformationString);
        						check_Information=CSC.check_Information(session, InformationString, out);
        						
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Information")){
        						String InformationString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Information");
        						InformationString = SaveReportDataClass.urlValue(InformationString);
        						check_Information=CSC.check_Information(session, InformationString, out);
        					}
        					String check_FixturetType_id="";
        					if(allReportJsonArrayInsideJSonobject.has("FixtureType")){
        						String FixtureTypeString=allReportJsonArrayInsideJSonobject.getString("FixtureType");
        						FixtureTypeString = SaveReportDataClass.urlValue(FixtureTypeString);
        						check_FixturetType_id=CSC.check_ReportType(session, FixtureTypeString, out);
        					}else if(allReportJsonArrayInsideJSonobject.has("Fixture Type")){
        						String FixtureTypeString=allReportJsonArrayInsideJSonobject.getString("Fixture Type");
        						FixtureTypeString = SaveReportDataClass.urlValue(FixtureTypeString);
        						check_FixturetType_id=CSC.check_ReportType(session, FixtureTypeString, out);
        					}
        					else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#FixtureType")){
        						String FixtureTypeString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#FixtureType");
        						FixtureTypeString = SaveReportDataClass.urlValue(FixtureTypeString);
        						check_FixturetType_id=CSC.check_ReportType(session, FixtureTypeString, out);
        					}
        					else{
        						check_FixturetType_id="2";
        					}
        					String check_Charterer="";
        					if(allReportJsonArrayInsideJSonobject.has("Charterer")){
        						String ChartererString=allReportJsonArrayInsideJSonobject.getString("Charterer");
        						ChartererString = SaveReportDataClass.urlValue(ChartererString);
        						check_Charterer=CSC.check_Charterer(session, ChartererString, out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers")){
        						String ChartererString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers");
        						ChartererString = SaveReportDataClass.urlValue(ChartererString);
        						check_Charterer=CSC.check_Charterer(session, ChartererString, out);
        					}
        					String check_Status="";
        					if(allReportJsonArrayInsideJSonobject.has("Status")){
        						String StatusString=allReportJsonArrayInsideJSonobject.getString("Status");
        						StatusString = SaveReportDataClass.urlValue(StatusString);
        						check_Status=CSC.check_EmployementStatus(session, StatusString,out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status")){
        						String StatusString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status");
        						StatusString = SaveReportDataClass.urlValue(StatusString);
        						check_Status=CSC.check_EmployementStatus(session, StatusString,out);
        					}
        					String check_CargoGrade="";
        					if(allReportJsonArrayInsideJSonobject.has("CargoGrade")){
        						String CargoGradeString=allReportJsonArrayInsideJSonobject.getString("CargoGrade");
        						CargoGradeString = SaveReportDataClass.urlValue(CargoGradeString);
        						check_CargoGrade=CSC.check_CargoGrade(session, CargoGradeString, out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade")){
        						String CargoGradeString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade");
        						CargoGradeString = SaveReportDataClass.urlValue(CargoGradeString);
        						check_CargoGrade=CSC.check_CargoGrade(session, CargoGradeString, out);
        					}
        					String CargoQtyString="";
        					if(allReportJsonArrayInsideJSonobject.has("CargoQty")){
        						 CargoQtyString=allReportJsonArrayInsideJSonobject.getString("CargoQty");
        						 CargoQtyString = SaveReportDataClass.urlValue(CargoQtyString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#CargoQty")){
        						CargoQtyString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#CargoQty");
       						    CargoQtyString = SaveReportDataClass.urlValue(CargoQtyString);
        					}
        					String LCStartString="";
        					if(allReportJsonArrayInsideJSonobject.has("LCStart")){
        						 LCStartString=allReportJsonArrayInsideJSonobject.getString("LCStart");
        						 LCStartString = SaveReportDataClass.urlValue(LCStartString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#LCEnd")){
        						LCStartString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#LCEnd");
       						    LCStartString = SaveReportDataClass.urlValue(LCStartString);
        					}
        					String LCEndString="";
        					if(allReportJsonArrayInsideJSonobject.has("LCEnd")){
        						 LCEndString=allReportJsonArrayInsideJSonobject.getString("LCEnd");
        						 LCEndString = SaveReportDataClass.urlValue(LCEndString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#LCStart")){
        						LCEndString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#LCStart");
       						    LCEndString = SaveReportDataClass.urlValue(LCEndString);
        					}else{
        						if(LCEndString.isEmpty()){
        						   LCEndString=LCStartString;
        						}
        					}
        					out.println("LCEndString: "+LCEndString);
        					String check_LoadPort_id="";
        					String flagPort="";
        					if(allReportJsonArrayInsideJSonobject.has("LoadPort")){
        						String LoadPortString=allReportJsonArrayInsideJSonobject.getString("LoadPort");
        						LoadPortString = SaveReportDataClass.urlValue(LoadPortString);
        						if(!GmailMethods.isNullString(LoadPortString)){
        							if(LoadPortString.contains("_")){
        								LoadPortString=  LoadPortString.replace("_", " ");
        								out.println("returnLoadPortString: "+LoadPortString);
        						      }
        						  String  check_LoadPort_id1=CSC.check_Port(session, LoadPortString, out);
        						   
        						   boolean checkjsonString=SaveReportDataClass.isJSONValid(check_LoadPort_id1);
          						   if(checkjsonString==true){
          							   JSONObject data=new JSONObject(check_LoadPort_id1);
          							   if(data.has("port_id")){
          								 check_LoadPort_id=data.getString("port_id");
          							   }
          							 if(data.has("flag")){
          								 flagPort=data.getString("flag");
          							   }
          							   
          						   }
        						   
        						}else{
        							check_LoadPort_id="";
//        							flagPort="false";
        						}
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#LoadPort")){
        						String LoadPortString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#LoadPort");
        						LoadPortString = SaveReportDataClass.urlValue(LoadPortString);
        						
        						if(!GmailMethods.isNullString(LoadPortString)){
        							if(LoadPortString.contains("_")){
        								LoadPortString=  LoadPortString.replace("_", " ");
        								out.println("returnLoadPortString: "+LoadPortString);
        						      }
        							
        						  String  check_LoadPort_id1=CSC.check_Port(session, LoadPortString, out);
        						   
        						   boolean checkjsonString=SaveReportDataClass.isJSONValid(check_LoadPort_id1);
          						   if(checkjsonString==true){
          							   JSONObject data=new JSONObject(check_LoadPort_id1);
          							   if(data.has("port_id")){
          								 check_LoadPort_id=data.getString("port_id");
          								out.println("check_LoadPort_id: "+check_LoadPort_id);
          							   }if(data.has("flag")){
          								 flagPort=data.getString("flag");
          							   }
          							   
          						   }
        						   
        						}else{
        							check_LoadPort_id="";
//        							flagPort="false";
        						}
        					}
        					String check_DiscPort_id="";
//        					String check_DiscPort_id1="";
        					if(allReportJsonArrayInsideJSonobject.has("DiscPort")){
        						String DiscPortString=allReportJsonArrayInsideJSonobject.getString("DiscPort");
        						DiscPortString = SaveReportDataClass.urlValue(DiscPortString);
        						if(!GmailMethods.isNullString(DiscPortString)){
        							if(DiscPortString.contains("_")){
        								DiscPortString=  DiscPortString.replace("_", " ");
        								out.println("returnDiscPortString: "+DiscPortString);
        						      }
        							
        						   String check_DiscPort_id1=CSC.check_Port(session, DiscPortString, out);
        						  
        						    boolean checkjsonString=SaveReportDataClass.isJSONValid(check_DiscPort_id1);
           						   if(checkjsonString==true){
           							   JSONObject data=new JSONObject(check_DiscPort_id1);
           							   if(data.has("port_id")){
           								check_DiscPort_id=data.getString("port_id");
           							   }if(data.has("flag")){
          								 flagPort=data.getString("flag");
          							   }
           							   
           						   }
           						   
        						}else{
        							check_DiscPort_id="";
//        							flagPort="false";
        						}
        						
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DiscPort")){
        						String DiscPortString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DiscPort");
        						DiscPortString = SaveReportDataClass.urlValue(DiscPortString);
        						if(!GmailMethods.isNullString(DiscPortString)){
        							
        							if(DiscPortString.contains("_")){
        								DiscPortString=  DiscPortString.replace("_", " ");
        								out.println("returnDiscPortString: "+DiscPortString);
        						      }
        							
        						   String check_DiscPort_id1=CSC.check_Port(session, DiscPortString, out);
        						  
        						    boolean checkjsonString=SaveReportDataClass.isJSONValid(check_DiscPort_id1);
           						   if(checkjsonString==true){
           							   JSONObject data=new JSONObject(check_DiscPort_id1);
           							   if(data.has("port_id")){
           								check_DiscPort_id=data.getString("port_id");
           								out.println("check_DiscPort_id: "+check_DiscPort_id);
           							   }if(data.has("flag")){
          								 flagPort=data.getString("flag");
          							   }
           							   
           						   }
           						   
        						}else{
        							check_DiscPort_id="";
//        							flagPort="false";
        						}
        					}
        					out.println("after disck port: ");
        					String check_RateType="";
        					if(allReportJsonArrayInsideJSonobject.has("RateType")){
        						String RateTypeString=allReportJsonArrayInsideJSonobject.getString("RateType");
        						RateTypeString = SaveReportDataClass.urlValue(RateTypeString);
        						check_RateType=CSC.check_RateType(session, RateTypeString, out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType")){
        						String RateTypeString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType");
        						RateTypeString = SaveReportDataClass.urlValue(RateTypeString);
        						check_RateType=CSC.check_RateType(session, RateTypeString, out);
        					}else if(allReportJsonArrayInsideJSonobject.has("Rate Type")){
        						String RateTypeString=allReportJsonArrayInsideJSonobject.getString("Rate Type");
        						RateTypeString = SaveReportDataClass.urlValue(RateTypeString);
        						check_RateType=CSC.check_RateType(session, RateTypeString, out);
           					}
        					out.println("check_RateType: "+check_RateType);
        					String RateString="";
        					if(allReportJsonArrayInsideJSonobject.has("Rate")){
        						 RateString=allReportJsonArrayInsideJSonobject.getString("Rate");
        						 RateString = SaveReportDataClass.urlValue(RateString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#SpotRate")){
        						 RateString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#SpotRate");
        						 RateString = SaveReportDataClass.urlValue(RateString);
        					}
        					String ReportDateString="";
        					if(allReportJsonArrayInsideJSonobject.has("ReportDate")){
        						 ReportDateString=allReportJsonArrayInsideJSonobject.getString("ReportDate");
        						 ReportDateString = SaveReportDataClass.urlValue(ReportDateString);
        					}else if(allReportJsonArrayInsideJSonobject.has("Report Date")){
        						ReportDateString=allReportJsonArrayInsideJSonobject.getString("Report Date");
        						 ReportDateString = SaveReportDataClass.urlValue(ReportDateString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportDate")){
        						ReportDateString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportDate");
       						    ReportDateString = SaveReportDataClass.urlValue(ReportDateString);
        					}
        					
        					else{
        						ReportDateString=timestampDateAndTime;
        					}
        					String check_Spot_TCS_id="";
        					if(allReportJsonArrayInsideJSonobject.has("Spot_TC")){
        						String Spot_TCString=allReportJsonArrayInsideJSonobject.getString("Spot_TC");
        						Spot_TCString = SaveReportDataClass.urlValue(Spot_TCString);
        						check_Spot_TCS_id=CSC.check_ReportType(session, Spot_TCString, out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Spot_TC")){
        						String Spot_TCString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Spot_TC");
        						Spot_TCString = SaveReportDataClass.urlValue(Spot_TCString);
        						check_Spot_TCS_id=CSC.check_ReportType(session, Spot_TCString, out);
        					}
        					String DateString="";
        					if(allReportJsonArrayInsideJSonobject.has("Date")){
        						 DateString=allReportJsonArrayInsideJSonobject.getString("Date");
        						 DateString = SaveReportDataClass.urlValue(DateString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#date")){
        						DateString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#date");
       						    DateString = SaveReportDataClass.urlValue(DateString);
        					}
        					String check_CPP_DPP_id="";
        					if(allReportJsonArrayInsideJSonobject.has("CargoType")){
        						String CPP_DPPString=allReportJsonArrayInsideJSonobject.getString("CargoType");
        						CPP_DPPString = SaveReportDataClass.urlValue(CPP_DPPString);
        						check_CPP_DPP_id=CSC.check_CargoType(session, CPP_DPPString, out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type")){
        						String CPP_DPPString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type");
        						CPP_DPPString = SaveReportDataClass.urlValue(CPP_DPPString);
        						check_CPP_DPP_id=CSC.check_CargoType(session, CPP_DPPString, out);
        					}
        					String DeliveryString="";
        					if(allReportJsonArrayInsideJSonobject.has("Delivery")){
        						 DeliveryString=allReportJsonArrayInsideJSonobject.getString("Delivery");
        						 DeliveryString = SaveReportDataClass.urlValue(DeliveryString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TcDelivery")){
        						 DeliveryString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TcDelivery");
        						 DeliveryString = SaveReportDataClass.urlValue(DeliveryString);
        					}
        					String check_DeliveryPlace="";
        					if(allReportJsonArrayInsideJSonobject.has("DeliveryPlace")){
        						String DeliveryPlaceString=allReportJsonArrayInsideJSonobject.getString("DeliveryPlace");
        						DeliveryPlaceString = SaveReportDataClass.urlValue(DeliveryPlaceString);
        						check_DeliveryPlace=CSC.check_DeliveryPlace(session, DeliveryPlaceString, out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DeliveryPlace")){
        						String DeliveryPlaceString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DeliveryPlace");
        						DeliveryPlaceString = SaveReportDataClass.urlValue(DeliveryPlaceString);
        						check_DeliveryPlace=CSC.check_DeliveryPlace(session, DeliveryPlaceString, out);
        					}
        					String PeriodString="";
        					if(allReportJsonArrayInsideJSonobject.has("Period")){
        						 PeriodString=allReportJsonArrayInsideJSonobject.getString("Period");
        						 PeriodString = SaveReportDataClass.urlValue(PeriodString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TcPeriod")){
        						 PeriodString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TcPeriod");
        						 PeriodString = SaveReportDataClass.urlValue(PeriodString);
        					}
        					String check_Periodunit="";
        					if(allReportJsonArrayInsideJSonobject.has("Periodunit")){
        						String PeriodunitString=allReportJsonArrayInsideJSonobject.getString("Periodunit");
        						PeriodunitString = SaveReportDataClass.urlValue(PeriodunitString);
        						check_Periodunit=CSC.check_Periodunit(session, PeriodunitString, out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Periodunit")){
        						String PeriodunitString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Periodunit");
        						PeriodunitString = SaveReportDataClass.urlValue(PeriodunitString);
        						check_Periodunit=CSC.check_Periodunit(session, PeriodunitString, out);
        					}
        					String RedeliveryString="";
        					if(allReportJsonArrayInsideJSonobject.has("Redelivery")){
        						 RedeliveryString=allReportJsonArrayInsideJSonobject.getString("Redelivery");
        						 RedeliveryString = SaveReportDataClass.urlValue(RedeliveryString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TcRedelivery")){
        						 RedeliveryString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TcRedelivery");
        						 RedeliveryString = SaveReportDataClass.urlValue(RedeliveryString);
        					}
        					String TCRateString="";
        					if(allReportJsonArrayInsideJSonobject.has("TCRate")){
        						 TCRateString=allReportJsonArrayInsideJSonobject.getString("TCRate");
        						 TCRateString = SaveReportDataClass.urlValue(TCRateString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#SpotRate")){
        						TCRateString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#SpotRate");
       						    TCRateString = SaveReportDataClass.urlValue(TCRateString);
        					}else if(allReportJsonArrayInsideJSonobject.has("Rate")){
        						TCRateString=allReportJsonArrayInsideJSonobject.getString("Rate");
       						    TCRateString = SaveReportDataClass.urlValue(TCRateString);
        					}
        					String check_currencyUnit="";
        					if(allReportJsonArrayInsideJSonobject.has("CurrencyUnit")){
        						String CurrencyUnitString=allReportJsonArrayInsideJSonobject.getString("CurrencyUnit");
        						CurrencyUnitString = SaveReportDataClass.urlValue(CurrencyUnitString);
        						check_currencyUnit=CSC.check_currencyUnit(session, CurrencyUnitString, out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#CurrencyUnit")){
        						String CurrencyUnitString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#CurrencyUnit");
        						CurrencyUnitString = SaveReportDataClass.urlValue(CurrencyUnitString);
        						check_currencyUnit=CSC.check_currencyUnit(session, CurrencyUnitString, out);
        					}
        					String Month_YearString="";
        					if(allReportJsonArrayInsideJSonobject.has("Month_Year")){
        						 Month_YearString=allReportJsonArrayInsideJSonobject.getString("Month_Year");
        						 Month_YearString = SaveReportDataClass.urlValue(Month_YearString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Month_Year")){
        						Month_YearString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Month_Year");
       						    Month_YearString = SaveReportDataClass.urlValue(Month_YearString);
        					}
        					String first_yrString="";
        					if(allReportJsonArrayInsideJSonobject.has("1yr")){
        						 first_yrString=allReportJsonArrayInsideJSonobject.getString("1yr");
        						 first_yrString = SaveReportDataClass.urlValue(first_yrString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#1yr")){
        						first_yrString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#1yr");
       						    first_yrString = SaveReportDataClass.urlValue(first_yrString);
        					}
        					String second_yrString="";
        					if(allReportJsonArrayInsideJSonobject.has("2yr")){
        						 second_yrString=allReportJsonArrayInsideJSonobject.getString("2yr");
        						 second_yrString = SaveReportDataClass.urlValue(second_yrString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#2yr")){
        						second_yrString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#2yr");
       						    second_yrString = SaveReportDataClass.urlValue(second_yrString);
        					}
        					String third_yrString="";
        					if(allReportJsonArrayInsideJSonobject.has("3yr")){
        						 third_yrString=allReportJsonArrayInsideJSonobject.getString("3yr");
        						 third_yrString = SaveReportDataClass.urlValue(third_yrString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#3yr")){
        						third_yrString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#3yr");
       						    third_yrString = SaveReportDataClass.urlValue(third_yrString);
        					}
        					String fifth_yrString="";
        					if(allReportJsonArrayInsideJSonobject.has("5yr")){
        						 fifth_yrString=allReportJsonArrayInsideJSonobject.getString("5yr");
        						 fifth_yrString = SaveReportDataClass.urlValue(fifth_yrString);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#5yr")){
        						fifth_yrString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#5yr");
       						    fifth_yrString = SaveReportDataClass.urlValue(fifth_yrString);
        					}
        					
        					String Flag="";
        					if(flag!=""){
        						Flag=flag;
        					}else {
        						Flag="False";
        					}
        					
        				   String flagPortOpen1="";
        					if(flagPortOpen!=""){
        						flagPortOpen1=flagPortOpen;
        						
        					}else{
        						flagPortOpen1="false";
        					}
        					
        					String flagPort1="";
        					if(flagPort!=""){
        						flagPort1=flagPort;
        					}else{
        						flagPort1="false";
        					}
        					
        					String dataAll=SaveReportDataClass.headersDataPdf(out, DynamicJson, session);
        					out.println("dataAll:: "+dataAll);
//        					String RG="";
        					String CT="";
        					String VT="";
        					String EB="";
        					if(!GmailMethods.isNullString(dataAll)){
        					
        					boolean checkjsonStringDataAll=SaveReportDataClass.isJSONValid(dataAll);
        					   if(checkjsonStringDataAll==true){
        					      JSONObject dataall=new JSONObject(dataAll);
        					      if(dataall.has("RG")){
        					    	  check_Port_repositionRegion_id= dataall.getString("RG");
        					    	  out.println("RG:: "+check_Port_repositionRegion_id);
        					      }if(dataall.has("CT")){
        					    	  check_CargoType_cargotype_id= dataall.getString("CT");
        					    	  out.println("CT_check_CargoType_cargotype_id:: "+check_CargoType_cargotype_id);
        					      }if(dataall.has("VT")){
        					    	  vesselType_id= dataall.getString("VT");
        					    	  out.println("VT_vesselType_id:: "+vesselType_id);
        					      }if(dataall.has("EB")){
        					    	  ETABasisString= dataall.getString("EB");
        					    	  out.println("EB_ETABasisString:: "+ETABasisString);
        					      }
        					      
        					      
        					   }
        					}
        					
        					
        					   SaveReportDataClass fd=new SaveReportDataClass();
								fd.callReportwiseReortsaveInSling(out, session, ReportTypeString, check_Port_repositionRegion_id, check_CargoType_cargotype_id, vessel_Id, vesselType_id, built, dwt, cubics, loa, ice, sternline, owners_id, check_Operators_id, check_EmployementStatus, check_OpenPort_id, OpenDateString, ETABasisString, CommentString, check_Source, ReportTimestampString, check_Information, check_FixturetType_id, check_Charterer, check_Status, CargoQtyString, check_CargoGrade, LCStartString, LCEndString, check_LoadPort_id, check_DiscPort_id, check_RateType, RateString, ReportDateString, check_Spot_TCS_id, DateString, check_CPP_DPP_id, DeliveryString, check_DeliveryPlace, PeriodString, check_Periodunit, RedeliveryString, TCRateString, check_currencyUnit, CommentString,Month_YearString,first_yrString,second_yrString,third_yrString,fifth_yrString, emailUrl, subjectNodePath,textSentMailTime,BrokerName,timestampDate,timestampDateAndTime, Flag, flagPort1, flagPortOpen1, reportcomesFrom, DynamicJson, allReportJsonArrayInsideJSonobject.toString());
							    session.save();
        					
        					
        					/*if( !GmailMethods.isNullString(ReportTypeString) && !GmailMethods.isNullString(BrokerName) ){
        						
          						Node ReportData=null;
          						Node Broker=null;
          						
          						if(scorpioDataBase.hasNode("ReportData")){
          							ReportData=scorpioDataBase.getNode("ReportData");
          						}
          						if(ReportData.hasNode("Broker")){
          							Broker=ReportData.getNode("Broker");
          						}
          						
          						if(Broker.hasNodes()){
          							NodeIterator itr=Broker.getNodes();
          							while(itr.hasNext()){
          								Node nextNode=itr.nextNode();
          								
          								if(BrokerName.equalsIgnoreCase("stepchart") && ReportTypeString.equalsIgnoreCase("Tonnage")){
          									
          									out.println("inside stepchartering");
          									if(nextNode.hasNode("Tonnage")){
          										Node reportName=nextNode.getNode("Tonnage");
          										
          										String unknown_0="";
          										if(reportName.hasProperty("unknown_0")){
          											 unknown_0=reportName.getProperty("unknown_0").getString();
          										}
          										String unknown_1="";
          										if(reportName.hasProperty("unknown_1")){
          											unknown_1=reportName.getProperty("unknown_1").getString();
          										}
          										String unknown_2="";
          										if(reportName.hasProperty("unknown_2")){
          											unknown_2=reportName.getProperty("unknown_2").getString();
          										}
          										String unknown_3="";
          										if(reportName.hasProperty("unknown_3")){
          											unknown_3=reportName.getProperty("unknown_3").getString();
          										}
          										String unknown_4="";
          										if(reportName.hasProperty("unknown_4")){
          											unknown_4=reportName.getProperty("unknown_4").getString();
          										}
          										String unknown_5="";
          										if(reportName.hasProperty("unknown_5")){
          											unknown_5=reportName.getProperty("unknown_5").getString();
          										}
          										String unknown_6="";
          										if(reportName.hasProperty("unknown_6")){
          											unknown_6=reportName.getProperty("unknown_6").getString();
          										}
          										String unknown_7="";
          										if(reportName.hasProperty("unknown_7")){
          											unknown_7=reportName.getProperty("unknown_7").getString();
          										}
          										String unknown_8="";
          										if(reportName.hasProperty("unknown_8")){
          											unknown_8=reportName.getProperty("unknown_8").getString();
          										}
          										String unknown_9="";
          										if(reportName.hasProperty("unknown_9")){
          											unknown_9=reportName.getProperty("unknown_9").getString();
          										}
          										String unknown_10="";
          										if(reportName.hasProperty("unknown_10")){
          											unknown_10=reportName.getProperty("unknown_10").getString();
          										}
          										String unknown_11="";
          										if(reportName.hasProperty("unknown_11")){
          											unknown_11=reportName.getProperty("unknown_11").getString();
          										}
          										String unknown_12="";
          										if(reportName.hasProperty("unknown_12")){
          											unknown_12=reportName.getProperty("unknown_12").getString();
          										}
          										
          										String unknown_0_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_0")){
          										unknown_0_Value= allReportJsonArrayInsideJSonobject.getString("unknown_0");
          		        						unknown_0=unknown_0_Value;
          		        						
          									}	
          									String unknown_1_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_1")){
          										unknown_1_Value= allReportJsonArrayInsideJSonobject.getString("unknown_1");
//          		        						unknown_1=unknown_1_Value;
          		        						
          		          						check_EmployementStatus=CSC.check_EmployementStatus(session, unknown_1_Value,out);
//          		        						check_EmployementStatus=unknown_1_Value;
          		        						
          									}
          									String unknown_2_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_2")){
          										unknown_2_Value= allReportJsonArrayInsideJSonobject.getString("unknown_2");
//          		        						unknown_2=unknown_2_Value;
          		        						
          		        						check_vesselNameProperties=CSC.check_vesselName(session, unknown_2_Value, out);
          		        						if(!GmailMethods.isNullString(check_vesselNameProperties)){
          		        							boolean checkjsonString=SaveReportDataClass.isJSONValid(check_vesselNameProperties);
          		          						JSONObject check_vesselNamePropertiesJsonobject=null;
          		          						if(checkjsonString==true){
          		          							check_vesselNamePropertiesJsonobject=new JSONObject(check_vesselNameProperties);
          		          						
          		          							if(check_vesselNamePropertiesJsonobject.has("Flag")){
          		          								flag=check_vesselNamePropertiesJsonobject.getString("Flag");
          		          							}
//          		          						if(check_vesselNamePropertiesJsonobject.length()>0){
//          		          						out.println("check_vesselNameProperties_test1: "+check_vesselNamePropertiesJsonobject);
          		          						if(check_vesselNamePropertiesJsonobject.has("vessel_Id")){
          		          							vessel_Id=check_vesselNamePropertiesJsonobject.getString("vessel_Id");
//          		          							out.println("check_vesselNameProperties_test2: "+vessel_Id);
          		          						}
          		        						}
          									}
          		        						}
          		        						
          									String unknown_3_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_3")){
          										unknown_3_Value= allReportJsonArrayInsideJSonobject.getString("unknown_3");
//          		        						unknown_3=unknown_3_Value;
          		        						owners_id=ChangedStructureCurrent_Methods.check_Owners(session, unknown_3_Value,out);
          									}
          									String unknown_4_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_4")){
          										unknown_4_Value= allReportJsonArrayInsideJSonobject.getString("unknown_4");
//          		        						unknown_4=unknown_4_Value;
          		        						dwt=unknown_4_Value;
          									}
          									String unknown_5_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_5")){
          										unknown_5_Value= allReportJsonArrayInsideJSonobject.getString("unknown_5");
//          		        						unknown_5=unknown_5_Value;
          		        						cubics=unknown_5_Value;
          									}
          									String unknown_6_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_6")){
          										unknown_6_Value= allReportJsonArrayInsideJSonobject.getString("unknown_6");
          		        						//unknown_6=unknown_6_Value;
          		        						built=unknown_6_Value;
          									}
          									String unknown_7_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_7")){
          										unknown_7_Value= allReportJsonArrayInsideJSonobject.getString("unknown_7");
          		        						//unknown_7=unknown_7_Value;
          		        						ice=unknown_7_Value;
          									}
          									String unknown_8_Value="";
          									String imo="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_10")){
          										unknown_8_Value= allReportJsonArrayInsideJSonobject.getString("unknown_10");
          		        						//unknown_8=unknown_8_Value;
          		        						imo=unknown_8_Value;
          									}
          									String unknown_9_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_12")){
          										unknown_9_Value= allReportJsonArrayInsideJSonobject.getString("unknown_12");
          		        						//unknown_9=unknown_9_Value;
          		        						ETABasisString=unknown_9_Value;
          									}
          									String unknown_10_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_13")){
          										unknown_10_Value= allReportJsonArrayInsideJSonobject.getString("unknown_13");
//          		        						unknown_10=unknown_10_Value;
          		        						
          		        						check_OpenPort_id=CSC.check_Port(session, unknown_10_Value, out);
          		        						 boolean checkjsonString=SaveReportDataClass.isJSONValid(check_OpenPort_id);
          		      						   if(checkjsonString==true){
          		      							   JSONObject data=new JSONObject(check_OpenPort_id);
          		      							   if(data.has("port_id")){
          		      								 check_OpenPort_id=data.getString("port_id");
          		      							   }
          		      							   if(data.has("Area")){
//          		      								 check_Port_repositionRegion_id=data.getString("Area");
          		      								    check_Port_repositionRegion_id=check_OpenPort_id;
          		      							   }
          		      							   
          		      							   if(data.has("flag")){
          		      								 flagPortOpen=data.getString("flag");
          		      							   }
          		      							   
          		      							   
          		      						   }
          									}
          									
          									String unknown_11_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_15")){
          										unknown_11_Value= allReportJsonArrayInsideJSonobject.getString("unknown_15");
//          		        						unknown_11=unknown_11_Value;
          		        						OpenDateString=unknown_11_Value;
          									}
          									String unknown_12_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_16")){
          										unknown_12_Value= allReportJsonArrayInsideJSonobject.getString("unknown_16");
//          		        						unknown_12=unknown_12_Value;
          		        						CommentString=unknown_12_Value;
          									}
          									SaveReportDataClass fd=new SaveReportDataClass();
          									fd.callReportwiseReortsaveInSling(out, session, ReportTypeString, check_Port_repositionRegion_id, check_CargoType_cargotype_id, vessel_Id, vesselType_id, built, dwt, cubics, loa, ice, sternline, owners_id, check_Operators_id, check_EmployementStatus, check_OpenPort_id, OpenDateString, ETABasisString, CommentString, check_Source, ReportTimestampString, check_Information, check_FixturetType_id, check_Charterer, check_Status, CargoQtyString, check_CargoGrade, LCStartString, LCEndString, check_LoadPort_id, check_DiscPort_id, check_RateType, RateString, ReportDateString, check_Spot_TCS_id, DateString, check_CPP_DPP_id, DeliveryString, check_DeliveryPlace, PeriodString, check_Periodunit, RedeliveryString, TCRateString, check_currencyUnit, CommentString,Month_YearString,first_yrString,second_yrString,third_yrString,fifth_yrString, emailUrl, subjectNodePath,textSentMailTime,BrokerName,timestampDate,timestampDateAndTime, Flag, flagPort1, flagPortOpen1, reportcomesFrom, DynamicJson);
          								session.save();
          								}
          								
          								
          							} 
          								else if("brsbrokers".equalsIgnoreCase(BrokerName) && ReportTypeString.equalsIgnoreCase("Spot")){
          									out.println("inside brsbroker");
          									if(nextNode.hasNode(ReportTypeString)){
          										Node reportName=nextNode.getNode(ReportTypeString);
          										
          										String unknown_0="";
          										if(reportName.hasProperty("unknown_0")){
          											 unknown_0=reportName.getProperty("unknown_0").getString();
          										}
          										String unknown_1="";
          										if(reportName.hasProperty("unknown_1")){
          											unknown_1=reportName.getProperty("unknown_1").getString();
          										}
          										String unknown_2="";
          										if(reportName.hasProperty("unknown_2")){
          											unknown_2=reportName.getProperty("unknown_2").getString();
          											
          										}
          										String unknown_3="";
          										if(reportName.hasProperty("unknown_3")){
          											unknown_3=reportName.getProperty("unknown_3").getString();
          										}
          										String unknown_4="";
          										if(reportName.hasProperty("unknown_4")){
          											unknown_4=reportName.getProperty("unknown_4").getString();
          										}
          										String unknown_5="";
          										if(reportName.hasProperty("unknown_5")){
          											unknown_5=reportName.getProperty("unknown_5").getString();
          										}
          										String unknown_6="";
          										if(reportName.hasProperty("unknown_6")){
          											unknown_6=reportName.getProperty("unknown_6").getString();
          										}
          										String unknown_7="";
          										if(reportName.hasProperty("unknown_7")){
          											unknown_7=reportName.getProperty("unknown_7").getString();
          										}
          										String unknown_8="";
          										if(reportName.hasProperty("unknown_8")){
          											unknown_8=reportName.getProperty("unknown_8").getString();
          										}
          										String unknown_9="";
          										if(reportName.hasProperty("unknown_9")){
          											unknown_9=reportName.getProperty("unknown_9").getString();
          										}
          										String unknown_10="";
          										if(reportName.hasProperty("unknown_10")){
          											unknown_10=reportName.getProperty("unknown_10").getString();
          										}
          										
          										
          										String unknown_0_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_0")){
          										unknown_0_Value= allReportJsonArrayInsideJSonobject.getString("unknown_0");
          		        						//unknown_0=unknown_0_Value;
          		        						check_Charterer=unknown_0_Value;
          									}	
          									String unknown_1_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_1")){
          										unknown_1_Value= allReportJsonArrayInsideJSonobject.getString("unknown_1");
          		        						//unknown_1=unknown_1_Value;
          		        						check_EmployementStatus=CSC.check_EmployementStatus(session, unknown_1_Value,out);
          		        						check_EmployementStatus=unknown_1_Value;
          		        						
          									}
          									String unknown_2_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_2")){
          										unknown_2_Value= allReportJsonArrayInsideJSonobject.getString("unknown_2");
          		        						//unknown_2=unknown_2_Value;
          		        						check_vesselNameProperties=CSC.check_vesselName(session, unknown_2_Value, out);
          		        						if(!GmailMethods.isNullString(check_vesselNameProperties)){
          		        							boolean checkjsonString=SaveReportDataClass.isJSONValid(check_vesselNameProperties);
          		          						JSONObject check_vesselNamePropertiesJsonobject=null;
          		          						if(checkjsonString==true){
          		          							check_vesselNamePropertiesJsonobject=new JSONObject(check_vesselNameProperties);
          		          						
          		          							if(check_vesselNamePropertiesJsonobject.has("Flag")){
          		          								flag=check_vesselNamePropertiesJsonobject.getString("Flag");
          		          							}
//          		          						if(check_vesselNamePropertiesJsonobject.length()>0){
//          		          						out.println("check_vesselNameProperties_test1: "+check_vesselNamePropertiesJsonobject);
          		          						if(check_vesselNamePropertiesJsonobject.has("vessel_Id")){
          		          							vessel_Id=check_vesselNamePropertiesJsonobject.getString("vessel_Id");
//          		          							out.println("check_vesselNameProperties_test2: "+vessel_Id);
          		          						}
          		        						}
          									}
          									}
          									String unknown_3_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_3")){
          										unknown_3_Value= allReportJsonArrayInsideJSonobject.getString("unknown_3");
          		        						//unknown_3=unknown_3_Value;
          		        						CargoQtyString=unknown_3_Value;
          									}
          									String unknown_4_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_4")){
          										unknown_4_Value= allReportJsonArrayInsideJSonobject.getString("unknown_4");
          		        						//unknown_4=unknown_4_Value;
          		        						check_CargoGrade=CSC.check_CargoGrade(session, unknown_4_Value, out);
          									}
          									String unknown_5_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_5")){
          										unknown_5_Value= allReportJsonArrayInsideJSonobject.getString("unknown_5");
          		        						//unknown_5=unknown_5_Value;
          		        						String  check_LoadPort_id1=CSC.check_Port(session, unknown_5_Value, out);
          	        						   
          	        						   boolean checkjsonString=SaveReportDataClass.isJSONValid(check_LoadPort_id1);
          	          						   if(checkjsonString==true){
          	          							   JSONObject data=new JSONObject(check_LoadPort_id1);
          	          							   if(data.has("port_id")){
          	          								 check_LoadPort_id=data.getString("port_id");
          	          								out.println("check_LoadPort_id: "+check_LoadPort_id);
          	          							   }if(data.has("flag")){
          	          								 flagPort=data.getString("flag");
          	          							   }
          	          							   
          	          						   }
          									}
          									String unknown_6_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_6")){
          										unknown_6_Value= allReportJsonArrayInsideJSonobject.getString("unknown_6");
          		        						//unknown_6=unknown_6_Value;
          		        						String check_DiscPort_id1=CSC.check_Port(session, unknown_6_Value, out);
          	        						  
          	        						    boolean checkjsonString=SaveReportDataClass.isJSONValid(check_DiscPort_id1);
          	           						   if(checkjsonString==true){
          	           							   JSONObject data=new JSONObject(check_DiscPort_id1);
          	           							   if(data.has("port_id")){
          	           								check_DiscPort_id=data.getString("port_id");
          	           							   }if(data.has("flag")){
          	          								 flagPort=data.getString("flag");
          	          							   }
          	           							   
          	           						   }
          									}
          									String unknown_7_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_7")){
          										unknown_7_Value= allReportJsonArrayInsideJSonobject.getString("unknown_7");
          		        						//unknown_7=unknown_7_Value;
          		        						LCStartString=unknown_7_Value;
          		        						LCEndString=unknown_7_Value;
          									}
          									String unknown_8_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_8")){
          										unknown_8_Value= allReportJsonArrayInsideJSonobject.getString("unknown_8");
          		        						//unknown_8=unknown_8_Value;
          		        						check_RateType=CSC.check_RateType(session, unknown_8_Value, out);
          									}
          									String unknown_9_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_9")){
          										unknown_9_Value= allReportJsonArrayInsideJSonobject.getString("unknown_9");
          		        						//unknown_9=unknown_9_Value;
          		        						RateString=unknown_9_Value;
          									}
          									String unknown_10_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_10")){
          										unknown_10_Value= allReportJsonArrayInsideJSonobject.getString("unknown_10");
          		        						//unknown_10=unknown_10_Value;
          		        						CommentString=unknown_10_Value;
          									}
          									
          									
          									SaveReportDataClass fd=new SaveReportDataClass();
          									fd.callReportwiseReortsaveInSling(out, session, ReportTypeString, check_Port_repositionRegion_id, check_CargoType_cargotype_id, vessel_Id, vesselType_id, built, dwt, cubics, loa, ice, sternline, owners_id, check_Operators_id, check_EmployementStatus, check_OpenPort_id, OpenDateString, ETABasisString, CommentString, check_Source, ReportTimestampString, check_Information, check_FixturetType_id, check_Charterer, check_Status, CargoQtyString, check_CargoGrade, LCStartString, LCEndString, check_LoadPort_id, check_DiscPort_id, check_RateType, RateString, ReportDateString, check_Spot_TCS_id, DateString, check_CPP_DPP_id, DeliveryString, check_DeliveryPlace, PeriodString, check_Periodunit, RedeliveryString, TCRateString, check_currencyUnit, CommentString,Month_YearString,first_yrString,second_yrString,third_yrString,fifth_yrString, emailUrl, subjectNodePath,textSentMailTime,BrokerName,timestampDate,timestampDateAndTime, Flag, flagPort1, flagPortOpen1, reportcomesFrom, DynamicJson);
          								session.save();
          								}
          									
          								}else if("howerob".equalsIgnoreCase(BrokerName) && ReportTypeString.equalsIgnoreCase("Spot")){
          									out.println("inside howerobinson");
          									if(nextNode.hasNode(ReportTypeString)){
          										Node reportName=nextNode.getNode(ReportTypeString);
          										
          										String unknown_0="";
          										if(reportName.hasProperty("unknown_0")){
          											 unknown_0=reportName.getProperty("unknown_0").getString();
          										}
          										String unknown_1="";
          										if(reportName.hasProperty("unknown_1")){
          											unknown_1=reportName.getProperty("unknown_1").getString();
          										}
          										String unknown_2="";
          										if(reportName.hasProperty("unknown_2")){
          											unknown_2=reportName.getProperty("unknown_2").getString();
          										}
          										String unknown_3="";
          										if(reportName.hasProperty("unknown_3")){
          											unknown_3=reportName.getProperty("unknown_3").getString();
          										}
          										String unknown_4="";
          										if(reportName.hasProperty("unknown_4")){
          											unknown_4=reportName.getProperty("unknown_4").getString();
          										}
          										String unknown_5="";
          										if(reportName.hasProperty("unknown_5")){
          											unknown_5=reportName.getProperty("unknown_5").getString();
          										}
          										String unknown_6="";
          										if(reportName.hasProperty("unknown_6")){
          											unknown_6=reportName.getProperty("unknown_6").getString();
          										}
          										String unknown_7="";
          										if(reportName.hasProperty("unknown_7")){
          											unknown_7=reportName.getProperty("unknown_7").getString();
          										}
          										String unknown_8="";
          										if(reportName.hasProperty("unknown_8")){
          											unknown_8=reportName.getProperty("unknown_8").getString();
          										}
          										String unknown_9="";
          										if(reportName.hasProperty("unknown_9")){
          											unknown_9=reportName.getProperty("unknown_9").getString();
          										}
          										String unknown_10="";
          										if(reportName.hasProperty("unknown_10")){
          											unknown_10=reportName.getProperty("unknown_10").getString();
          										}
          										
          										
          										String unknown_0_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_0")){
          										unknown_0_Value= allReportJsonArrayInsideJSonobject.getString("unknown_0");
          		        						//unknown_0=unknown_0_Value;
          		        						check_Charterer=unknown_0_Value;
          									}	
          									String unknown_1_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_1")){
          										unknown_1_Value= allReportJsonArrayInsideJSonobject.getString("unknown_1");
          		        						unknown_1=unknown_1_Value;// owner
          									}
          									String unknown_2_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_2")){
          										unknown_2_Value= allReportJsonArrayInsideJSonobject.getString("unknown_2");
          		        						//unknown_2=unknown_2_Value;
          		        						check_vesselNameProperties=CSC.check_vesselName(session, unknown_2_Value, out);
          		        						if(!GmailMethods.isNullString(check_vesselNameProperties)){
          		        							boolean checkjsonString=SaveReportDataClass.isJSONValid(check_vesselNameProperties);
          		          						JSONObject check_vesselNamePropertiesJsonobject=null;
          		          						if(checkjsonString==true){
          		          							check_vesselNamePropertiesJsonobject=new JSONObject(check_vesselNameProperties);
          		          						
          		          							if(check_vesselNamePropertiesJsonobject.has("Flag")){
          		          								flag=check_vesselNamePropertiesJsonobject.getString("Flag");
          		          							}
//          		          						if(check_vesselNamePropertiesJsonobject.length()>0){
//          		          						out.println("check_vesselNameProperties_test1: "+check_vesselNamePropertiesJsonobject);
          		          						if(check_vesselNamePropertiesJsonobject.has("vessel_Id")){
          		          							vessel_Id=check_vesselNamePropertiesJsonobject.getString("vessel_Id");
//          		          							out.println("check_vesselNameProperties_test2: "+vessel_Id);
          		          						}
          		        						}
          									}
          									}
          									String unknown_3_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_3")){
          										unknown_3_Value= allReportJsonArrayInsideJSonobject.getString("unknown_3");
          		        						//unknown_3=unknown_3_Value;
          		        						CargoQtyString=unknown_3_Value;
          									}
          									String unknown_4_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_4")){
          										unknown_4_Value= allReportJsonArrayInsideJSonobject.getString("unknown_4");
          		        						//unknown_4=unknown_4_Value;
          		        						check_CargoGrade=CSC.check_CargoGrade(session, unknown_4_Value, out);
          									}
          									String unknown_5_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_5")){
          										unknown_5_Value= allReportJsonArrayInsideJSonobject.getString("unknown_5");
          		        						//unknown_5=unknown_5_Value;
          		        						String  check_LoadPort_id1=CSC.check_Port(session, unknown_5_Value, out);
           	        						   
           	        						   boolean checkjsonString=SaveReportDataClass.isJSONValid(check_LoadPort_id1);
           	          						   if(checkjsonString==true){
           	          							   JSONObject data=new JSONObject(check_LoadPort_id1);
           	          							   if(data.has("port_id")){
           	          								 check_LoadPort_id=data.getString("port_id");
           	          								out.println("check_LoadPort_id: "+check_LoadPort_id);
           	          							   }if(data.has("flag")){
           	          								 flagPort=data.getString("flag");
           	          							   }
           	          							   
           	          						   }
          									}
          									String unknown_6_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_6")){
          										unknown_6_Value= allReportJsonArrayInsideJSonobject.getString("unknown_6");
          		        						//unknown_6=unknown_6_Value;
          		        						String check_DiscPort_id1=CSC.check_Port(session, unknown_6_Value, out);
            	        						  
          	        						    boolean checkjsonString=SaveReportDataClass.isJSONValid(check_DiscPort_id1);
          	           						   if(checkjsonString==true){
          	           							   JSONObject data=new JSONObject(check_DiscPort_id1);
          	           							   if(data.has("port_id")){
          	           								check_DiscPort_id=data.getString("port_id");
          	           							   }if(data.has("flag")){
          	          								 flagPort=data.getString("flag");
          	          							   }
          	           							   
          	           						   }
          									}
          									String unknown_7_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_7")){
          										unknown_7_Value= allReportJsonArrayInsideJSonobject.getString("unknown_7");
          		        						//unknown_7=unknown_7_Value;
          		        						LCStartString=unknown_7_Value;
          		        						LCEndString=unknown_7_Value;
          									}
          									String unknown_8_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_8")){
          										unknown_8_Value= allReportJsonArrayInsideJSonobject.getString("unknown_8");
          		        						//unknown_8=unknown_8_Value;
          		        						check_RateType=CSC.check_RateType(session, unknown_8_Value, out);
          									}
          									String unknown_9_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_9")){
          										unknown_9_Value= allReportJsonArrayInsideJSonobject.getString("unknown_9");
          		        						//unknown_9=unknown_9_Value;
          		        						RateString=unknown_9_Value;
          									}
          									String unknown_10_Value="";
          									if(allReportJsonArrayInsideJSonobject.has("unknown_10")){
          										unknown_10_Value= allReportJsonArrayInsideJSonobject.getString("unknown_10");
          		        						//unknown_10=unknown_10_Value;
          		        						CommentString=unknown_10_Value;
          									}
          									
          									
          									SaveReportDataClass fd=new SaveReportDataClass();
          									fd.callReportwiseReortsaveInSling(out, session, ReportTypeString, check_Port_repositionRegion_id, check_CargoType_cargotype_id, vessel_Id, vesselType_id, built, dwt, cubics, loa, ice, sternline, owners_id, check_Operators_id, check_EmployementStatus, check_OpenPort_id, OpenDateString, ETABasisString, CommentString, check_Source, ReportTimestampString, check_Information, check_FixturetType_id, check_Charterer, check_Status, CargoQtyString, check_CargoGrade, LCStartString, LCEndString, check_LoadPort_id, check_DiscPort_id, check_RateType, RateString, ReportDateString, check_Spot_TCS_id, DateString, check_CPP_DPP_id, DeliveryString, check_DeliveryPlace, PeriodString, check_Periodunit, RedeliveryString, TCRateString, check_currencyUnit, CommentString,Month_YearString,first_yrString,second_yrString,third_yrString,fifth_yrString, emailUrl, subjectNodePath,textSentMailTime,BrokerName,timestampDate,timestampDateAndTime, Flag, flagPort1, flagPortOpen1, reportcomesFrom, DynamicJson);
          								session.save();
          								}
          									
          								}
          								
          								
          						}// while close
          						
        					}
        					
        					
        					
        					//callReportwiseReortsaveInSling(out, session, ReportTypeString, check_Port_repositionRegion_id, check_CargoType_cargotype_id, vessel_Id, vesselType_id, built, dwt, cubics, loa, ice, sternline, owners_id, check_Operators_id, check_EmployementStatus, check_OpenPort_id, OpenDateString, ETABasisString, CommentString, check_Source, ReportTimestampString, check_Information, check_FixturetType_id, check_Charterer, check_Status, CargoQtyString, check_CargoGrade, LCStartString, LCEndString, check_LoadPort_id, check_DiscPort_id, check_RateType, RateString, ReportDateString, check_Spot_TCS_id, DateString, check_CPP_DPP_id, DeliveryString, check_DeliveryPlace, PeriodString, check_Periodunit, RedeliveryString, TCRateString, check_currencyUnit, CommentString,Month_YearString,first_yrString,second_yrString,third_yrString,fifth_yrString, emailUrl, subjectNodePath,textSentMailTime,from_Source,timestampDate,timestampDateAndTime, Flag, flagPort1, flagPortOpen1, reportcomesFrom, allReport);
        					
        					
        				}*/
        				
        			}
            	}
            //}
			
			
		//	JSONObject allReportJsonObject=new JSONObject(allReport);
		
			
			
            }
			
		} catch (Exception e) {
			e.printStackTrace(out);
//			out.println(e.getMessage());
		}
			
			
			
		
	}
	

	public static String fetchTonnageDataChanged(PrintWriter out, Session session) {
		String tonnageSolr="";
		try {
			FetchData fd = new FetchData();

			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpio.getNode("ReportData");
			Node Tonnage = ReportData.getNode("Tonnage");

			if (Tonnage.hasNodes()) {
				long size = Tonnage.getNodes().getSize();
				JSONObject keyNameObject = null;

				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = Tonnage.getNodes();
				int i = 0;
				while (itr.hasNext()) {

					Node nextnode = itr.nextNode();
					int parseId = 0;
					i++;
					String subNodeName = nextnode.getName();

					String EmploymentStatus = "";
					String Owners = "";
					String OpenPort = "";
					String Operators = "";
					String Id = "";
					String CargoType = "";
					String Comment = "";
					String ETABasis = "";
					String OpenDate = "";
					String ReportType = "";
					// Object ReportTimestamp = null;
					String ReportTimestamp = "";
					String RepositionRegion = "";
					String Source = "";
					String VesselName = "";
					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";
					String emailClientNodeId = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("EmploymentStatus")) {
						EmploymentStatus = nextnode.getProperty("EmploymentStatus").getString();
						EmploymentStatus = fd.fetch_EmployementStatus(session, EmploymentStatus);

					}
					if (nextnode.hasProperty("Owners")) {
						Owners = nextnode.getProperty("Owners").getString();
						Owners = FetchData.fetch_Owners(session, Owners);

					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();

					}
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}

					if (nextnode.hasProperty("OpenPort")) {
						OpenPort = nextnode.getProperty("OpenPort").getString();
						String OpenPort1 = fd.fetch_Port(session, OpenPort);

						boolean checkjsonString = SaveReportDataClass.isJSONValid(OpenPort1);
						if (checkjsonString == true) {
							JSONObject data = new JSONObject(OpenPort1);
							if (data.has("portName")) {
								OpenPort = data.getString("portName");
							}
							if (data.has("Area")) {
								RepositionRegion = data.getString("Area");
								// check_Port_repositionRegion_id=check_OpenPort_id;
							} else {
								RepositionRegion = "";
							}

						}

					}

					if (GmailMethods.isNullString(RepositionRegion)) {

						if (nextnode.hasProperty("RepositionRegion")) {
							RepositionRegion = nextnode.getProperty("RepositionRegion").getString();

							String RepositionRegion1 = fd.fetch_Port(session, RepositionRegion);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(RepositionRegion1);
							if (checkjsonString == true) {
								JSONObject data = new JSONObject(RepositionRegion1);

								if (data.has("Area")) {
									RepositionRegion = data.getString("Area");
									// check_Port_repositionRegion_id=check_OpenPort_id;
								}

							}

						} else {
							RepositionRegion = "";
						}

					} // blank

					if (nextnode.hasProperty("Operators")) {
						Operators = nextnode.getProperty("Operators").getString();
						Operators = fd.fetch_Operators(session, Operators);

					}
					if (nextnode.hasProperty("CargoType")) {
						CargoType = nextnode.getProperty("CargoType").getString();
						CargoType = fd.fetch_CargoType(session, CargoType);

					}
					if (nextnode.hasProperty("Comment")) {
						Comment = nextnode.getProperty("Comment").getString();

					}
					if (nextnode.hasProperty("ETABasis")) {
						ETABasis = nextnode.getProperty("ETABasis").getString();

					}

					String ReportTimestamp1 = "";
					if (nextnode.hasProperty("ReportTimestamp")) {
						ReportTimestamp1 = nextnode.getProperty("ReportTimestamp").getString();

						if (ReportTimestamp1.lastIndexOf(".") != -1) {
							ReportTimestamp1 = ReportTimestamp1.substring(0, ReportTimestamp1.lastIndexOf("."));
							// out.println(ReportTimestamp);

							LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							ReportTimestamp = localDateTime.format(formatter);

						} else {
							ReportTimestamp = ReportTimestamp1;
						}

					}

					if (nextnode.hasProperty("OpenDate")) {
						String OpenDate1 = nextnode.getProperty("OpenDate").getString();

						boolean booleanOpenDate = Pattern.matches("(\\d{2}\\-\\d{2}\\-\\d{4})", OpenDate1);

						if (booleanOpenDate == true) {
							OpenDate = FourReportsExcelClass.convertStringToDate(OpenDate1);

						} else {

							if (OpenDate1.equalsIgnoreCase("PPT") || OpenDate1.equalsIgnoreCase("PPT - exten")) {

								LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								OpenDate = localDateTime.format(formatter);

							} else {
								OpenDate = OpenDate1;
							}

						}

					}
					if (nextnode.hasProperty("ReportType")) {
						ReportType = nextnode.getProperty("ReportType").getString();
						ReportType = fd.fetch_ReportType(session, ReportType);

					}

					if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();

						boolean numeric = true;

						try {
							Integer num = Integer.parseInt(Source);

						} catch (NumberFormatException e) {
							numeric = false;
						}

						if (numeric) {
							Source = fd.fetch_Source(session, Source);
						}

						else {
							if (nextnode.hasProperty("from_Source")) {
								Source = nextnode.getProperty("from_Source").getString();
								Source = fd.fetch_Source(session, Source);
							}
						}

					}

					String Flag = "";
					if (nextnode.hasProperty("VesselName")) {
						VesselName = nextnode.getProperty("VesselName").getString();
						VesselName = fd.fetch_vesselName(session, VesselName);
						if (!GmailMethods.isNullString(VesselName)) {
							boolean checkjsonString = SaveReportDataClass.isJSONValid(VesselName);
							if (checkjsonString == true) {
								JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
								if (fetch_vesselNamejsonobject.has("vessel_Id")) {
									VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");

								}
							}

						} else {
							VesselName = "Route Rate";
						}

					}
					String Built = "";
					if (nextnode.hasProperty("Built")) {

						String Built1 = nextnode.getProperty("Built").getString();

						boolean booleanOpenDate = Pattern.matches("(\\d{2}\\-\\d{2}\\-\\d{4})", Built1);

						if (booleanOpenDate == true) {
							if (Built1.lastIndexOf("-") != -1) {
								int d = Built1.lastIndexOf("-");
								Built = Built1.substring(d + 1);
							} else {
								Built = Built1;
							}
						} else {
							Built = Built1;
						}

					}
					String DWT = "";
					if (nextnode.hasProperty("DWT")) {

						String DWT1 = nextnode.getProperty("DWT").getString();

						boolean booleandwt = Pattern.matches("(\\d{1,3})$", DWT1);

						if (booleandwt == true) {
							int intDWT1 = Integer.parseInt(DWT1);
							int dwtInt = intDWT1 * 1000;
							DWT = String.valueOf(dwtInt);
						} else {
							booleandwt = Pattern.matches("(\\d{1,3}\\.\\d{2})", DWT1);
							if (booleandwt == true) {
								int intDWT1 = Integer.parseInt(DWT1);
								int dwtInt = intDWT1 * 1000;
								DWT = String.valueOf(dwtInt);

							} else {
								DWT = DWT1;
							}
						}

					}
					String Cubics = "";
					if (nextnode.hasProperty("Cubics")) {

						String Cubics1 = nextnode.getProperty("Cubics").getString();

						boolean booleandwt = Pattern.matches("(\\d{1,3})$", Cubics1);

						if (booleandwt == true) {
							int intDWT1 = Integer.parseInt(Cubics1);
							int dwtInt = intDWT1 * 1000;
							Cubics = String.valueOf(dwtInt);
						} else {
							booleandwt = Pattern.matches("(\\d{1,3}\\.\\d{2})", Cubics1);
							if (booleandwt == true) {
								int intDWT1 = Integer.parseInt(Cubics1);
								int dwtInt = intDWT1 * 1000;
								Cubics = String.valueOf(dwtInt);

							} else {
								Cubics = Cubics1;
							}
						}

					}
					String LOA = "";
					if (nextnode.hasProperty("LOA")) {

						LOA = nextnode.getProperty("LOA").getString();

					}

					String ICE = "";
					if (nextnode.hasProperty("ICE")) {

						ICE = nextnode.getProperty("ICE").getString();

					}

					String SternLine = "";
					if (nextnode.hasProperty("SternLine")) {

						SternLine = nextnode.getProperty("SternLine").getString();

					}
					String VesselType = "";
					if (nextnode.hasProperty("VesselType")) {

						VesselType = nextnode.getProperty("VesselType").getString();
						VesselType = FetchData.fetch_vesselType(session, VesselType);

					}
					String vesselFlag = "";
					if (nextnode.hasProperty("vesselFlag")) {

						vesselFlag = nextnode.getProperty("vesselFlag").getString();

					}
					String flagPort = "";
					if (nextnode.hasProperty("flagOpenPort")) {

						flagPort = nextnode.getProperty("flagOpenPort").getString();

					}

					 tonnageSolr=postSolr(out, ReportType.toUpperCase(), RepositionRegion.toUpperCase(), CargoType.toUpperCase(),
							VesselName.toUpperCase(), VesselType.toUpperCase(), Built, DWT, Cubics, LOA,
							ICE.toUpperCase(), SternLine, Owners.toUpperCase(), Operators.toUpperCase(),
							EmploymentStatus.toUpperCase(), OpenPort.toUpperCase(), OpenDate, ETABasis.toUpperCase(),
							Comment.toUpperCase(), Source.toUpperCase(), ReportTimestamp.toUpperCase(), ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"",
							""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"");

				} // while close

			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);

		}
		return tonnageSolr;

	}

	public static String fetchSpotDataChanged(PrintWriter out, Session session) {

		String spotSolr="";
		try {

			FetchData fd = new FetchData();
			Node scorpio = null;
			Node ReportData = null;
			Node Spot = null;

			if (session.getRootNode().hasNode("scorpioDataBase")) {
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}
			if (scorpio.hasNode("ReportData")) {
				ReportData = scorpio.getNode("ReportData");
			}
			if (ReportData.hasNode("Spot")) {
				Spot = ReportData.getNode("Spot");
			}

			if (Spot.hasNodes()) {
				long size = Spot.getNodes().getSize();

				String Id = "";

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = Spot.getNodes();
				int i = 0;

				while (itr.hasNext()) {
					Node nextnode = itr.nextNode();

					int parseId = 0;
					i++;
					String subNodeName = nextnode.getName();

					String Information = "";
					String FixtureType = "";
					String Charterer = "";
					String Status = "";
					String CargoType = "";
					String CargoGrade = "";
					String CargoQty = "";
					String LCStart = "";
					String Source = "";
					String VesselName = "";
					String LCEnd = "";
					String LoadPort = "";
					String DiscPort = "";
					String RateType = "";
					String Rate = "";
					String Comments = "";
					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";
					String emailClientNodeId = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("Information")) {
						Information = nextnode.getProperty("Information").getString();
						Information = fd.fetch_Information(session, Information);

					}
					if (nextnode.hasProperty("FixtureType")) {
						FixtureType = nextnode.getProperty("FixtureType").getString();
						FixtureType = fd.fetch_ReportType(session, FixtureType);

					}
					if (nextnode.hasProperty("Charterer")) {
						Charterer = nextnode.getProperty("Charterer").getString();
						Charterer = fd.fetch_Charterer(session, Charterer);

					}
					if (nextnode.hasProperty("Status")) {
						Status = nextnode.getProperty("Status").getString();
						Status = fd.fetch_EmployementStatus(session, Status);

					}
					if (nextnode.hasProperty("CargoType")) {
						CargoType = nextnode.getProperty("CargoType").getString();
						CargoType = fd.fetch_CargoType(session, CargoType);

					}
					if (nextnode.hasProperty("CargoGrade")) {
						CargoGrade = nextnode.getProperty("CargoGrade").getString();
						CargoGrade = fd.fetch_CargoGrade(session, CargoGrade);

					}
					if (nextnode.hasProperty("CargoQty")) {
						CargoQty = nextnode.getProperty("CargoQty").getString();

					}
					if (nextnode.hasProperty("LCStart")) {
						LCStart = nextnode.getProperty("LCStart").getString();

					}
					if (nextnode.hasProperty("LCEnd")) {
						LCEnd = nextnode.getProperty("LCEnd").getString();

					}
					if (nextnode.hasProperty("LoadPort")) {
						LoadPort = nextnode.getProperty("LoadPort").getString();
						// out.println("LoadPort:: "+LoadPort);
						String LoadPort1 = fd.fetch_Port(session, LoadPort);
						// out.println("LoadPort1: "+LoadPort1);
						boolean checkjsonString = SaveReportDataClass.isJSONValid(LoadPort1);
						if (checkjsonString == true) {
							JSONObject data = new JSONObject(LoadPort1);
							if (data.has("portName")) {
								LoadPort = data.getString("portName");
								// out.println("LoadPort: "+LoadPort);
							}

						}

					}
					if (nextnode.hasProperty("DiscPort")) {
						DiscPort = nextnode.getProperty("DiscPort").getString();
						DiscPort = fd.fetch_Port(session, DiscPort);

						boolean checkjsonString = SaveReportDataClass.isJSONValid(DiscPort);
						if (checkjsonString == true) {
							JSONObject data = new JSONObject(DiscPort);
							if (data.has("portName")) {
								DiscPort = data.getString("portName");
							}

						}

					}
					if (nextnode.hasProperty("VesselName")) {
						VesselName = nextnode.getProperty("VesselName").getString();

						VesselName = fd.fetch_vesselName(session, VesselName);
						JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
						if (fetch_vesselNamejsonobject.has("vessel_Id")) {
							VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
						}

					}
					if (nextnode.hasProperty("RateType")) {
						RateType = nextnode.getProperty("RateType").getString();
						RateType = fd.fetch_RateType(session, RateType);

					}
					if (nextnode.hasProperty("Rate")) {
						Rate = nextnode.getProperty("Rate").getString();

					}
					if (nextnode.hasProperty("Comments")) {
						Comments = nextnode.getProperty("Comments").getString();

					}
					String ReportDate = "";
					if (nextnode.hasProperty("ReportDate")) {

						ReportDate = nextnode.getProperty("ReportDate").getString();

					}
					/*
					 * if (nextnode.hasProperty("Source")) { Source =
					 * nextnode.getProperty("Source").getString(); Source =
					 * fd.fetch_Source(session, Source);
					 * 
					 * }
					 */

					if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();

						boolean numeric = true;

						try {
							Integer num = Integer.parseInt(Source);

						} catch (NumberFormatException e) {
							numeric = false;
						}

						if (numeric) {
							Source = fd.fetch_Source(session, Source);
						}

						else {
							if (nextnode.hasProperty("from_Source")) {
								Source = nextnode.getProperty("from_Source").getString();
								Source = fd.fetch_Source(session, Source);
							}
						}

					}

					String Vesseltype = "";
					if (nextnode.hasProperty("Vesseltype")) {

						Vesseltype = nextnode.getProperty("Vesseltype").getString();
						Vesseltype = FetchData.fetch_vesselType(session, Vesseltype);

					}
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}
					String vesselFlag = "";
					if (nextnode.hasProperty("vesselFlag")) {

						vesselFlag = nextnode.getProperty("vesselFlag").getString();

					}
					String flagPort = "";
					if (nextnode.hasProperty("flagPort")) {

						flagPort = nextnode.getProperty("flagPort").getString();

					}
					String ReportType = "";
					if (nextnode.hasProperty("ReportType")) {
						ReportType = nextnode.getProperty("ReportType").getString();
						ReportType = fd.fetch_ReportType(session, ReportType);

					}
					
					spotSolr=postSolr(out, ReportType, "", CargoType, VesselName, Vesseltype, "", "", "", "", "", "", "", "", Status, "", "", "", Comments, Source, "", Information, FixtureType, Charterer, CargoGrade, CargoQty, LCStart, LCEnd, LoadPort, DiscPort, RateType, Rate, ReportDate, "", "", "", "", "", "", "", "", "", "", "", "", "");

					/*spotSolr=postSolr(out, ReportType.toUpperCase(), ""+" "+"", CargoType.toUpperCase(), VesselName.toUpperCase(),
							Vesseltype.toUpperCase(), ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", Status.toUpperCase(), ""+" "+"", ""+" "+"", ""+" "+"",
							Comments.toUpperCase(), Source.toUpperCase(), ""+" "+"", Information.toUpperCase(),
							FixtureType.toUpperCase(), Charterer.toUpperCase(), CargoGrade.toUpperCase(), CargoQty,
							LCStart, LCEnd, LoadPort.toUpperCase(), DiscPort.toUpperCase(), RateType.toUpperCase(),
							Rate, ReportDate, ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"");
*/
				} // while close

			} // node check close if

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return spotSolr;

	}

	public static String fetchTimeCharterReportsDataChanged(PrintWriter out, Session session) {
String timechartereSolr="";
		try {
			FetchData fd = new FetchData();
			Node scorpio = null;
			Node ReportData = null;
			Node TCReports = null;
			Node TimeCharterReports = null;

			if (session.getRootNode().hasNode("scorpioDataBase")) {
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}
			if (scorpio.hasNode("ReportData")) {
				ReportData = scorpio.getNode("ReportData");
			}
			if (ReportData.hasNode("TCReports")) {
				TCReports = ReportData.getNode("TCReports");
			}
			if (TCReports.hasNode("TimeCharterReport")) {
				TimeCharterReports = TCReports.getNode("TimeCharterReport");
			}

			if (TimeCharterReports.hasNodes()) {
				long size = TimeCharterReports.getNodes().getSize();
				String Id = "";

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = TimeCharterReports.getNodes();
				int i = 0;

				while (itr.hasNext()) {
					Node nextnode = itr.nextNode();
					i++;
					int parseId = 0;
					String subNodeName = nextnode.getName();

					String Information = "";
					String Charterer = "";
					String Source = "";
					String VesselName = "";
					String Comments = "";
					String Rate = "";
					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("Information")) {
						Information = nextnode.getProperty("Information").getString();
						Information = fd.fetch_Information(session, Information);

					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();
					}
					String Spot_TC = "";
					if (nextnode.hasProperty("Spot_TC")) {

						Spot_TC = nextnode.getProperty("Spot_TC").getString();
						Spot_TC = fd.fetch_ReportType(session, Spot_TC);

					}
					String Date = "";
					if (nextnode.hasProperty("Date")) {

						Date = nextnode.getProperty("Date").getString();

					}

					if (nextnode.hasProperty("VesselName")) {
						VesselName = nextnode.getProperty("VesselName").getString();

						VesselName = fd.fetch_vesselName(session, VesselName);

						JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
						if (fetch_vesselNamejsonobject.has("vessel_Id")) {
							VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
						}

					}

					String CPP_DPP = "";
					if (nextnode.hasProperty("CPP_DPP")) {

						CPP_DPP = nextnode.getProperty("CPP_DPP").getString();
						CPP_DPP = fd.fetch_CargoType(session, CPP_DPP);

					}

					if (nextnode.hasProperty("Charterer")) {
						Charterer = nextnode.getProperty("Charterer").getString();
						Charterer = fd.fetch_Charterer(session, Charterer);

					}
					String Delivery = "";
					if (nextnode.hasProperty("Delivery")) {

						Delivery = nextnode.getProperty("Delivery").getString();

					}
					String DeliveryPlace = "";
					if (nextnode.hasProperty("DeliveryPlace")) {

						DeliveryPlace = nextnode.getProperty("DeliveryPlace").getString();
						DeliveryPlace = fd.fetch_DeliveryPlace(session, DeliveryPlace);

					}
					String Period = "";
					if (nextnode.hasProperty("Period")) {

						Period = nextnode.getProperty("Period").getString();

					}
					String Periodunit = "";
					if (nextnode.hasProperty("Periodunit")) {

						Periodunit = nextnode.getProperty("Periodunit").getString();
						Periodunit = fd.fetch_Periodunit(session, Periodunit);

					}
					String Redelivery = "";
					if (nextnode.hasProperty("Redelivery")) {

						Redelivery = nextnode.getProperty("Redelivery").getString();

					}
					String TCRate = "";
					if (nextnode.hasProperty("TCRate")) {

						TCRate = nextnode.getProperty("TCRate").getString();

					}

					if (nextnode.hasProperty("Rate")) {
						Rate = nextnode.getProperty("Rate").getString();

					}
					String Unit = "";
					if (nextnode.hasProperty("Unit")) {

						Unit = nextnode.getProperty("Unit").getString();
						Unit = fd.fetch_currencyUnit(session, Unit);

					}

					if (nextnode.hasProperty("Comments")) {
						Comments = nextnode.getProperty("Comments").getString();

					}

					if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();

						boolean numeric = true;

						try {
							Integer num = Integer.parseInt(Source);

						} catch (NumberFormatException e) {
							numeric = false;
						}

						if (numeric) {
							Source = fd.fetch_Source(session, Source);
						}

						else {
							if (nextnode.hasProperty("from_Source")) {
								Source = nextnode.getProperty("from_Source").getString();
								Source = fd.fetch_Source(session, Source);
							}
						}

					}

					String Vesseltype = "";
					if (nextnode.hasProperty("Vesseltype")) {

						Vesseltype = nextnode.getProperty("Vesseltype").getString();
						Vesseltype = FetchData.fetch_vesselType(session, Vesseltype);

					}
					String TimeStamp = "";
					if (nextnode.hasProperty("TimeStamp")) {

						TimeStamp = nextnode.getProperty("TimeStamp").getString();

					}
					String emailClientNodeId = "";
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}

					String vesselFlag = "";
					if (nextnode.hasProperty("vesselFlag")) {

						vesselFlag = nextnode.getProperty("vesselFlag").getString();

					}
					String ReportType = "";
					if (nextnode.hasProperty("ReportType")) {
						ReportType = nextnode.getProperty("ReportType").getString();
						ReportType = fd.fetch_ReportType(session, ReportType);

					}

					timechartereSolr=postSolr(out, ReportType.toUpperCase(), ""+" "+"", CPP_DPP.toUpperCase(), VesselName.toUpperCase(),
							Vesseltype.toUpperCase(), ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"",
							Comments.toUpperCase(), Source.toUpperCase(), TimeStamp, Information.toUpperCase(), "", Charterer.toUpperCase(), ""+" "+"", ""+" "+"",
							""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", TCRate, ""+" "+"", Spot_TC.toUpperCase(), Date, Delivery, DeliveryPlace.toUpperCase(), Period, Periodunit.toUpperCase(),
							Redelivery, Unit.toUpperCase(), ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"");

				} // while close

			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
//			out.println(e.getMessage());

		}
		return timechartereSolr;

	}

	public static String fetchBrokerTcRateDataChanged(PrintWriter out, Session session) {

		String brokertc="";
		try {
			FetchData fd = new FetchData();
			Node scorpio = null;
			Node ReportData = null;
			Node TCReports = null;
			Node TimeCharterReports = null;

			if (session.getRootNode().hasNode("scorpioDataBase")) {
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}
			if (scorpio.hasNode("ReportData")) {
				ReportData = scorpio.getNode("ReportData");
			}
			if (ReportData.hasNode("TCReports")) {
				TCReports = ReportData.getNode("TCReports");
			}
			if (TCReports.hasNode("BrokerTCRate")) {
				TimeCharterReports = TCReports.getNode("BrokerTCRate");
			}

			if (TimeCharterReports.hasNodes()) {

				JSONObject keyNameObject = null;
				long size = TimeCharterReports.getNodes().getSize();
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = TimeCharterReports.getNodes();
				int i = 0;

				while (itr.hasNext()) {
					Node nextnode = itr.nextNode();
					String Id = "";
					int parseId = 0;
					i++;

					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";

					String subNodeName = nextnode.getName();

					if (nextnode.hasProperty("Id")) {

						Id = nextnode.getProperty("Id").getString();
						parseId = Integer.parseInt(Id);
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();
					}
					String Broker = "";

					if (nextnode.hasProperty("Broker")) {
						Broker = nextnode.getProperty("Broker").getString();

						boolean numeric = true;

						try {
							Integer num = Integer.parseInt(Broker);

						} catch (NumberFormatException e) {
							numeric = false;
						}

						if (numeric) {
							Broker = fd.fetch_Source(session, Broker);
						}

						else {
							if (nextnode.hasProperty("from_Source")) {
								Broker = nextnode.getProperty("from_Source").getString();
								Broker = fd.fetch_Source(session, Broker);
							}
						}

					}
					out.println("Broker:: "+Broker);
				
					String VesselType = "";
					if (nextnode.hasProperty("VesselType")) {

						VesselType = nextnode.getProperty("VesselType").getString();
						VesselType = FetchData.fetch_vesselType(session, VesselType);
						out.println("VesselType:: "+VesselType);
					}
					String Month_Year = "";
					if (nextnode.hasProperty("Month_Year")) {

						Month_Year = nextnode.getProperty("Month_Year").getString();
						out.println("Month_Year:: "+Month_Year);
					}
					String first_yr = "";
					if (nextnode.hasProperty("first_yr")) {

						first_yr = nextnode.getProperty("first_yr").getString();
						out.println("first_yr:: "+first_yr);
					}

					String second_yr = "";
					if (nextnode.hasProperty("second_yr")) {

						second_yr = nextnode.getProperty("second_yr").getString();
						out.println("second_yr:: "+second_yr);
					}
					String third_yr = "";
					if (nextnode.hasProperty("third_yr")) {

						third_yr = nextnode.getProperty("third_yr").getString();
						out.println("third_yr:: "+third_yr);
					}
					String fifth_yr = "";
					if (nextnode.hasProperty("fifth_yr")) {

						fifth_yr = nextnode.getProperty("fifth_yr").getString();
                        out.println("fifth_yr: "+fifth_yr);
					}
					String emailClientNodeId = "";
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}
					String ReportType = "";
					if (nextnode.hasProperty("ReportType")) {
						ReportType = nextnode.getProperty("ReportType").getString();
						ReportType = fd.fetch_ReportType(session, ReportType);
						 out.println("ReportType: "+ReportType);
					}

//					if(i==0){
					brokertc=postSolr(out, ReportType, " "+" "+" ", ""+" "+"", " "+" "+" ", VesselType, ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"",
							""+" "+"", Broker, ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"", ""+" "+"",
							""+" "+"", Month_Year, first_yr, second_yr, third_yr, fifth_yr);
					out.println("brokertc:: "+brokertc);
//					break;
//					}
				} // while close

			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
//			out.println(e.getMessage());

		}
		return brokertc;

	}

	public static String postSolr(PrintWriter out, String ReportType, String RepositionRegion, String CargoType,
			String VesselName, String VesselType, String Built, String DWT, String Cubics, String LOA, String ICE,
			String SternLine, String Owners, String Operators, String Status, String OpenPort, String OpenDate,
			String ETABasis, String Comment, String Source, String ReportTimestamp, String Information,
			String FixtureType, String Charterer, String CargoGrade, String CargoQty, String LCStart, String LCEnd,
			String LoadPort, String DiscPort, String RateType, String Rate, String ReportDate, String Spot_TC,
			String Date, String Delivery, String DeliveryPlace, String Period, String Periodunit, String Redelivery,
			String CurrencyUnit, String Month_Year, String one_yr, String two_yr, String three_yr, String five_yr

	) throws UnsupportedEncodingException {

		int responseCode = 0;
		String urlParameters = "";

		try {

			String url1 = "http://34.73.112.165:8983/solr/Report/update/json/docs?commit=true";
			URL url = new URL(url1);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");

			con.setRequestProperty("Content-Type", "application/json");

			
			urlParameters="{\n" +
			"\n" +
			"\"ReportType\":\"\" "+ ReportType+"\"\",\n" +
			"\"RepositionRegion\":\"\",\n" +
			"\"CargoType\":\"\" "+ FixtureType+"\"\",\n" +
			"\"VesselName\":\"\" "+ VesselName+"\"\",\n" +
			"\"VesselType\":\"\" "+ VesselType+"\"\",\n" +
			"\"Built\":\"\",\n" +
			"\"DWT\":\"\",\n" +
			"\"Cubics\":\"\",\n" +
			"\"LOA\":\"\",\n" +
			"\"ICE\":\"\",\n" +
			"\"SternLine\":\"\",\n" +
			"\"Owners\":\"\",\n" +
			"\"Operators\":\"\",\n" +
			"\"Status\":\"\" "+ FixtureType+"\"\",\n" +
			"\"OpenPort\":\"\",\n" +
			"\"OpenDate\":\"\",\n" +
			"\"ETABasis\":\"\",\n" +
			"\"Comment\":\"\" "+ Comment+"\"\",\n" +
			"\"Source\":\" "+ Source +"\",\n" +
			"\"ReportTimestamp\":\"\",\n" +
			"\"Information\":\"\" "+Information+" \"\",\n" +
			"\"FixtureType\":\"\" "+ FixtureType+"\"\",\n" +
			"\"Charterer\":\"\" "+ Charterer+"\"\",\n" +
			"\"CargoGrade\":\"\" "+ CargoGrade+"\"\",\n" +
			"\"CargoQty\":\"\" "+ CargoQty+"\"\",\n" +
			"\"LCStart\":\"\" "+ LCStart+"\"\",\n" +
			"\"LCEnd\":\"\" "+ LCEnd+"\"\",\n" +
			"\"LoadPort\":\"\" "+ LoadPort+"\"\",\n" +
			"\"DiscPort\":\"\" "+ DiscPort+"\"\",\n" +
			"\"RateType\":\"\" "+ RateType+"\"\",\n" +
			"\"Rate\":\"\" "+ Rate+"\"\",\n" +
			"\"ReportDate\":\"\" "+ ReportDate+"\"\",\n" +
			"\"Spot_TC\":\"\",\n" +
			"\"Date\":\"\",\n" +
			"\"Delivery\":\"\",\n" +
			"\"DeliveryPlace\":\"\",\n" +
			"\"Period\":\"\",\n" +
			"\"Periodunit\":\"\",\n" +
			"\"Redelivery\":\"\",\n" +
			"\"CurrencyUnit\":\"\",\n" +
			"\"Month_Year\":\"\",\n" +
			"\"1yr\":\"\",\n" +
			"\"2yr\":\"\",\n" +
			"\"3yr\":\"\",\n" +
			"\"5yr\":\"\"\n" +
			"\n" +
			"}";
			
			
			
			/*urlParameters = "{\n" + "\n" + "\"ReportType\":\"" + ReportType
					+ "\",\n" + "\"RepositionRegion\":\"" + RepositionRegion + "\",\n" + "\"CargoType\":\"" + CargoType
					+ "\",\n" + "\"VesselName\":\"" + VesselName + "\",\n" + "\"VesselType\":\"" + VesselType + "\",\n"
					+ "\"Built\":\"" + Built + "\",\n" + "\"DWT\":\"" + DWT + "\",\n" + "\"Cubics\":\"" + Cubics
					+ "\",\n" + "\"LOA\":\"" + LOA + "\",\n" + "\"ICE\":\"" + ICE + "\",\n" + "\"SternLine\":\""
					+ SternLine + "\",\n" + "\"Owners\":\"" + Owners + "\",\n" + "\"Operators\":\"" + Operators
					+ "\",\n" + "\"Status\":\"" + Status + "\",\n" + "\"OpenPort\":\"" + OpenPort + "\",\n"
					+ "\"OpenDate\":\"" + OpenDate + "\",\n" + "\"ETABasis\":\"" + ETABasis + "\",\n" + "\"Comment\":\""
					+ Comment + "\",\n" + "\"Source\":\"" + Source + "\",\n" + "\"ReportTimestamp\":\""
					+ ReportTimestamp + "\",\n" + "\"Information\":\"" + Information + "\",\n" + "\"FixtureType\":\""
					+ FixtureType + "\",\n" + "\"Charterer\":\"" + Charterer + "\",\n" + "\"CargoGrade\":\""
					+ CargoGrade + "\",\n" + "\"CargoQty\":\"" + CargoQty + "\",\n" + "\"LCStart\":\"" + LCStart
					+ "\",\n" + "\"LCEnd\":\"" + LCEnd + "\",\n" + "\"LoadPort\":\"" + LoadPort + "\",\n"
					+ "\"DiscPort\":\"" + DiscPort + "\",\n" + "\"RateType\":\"" + RateType + "\",\n" + "\"Rate\":\""
					+ Rate + "\",\n" + "\"ReportDate\":\"" + ReportDate + "\",\n" + "\"Spot_TC\":\"" + Spot_TC + "\",\n"
					+ "\"Date\":\"" + Date + "\",\n" + "\"Delivery\":\"" + Delivery + "\",\n" + "\"DeliveryPlace\":\""
					+ DeliveryPlace + "\",\n" + "\"Period\":\"" + Period + "\",\n" + "\"Periodunit\":\"" + Periodunit
					+ "\",\n" + "\"Redelivery\":\"" + Redelivery + "\",\n" + "\"CurrencyUnit\":\"" + CurrencyUnit
					+ "\",\n" + "\"Month_Year\":\"" + Month_Year + "\",\n" + "\"1yr\":\"" + one_yr + "\",\n"
					+ "\"2yr\":\"" + two_yr + "\",\n" + "\"3yr\":\"" + three_yr + "\",\n" + "\"5yr\":\"" + five_yr
					+ "\"\n" + "\n" + "}";*/

			out.println("urlParameters:: "+urlParameters);
			con.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//			 urlParameters = urlParameters.replace(" ", "%20");
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);

			}
			in.close();

			 out.println(response.toString());

		}

		catch (Exception e) {

			e.printStackTrace(out);
//			out.println(e.getMessage());
		}
		return String.valueOf(responseCode) + urlParameters;

	}
	
	public static void allMethodsIncluded(PrintWriter out, Session session){
		
		try {
			
			//String data=fetchTonnageDataChanged(out, session);
			//out.println("tonnage:: "+data);
			String data1=fetchSpotDataChanged(out, session);
			//out.println("spot:: "+data1);
			
			/*String data2=fetchTimeCharterReportsDataChanged(out, session);
			out.println("timecharter:: "+data2);*/
			//String data3=fetchBrokerTcRateDataChanged(out, session);
			//out.println("brokertc:: "+data3)
			
		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}
	
	
	public static void pdfTonnage(Session session, PrintWriter out) throws PathNotFoundException, RepositoryException{
		Node scorpio=null;
		if (session.getRootNode().hasNode("scorpioDataBase4")) {
			scorpio = session.getRootNode().getNode("scorpioDataBase4");
		}
		
		Node allReport=scorpio.getNode("ReportData");
		allReport.remove();
		session.save();
		/*Node tonnage=allReport.getNode("Tonnage");
		long data=tonnage.getNodes().getSize();
		out.println("size :: "+data);*/
		
		//scorpio.remove();
		
		
	}
	
	public static JSONObject fetchTonnage_37KData(PrintWriter out, Session session) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			Node scorpio=null;
			Node ReportData=null;
			Node Tonnage=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio=session.getRootNode().getNode("scorpioDataBase");
			}
			if(scorpio.hasNode("ReportData")){
				ReportData=scorpio.getNode("ReportData");
			}
			if(ReportData.hasNode("Tonnage")){
				Tonnage=ReportData.getNode("Tonnage");
			}
			
			if (Tonnage.hasNodes()) {
				long size = Tonnage.getNodes().getSize();
				JSONObject keyNameObject = null;

				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = Tonnage.getNodes();
				
				int i = 0;
				
				while (itr.hasNext()) {
					Node nextnode= itr.nextNode();
					
					int parseId = 0;
					i++;
					String subNodeName = nextnode.getName();

					String EmploymentStatus = "";
					String Owners = "";
					String OpenPort = "";
					String Operators = "";
					String Id = "";
					String CargoType = "";
					String Comment = "";
					String ETABasis = "";
					String OpenDate = "";
					String ReportType = "";
					String ReportTimestamp = "";
					String RepositionRegion = "";
					String Source = "";
					String VesselName = "";
					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";
					String emailClientNodeId = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("EmploymentStatus")) {
						String EmploymentStatus1 = nextnode.getProperty("EmploymentStatus").getString();
						EmploymentStatus = fd.fetch_EmployementStatus(session, EmploymentStatus1);

						if(GmailMethods.isNullString(EmploymentStatus) ){
							EmploymentStatus=EmploymentStatus1;
						}
						
					}
					
					
					
					
					if (nextnode.hasProperty("Owners")) {
						String Owners1 = nextnode.getProperty("Owners").getString();
						Owners = FetchData.fetch_Owners(session, Owners1);

						if(GmailMethods.isNullString(Owners) ){
							Owners=Owners1;
						}
						
					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();

					}
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}


//					if (GmailMethods.isNullString(RepositionRegion)) {

						if (nextnode.hasProperty("RepositionRegion")) {
							String RepositionRegion12 = nextnode.getProperty("RepositionRegion").getString();

							String RepositionRegion1 = fd.fetch_Port(session, RepositionRegion12);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(RepositionRegion1);
							if (checkjsonString == true) {
								JSONObject data = new JSONObject(RepositionRegion1);
								
								/*if (data.has("Area")) {
									RepositionRegion = data.getString("Area");
									// check_Port_repositionRegion_id=check_OpenPort_id;
								}*/
								
								if (data.has("portName")) {
									RepositionRegion = data.getString("portName");
									// check_Port_repositionRegion_id=check_OpenPort_id;
								}

							}
							
							if(GmailMethods.isNullString(RepositionRegion) ){
								RepositionRegion=RepositionRegion12;
							}

						} else {
							RepositionRegion = "";
						}

//					} // blank
					
					String ReportTimestamp1="";
					String ReportTimestamp2="";
					if (nextnode.hasProperty("ReportTimestamp")) {
						 ReportTimestamp1 = nextnode.getProperty("ReportTimestamp").getString();
						
						if(ReportTimestamp1.lastIndexOf(".")!=-1){
							ReportTimestamp1=ReportTimestamp1.substring( 0,ReportTimestamp1.lastIndexOf(".") );
//							out.println(ReportTimestamp);
							
							LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
							ReportTimestamp = localDateTime.format(formatter);
							
							 localDateTime = LocalDateTime.parse(ReportTimestamp1);
							 formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
							  ReportTimestamp2 = localDateTime.format(formatter);
							
						}else {
							ReportTimestamp=ReportTimestamp1;
						}
						
					}
					
					String year="";
					if(ReportTimestamp2.lastIndexOf("/")!=-1){
					   year=ReportTimestamp2.substring(ReportTimestamp2.lastIndexOf("/")+1);
					}

					if (nextnode.hasProperty("Operators")) {
						String Operators1 = nextnode.getProperty("Operators").getString();
						Operators = fd.fetch_Operators(session, Operators1);

						if(GmailMethods.isNullString(Operators) ){
							Operators=Operators1;
						}
					}
					if (nextnode.hasProperty("CargoType")) {
						String CargoType1 = nextnode.getProperty("CargoType").getString();
						CargoType = fd.fetch_CargoType(session, CargoType1);

						if(GmailMethods.isNullString(CargoType) ){
							CargoType=CargoType1;
						}
					}
					if (nextnode.hasProperty("Comment")) {
						Comment = nextnode.getProperty("Comment").getString();

					}
					if (nextnode.hasProperty("ETABasis")) {
						 ETABasis = nextnode.getProperty("ETABasis").getString();
						//ETABasis=CD.convertDateFormat(ETABasis1);
						/* if( ETABasis.contains("PPT") || ETABasis.contains("PPT - exten") ){
								
						     LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
						     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
						     ETABasis = localDateTime.format(formatter);
						//out.println("ETABasis_ppt: "+ETABasis);
						
					}*/
						// nextnode.setProperty("ETABasis", ETABasis);
						// session.save();
						 
					}
					String date="";
					if (nextnode.hasProperty("OpenPort")) {
						String OpenPort12 = nextnode.getProperty("OpenPort").getString();
						String OpenPort1 = fd.fetch_Port(session, OpenPort12);

						boolean checkjsonString = SaveReportDataClass.isJSONValid(OpenPort1);
						if (checkjsonString == true) {
							JSONObject data = new JSONObject(OpenPort1);
							if (data.has("portName")) {
								OpenPort = data.getString("portName");
							}
							/*if (data.has("Area")) {
								RepositionRegion = data.getString("Area");
								// check_Port_repositionRegion_id=check_OpenPort_id;
							}else {
								RepositionRegion="";
							}*/

						}
						
						if(GmailMethods.isNullString(OpenPort) ){
							OpenPort=OpenPort12;
							
							/*String portJson=CombinePortAndDateSeparateLogic.portIdentifiedJoint(OpenPort12);
							boolean checkPortandDate = SaveReportDataClass.isJSONValid(portJson);
							if (checkPortandDate == true) {
								JSONObject portdate = new JSONObject(portJson);
								if(portdate.has("port")){
									OpenPort=portdate.getString("port");
								}
								if(portdate.has("date")){
									date=portdate.getString("date");
								}
							}// true
*/						} // check null

					}
					
					
					String OpenDate1="";
					if (nextnode.hasProperty("OpenDate")) {
						OpenDate= nextnode.getProperty("OpenDate").getString();
						//OpenDate=CD.convertDateFormat(OpenDate1);
						
					/*	if( OpenDate1.contains("PPT") || OpenDate1.contains("PPT - exten") ){
							
						     LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
						     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
						     OpenDate = localDateTime.format(formatter);
						//out.println("ETABasis_ppt: "+ETABasis);
						
					}*/
						//nextnode.setProperty("OpenDate", OpenDate);
						//session.save();
						/*if(GmailMethods.isNullString(date)){
							String dateJson=CombinePortAndDateSeparateLogic.portIdentifiedJoint(OpenDate1);
							boolean checkPortandDate = SaveReportDataClass.isJSONValid(dateJson);
							if (checkPortandDate == true) {
								JSONObject dateport = new JSONObject(dateJson);
								if(dateport.has("port")){
									String OpenPortDAte=dateport.getString("port");
									if(!GmailMethods.isNullString(OpenPortDAte)){
										OpenPort=OpenPortDAte;
									}
								}
								if(dateport.has("date")){
									date=dateport.getString("date");
									if(!GmailMethods.isNullString(date)){
										OpenDate=date;
									}
								}
							}// true
						}else{
							OpenDate=date;
						}*/

					}
					if (nextnode.hasProperty("ReportType")) {
						ReportType = nextnode.getProperty("ReportType").getString();
						ReportType = fd.fetch_ReportType(session, ReportType);

					}
					
					
					/*if (nextnode.hasProperty("textSentMailTime")) {
					    ReportTimestamp = nextnode.getProperty("textSentMailTime").getString();
					    
					    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
						ReportTimestamp = dateFormat.format(ReportTimestamp);
//						System.out.println(formatedDate);
				    }*/
				
					/*if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

					}*/
					
					if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						
				        boolean numeric = true;

				        try {
				            Integer num = Integer.parseInt(Source);
				            
				        } catch (NumberFormatException e) {
				            numeric = false;
				        }

				        if(numeric){
				        	Source = fd.fetch_Source(session, Source);
				        	String houstontoclarkson="houston";
				        	if(Source.toLowerCase().equals(houstontoclarkson.toLowerCase())){
				        		Source="Clarksons";
				        	}
				        }
				            
				        else{
				        	if (nextnode.hasProperty("from_Source")) {
								Source = nextnode.getProperty("from_Source").getString();
								Source = fd.fetch_Source(session, Source);
								
								String houstontoclarkson="houston";
					        	if(Source.toLowerCase().equals(houstontoclarkson.toLowerCase())){
					        		Source="Clarksons";
					        	}
					        	
				        	}
				        }
				           
				    }
					
					String Flag="";
					if (nextnode.hasProperty("VesselName")) {
						String VesselName1 = nextnode.getProperty("VesselName").getString();
						VesselName = fd.fetch_vesselName(session, VesselName1);
//						if( !GmailMethods.isNullString(VesselName) ){
						boolean checkjsonString = SaveReportDataClass.isJSONValid(VesselName);
						if (checkjsonString == true) {
							JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
							if (fetch_vesselNamejsonobject.has("vessel_Id")) {
								 VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
								
								/*boolean hasLowercase=false;
//								 hasLowercase = !VesselName.equals(VesselName.toUpperCase());
								
								   
								  hasLowercase= checkString(VesselName);
								  if(hasLowercase==true){
									  
									  Flag="true";
//									  VesselName=VesselName1;
									  
									 // System.out.println("lowercase: "+s);
								  }else{
									  Flag="false";
								  }*/
								  
								  
							}
						}
						
						if(GmailMethods.isNullString(VesselName)){
							VesselName=VesselName1;
						}
						
						/*}else {
							VesselName="Route Rate";
						}*/

					}
					
					
					
					
					String Built = "";
					if (nextnode.hasProperty("Built")) {

						String Built1 = nextnode.getProperty("Built").getString();
						
                         boolean booleanOpenDate = Pattern.matches("(\\d{2}\\-\\d{2}\\-\\d{4})", Built1);
						
						if(booleanOpenDate==true){
							if(Built1.lastIndexOf("-")!=-1){
							   int d=Built1.lastIndexOf("-");
							   Built=Built1.substring(d+1);
							}else{
								Built=Built1;
							}
						}else {
							Built=Built1;
						}

					}
					String DWT = "";
					if (nextnode.hasProperty("DWT")) {

						String DWT1 = nextnode.getProperty("DWT").getString();
						
						boolean booleandwt = Pattern.matches("(\\d{1,3})$", DWT1);
						
						if(booleandwt==true){
							int intDWT1=Integer.parseInt(DWT1);
							int dwtInt=intDWT1*1000;
							DWT=String.valueOf(dwtInt);
						}else{
							 booleandwt = Pattern.matches("(\\d{1,3}\\.\\d{2})", DWT1);
							if(booleandwt==true){
								int intDWT1=Integer.parseInt(DWT1);
								int dwtInt=intDWT1*1000;
								DWT=String.valueOf(dwtInt);
								
							}else{
								DWT=DWT1;
							}
						}

					}
					String Cubics = "";
					if (nextnode.hasProperty("Cubics")) {

						String Cubics1 = nextnode.getProperty("Cubics").getString();

                          boolean booleandwt = Pattern.matches("(\\d{1,3})$", Cubics1);
						
						if(booleandwt==true){
							int intDWT1=Integer.parseInt(Cubics1);
							int dwtInt=intDWT1*1000;
							Cubics=String.valueOf(dwtInt);
						}else{
							 booleandwt = Pattern.matches("(\\d{1,3}\\.\\d{2})", Cubics1);
							if(booleandwt==true){
								int intDWT1=Integer.parseInt(Cubics1);
								int dwtInt=intDWT1*1000;
								Cubics=String.valueOf(dwtInt);
								
							}else{
								Cubics=Cubics1;
							}
						}
						
					}
					String LOA = "";
					if (nextnode.hasProperty("LOA")) {

						LOA = nextnode.getProperty("LOA").getString();

					}

					String ICE = "";
					if (nextnode.hasProperty("ICE")) {

						ICE = nextnode.getProperty("ICE").getString();

					}

					String SternLine = "";
					if (nextnode.hasProperty("SternLine")) {

						SternLine = nextnode.getProperty("SternLine").getString();

					}
					String VesselType = "";
					if (nextnode.hasProperty("VesselType")) {

						String VesselType1 = nextnode.getProperty("VesselType").getString();
						//out.println("VesselType1:: "+VesselType1);
						VesselType = FetchData.fetch_vesselType(session, VesselType1);
						//out.println("VesselType:: "+VesselType);
						if(GmailMethods.isNullString(VesselType)){
							VesselType=VesselType1;
							//out.println("VesselType_if:: "+VesselType);
						}
						
					}
					String vesselFlag = "";
					if (nextnode.hasProperty("vesselFlag")) {

						vesselFlag = nextnode.getProperty("vesselFlag").getString();

					}
					String flagPort = "";
					if (nextnode.hasProperty("flagOpenPort")) {

						flagPort = nextnode.getProperty("flagOpenPort").getString();

					}
					String SingleJson = "";
					if (nextnode.hasProperty("SingleJson")) {

						SingleJson = nextnode.getProperty("SingleJson").getString();

					}
					

					keyNameObject = new JSONObject();

					
					
					/*if( OpenDate==null ||  OpenDate==""){
					     
					}*//*else{*/
						keyNameObject.put("OpenDate", OpenDate);
						keyNameObject.put("tonnageId", Id);
						keyNameObject.put("dateAndTime", dateAndTime);
						keyNameObject.put("EmploymentStatus", EmploymentStatus.toUpperCase());
						keyNameObject.put("Owners", Owners.toUpperCase());
						keyNameObject.put("OpenPort", OpenPort.toUpperCase());
						keyNameObject.put("Operators", Operators.toUpperCase());
						keyNameObject.put("CargoType", CargoType.toUpperCase());
						keyNameObject.put("Comment", Comment.toUpperCase());
						keyNameObject.put("ETABasis", ETABasis);
						
						keyNameObject.put("ReportType", ReportType.toUpperCase());
						keyNameObject.put("ReportTimestamp", ReportTimestamp);
						keyNameObject.put("RepositionRegion", RepositionRegion.toUpperCase());
						keyNameObject.put("Source", Source.toUpperCase());
						keyNameObject.put("VesselName", VesselName.toUpperCase());
						keyNameObject.put("Built", Built);
						keyNameObject.put("DWT", DWT);
						keyNameObject.put("Cubics", Cubics);
						keyNameObject.put("LOA", LOA);
						keyNameObject.put("ICE", ICE.toUpperCase());
						keyNameObject.put("SternLine", SternLine);
						keyNameObject.put("VesselType", VesselType.toUpperCase());
						keyNameObject.put("subNodeName", subNodeName);
						keyNameObject.put("size", size + 1);// parseId
						keyNameObject.put("parseId", parseId + 1);
						keyNameObject.put("i", i);
						keyNameObject.put("Sr_No", Sr_No);
						keyNameObject.put("emailUrl", emailUrl);
						keyNameObject.put("emailClientNodeId", emailClientNodeId);
						keyNameObject.put("vesselFlag", vesselFlag);
						keyNameObject.put("flagOpenPort", flagPort);
						keyNameObject.put("SingleJson", SingleJson);
						
					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}
					
			} // while close
 

					mainJsonobj = new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

					Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("TonnageExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("TonnageExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("TonnageExcelSummaryRecord");
						session.save();
					}

					String resultExcel = createExcelTonnageNew(out, mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}


			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
//			out.println(e.getMessage());

		}
		return mainJsonobj;

	}
	
	
	public static String createExcelTonnageNew(PrintWriter out, String fetchTonnageDaywiseData) {
		JSONObject final_obj = null;
		try {

			int rowIndex = 0;
			// Create a Workbook
			Workbook workbook = new HSSFWorkbook(); // XSSFWorkbook new
													// HSSFWorkbook() for
													// generating `.xls` file
			/*
			 * CreationHelper helps us create instances of various things like
			 * DataFormat, Hyperlink, RichTextString etc, in a format (HSSF,
			 * XSSF) independent way
			 */
			CreationHelper createHelper = workbook.getCreationHelper();

			// Create a Sheet
			Sheet sheet = workbook.createSheet("Tonnage-Summery");
			// Create a Font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
	        /*CellStyle style = workbook.createCellStyle();
	        Font font = workbook.createFont();
	        font.setColor(HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());
	        style.setFont(font);*/
			
			 CellStyle style = workbook.createCellStyle();
			 style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			 style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			 

			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
			headerCellStyle.setWrapText(true);
			headerCellStyle.setFont(headerFont);

			CellStyle hlinkstyle = workbook.createCellStyle();
			Font hlinkfont = workbook.createFont();
			hlinkfont.setUnderline(HSSFFont.U_SINGLE);
			hlinkfont.setColor(HSSFColor.BLUE.index);

			hlinkstyle.setFont(hlinkfont);

			// Create Cell Style for formatting Date
			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

			Row brokertcratedatarow = sheet.createRow((rowIndex));
			// String
			// brokerfetchdata=SlingMethods.fetchBrokerTcRateDaywiseData(out,
			// session);
			String tonnagefetchdata = fetchTonnageDaywiseData;
			// String
			// brokerfetchdata="{\"BrokerTcRateList\":[{\"BrokerTcRateId\":\"6\",\"dateAndTime\":\"Sun
			// Oct 08 18:56:35 UTC
			// 2018\",\"BrokerJsonarray\":[{\"Broker\":\"f\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"\"}],\"first_yrJsonarray\":[{\"first_yr\":\"\"}],\"second_yrJsonarray\":[{\"second_yr\":\"\"}],\"third_yrJsonarray\":[{\"third_yr\":\"\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"\"}],\"subNodeName\":\"6\",\"size\":8,\"parseId\":7},{\"BrokerTcRateId\":\"7\",\"dateAndTime\":\"Mon
			// Oct 08 07:27:16 UTC
			// 2018\",\"BrokerJsonarray\":[{\"Broker\":\"a\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"b\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"c\"}],\"first_yrJsonarray\":[{\"first_yr\":\"d\"}],\"second_yrJsonarray\":[{\"second_yr\":\"f\"}],\"third_yrJsonarray\":[{\"third_yr\":\"g\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"h\"}],\"subNodeName\":\"7\",\"size\":8,\"parseId\":8}]}";

			JSONObject Tonnagejsonobj = new JSONObject(tonnagefetchdata);

			/*Cell Broker = brokertcratedatarow.createCell(0);
			Broker.setCellValue("EmploymentStatus");
			Broker.setCellStyle(headerCellStyle);

			Cell vesseltype = brokertcratedatarow.createCell(1);
			vesseltype.setCellValue("Owners");
			vesseltype.setCellStyle(headerCellStyle);

			Cell monthYear = brokertcratedatarow.createCell(2);
			monthYear.setCellValue("OpenPort");
			monthYear.setCellStyle(headerCellStyle);

			Cell firstYear = brokertcratedatarow.createCell(3);
			firstYear.setCellValue("Operators");
			firstYear.setCellStyle(headerCellStyle);

			Cell secondYear = brokertcratedatarow.createCell(4);
			secondYear.setCellValue("CargoType");
			secondYear.setCellStyle(headerCellStyle);

			Cell thirdYear = brokertcratedatarow.createCell(5);
			thirdYear.setCellValue("Comment");
			thirdYear.setCellStyle(headerCellStyle);

			Cell fifthYear = brokertcratedatarow.createCell(6);
			fifthYear.setCellValue("ETABasis");
			fifthYear.setCellStyle(headerCellStyle);

			Cell OpenDate = brokertcratedatarow.createCell(7);
			OpenDate.setCellValue("OpenDate");
			OpenDate.setCellStyle(headerCellStyle);

			Cell ReportType = brokertcratedatarow.createCell(8);
			ReportType.setCellValue("ReportType");
			ReportType.setCellStyle(headerCellStyle);

			Cell ReportTimestamp = brokertcratedatarow.createCell(9);
			ReportTimestamp.setCellValue("ReportTimestamp");
			ReportTimestamp.setCellStyle(headerCellStyle);

			Cell RepositionRegion = brokertcratedatarow.createCell(10);
			RepositionRegion.setCellValue("RepositionRegion");
			RepositionRegion.setCellStyle(headerCellStyle);

			Cell Source = brokertcratedatarow.createCell(11);
			Source.setCellValue("Source");
			Source.setCellStyle(headerCellStyle);

			Cell VesselName = brokertcratedatarow.createCell(12);
			VesselName.setCellValue("VesselName");
			VesselName.setCellStyle(headerCellStyle);

			Cell Built = brokertcratedatarow.createCell(13);
			Built.setCellValue("Built");
			Built.setCellStyle(headerCellStyle);

			Cell DWT = brokertcratedatarow.createCell(14);
			DWT.setCellValue("DWT");
			DWT.setCellStyle(headerCellStyle);

			Cell Cubics = brokertcratedatarow.createCell(15);
			Cubics.setCellValue("Cubics");
			Cubics.setCellStyle(headerCellStyle);

			Cell LOA = brokertcratedatarow.createCell(16);
			LOA.setCellValue("LOA");
			LOA.setCellStyle(headerCellStyle);

			Cell ICE = brokertcratedatarow.createCell(17);
			ICE.setCellValue("ICE");
			ICE.setCellStyle(headerCellStyle);

			Cell SternLine = brokertcratedatarow.createCell(18);
			SternLine.setCellValue("SternLine");
			SternLine.setCellStyle(headerCellStyle);

			Cell VesselType = brokertcratedatarow.createCell(19);
			VesselType.setCellValue("VesselType");
			VesselType.setCellStyle(headerCellStyle);*/
			
			Cell Broker = brokertcratedatarow.createCell(0);
			Broker.setCellValue("ReportType");
			Broker.setCellStyle(headerCellStyle);

			Cell vesseltype = brokertcratedatarow.createCell(1);
			vesseltype.setCellValue("RepositionRegion");
			vesseltype.setCellStyle(headerCellStyle);

			Cell LOA = brokertcratedatarow.createCell(2);
			LOA.setCellValue("ETABasis");
			LOA.setCellStyle(headerCellStyle);
			
			Cell monthYear = brokertcratedatarow.createCell(3);
			monthYear.setCellValue("CargoType");
			monthYear.setCellStyle(headerCellStyle);

			Cell firstYear = brokertcratedatarow.createCell(4);
			firstYear.setCellValue("VesselName");
			firstYear.setCellStyle(headerCellStyle);

			Cell secondYear = brokertcratedatarow.createCell(5);
			secondYear.setCellValue("VesselType");
			secondYear.setCellStyle(headerCellStyle);

			Cell thirdYear = brokertcratedatarow.createCell(6);
			thirdYear.setCellValue("Built");
			thirdYear.setCellStyle(headerCellStyle);

			Cell fifthYear = brokertcratedatarow.createCell(7);
			fifthYear.setCellValue("DWT");
			fifthYear.setCellStyle(headerCellStyle);

			Cell OpenDate = brokertcratedatarow.createCell(8);
			OpenDate.setCellValue("Cubics");
			OpenDate.setCellStyle(headerCellStyle);

			Cell ReportType = brokertcratedatarow.createCell(9);
			ReportType.setCellValue("LOA");
			ReportType.setCellStyle(headerCellStyle);

			Cell ReportTimestamp = brokertcratedatarow.createCell(10);
			ReportTimestamp.setCellValue("ICE");
			ReportTimestamp.setCellStyle(headerCellStyle);

			Cell RepositionRegion = brokertcratedatarow.createCell(11);
			RepositionRegion.setCellValue("SternLine");
			RepositionRegion.setCellStyle(headerCellStyle);

			Cell Source = brokertcratedatarow.createCell(12);
			Source.setCellValue("Owners");
			Source.setCellStyle(headerCellStyle);

			Cell VesselName = brokertcratedatarow.createCell(13);
			VesselName.setCellValue("Operators");
			VesselName.setCellStyle(headerCellStyle);

			Cell Built = brokertcratedatarow.createCell(14);
			Built.setCellValue("EmploymentStatus");
			Built.setCellStyle(headerCellStyle);

			Cell DWT = brokertcratedatarow.createCell(15);
			DWT.setCellValue("OpenPort");
			DWT.setCellStyle(headerCellStyle);

			Cell Cubics = brokertcratedatarow.createCell(16);
			Cubics.setCellValue("OpenDate");
			Cubics.setCellStyle(headerCellStyle);

			

			Cell ICE = brokertcratedatarow.createCell(17);
			ICE.setCellValue("Comment");
			ICE.setCellStyle(headerCellStyle);

			Cell SternLine = brokertcratedatarow.createCell(18);
			SternLine.setCellValue("Source");
			SternLine.setCellStyle(headerCellStyle);

			Cell VesselType = brokertcratedatarow.createCell(19);
			VesselType.setCellValue("ReportTimestamp");
			VesselType.setCellStyle(headerCellStyle);
			
			Cell emailUrl = brokertcratedatarow.createCell(20);
			emailUrl.setCellValue("emailUrl");
			emailUrl.setCellStyle(headerCellStyle); //SingleJson
			
			Cell SingleJson = brokertcratedatarow.createCell(21);
			SingleJson.setCellValue("SingleJson");
			SingleJson.setCellStyle(headerCellStyle);

			if (Tonnagejsonobj.has("tonnageList")) {
				JSONArray combinebrokerjsonarray = new JSONArray();

				JSONArray tonnageList = Tonnagejsonobj.getJSONArray("tonnageList");
				for (int i = 0; i < tonnageList.length(); i++) {
					JSONObject combinebrokerobj = new JSONObject();

					JSONObject BrokerTcRateList_inside_jsonobj = tonnageList.getJSONObject(i);
					String broker = "";
					if (BrokerTcRateList_inside_jsonobj.has("EmploymentStatus")) {
						broker = BrokerTcRateList_inside_jsonobj.getString("EmploymentStatus");
						combinebrokerobj.put("EmploymentStatusobj", broker);
					}

					String vesseltype_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("Owners")) {
						vesseltype_value = BrokerTcRateList_inside_jsonobj.getString("Owners");
						combinebrokerobj.put("Ownersobj", vesseltype_value);
					}
					
					String emailUrl1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("emailUrl")) {
						emailUrl1 = BrokerTcRateList_inside_jsonobj.getString("emailUrl");
						combinebrokerobj.put("emailurlobj", emailUrl1);
					}

					String Month_Year_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("OpenPort")) {
						Month_Year_value = BrokerTcRateList_inside_jsonobj.getString("OpenPort");
						combinebrokerobj.put("OpenPortobj", Month_Year_value);
					}

					String firstyr_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("Operators")) {
						firstyr_value = BrokerTcRateList_inside_jsonobj.getString("Operators");
						combinebrokerobj.put("Operatorsobj", firstyr_value);
					}
					String CargoType = "";
					if (BrokerTcRateList_inside_jsonobj.has("CargoType")) {
						CargoType = BrokerTcRateList_inside_jsonobj.getString("CargoType");
						combinebrokerobj.put("CargoTypeobj", CargoType);
					}
					String Comment = "";
					if (BrokerTcRateList_inside_jsonobj.has("Comment")) {
						Comment = BrokerTcRateList_inside_jsonobj.getString("Comment");
						combinebrokerobj.put("Commentobj", Comment);
					}

					String firstyr_value6 = "";
					if (BrokerTcRateList_inside_jsonobj.has("ETABasis")) {
						firstyr_value6 = BrokerTcRateList_inside_jsonobj.getString("ETABasis");
						combinebrokerobj.put("ETABasisobj", firstyr_value6);
					}

					String firstyr_value5 = "";
					if (BrokerTcRateList_inside_jsonobj.has("OpenDate")) {
						firstyr_value5 = BrokerTcRateList_inside_jsonobj.getString("OpenDate");
						combinebrokerobj.put("OpenDateobj", firstyr_value5);
					}

					String firstyr_value4 = "";
					if (BrokerTcRateList_inside_jsonobj.has("ReportType")) {
						firstyr_value4 = BrokerTcRateList_inside_jsonobj.getString("ReportType");
						combinebrokerobj.put("ReportTypeobj", firstyr_value4);
					}

					String firstyr_value3 = "";
					if (BrokerTcRateList_inside_jsonobj.has("ReportType")) {
						firstyr_value3 = BrokerTcRateList_inside_jsonobj.getString("ReportTimestamp");
						combinebrokerobj.put("ReportTimestampobj", firstyr_value3);
					}

					String firstyr_value2 = "";
					if (BrokerTcRateList_inside_jsonobj.has("RepositionRegion")) {
						firstyr_value2 = BrokerTcRateList_inside_jsonobj.getString("RepositionRegion");
						combinebrokerobj.put("RepositionRegionobj", firstyr_value2);
					}

					String firstyr_value1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Source")) {
						firstyr_value1 = BrokerTcRateList_inside_jsonobj.getString("Source");
						combinebrokerobj.put("Sourceobj", firstyr_value1);
					}

					String VesselName1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("VesselName")) {
						VesselName1 = BrokerTcRateList_inside_jsonobj.getString("VesselName");
						combinebrokerobj.put("VesselNameobj", VesselName1);
					}

					String Built1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Built")) {
						Built1 = BrokerTcRateList_inside_jsonobj.getString("Built");
						combinebrokerobj.put("Builtobj", Built1);
					}

					String firstyr_value7 = "";
					if (BrokerTcRateList_inside_jsonobj.has("DWT")) {
						firstyr_value7 = BrokerTcRateList_inside_jsonobj.getString("DWT");
						combinebrokerobj.put("DWTobj", firstyr_value7);
					}

					String firstyr_value8 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Cubics")) {
						firstyr_value8 = BrokerTcRateList_inside_jsonobj.getString("Cubics");
						combinebrokerobj.put("Cubicsobj", firstyr_value8);
					}

					String firstyr_value9 = "";
					if (BrokerTcRateList_inside_jsonobj.has("LOA")) {
						firstyr_value9 = BrokerTcRateList_inside_jsonobj.getString("LOA");
						combinebrokerobj.put("LOAobj", firstyr_value9);
					}

					String firstyr_value10 = "";
					if (BrokerTcRateList_inside_jsonobj.has("ICE")) {
						firstyr_value10 = BrokerTcRateList_inside_jsonobj.getString("ICE");
						combinebrokerobj.put("ICEobj", firstyr_value10);
					}

					String firstyr_value11 = "";
					if (BrokerTcRateList_inside_jsonobj.has("SternLine")) {
						firstyr_value11 = BrokerTcRateList_inside_jsonobj.getString("SternLine");
						combinebrokerobj.put("SternLineobj", firstyr_value11);
					}

					String firstyr_value12 = "";
					if (BrokerTcRateList_inside_jsonobj.has("VesselType")) {
						firstyr_value12 = BrokerTcRateList_inside_jsonobj.getString("VesselType");
						combinebrokerobj.put("VesselTypeeobj", firstyr_value12);
					}
					String firstyr_value13 = "";
					if (BrokerTcRateList_inside_jsonobj.has("SingleJson")) {
						firstyr_value13 = BrokerTcRateList_inside_jsonobj.getString("SingleJson");
						combinebrokerobj.put("SingleJsonobj", firstyr_value13);
					}

					if (combinebrokerobj.length() > 0) {
						combinebrokerjsonarray.put(combinebrokerobj);
					}

				} // main for

				if (combinebrokerjsonarray.length() > 0) {
					for (int j = 0; j < combinebrokerjsonarray.length(); j++) {
						JSONObject brokercombineobj = combinebrokerjsonarray.getJSONObject(j);

						String brokerobj = "";
						if (brokercombineobj.has("ReportTypeobj")) {
							brokerobj = brokercombineobj.getString("ReportTypeobj");
						}

						Row brokerrow = sheet.createRow((rowIndex + 1 + j));

						Cell broker_value = brokerrow.createCell(0);
						broker_value.setCellValue(brokerobj);

						String vesseltypeobj = "";
						if (brokercombineobj.has("RepositionRegionobj")) {
							vesseltypeobj = brokercombineobj.getString("RepositionRegionobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(1);
						broker_value.setCellValue(vesseltypeobj);

						String vesseltypeobj14 = "";
						if (brokercombineobj.has("ETABasisobj")) {
							vesseltypeobj14 = brokercombineobj.getString("ETABasisobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(2);
						broker_value.setCellValue(vesseltypeobj14);
						
						
						String vesseltypeobj1 = "";
						if (brokercombineobj.has("CargoTypeobj")) {
							vesseltypeobj1 = brokercombineobj.getString("CargoTypeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(3);
						broker_value.setCellValue(vesseltypeobj1);

						String vesseltypeobj2 = "";
						if (brokercombineobj.has("VesselNameobj")) {
							vesseltypeobj2 = brokercombineobj.getString("VesselNameobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(4);
						if("Route Rate".equalsIgnoreCase(vesseltypeobj2)){
//							row.getCell(0).setCellStyle(style);
							broker_value.setCellStyle(style);
						}
						broker_value.setCellValue(vesseltypeobj2);

						String vesseltypeobj3 = "";
						if (brokercombineobj.has("VesselTypeeobj")) {
							vesseltypeobj3 = brokercombineobj.getString("VesselTypeeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(5);
						broker_value.setCellValue(vesseltypeobj3);

						String vesseltypeobj4 = "";
						if (brokercombineobj.has("Builtobj")) {
							vesseltypeobj4 = brokercombineobj.getString("Builtobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(6);
						broker_value.setCellValue(vesseltypeobj4);

						String vesseltypeobj5 = "";
						if (brokercombineobj.has("DWTobj")) {
							vesseltypeobj5 = brokercombineobj.getString("DWTobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(7);
						broker_value.setCellValue(vesseltypeobj5);

						String vesseltypeobj6 = "";
						if (brokercombineobj.has("Cubicsobj")) {
							vesseltypeobj6 = brokercombineobj.getString("Cubicsobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(8);
						broker_value.setCellValue(vesseltypeobj6);

						String vesseltypeobj17 = "";
						if (brokercombineobj.has("LOAobj")) {
							vesseltypeobj17 = brokercombineobj.getString("LOAobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(9);
						broker_value.setCellValue(vesseltypeobj17);

						String vesseltypeobj7 = "";
						if (brokercombineobj.has("ICEobj")) {
							vesseltypeobj7 = brokercombineobj.getString("ICEobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(10);
						broker_value.setCellValue(vesseltypeobj7);

						String vesseltypeobj8 = "";
						if (brokercombineobj.has("SternLineobj")) {
							vesseltypeobj8 = brokercombineobj.getString("SternLineobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(11);
						broker_value.setCellValue(vesseltypeobj8);

						String vesseltypeobj9 = "";
						if (brokercombineobj.has("Ownersobj")) {
							vesseltypeobj9 = brokercombineobj.getString("Ownersobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(12);
						broker_value.setCellValue(vesseltypeobj9);

						String vesseltypeobj10 = "";
						if (brokercombineobj.has("Operatorsobj")) {
							vesseltypeobj10 = brokercombineobj.getString("Operatorsobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(13);
						broker_value.setCellValue(vesseltypeobj10);

						String vesseltypeobj11 = "";
						if (brokercombineobj.has("EmploymentStatusobj")) {
							vesseltypeobj11 = brokercombineobj.getString("EmploymentStatusobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(14);
						broker_value.setCellValue(vesseltypeobj11);

						String vesseltypeobj12 = "";
						if (brokercombineobj.has("OpenPortobj")) {
							vesseltypeobj12 = brokercombineobj.getString("OpenPortobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(15);
						broker_value.setCellValue(vesseltypeobj12);

						String vesseltypeobj13 = "";
						if (brokercombineobj.has("OpenDateobj")) {
							vesseltypeobj13 = brokercombineobj.getString("OpenDateobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(16);
						broker_value.setCellValue(vesseltypeobj13);

						

						String vesseltypeobj15 = "";
						if (brokercombineobj.has("Commentobj")) {
							vesseltypeobj15 = brokercombineobj.getString("Commentobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(17);
						broker_value.setCellValue(vesseltypeobj15);

						String vesseltypeobj16 = "";
						if (brokercombineobj.has("Sourceobj")) {
							vesseltypeobj16 = brokercombineobj.getString("Sourceobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(18);
						broker_value.setCellValue(vesseltypeobj16);

						String vesseltypeobj171 = "";
						if (brokercombineobj.has("ReportTimestampobj")) {
							vesseltypeobj171 = brokercombineobj.getString("ReportTimestampobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(19);
						broker_value.setCellValue(vesseltypeobj171);
						
						String vesseltypeobj1712 = "";
						if (brokercombineobj.has("emailurlobj")) {
							vesseltypeobj1712 = brokercombineobj.getString("emailurlobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(20);
						broker_value.setCellValue(vesseltypeobj1712);

						String vesseltypeobj1713 = "";
						if (brokercombineobj.has("SingleJsonobj")) {
							vesseltypeobj1713 = brokercombineobj.getString("SingleJsonobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(21);
						broker_value.setCellValue(vesseltypeobj1713);

					}

				} //

			} // if

			Calendar cal = null;
			cal = Calendar.getInstance();
			Date minDate = new Date(cal.getTimeInMillis());

			JSONObject dateJson = new JSONObject();
			Date date = GmailMethods.parseDate(minDate.toString());
			dateJson = SlingMethods.formatDate_Month_Year_Day(date, out);

			int day = dateJson.getInt("day");
			int month = dateJson.getInt("month");
			int year = dateJson.getInt("year");

			// String fileName = "broker" + "tcrate" + "_" + day + "-" + month +
			// "-" + year + ".xls";
			String fileName = "Tonnage" + "Data" + "_" + day + "-" + month + "-" + year + ".xls";
			fileName = fileName.replace(" ", "-");

			File linuxdirectory = new File("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpioexcel");
			if (!linuxdirectory.exists()) {

				linuxdirectory.mkdir();
				out.println("folder created in linux");
			}

			// Write the output to a file
			// FileOutputStream fileOut = new
			// FileOutputStream("D:\\Scorpio\\Email\\"+fileName.trim());
			FileOutputStream fileOut = new FileOutputStream(
					"/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpioexcel/" + fileName.trim());
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();

			final_obj = new JSONObject();
			final_obj.put("fileName", fileName.trim());
			final_obj.put("fileUrl", "https://dev.bizlem.io:8082/scorpioexcel/" + fileName.trim());
			final_obj.put("status", "S");

		} catch (Exception e) {
			return "{\"status\":\"E\",\"Message\" : " + e.getMessage() + "}";
		}
		return final_obj.toString();
	}

	public static JSONObject callPArsedBrokerDataTemplate(Session session, String brokerStr, String reportTypeStr,String jsonStr){
		JSONObject mainJson = new JSONObject();
		try {
			
			Node rootNode = session.getRootNode();
			
			JSONArray matchArr = new JSONArray();
				if(rootNode.hasNode("scorpioDataBase")) {
				  Node scorpio = rootNode.getNode("scorpioDataBase");
				  if(scorpio.hasNode("ReportData")) {
					  Node reportData = scorpio.getNode("ReportData");
					  if(reportData.hasNode("Broker")) {
						  Node broker = reportData.getNode("Broker");
						  if(broker.hasNode(brokerStr) && broker.getNode(brokerStr).hasNode(reportTypeStr)) {
							  Node reportType = broker.getNode(brokerStr).getNode(reportTypeStr);
							  if(reportType.hasNodes()) {
								  Node matchReportType = reportType.getNode("1");
								  if(matchReportType.hasProperties()) {
//									  out.println("size :: "+matchReportType.getProperties().getSize());
//									  out.println(reportType.getProperty("jsondata").getString());
									  JSONObject entireReport = new JSONObject(jsonStr);
//									  out.println("entireReport  :: "+entireReport);
									  Iterator<String> keys = entireReport.keys();
							            while (keys.hasNext()) {
								             String key = keys.next();
								             Object keyValue = entireReport.get(key);
								             if (keyValue instanceof JSONArray) {
//								            	 out.println("keyValue  :: "+keyValue);
								            	 JSONArray allReportJsonArray=null;
								        		 allReportJsonArray = (JSONArray)keyValue;
								        			
								            	 if(reportType.getName().equals(key)) {
								            		//out.println("allReportJsonArray  :: "+allReportJsonArray.length());
								            		 JSONObject matchJSON = null;
								        				for(int i=0;i<allReportJsonArray.length();i++){
								        					//JSONObject allReportJsonArrayInsideJSonobject=allReportJsonArray.getJSONObject(i);
								        					matchJSON = allReportJsonArray.getJSONObject(i);
								        					for(int j=0;j<matchReportType.getProperties().getSize()-1;j++) {
								        						//out.println("unknownnnnnnn :: "+j);
								        						String slingKey = matchReportType.getProperty("unknown_"+j).getString();
//								        						out.println("unknownnnnnnn :: "+i + "   slingKey  :: "+slingKey);
								        						if(!slingKey.equals("")) {
								        							if(!matchJSON.has(slingKey)) {
								        								matchJSON.put(slingKey, matchJSON.getString("unknown_"+j));
								        							}else {
								        							if(matchJSON.has(slingKey)) {
								        								if(matchJSON.getString(slingKey).equals("")) {
								        								matchJSON.put(slingKey, matchJSON.getString("unknown_"+j));
								        								}
								        							}
								        							}
								        						}
								        					}
								        					matchArr.put(matchJSON);
								        				}
								        				mainJson.put(key, matchArr);
								            	  }else {
								            		  mainJson.put(key, allReportJsonArray);
								            	  }
								             }
							            }
								  }
							  }
						  }
					  }
				  }
				}
//				out.println(mainJson);
			
			
		} catch (Exception e) {
			
		}
		return mainJson;
		
	}
	
	public static JSONObject callPArsedBrokerDataTemplateMultiple(Session session, String brokerStr, String reportTypeStr,String jsonStr, PrintWriter out){
		JSONObject mainJson = new JSONObject();
		try {
			
			Node rootNode = session.getRootNode();
			
			JSONArray matchArr = new JSONArray();
				if(rootNode.hasNode("scorpioDataBase")) {
				  Node scorpio = rootNode.getNode("scorpioDataBase");
				  if(scorpio.hasNode("ReportData")) {
					  Node reportData = scorpio.getNode("ReportData");
					  if(reportData.hasNode("Broker")) {
						  Node broker = reportData.getNode("Broker");
						  out.println("broker code: "+broker.getName().toString());
						  if(broker.hasNode(brokerStr) && broker.getNode(brokerStr).hasNode(reportTypeStr)) {
							  Node reportType = broker.getNode(brokerStr).getNode(reportTypeStr);
							  out.println("reportType code: "+reportType.getName().toString());
							  if(reportType.hasNodes()) {
								  
								  //...........
								 NodeIterator itr= reportType.getNodes();
								 while(itr.hasNext()){
									Node matchReportType= itr.nextNode();
									 out.println("matchReportType code: "+matchReportType.getName().toString());
//								  Node matchReportType = reportType.getNode("1");
								  if(matchReportType.hasProperties()) {
//									  out.println("size :: "+matchReportType.getProperties().getSize());
//									  out.println(reportType.getProperty("jsondata").getString());
									  
									 int unknowSize= countUnknownX(jsonStr);
									 long unknowSizeLong=Long.valueOf(unknowSize);
									 long propertySize= matchReportType.getProperties().getSize()-1;
									 out.println("unknowSizeLong:: "+unknowSizeLong);
									 out.println("propertySize:: "+propertySize);
									  
									 if( String.valueOf(unknowSizeLong).equals(String.valueOf(propertySize)) ){
										 out.println("if true");
									  JSONObject entireReport = new JSONObject(jsonStr);
//									  out.println("entireReport  :: "+entireReport);
									  Iterator<String> keys = entireReport.keys();
							            while (keys.hasNext()) {
								             String key = keys.next();
								             Object keyValue = entireReport.get(key);
								             if (keyValue instanceof JSONArray) {
//								            	 out.println("keyValue  :: "+keyValue);
								            	 JSONArray allReportJsonArray=null;
								        		 allReportJsonArray = (JSONArray)keyValue;
								        			
								            	 if(reportType.getName().equals(key)) {
								            		//out.println("allReportJsonArray  :: "+allReportJsonArray.length());
								            		 JSONObject matchJSON = null;
								        				for(int i=0;i<allReportJsonArray.length();i++){
								        					//JSONObject allReportJsonArrayInsideJSonobject=allReportJsonArray.getJSONObject(i);
								        					matchJSON = allReportJsonArray.getJSONObject(i);
								        					for(int j=0;j<matchReportType.getProperties().getSize()-1;j++) {
								        						//out.println("unknownnnnnnn :: "+j);
								        						String slingKey = matchReportType.getProperty("unknown_"+j).getString();
//								        						out.println("unknownnnnnnn :: "+i + "   slingKey  :: "+slingKey);
								        						if(!slingKey.equals("")) {
								        							if(!matchJSON.has(slingKey)) {
								        								matchJSON.put(slingKey, matchJSON.getString("unknown_"+j));
								        							}else {
								        							if(matchJSON.has(slingKey)) {
								        								if(matchJSON.getString(slingKey).equals("")) {
								        								matchJSON.put(slingKey, matchJSON.getString("unknown_"+j));
								        								}
								        							}
								        							}
								        						}
								        					}
								        					matchArr.put(matchJSON);
								        				}
								        				mainJson.put(key, matchArr);
								            	  }else {
								            		  mainJson.put(key, allReportJsonArray);
								            	  }
								             }
							            }
//								  break;
								  }//after  this
								  
									 else {
										 if( !GmailMethods.isNullString(jsonStr) ){
											 JSONObject s=new JSONObject(jsonStr);
											 return s;
										 }
										 
									 }
								 }
								 }
							  }
						  }
					  }
				  }
				}
//				out.println(mainJson);
			
			
		} catch (Exception e) {
			
		}
		return mainJson;
		
	}
	
public static int countUnknownX(String mainjson){
	int k=0;
		try {
//			System.out.println(mainjson);
			 JSONObject entireReport = new JSONObject(mainjson);
			 Iterator<String> keys = entireReport.keys();
	            while (keys.hasNext()) {
	            	 String key = keys.next();
		             Object keyValue = entireReport.get(key);
		             if (keyValue instanceof JSONArray) {
		            	 JSONArray allReportJsonArray=null;
		        		 allReportJsonArray = (JSONArray)keyValue;
		        		 for(int i=0;i<allReportJsonArray.length();i++){
		        			 if(i==0){
		        			   JSONObject objzero= allReportJsonArray.getJSONObject(i);
//		        			   System.out.println(objzero);
		        			   JSONArray data=objzero.names();
//		        			   System.out.println(data);
//		        			   System.out.println(data.length());
		        			   
		        			   
		        			   for(int j=0;j<data.length();j++){
		        				  String finaldata= data.getString(j);
		        				 // System.out.println(finaldata);
		        				  
		        				  if(finaldata.contains("unknown_")){
		        					  k++;
		        				  }
		        				  
		        			   }
//		        			   System.out.println(k);
		        			   
		        			 }
		        			/*JSONArray data=objzero.names();
		        			System.out.println(data);*/
		        		 }
		            	 
		            	 
		             }// if close
		             
	            } // while
			
		} catch (Exception e) {
			
		}
		return k;
		
	}
	
	public static void data(Session session) throws PathNotFoundException, RepositoryException{
		Node scorpio=session.getRootNode().getNode("scorpioDataBase");
		Node ReportData=scorpio.getNode("ReportData");
		Node Broker=ReportData.getNode("Broker");
		Node mcquilling=Broker.getNode("mcquilling");
		Node Spot=mcquilling.getNode("Spot");
		Node one=Spot.getNode("1");
		
		for(int i=0;i<=199;i++){
			one.setProperty("unknown_"+i+"", "");
			session.save();
		}
		
	}
	
	public static JSONObject fetchSpotDataChangedNew(PrintWriter out, Session session) {

		JSONObject mainJsonobj = null;
		try {

			FetchData fd = new FetchData();
			Node scorpio=null;
			Node ReportData=null;
			Node Spot=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}
			if(ReportData.hasNode("Spot")){
				Spot=ReportData.getNode("Spot");
			}

			if (Spot.hasNodes()) {
				long size = Spot.getNodes().getSize();
				
				String Id = "";

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = Spot.getNodes();
				int i = 0;

				while (itr.hasNext()) {
					Node nextnode=itr.nextNode();

					int parseId = 0;
					i++;
					String subNodeName = nextnode.getName();

					String Information = "";
					String FixtureType = "";
					String Charterer = "";
					String Status = "";
					String CargoType = "";
					String CargoGrade = "";
					String CargoQty = "";
					String LCStart = "";
					String Source = "";
					String VesselName = "";
					String LCEnd = "";
					String LoadPort = "";
					String DiscPort = "";
					String RateType = "";
					String Rate = "";
					String Comments = "";
					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";
					String emailClientNodeId = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("Information")) {
						Information = nextnode.getProperty("Information").getString();
						Information = fd.fetch_Information(session, Information);

					}
					if (nextnode.hasProperty("FixtureType")) {
						FixtureType = nextnode.getProperty("FixtureType").getString();
						FixtureType = fd.fetch_ReportType(session, FixtureType);

					}
					String Charterer1="";
					if (nextnode.hasProperty("Charterer")) {
						Charterer1 = nextnode.getProperty("Charterer").getString();
						Charterer = fd.fetch_Charterer(session, Charterer1);

					}
					
					if(GmailMethods.isNullString(Charterer)){
						Charterer=Charterer1;
					}
					String Status1="";
					if (nextnode.hasProperty("Status")) {
						Status1 = nextnode.getProperty("Status").getString();
						Status = fd.fetch_EmployementStatus(session, Status1);

					}
					
					if(GmailMethods.isNullString(Status)){
						Status=Status1;
					}
					String CargoType1="";
					if (nextnode.hasProperty("CargoType")) {
						CargoType1 = nextnode.getProperty("CargoType").getString();
						CargoType = fd.fetch_CargoType(session, CargoType1);

					}
					
					if(GmailMethods.isNullString(CargoType)){
						CargoType=CargoType1;
					}
					
					
					String CargoGrade1="";
					if (nextnode.hasProperty("CargoGrade")) {
						CargoGrade1 = nextnode.getProperty("CargoGrade").getString();
						CargoGrade = fd.fetch_CargoGrade(session, CargoGrade1);

					}
					
					if(GmailMethods.isNullString(CargoGrade)){
						CargoGrade=CargoGrade1;
					}
					
					if (nextnode.hasProperty("CargoQty")) {
						CargoQty = nextnode.getProperty("CargoQty").getString();

					}
					String ReportTimestamp="";
					String ReportTimestamp1="";
					String ReportTimestamp2="";
					if (nextnode.hasProperty("ReportTimestamp")) {
						 ReportTimestamp1 = nextnode.getProperty("ReportTimestamp").getString();
						
						if(ReportTimestamp1.lastIndexOf(".")!=-1){
							ReportTimestamp1=ReportTimestamp1.substring( 0,ReportTimestamp1.lastIndexOf(".") );
//							out.println(ReportTimestamp);
							
							LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
							ReportTimestamp = localDateTime.format(formatter);
							
							 localDateTime = LocalDateTime.parse(ReportTimestamp1);
							 formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
							  ReportTimestamp2 = localDateTime.format(formatter);
							
						}else {
							ReportTimestamp=ReportTimestamp1;
						}
						
					}
					
					String year="";
					if(ReportTimestamp2.lastIndexOf("/")!=-1){
					   year=ReportTimestamp2.substring(ReportTimestamp2.lastIndexOf("/")+1);
					}
					
					
					if (nextnode.hasProperty("LCStart")) {
						LCStart = nextnode.getProperty("LCStart").getString();

					}
					if (nextnode.hasProperty("LCEnd")) {
						LCEnd = nextnode.getProperty("LCEnd").getString();

					}
					if(GmailMethods.isNullString(LCStart)){
						LCStart=LCEnd;
					}
					if(GmailMethods.isNullString(LCStart)){
						LCStart=LCEnd;
					}
					
					if (nextnode.hasProperty("LoadPort")) {
						String LoadPortData = nextnode.getProperty("LoadPort").getString();
						//out.println("LoadPort:: "+LoadPort);
						String LoadPort1 = fd.fetch_Port(session, LoadPortData);
						//out.println("LoadPort1: "+LoadPort1);
						boolean checkjsonString=SaveReportDataClass.isJSONValid(LoadPort1);
						   if(checkjsonString==true){
							   JSONObject data=new JSONObject(LoadPort1);
							   if(data.has("portName")){
								   LoadPort=data.getString("portName");
								   //out.println("LoadPort: "+LoadPort);
							   }
							   
						   }
						   
						   if(GmailMethods.isNullString(LoadPort)){
							   LoadPort=LoadPortData;
						   }

					}
					
//					String DiscPortData="";
					
					if (nextnode.hasProperty("DiscPort")) {
						String DiscPortData = nextnode.getProperty("DiscPort").getString();
						DiscPort = fd.fetch_Port(session, DiscPortData);
						
						boolean checkjsonString=SaveReportDataClass.isJSONValid(DiscPort);
						   if(checkjsonString==true){
							   JSONObject data=new JSONObject(DiscPort);
							   if(data.has("portName")){
								   DiscPort=data.getString("portName");
							   }
							   
						   }
						   
						   if(GmailMethods.isNullString(DiscPort)){
							   DiscPort=DiscPortData;
						   }

					}
//					String VesselNameData="";
					if (nextnode.hasProperty("VesselName")) {
						String VesselNameData = nextnode.getProperty("VesselName").getString();

						VesselName = fd.fetch_vesselName(session, VesselNameData);
						
						boolean checkjsonString=SaveReportDataClass.isJSONValid(VesselName);
						  if(checkjsonString==true){
						JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
						if (fetch_vesselNamejsonobject.has("vessel_Id")) {
							VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
						}
						  }
						  
						  if(GmailMethods.isNullString(VesselName)){
							  VesselName=VesselNameData;
						   }
						  
						 // out.println("vesselname: "+VesselName);
					}
//					String RateTypeData="";
					if (nextnode.hasProperty("RateType")) {
						String RateTypeData = nextnode.getProperty("RateType").getString();
						RateType = fd.fetch_RateType(session, RateTypeData);

						if( GmailMethods.isNullString(RateType) ){
							RateType=RateTypeData;
						}
						
					}
					
					
					if (nextnode.hasProperty("Rate")) {
						Rate = nextnode.getProperty("Rate").getString();

					}
					if (nextnode.hasProperty("Comments")) {
						Comments = nextnode.getProperty("Comments").getString();

					}
					String ReportDate = "";
					if (nextnode.hasProperty("ReportDate")) {

						ReportDate = nextnode.getProperty("ReportDate").getString();

					}
					/*if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

					}*/
					
					if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						
				        boolean numeric = true;

				        try {
				            Integer num = Integer.parseInt(Source);
				            
				        } catch (NumberFormatException e) {
				            numeric = false;
				        }

				        if(numeric){
				        	Source = fd.fetch_Source(session, Source);
				        	String houstontoclarkson="houston";
				        	if(Source.toLowerCase().equals(houstontoclarkson.toLowerCase())){
				        		Source="Clarksons";
				        	}
				        }
				            
				        else{
				        	if (nextnode.hasProperty("from_Source")) {
								Source = nextnode.getProperty("from_Source").getString();
								Source = fd.fetch_Source(session, Source);
								String houstontoclarkson="houston";
					        	if(Source.toLowerCase().equals(houstontoclarkson.toLowerCase())){
					        		Source="Clarksons";
					        	}
				        	}
				        }
				           
				    }
					
					
				
					String Vesseltype="";
					if (nextnode.hasProperty("Vesseltype")) {

						String VesseltypeData = nextnode.getProperty("Vesseltype").getString();
						Vesseltype = FetchData.fetch_vesselType(session, VesseltypeData);
						if(GmailMethods.isNullString(Vesseltype)){
							Vesseltype=VesseltypeData;
						}/*else{
							Vesseltype="";
						}*/
						
						
					}
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}
					String vesselFlag = "";
					if (nextnode.hasProperty("vesselFlag")) {

						vesselFlag = nextnode.getProperty("vesselFlag").getString();

					}
					String flagPort = "";
					if (nextnode.hasProperty("flagPort")) {

						flagPort = nextnode.getProperty("flagPort").getString();

					}

					String SingleJson = "";
					if (nextnode.hasProperty("SingleJson")) {

						SingleJson = nextnode.getProperty("SingleJson").getString();

					}
					
//					if( LoadPort!="null" && LoadPort!="") {
//						out.println(LoadPort);
					keyNameObject = new JSONObject();
					keyNameObject.put("spotId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("Information", Information.toUpperCase());
					keyNameObject.put("FixtureType", FixtureType.toUpperCase());
					keyNameObject.put("LCStart", LCStart);
					keyNameObject.put("LCEnd", LCEnd);
					keyNameObject.put("LoadPort", LoadPort.toUpperCase());
					keyNameObject.put("DiscPort", DiscPort.toUpperCase());
					keyNameObject.put("VesselName", VesselName.toUpperCase());
					keyNameObject.put("Charterer", Charterer.toUpperCase());
					keyNameObject.put("Status", Status.toUpperCase());
					keyNameObject.put("CargoType", CargoType.toUpperCase());
					keyNameObject.put("CargoGrade", CargoGrade.toUpperCase());
					keyNameObject.put("CargoQty", CargoQty);
					keyNameObject.put("RateType", RateType.toUpperCase());
					keyNameObject.put("Rate", Rate);
					keyNameObject.put("Comments", Comments.toUpperCase());
					keyNameObject.put("ReportDate", ReportDate);
					keyNameObject.put("Source", Source.toUpperCase());
					keyNameObject.put("Vesseltype", Vesseltype.toUpperCase());
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size + 1);// parseId
					keyNameObject.put("parseId", parseId + 1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);
					keyNameObject.put("emailUrl", emailUrl);
					keyNameObject.put("emailClientNodeId", emailClientNodeId);
					keyNameObject.put("vesselFlag", vesselFlag);
					keyNameObject.put("flagPort", flagPort);
					keyNameObject.put("ReportTimestamp", ReportTimestamp);
					keyNameObject.put("SingleJson", SingleJson);
					
					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}


				} // while close 
	

					mainJsonobj = new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);

					Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("SpotExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("SpotExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("SpotExcelSummaryRecord");
						session.save();
					}

					String resultExcel = createExcelSpotNewSpot(out, mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}

				


			} // node check close if

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}
	
	public static String createExcelSpotNewSpot(PrintWriter out, String fetchTonnageDaywiseData) {
		JSONObject final_obj = null;
		try {

			int rowIndex = 0;
			// Create a Workbook
			Workbook workbook = new HSSFWorkbook(); // XSSFWorkbook new
													// HSSFWorkbook() for
													// generating `.xls` file
			/*
			 * CreationHelper helps us create instances of various things like
			 * DataFormat, Hyperlink, RichTextString etc, in a format (HSSF,
			 * XSSF) independent way
			 */
			CreationHelper createHelper = workbook.getCreationHelper();

			// Create a Sheet
			Sheet sheet = workbook.createSheet("Spot-Summary");
			// Create a Font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
			headerCellStyle.setWrapText(true);
			headerCellStyle.setFont(headerFont);

			CellStyle hlinkstyle = workbook.createCellStyle();
			Font hlinkfont = workbook.createFont();
			hlinkfont.setUnderline(HSSFFont.U_SINGLE);
			hlinkfont.setColor(HSSFColor.BLUE.index);

			hlinkstyle.setFont(hlinkfont);

			// Create Cell Style for formatting Date
			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

			Row brokertcratedatarow = sheet.createRow((rowIndex));
			// String
			// brokerfetchdata=SlingMethods.fetchBrokerTcRateDaywiseData(out,
			// session);
			String tonnagefetchdata = fetchTonnageDaywiseData;
			// String
			// brokerfetchdata="{\"BrokerTcRateList\":[{\"BrokerTcRateId\":\"6\",\"dateAndTime\":\"Sun
			// Oct 08 18:56:35 UTC
			// 2018\",\"BrokerJsonarray\":[{\"Broker\":\"f\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"\"}],\"first_yrJsonarray\":[{\"first_yr\":\"\"}],\"second_yrJsonarray\":[{\"second_yr\":\"\"}],\"third_yrJsonarray\":[{\"third_yr\":\"\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"\"}],\"subNodeName\":\"6\",\"size\":8,\"parseId\":7},{\"BrokerTcRateId\":\"7\",\"dateAndTime\":\"Mon
			// Oct 08 07:27:16 UTC
			// 2018\",\"BrokerJsonarray\":[{\"Broker\":\"a\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"b\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"c\"}],\"first_yrJsonarray\":[{\"first_yr\":\"d\"}],\"second_yrJsonarray\":[{\"second_yr\":\"f\"}],\"third_yrJsonarray\":[{\"third_yr\":\"g\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"h\"}],\"subNodeName\":\"7\",\"size\":8,\"parseId\":8}]}";

			JSONObject Tonnagejsonobj = new JSONObject(tonnagefetchdata);

			/*Cell Broker = brokertcratedatarow.createCell(0);
			Broker.setCellValue("Information");
			Broker.setCellStyle(headerCellStyle);

			Cell vesseltype = brokertcratedatarow.createCell(1);
			vesseltype.setCellValue("FixtureType");
			vesseltype.setCellStyle(headerCellStyle);

			Cell monthYear = brokertcratedatarow.createCell(2);
			monthYear.setCellValue("LCStart");
			monthYear.setCellStyle(headerCellStyle);

			Cell firstYear = brokertcratedatarow.createCell(3);
			firstYear.setCellValue("LCEnd");
			firstYear.setCellStyle(headerCellStyle);

			Cell secondYear = brokertcratedatarow.createCell(4);
			secondYear.setCellValue("LoadPort");
			secondYear.setCellStyle(headerCellStyle);

			Cell thirdYear = brokertcratedatarow.createCell(5);
			thirdYear.setCellValue("DiscPort");
			thirdYear.setCellStyle(headerCellStyle);

			Cell fifthYear = brokertcratedatarow.createCell(6);
			fifthYear.setCellValue("VesselName");
			fifthYear.setCellStyle(headerCellStyle);

			Cell OpenDate = brokertcratedatarow.createCell(7);
			OpenDate.setCellValue("Charterer");
			OpenDate.setCellStyle(headerCellStyle);

			Cell ReportType = brokertcratedatarow.createCell(8);
			ReportType.setCellValue("Status");
			ReportType.setCellStyle(headerCellStyle);

			Cell ReportTimestamp = brokertcratedatarow.createCell(9);
			ReportTimestamp.setCellValue("CargoType");
			ReportTimestamp.setCellStyle(headerCellStyle);

			Cell RepositionRegion = brokertcratedatarow.createCell(10);
			RepositionRegion.setCellValue("CargoGrade");
			RepositionRegion.setCellStyle(headerCellStyle);

			Cell Source = brokertcratedatarow.createCell(11);
			Source.setCellValue("CargoQty");
			Source.setCellStyle(headerCellStyle);

			Cell VesselName = brokertcratedatarow.createCell(12);
			VesselName.setCellValue("RateType");
			VesselName.setCellStyle(headerCellStyle);

			Cell Built = brokertcratedatarow.createCell(13);
			Built.setCellValue("Rate");
			Built.setCellStyle(headerCellStyle);

			Cell DWT = brokertcratedatarow.createCell(14);
			DWT.setCellValue("Comments");
			DWT.setCellStyle(headerCellStyle);

			Cell Cubics = brokertcratedatarow.createCell(15);
			Cubics.setCellValue("ReportDate");
			Cubics.setCellStyle(headerCellStyle);

			Cell LOA = brokertcratedatarow.createCell(16);
			LOA.setCellValue("Source");
			LOA.setCellStyle(headerCellStyle);

			Cell ICE = brokertcratedatarow.createCell(17);
			ICE.setCellValue("Vesseltype");
			ICE.setCellStyle(headerCellStyle);
*/
			Cell Broker = brokertcratedatarow.createCell(0);
			Broker.setCellValue("Information");
			Broker.setCellStyle(headerCellStyle);

			Cell vesseltype = brokertcratedatarow.createCell(1);
			vesseltype.setCellValue("FixtureType");
			vesseltype.setCellStyle(headerCellStyle);

			Cell monthYear = brokertcratedatarow.createCell(2);
			monthYear.setCellValue("Charterer");
			monthYear.setCellStyle(headerCellStyle);

			Cell firstYear = brokertcratedatarow.createCell(3);
			firstYear.setCellValue("Status");
			firstYear.setCellStyle(headerCellStyle);

			Cell secondYear = brokertcratedatarow.createCell(4);
			secondYear.setCellValue("CargoType");
			secondYear.setCellStyle(headerCellStyle);

			Cell thirdYear = brokertcratedatarow.createCell(5);
			thirdYear.setCellValue("CargoGrade");
			thirdYear.setCellStyle(headerCellStyle);

			Cell fifthYear = brokertcratedatarow.createCell(6);
			fifthYear.setCellValue("CargoQty");
			fifthYear.setCellStyle(headerCellStyle);

			Cell OpenDate = brokertcratedatarow.createCell(7);
			OpenDate.setCellValue("LCStart");
			OpenDate.setCellStyle(headerCellStyle);

			Cell ReportType = brokertcratedatarow.createCell(8);
			ReportType.setCellValue("LCEnd");
			ReportType.setCellStyle(headerCellStyle);

			Cell ReportTimestamp = brokertcratedatarow.createCell(9);
			ReportTimestamp.setCellValue("LoadPort");
			ReportTimestamp.setCellStyle(headerCellStyle);

			Cell RepositionRegion = brokertcratedatarow.createCell(10);
			RepositionRegion.setCellValue("DiscPort");
			RepositionRegion.setCellStyle(headerCellStyle);

			Cell Source = brokertcratedatarow.createCell(11);
			Source.setCellValue("VesselName");
			Source.setCellStyle(headerCellStyle);

			Cell VesselName = brokertcratedatarow.createCell(12);
			VesselName.setCellValue("RateType");
			VesselName.setCellStyle(headerCellStyle);

			Cell Built = brokertcratedatarow.createCell(13);
			Built.setCellValue("Rate");
			Built.setCellStyle(headerCellStyle);

			Cell DWT = brokertcratedatarow.createCell(14);
			DWT.setCellValue("Comments");
			DWT.setCellStyle(headerCellStyle);

			Cell Cubics = brokertcratedatarow.createCell(15);
			Cubics.setCellValue("ReportDate");
			Cubics.setCellStyle(headerCellStyle);

			Cell LOA = brokertcratedatarow.createCell(16);
			LOA.setCellValue("Source");
			LOA.setCellStyle(headerCellStyle);

			Cell ICE = brokertcratedatarow.createCell(17);
			ICE.setCellValue("Vesseltype");
			ICE.setCellStyle(headerCellStyle);

			Cell ReportTimestampSpot = brokertcratedatarow.createCell(18);
			ReportTimestampSpot.setCellValue("ReportTimestamp");
			ReportTimestampSpot.setCellStyle(headerCellStyle);
			
		  	Cell emailUrl = brokertcratedatarow.createCell(19);
					emailUrl.setCellValue("emailUrl");
					emailUrl.setCellStyle(headerCellStyle); //SingleJson
					
					Cell SingleJson = brokertcratedatarow.createCell(20);
					SingleJson.setCellValue("SingleJson");
					SingleJson.setCellStyle(headerCellStyle);
			
			
			if (Tonnagejsonobj.has("spotList")) {
				JSONArray combinebrokerjsonarray = new JSONArray();

				JSONArray tonnageList = Tonnagejsonobj.getJSONArray("spotList");
				for (int i = 0; i < tonnageList.length(); i++) {
					JSONObject combinebrokerobj = new JSONObject();

					JSONObject BrokerTcRateList_inside_jsonobj = tonnageList.getJSONObject(i);

					String broker = "";
					if (BrokerTcRateList_inside_jsonobj.has("Information")) {
						broker = BrokerTcRateList_inside_jsonobj.getString("Information");
						combinebrokerobj.put("Informationobj", broker);
					}

					String vesseltype_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("FixtureType")) {
						vesseltype_value = BrokerTcRateList_inside_jsonobj.getString("FixtureType");
						combinebrokerobj.put("FixtureTypeobj", vesseltype_value);
					}

					String Month_Year_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("LCStart")) {
						Month_Year_value = BrokerTcRateList_inside_jsonobj.getString("LCStart");
						combinebrokerobj.put("LCStartobj", Month_Year_value);
					}

					String firstyr_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("LCEnd")) {
						firstyr_value = BrokerTcRateList_inside_jsonobj.getString("LCEnd");
						combinebrokerobj.put("LCEndobj", firstyr_value);
					}

					String firstyr_value1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("LoadPort")) {
						firstyr_value1 = BrokerTcRateList_inside_jsonobj.getString("LoadPort");
						combinebrokerobj.put("LoadPortobj", firstyr_value1);
					}

					String firstyr_value2 = "";
					if (BrokerTcRateList_inside_jsonobj.has("DiscPort")) {
						firstyr_value2 = BrokerTcRateList_inside_jsonobj.getString("DiscPort");
						combinebrokerobj.put("DiscPortobj", firstyr_value2);
					}

					String firstyr_value12 = "";
					if (BrokerTcRateList_inside_jsonobj.has("VesselName")) {
						firstyr_value12 = BrokerTcRateList_inside_jsonobj.getString("VesselName");
						combinebrokerobj.put("VesselNameobj", firstyr_value12);
					}

					String firstyr_value13 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Charterer")) {
						firstyr_value13 = BrokerTcRateList_inside_jsonobj.getString("Charterer");
						combinebrokerobj.put("Chartererobj", firstyr_value13);
					}

					String firstyr_value14 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Status")) {
						firstyr_value14 = BrokerTcRateList_inside_jsonobj.getString("Status");
						combinebrokerobj.put("Statusobj", firstyr_value14);
					}

					String firstyr_value15 = "";
					if (BrokerTcRateList_inside_jsonobj.has("CargoType")) {
						firstyr_value15 = BrokerTcRateList_inside_jsonobj.getString("CargoType");
						combinebrokerobj.put("CargoTypeobj", firstyr_value15);
					}

					String firstyr_value16 = "";
					if (BrokerTcRateList_inside_jsonobj.has("CargoGrade")) {
						firstyr_value16 = BrokerTcRateList_inside_jsonobj.getString("CargoGrade");
						combinebrokerobj.put("CargoGradeobj", firstyr_value16);
					}

					String firstyr_value17 = "";
					if (BrokerTcRateList_inside_jsonobj.has("CargoQty")) {
						firstyr_value17 = BrokerTcRateList_inside_jsonobj.getString("CargoQty");
						combinebrokerobj.put("CargoQtyobj", firstyr_value17);
					}

					String firstyr_value18 = "";
					if (BrokerTcRateList_inside_jsonobj.has("RateType")) {
						firstyr_value18 = BrokerTcRateList_inside_jsonobj.getString("RateType");
						combinebrokerobj.put("RateTypeobj", firstyr_value18);
					}

					String firstyr_value19 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Rate")) {
						firstyr_value19 = BrokerTcRateList_inside_jsonobj.getString("Rate");
						combinebrokerobj.put("Rateobj", firstyr_value19);
					}

					String firstyr_value20 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Comments")) {
						firstyr_value20 = BrokerTcRateList_inside_jsonobj.getString("Comments");
						combinebrokerobj.put("Commentsobj", firstyr_value20);
					}

					String firstyr_value8 = "";
					if (BrokerTcRateList_inside_jsonobj.has("ReportDate")) {
						firstyr_value8 = BrokerTcRateList_inside_jsonobj.getString("ReportDate");
						combinebrokerobj.put("ReportDateobj", firstyr_value8);
					}

					String firstyr_value9 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Source")) {
						firstyr_value9 = BrokerTcRateList_inside_jsonobj.getString("Source");
						combinebrokerobj.put("Sourceobj", firstyr_value9);
					}

					String firstyr_value7 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Vesseltype")) {
						firstyr_value7 = BrokerTcRateList_inside_jsonobj.getString("Vesseltype");
						combinebrokerobj.put("Vesseltypeobj", firstyr_value7);
					}
					String firstyr_value81 = "";
					if (BrokerTcRateList_inside_jsonobj.has("ReportTimestamp")) {
						firstyr_value81 = BrokerTcRateList_inside_jsonobj.getString("ReportTimestamp");
						combinebrokerobj.put("ReportTimestampobj", firstyr_value81);
					}
					
					String firstyr_value144 = "";
					if (BrokerTcRateList_inside_jsonobj.has("SingleJson")) {
						firstyr_value144 = BrokerTcRateList_inside_jsonobj.getString("SingleJson");
						combinebrokerobj.put("SingleJsonobj", firstyr_value144);
					}
					
					
					String emailUrl1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("emailUrl")) {
						emailUrl1 = BrokerTcRateList_inside_jsonobj.getString("emailUrl");
						combinebrokerobj.put("emailurlobj", emailUrl1);
					}
					

					if (combinebrokerobj.length() > 0) {
						combinebrokerjsonarray.put(combinebrokerobj);
					}

				} // main for

				if (combinebrokerjsonarray.length() > 0) {
					for (int j = 0; j < combinebrokerjsonarray.length(); j++) {
						JSONObject brokercombineobj = combinebrokerjsonarray.getJSONObject(j);

						String brokerobj = "";
						if (brokercombineobj.has("Informationobj")) {
							brokerobj = brokercombineobj.getString("Informationobj");
						}

						Row brokerrow = sheet.createRow((rowIndex + 1 + j));

						Cell broker_value = brokerrow.createCell(0);
						broker_value.setCellValue(brokerobj);

						String vesseltypeobj = "";
						if (brokercombineobj.has("FixtureTypeobj")) {
							vesseltypeobj = brokercombineobj.getString("FixtureTypeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(1);
						broker_value.setCellValue(vesseltypeobj);

						String vesseltypeobj12 = "";
						if (brokercombineobj.has("Chartererobj")) {
							vesseltypeobj12 = brokercombineobj.getString("Chartererobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(2);
						broker_value.setCellValue(vesseltypeobj12);

						String vesseltypeobj13 = "";
						if (brokercombineobj.has("Statusobj")) {
							vesseltypeobj13 = brokercombineobj.getString("Statusobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(3);
						broker_value.setCellValue(vesseltypeobj13);

						String vesseltypeobj14 = "";
						if (brokercombineobj.has("CargoTypeobj")) {
							vesseltypeobj14 = brokercombineobj.getString("CargoTypeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(4);
						broker_value.setCellValue(vesseltypeobj14);

						String vesseltypeobj15 = "";
						if (brokercombineobj.has("CargoGradeobj")) {
							vesseltypeobj15 = brokercombineobj.getString("CargoGradeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(5);
						broker_value.setCellValue(vesseltypeobj15);

						String vesseltypeobj16 = "";
						if (brokercombineobj.has("CargoQtyobj")) {
							vesseltypeobj16 = brokercombineobj.getString("CargoQtyobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(6);
						broker_value.setCellValue(vesseltypeobj16);

						String vesseltypeobj17 = "";
						if (brokercombineobj.has("LCStartobj")) {
							vesseltypeobj17 = brokercombineobj.getString("LCStartobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(7);
						broker_value.setCellValue(vesseltypeobj17);

						String vesseltypeobj18 = "";
						if (brokercombineobj.has("LCEndobj")) {
							vesseltypeobj18 = brokercombineobj.getString("LCEndobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(8);
						broker_value.setCellValue(vesseltypeobj18);

						String vesseltypeobj19 = "";
						if (brokercombineobj.has("LoadPortobj")) {
							vesseltypeobj19 = brokercombineobj.getString("LoadPortobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(9);
						broker_value.setCellValue(vesseltypeobj19);

						String vesseltypeobj20 = "";
						if (brokercombineobj.has("DiscPortobj")) {
							vesseltypeobj20 = brokercombineobj.getString("DiscPortobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(10);
						broker_value.setCellValue(vesseltypeobj20);

						String vesseltypeobj2 = "";
						if (brokercombineobj.has("VesselNameobj")) {
							vesseltypeobj2 = brokercombineobj.getString("VesselNameobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(11);
						broker_value.setCellValue(vesseltypeobj2);

						String vesseltypeobj3 = "";
						if (brokercombineobj.has("RateTypeobj")) {
							vesseltypeobj3 = brokercombineobj.getString("RateTypeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(12);
						broker_value.setCellValue(vesseltypeobj3);

						String vesseltypeobj4 = "";
						if (brokercombineobj.has("Rateobj")) {
							vesseltypeobj4 = brokercombineobj.getString("Rateobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(13);
						broker_value.setCellValue(vesseltypeobj4);

						String vesseltypeobj5 = "";
						if (brokercombineobj.has("Commentsobj")) {
							vesseltypeobj5 = brokercombineobj.getString("Commentsobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(14);
						broker_value.setCellValue(vesseltypeobj5);

						String vesseltypeobj6 = "";
						if (brokercombineobj.has("ReportDateobj")) {
							vesseltypeobj6 = brokercombineobj.getString("ReportDateobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(15);
						broker_value.setCellValue(vesseltypeobj6);

						String vesseltypeobj7 = "";
						if (brokercombineobj.has("Sourceobj")) {
							vesseltypeobj7 = brokercombineobj.getString("Sourceobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(16);
						broker_value.setCellValue(vesseltypeobj7);

						String vesseltypeobj8 = "";
						if (brokercombineobj.has("Vesseltypeobj")) {
							vesseltypeobj8 = brokercombineobj.getString("Vesseltypeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(17);
						broker_value.setCellValue(vesseltypeobj8);
						
						String vesseltypeobj9 = "";
						if (brokercombineobj.has("ReportTimestampobj")) {
							vesseltypeobj9 = brokercombineobj.getString("ReportTimestampobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(18);
						broker_value.setCellValue(vesseltypeobj9);
						
						
						String vesseltypeobj1712 = "";
						if (brokercombineobj.has("emailurlobj")) {
							vesseltypeobj1712 = brokercombineobj.getString("emailurlobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(19);
						broker_value.setCellValue(vesseltypeobj1712);

						String vesseltypeobj1713 = "";
						if (brokercombineobj.has("SingleJsonobj")) {
							vesseltypeobj1713 = brokercombineobj.getString("SingleJsonobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(20);
						broker_value.setCellValue(vesseltypeobj1713);

					}
				} //

			} // main if

			Calendar cal = null;
			cal = Calendar.getInstance();
			Date minDate = new Date(cal.getTimeInMillis());

			JSONObject dateJson = new JSONObject();
			Date date = GmailMethods.parseDate(minDate.toString());
			dateJson = SlingMethods.formatDate_Month_Year_Day(date, out);

			int day = dateJson.getInt("day");
			int month = dateJson.getInt("month");
			int year = dateJson.getInt("year");

			String fileName = "Spot" + "Data" + "_" + day + "-" + month + "-" + year + ".xls";
			fileName = fileName.replace(" ", "-");

			File linuxdirectory = new File("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpioexcel");
			if (!linuxdirectory.exists()) {

				linuxdirectory.mkdir();
				out.println("folder created in linux");
			}

			// Write the output to a file
			// FileOutputStream fileOut = new
			// FileOutputStream("D:\\Scorpio\\Email\\"+fileName.trim());
			FileOutputStream fileOut = new FileOutputStream(
					"/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpioexcel/" + fileName.trim());
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();

			final_obj = new JSONObject();
			final_obj.put("fileName", fileName.trim());
			final_obj.put("fileUrl", "https://dev.bizlem.io:8082/scorpioexcel/" + fileName.trim());
			final_obj.put("status", "S");
		} catch (Exception e) {
			return "{\"status\":\"E\",\"Message\" : " + e.getMessage() + "}";
		}
		return final_obj.toString();
	}
	
	

}

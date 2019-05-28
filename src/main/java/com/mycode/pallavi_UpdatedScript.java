package com.mycode;

import java.io.BufferedReader;


import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.pallavi.code.pallavi_ConvertDate;
import com.pallavi.code.pallavi_ConvertDatewithPattern;
import com.pallavi.code.pallavi_SortJSON;
import com.reportinformationsystem.SaveReportDataClass;

public class pallavi_UpdatedScript {
	/**
	 * @param args
	 * @throws JSONException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws JSONException   {
	
		System.out.println("start");
		
JSONObject returnobj=null;
JSONObject obj=null;
JSONObject Tableobj=null;
JSONObject Tableobj_new=null;

JSONArray table_dataobj=null;
JSONArray table_dataobj_new=null;
JSONArray header_dataobj =null;
JSONArray header_dataobj_new =null;

JSONObject eachrowobj=null;
JSONObject eachrowobj_new=null;
JSONObject data=null;
JSONObject header=null;
JSONObject new_data=null;
JSONObject new_header=null;
JSONObject objheader=null;


  String pythonScriptApiData="{\"table_0\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CLEAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"LR2\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SIKKA\"}],\"line_index\":0,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Source\",\"word\":\"Simpson\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":1,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SIKKA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"LR2\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CLEAN\"}],\"line_index\":4,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"FLEET\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Current_Status\",\"word\":\"open\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"last cgo\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SIKKA\"}],\"line_index\":6,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"GALLE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"NAVIG8 PRIDE LHJ\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"FUJ\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"FUJ\"}],\"line_index\":8,\"number_of_words\":5},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"NAVIG8 PROVIDENCE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"vitol\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"fujairah\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"CPP\"}],\"line_index\":9,\"number_of_words\":5},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI ALEXIS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"COLOMBO\"}],\"line_index\":10,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"FRONT COUGAR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":11,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI OXFORD\"}],\"line_index\":12,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"FUJAIRAH\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"LADY HENRIETTA\"}],\"line_index\":13,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JEBEL ALI\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ELKA ATHINA\"}],\"line_index\":14,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"FUJAIRAH\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"FRONT TIGER\"}],\"line_index\":15,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI WINNIE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"fujairah\"}],\"line_index\":16,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI PARK\"}],\"line_index\":17,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI SANCTITY\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"DAESAN\"}],\"line_index\":18,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"FRONT ALTAIR\"}],\"line_index\":19,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"CAPE TOWN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI SUPREME\"}],\"line_index\":21,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"PACIFIC RAWAN\"}],\"line_index\":22,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"HEIDMAR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"JAG LOKESH\"}],\"line_index\":23,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"sohar\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"}],\"line_index\":24,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"PASIR GUDANG\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"HEIDMAR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"PHOENIX HOPE\"}],\"line_index\":25,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI SELATAR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ULSD\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"DAR ES SALAAM\"}],\"line_index\":26,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"TORM VALBORG\"}],\"line_index\":27,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"KARIMUN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ULSD\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ABU DHABI III\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"ADNOC\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"ABU DHABI\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"FUJ\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"FUJ\"}],\"line_index\":28,\"number_of_words\":7},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"st shipping\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CHRYSANTHEMUM\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":29,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"TORM HERMIA\"}],\"line_index\":30,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"HUIZHOU\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"NAVIG8 PRECISION\"}],\"line_index\":31,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"MOMBASA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI GRACE\"}],\"line_index\":32,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ULSD+JET\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"FALCON EXPRESS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"st shipping\"}],\"line_index\":33,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"LAGOS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SANTIAGO\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"TRAFIGURA\"}],\"line_index\":34,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"BP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"BRITISH RESTRAINT\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JORF LASFAR\"}],\"line_index\":35,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"TOKUYAMA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI STABILITY\"}],\"line_index\":36,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI GRATITUDE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"MELBOURNE\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"FUJ\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"FUJ\"}],\"line_index\":37,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"GIBRALTAR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"CARIBS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"TORM HILDE\"}],\"line_index\":38,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"HUA LIN WAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"YOSU\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"COSCO\"}],\"line_index\":39,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"FUJ\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ULSD+JET\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"BOTANY BAY\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"FUJ\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"Maersk\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"MAERSK PIPER\"}],\"line_index\":40,\"number_of_words\":6},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"total\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"NISSOS HERACLEA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"YOSU\"}],\"line_index\":42,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI SOLIDARITY\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"CHITA\"}],\"line_index\":43,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"TORM HELENE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"LUANDA\"}],\"line_index\":44,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SKS DODA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"DAESAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ULSD\"}],\"line_index\":45,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"SUBS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SEA BEAUTY\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"FALMOUTH\"}],\"line_index\":46,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"BARCELONA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"FRONT CHEETAH\"}],\"line_index\":47,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"rotterdam\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"}],\"line_index\":48,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"GULEI\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"TRAFIGURA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"MARLIN LILY\"}],\"line_index\":49,\"number_of_words\":5},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"LOME\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"breeze\"}],\"line_index\":50,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"DUNKIRK\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"}],\"line_index\":51,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CLIO\"}],\"line_index\":52,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"ST SHIPPING\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SEAENVOY\"}],\"line_index\":53,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"DAESAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SEA LEGEND\"}],\"line_index\":54,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"DAKAR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ELKA VASSILIKI\"}],\"line_index\":55,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"LA PALLICE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"total\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ARETEA\"}],\"line_index\":56,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"CARDIFF\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"}],\"line_index\":57,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"UMS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ELKA ARISTOTLE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"LAGOS\"}],\"line_index\":58,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"Maersk\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"MAERSK PHOENIX\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"}],\"line_index\":59,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"FUJAIRAH\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"ELETSON\"}],\"line_index\":60,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"MINERVA ARIES\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"KAOHSIUNG\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"}],\"line_index\":61,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI LAUREN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"YOSU\"}],\"line_index\":62,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"MAERSK PELICAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"CHIBA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"Maersk\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"}],\"line_index\":63,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"ELETSON\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"GIBRALTAR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"FLORIDA\"}],\"line_index\":64,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"UMS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"OITA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"OCEAN ODYSSEY\"}],\"line_index\":65,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ULSD\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"BOTANY BAY\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SEA STAR\"}],\"line_index\":66,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"vitol\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"UKC\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI GALLANTRY\"}],\"line_index\":67,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"FS ENDEAVOR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":68,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI MADISON\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"BOTANY BAY\"}],\"line_index\":69,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"MARLIN LAVENDER\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"LOME\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"TRAFIGURA\"}],\"line_index\":70,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"ARA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"AG NEPTUNE\"}],\"line_index\":71,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"BATAAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ULSD\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ENCELIA\"}],\"line_index\":72,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"KALUNDBORG\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SKS DEE\"}],\"line_index\":73,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"TRAFIGURA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"DESPINA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"MILFORD HAVEN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"}],\"line_index\":74,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SWARNA KAMAL\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":75,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"UST LUGA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"}],\"line_index\":76,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"TONG LIN WAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"COSCO\"}],\"line_index\":77,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"TAURUS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"DAESAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"PROSTAR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"}],\"line_index\":78,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET + ULSD\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SERIANA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"BOTANY BAY\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"TRAFIGURA\"}],\"line_index\":79,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CHAMPION PRINCESS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"CHIBA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"NYK\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"}],\"line_index\":80,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"TORM MAREN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET + ULSD\"}],\"line_index\":81,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"YANG LI HU\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"HEIDMAR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"YOSU\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"}],\"line_index\":82,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SEA SHELL\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"LUANDA\"}],\"line_index\":84,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"VAN PHONG 1\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"LAGOS\"}],\"line_index\":85,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STAR ENERGY\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"IRELAND\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"}],\"line_index\":86,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ULSD\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"USG\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"BOW PIONEER\"}],\"line_index\":87,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"Maersk\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"LOME\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"POLARIS\"}],\"line_index\":88,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"FPMC P IDEAL\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SAKAI\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"st shipping\"}],\"line_index\":89,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"HEIDMAR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"NORDDOLPHIN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"ARA\"}],\"line_index\":90,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"vitol\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"DAESAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"THETIS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"}],\"line_index\":91,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"rotterdam\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"MOON\"}],\"line_index\":92,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SHANGHAI\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SEA JEWEL\"}],\"line_index\":93,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"ROTTERDAM\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"LUZON SPIRIT\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"TAURUS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"}],\"line_index\":94,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SWARNA JAYANTI\"}],\"line_index\":95,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"PRO TRIUMPH\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"YOKKAICHI\"}],\"line_index\":96,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"DALIAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ZALIV VOSTOK\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"}],\"line_index\":97,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"HANOVER SQUARE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"HUIZHOU\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"}],\"line_index\":98,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"RONG LIN WAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":99,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ALBURAQ\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"UKC\"}],\"line_index\":100,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"MEI LIN WAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"OITA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"COSCO\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"}],\"line_index\":101,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JAPAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI GAUNTLET\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"}],\"line_index\":103,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"TAO LIN WAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"COSCO\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"CHIBA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"}],\"line_index\":104,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"NYK\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"BRISBANE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CHAMPION PRINCE\"}],\"line_index\":105,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"PORTMAN SQUARE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"YOSU\"}],\"line_index\":106,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"NANTONG\"}],\"line_index\":107,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ALDANA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"ANTWERP\"}],\"line_index\":108,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SANMAR SANGEET\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"Maersk\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JAPAN\"}],\"line_index\":109,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"vitol\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI ROSE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JAPAN\"}],\"line_index\":110,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"TAURUS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"BRISBANE\"}],\"line_index\":111,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"NANTONG\"}],\"line_index\":112,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"MARE NOSTRUM\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"TAURUS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JAPAN\"}],\"line_index\":113,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STELLATA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JAPAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"TRAFIGURA\"}],\"line_index\":114,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JAPAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"ST SHIPPING\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ALPINE AQUALINA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"}],\"line_index\":115,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"OCEAN QUEST\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JAPAN\"}],\"line_index\":116,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"HAIMA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"JET\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"ARA\"}],\"line_index\":117,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"UKC\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"CPP\"}],\"line_index\":118,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"APOLLON\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"KOREA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"CLEARLAKE\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"KOREA\"}],\"line_index\":119,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"MOBILE\"}],\"line_index\":122,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"ICE\"}],\"line_index\":123,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"Neo\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":124,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"MOBILE\"}],\"line_index\":125,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"ICE\"}],\"line_index\":126,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CHRIS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":127,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"MOBILE\"}],\"line_index\":128,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"ICE\"}],\"line_index\":129,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":130,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"MOBILE\"}],\"line_index\":131,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"ICE\"}],\"line_index\":132,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":133,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"MOBILE\"}],\"line_index\":134,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"ICE\"}],\"line_index\":135,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Source\",\"word\":\"Simpson\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":136,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":138,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":139,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Source\",\"word\":\"Simpson\"}],\"line_index\":144,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Source\",\"word\":\"Simpson\"}],\"line_index\":145,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Source\",\"word\":\"Simpson\"}],\"line_index\":146,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Source\",\"word\":\"Simpson\"}],\"line_index\":147,\"number_of_words\":1}],\"table_data\":[{\"StructureType\":\"line\",\"data\":{\"0\":\"   SSY_CLEAN_LR2_LIST_BASIS_SIKKA(21ST_MAY_30TH_JUNE)   \"},\"header\":{\"0\":\"   SSY_CLEAN_LR2_LIST_BASIS_SIKKA(21ST_MAY_30TH_JUNE)   \"},\"label_line_index\":0,\"label_string\":\"   SSY_CLEAN_LR2_LIST_BASIS_SIKKA(21ST_MAY_30TH_JUNE)   \",\"line_index\":0},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SIMPSON SPENCE YOUNG(SINGAPORE)      \"},\"header\":{\"0\":\"   SSY_CLEAN_LR2_LIST_BASIS_SIKKA(21ST_MAY_30TH_JUNE)   \"},\"label_line_index\":0,\"label_string\":\"   SSY_CLEAN_LR2_LIST_BASIS_SIKKA(21ST_MAY_30TH_JUNE)   \",\"line_index\":1},{\"StructureType\":\"line\",\"data\":{\"0\":\"       CLEAN LR2 LIST BASIS SIKKA      \"},\"header\":{\"0\":\"   SSY_CLEAN_LR2_LIST_BASIS_SIKKA(21ST_MAY_30TH_JUNE)   \"},\"label_line_index\":0,\"label_string\":\"   SSY_CLEAN_LR2_LIST_BASIS_SIKKA(21ST_MAY_30TH_JUNE)   \",\"line_index\":4},{\"StructureType\":\"line\",\"data\":{\"0\":\"       VESSEL             SIKKA DWT CBM LOA YR PORT          OPEN     FLEET        LAST CGOS                      COMMENT      \"},\"header\":{\"0\":\"       VESSEL             SIKKA DWT CBM LOA YR PORT          OPEN     FLEET        LAST CGOS                      COMMENT      \"},\"label_line_index\":6,\"label_string\":\"       VESSEL             SIKKA DWT CBM LOA YR PORT          OPEN     FLEET        LAST CGOS                      COMMENT      \",\"line_index\":6},{\"StructureType\":\"line\",\"data\":{\"0\":\"       NAVIG8 PRIDE LHJ   23/05 109 123 250 18 GALLE         19/05    NAVIG8       ULSD/NAP/NAP/NAP/NB            SLD FUJ26/MAY      \"},\"header\":{\"0\":\"       NAVIG8 PRIDE LHJ   23/05 109 123 250 18 GALLE         19/05    NAVIG8       ULSD/NAP/NAP/NAP/NB            SLD FUJ26/MAY      \"},\"label_line_index\":8,\"label_string\":\"       NAVIG8 PRIDE LHJ   23/05 109 123 250 18 GALLE         19/05    NAVIG8       ULSD/NAP/NAP/NAP/NB            SLD FUJ26/MAY      \",\"line_index\":8},{\"StructureType\":\"line\",\"data\":{\"0\":\"       NAVIG8 PROVIDENCE  24/05 109 123 250 18 FUJAIRAH      21/05    VITOL        CPP/NB                         SUB      \"},\"header\":{\"0\":\"       NAVIG8 PROVIDENCE  24/05 109 123 250 18 FUJAIRAH      21/05    VITOL        CPP/NB                         SUB      \"},\"label_line_index\":9,\"label_string\":\"       NAVIG8 PROVIDENCE  24/05 109 123 250 18 FUJAIRAH      21/05    VITOL        CPP/NB                         SUB      \",\"line_index\":9},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI ALEXIS         25/05 109 125 256 15 COLOMBO       21/05    SCORPIO      ULSD                           SLD FUJ27/MAY      \"},\"header\":{\"0\":\"       STI ALEXIS         25/05 109 125 256 15 COLOMBO       21/05    SCORPIO      ULSD                           SLD FUJ27/MAY      \"},\"label_line_index\":10,\"label_string\":\"       STI ALEXIS         25/05 109 125 256 15 COLOMBO       21/05    SCORPIO      ULSD                           SLD FUJ27/MAY      \",\"line_index\":10},{\"StructureType\":\"line\",\"data\":{\"0\":\"       FRONT COUGAR       25/05 109 120 250 16 SINGAPORE     16/05    FRONTLINE    CPP/ULSD/GO/CPP/ULSD           UNC      \"},\"header\":{\"0\":\"       FRONT COUGAR       25/05 109 120 250 16 SINGAPORE     16/05    FRONTLINE    CPP/ULSD/GO/CPP/ULSD           UNC      \"},\"label_line_index\":11,\"label_string\":\"       FRONT COUGAR       25/05 109 120 250 16 SINGAPORE     16/05    FRONTLINE    CPP/ULSD/GO/CPP/ULSD           UNC      \",\"line_index\":11},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI OXFORD         25/05 109 132 256 15 SINGAPORE     16/05    SCORPIO      NAP/NAP                        SUB SLD AG28/MAY      \"},\"header\":{\"0\":\"       STI OXFORD         25/05 109 132 256 15 SINGAPORE     16/05    SCORPIO      NAP/NAP                        SUB SLD AG28/MAY      \"},\"label_line_index\":12,\"label_string\":\"       STI OXFORD         25/05 109 132 256 15 SINGAPORE     16/05    SCORPIO      NAP/NAP                        SUB SLD AG28/MAY      \",\"line_index\":12},{\"StructureType\":\"line\",\"data\":{\"0\":\"       LADY HENRIETTA     28/05 111 123 250 12 FUJAIRAH      25/05    NAVIG8       UMS/NAP                        BLST      \"},\"header\":{\"0\":\"       LADY HENRIETTA     28/05 111 123 250 12 FUJAIRAH      25/05    NAVIG8       UMS/NAP                        BLST      \"},\"label_line_index\":13,\"label_string\":\"       LADY HENRIETTA     28/05 111 123 250 12 FUJAIRAH      25/05    NAVIG8       UMS/NAP                        BLST      \",\"line_index\":13},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ELKA ATHINA        28/05 99  120 244 04 JEBEL ALI     25/05    ELKA         COND/ULSD/UMS                  SUB      \"},\"header\":{\"0\":\"       ELKA ATHINA        28/05 99  120 244 04 JEBEL ALI     25/05    ELKA         COND/ULSD/UMS                  SUB      \"},\"label_line_index\":14,\"label_string\":\"       ELKA ATHINA        28/05 99  120 244 04 JEBEL ALI     25/05    ELKA         COND/ULSD/UMS                  SUB      \",\"line_index\":14},{\"StructureType\":\"line\",\"data\":{\"0\":\"       FRONT TIGER        30/05 115 123 250 15 FUJAIRAH      27/05    FRONTLINE    NAP                            SUB IN BALLAST      \"},\"header\":{\"0\":\"       FRONT TIGER        30/05 115 123 250 15 FUJAIRAH      27/05    FRONTLINE    NAP                            SUB IN BALLAST      \"},\"label_line_index\":15,\"label_string\":\"       FRONT TIGER        30/05 115 123 250 15 FUJAIRAH      27/05    FRONTLINE    NAP                            SUB IN BALLAST      \",\"line_index\":15},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI WINNIE         30/05 109 129 256 15 FUJAIRAH      27/05    SCORPIO      CPP/UMS                        SUB      \"},\"header\":{\"0\":\"       STI WINNIE         30/05 109 129 256 15 FUJAIRAH      27/05    SCORPIO      CPP/UMS                        SUB      \"},\"label_line_index\":16,\"label_string\":\"       STI WINNIE         30/05 109 129 256 15 FUJAIRAH      27/05    SCORPIO      CPP/UMS                        SUB      \",\"line_index\":16},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI PARK           31/05 109 132 256 14 SINGAPORE     22/05    SCORPIO      GO/ULSD                        IF PROJ TBC      \"},\"header\":{\"0\":\"       STI PARK           31/05 109 132 256 14 SINGAPORE     22/05    SCORPIO      GO/ULSD                        IF PROJ TBC      \"},\"label_line_index\":17,\"label_string\":\"       STI PARK           31/05 109 132 256 14 SINGAPORE     22/05    SCORPIO      GO/ULSD                        IF PROJ TBC      \",\"line_index\":17},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI SANCTITY       31/05 109 123 250 16 DAESAN        13/05    SCORPIO      NAP/COND/JET/GO                SLD SING21/MAY      \"},\"header\":{\"0\":\"       STI SANCTITY       31/05 109 123 250 16 DAESAN        13/05    SCORPIO      NAP/COND/JET/GO                SLD SING21/MAY      \"},\"label_line_index\":18,\"label_string\":\"       STI SANCTITY       31/05 109 123 250 16 DAESAN        13/05    SCORPIO      NAP/COND/JET/GO                SLD SING21/MAY      \",\"line_index\":18},{\"StructureType\":\"line\",\"data\":{\"0\":\"       FRONT ALTAIR       31/05 109 123 252 16 SINGAPORE     22/05    FRONTLINE    NAP/ULSD/ULSD/GO/GO            IN BALLAST      \"},\"header\":{\"0\":\"       FRONT ALTAIR       31/05 109 123 252 16 SINGAPORE     22/05    FRONTLINE    NAP/ULSD/ULSD/GO/GO            IN BALLAST      \"},\"label_line_index\":19,\"label_string\":\"       FRONT ALTAIR       31/05 109 123 252 16 SINGAPORE     22/05    FRONTLINE    NAP/ULSD/ULSD/GO/GO            IN BALLAST      \",\"line_index\":19},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI SUPREME        01/06 109 123 250 16 CAPE TOWN     16/05    SCORPIO                                     SLD FUJ01/JUN      \"},\"header\":{\"0\":\"       STI SUPREME        01/06 109 123 250 16 CAPE TOWN     16/05    SCORPIO                                     SLD FUJ01/JUN      \"},\"label_line_index\":21,\"label_string\":\"       STI SUPREME        01/06 109 123 250 16 CAPE TOWN     16/05    SCORPIO                                     SLD FUJ01/JUN      \",\"line_index\":21},{\"StructureType\":\"line\",\"data\":{\"0\":\"       PACIFIC RAWAN      02/06 109 124 256 17 SINGAPORE     24/05    SHELL        ULSD/UMS      \"},\"header\":{\"0\":\"       PACIFIC RAWAN      02/06 109 124 256 17 SINGAPORE     24/05    SHELL        ULSD/UMS      \"},\"label_line_index\":22,\"label_string\":\"       PACIFIC RAWAN      02/06 109 124 256 17 SINGAPORE     24/05    SHELL        ULSD/UMS      \",\"line_index\":22},{\"StructureType\":\"line\",\"data\":{\"0\":\"       JAG LOKESH         02/06 105 115 244 09 SINGAPORE     24/05    HEIDMAR      XDD/NAP/NAP/CCOND              SLD XDD      \"},\"header\":{\"0\":\"       JAG LOKESH         02/06 105 115 244 09 SINGAPORE     24/05    HEIDMAR      XDD/NAP/NAP/CCOND              SLD XDD      \"},\"label_line_index\":23,\"label_string\":\"       JAG LOKESH         02/06 105 115 244 09 SINGAPORE     24/05    HEIDMAR      XDD/NAP/NAP/CCOND              SLD XDD      \",\"line_index\":23},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SABETTA            02/06 109 123 250 17 SOHAR         30/05    NOVATEK      NAP/ULSD/ULSD/GO+JET      \"},\"header\":{\"0\":\"       SABETTA            02/06 109 123 250 17 SOHAR         30/05    NOVATEK      NAP/ULSD/ULSD/GO+JET      \"},\"label_line_index\":24,\"label_string\":\"       SABETTA            02/06 109 123 250 17 SOHAR         30/05    NOVATEK      NAP/ULSD/ULSD/GO+JET      \",\"line_index\":24},{\"StructureType\":\"line\",\"data\":{\"0\":\"       PHOENIX HOPE       04/06 105 115 244 08 PASIR GUDANG  26/05    HEIDMAR      NAP/NAP/NAP      \"},\"header\":{\"0\":\"       PHOENIX HOPE       04/06 105 115 244 08 PASIR GUDANG  26/05    HEIDMAR      NAP/NAP/NAP      \"},\"label_line_index\":25,\"label_string\":\"       PHOENIX HOPE       04/06 105 115 244 08 PASIR GUDANG  26/05    HEIDMAR      NAP/NAP/NAP      \",\"line_index\":25},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI SELATAR        04/06 109 126 256 17 DAR ES SALAAM 27/05    SCORPIO      GO/ULSD      \"},\"header\":{\"0\":\"       STI SELATAR        04/06 109 126 256 17 DAR ES SALAAM 27/05    SCORPIO      GO/ULSD      \"},\"label_line_index\":26,\"label_string\":\"       STI SELATAR        04/06 109 126 256 17 DAR ES SALAAM 27/05    SCORPIO      GO/ULSD      \",\"line_index\":26},{\"StructureType\":\"line\",\"data\":{\"0\":\"       TORM VALBORG       05/06 99  115 244 03 SINGAPORE     27/05    TORM         COND/COND/COND      \"},\"header\":{\"0\":\"       TORM VALBORG       05/06 99  115 244 03 SINGAPORE     27/05    TORM         COND/COND/COND      \"},\"label_line_index\":27,\"label_string\":\"       TORM VALBORG       05/06 99  115 244 03 SINGAPORE     27/05    TORM         COND/COND/COND      \",\"line_index\":27},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ABU DHABI-III      06/06 105 114 244 11 KARIMUN       28/05    ADNOC        ULSD/NAP/NAP/NAP               FUJ10/JUN      \"},\"header\":{\"0\":\"       ABU DHABI-III      06/06 105 114 244 11 KARIMUN       28/05    ADNOC        ULSD/NAP/NAP/NAP               FUJ10/JUN      \"},\"label_line_index\":28,\"label_string\":\"       ABU DHABI-III      06/06 105 114 244 11 KARIMUN       28/05    ADNOC        ULSD/NAP/NAP/NAP               FUJ10/JUN      \",\"line_index\":28},{\"StructureType\":\"line\",\"data\":{\"0\":\"       CHRYSANTHEMUM      06/06 105 114 244 09 SINGAPORE     28/05    ST SHIPPING  UMS/NAP/ULSD/JET/EX DD         IF PROJ TBC      \"},\"header\":{\"0\":\"       CHRYSANTHEMUM      06/06 105 114 244 09 SINGAPORE     28/05    ST SHIPPING  UMS/NAP/ULSD/JET/EX DD         IF PROJ TBC      \"},\"label_line_index\":29,\"label_string\":\"       CHRYSANTHEMUM      06/06 105 114 244 09 SINGAPORE     28/05    ST SHIPPING  UMS/NAP/ULSD/JET/EX DD         IF PROJ TBC      \",\"line_index\":29},{\"StructureType\":\"line\",\"data\":{\"0\":\"       TORM HERMIA        06/06 114 121 250 18 SINGAPORE     28/05    TORM         UMS+REF/NAP/ULSD/NAP      \"},\"header\":{\"0\":\"       TORM HERMIA        06/06 114 121 250 18 SINGAPORE     28/05    TORM         UMS+REF/NAP/ULSD/NAP      \"},\"label_line_index\":30,\"label_string\":\"       TORM HERMIA        06/06 114 121 250 18 SINGAPORE     28/05    TORM         UMS+REF/NAP/ULSD/NAP      \",\"line_index\":30},{\"StructureType\":\"line\",\"data\":{\"0\":\"       NAVIG8 PRECISION   06/06 109 123 250 18 HUIZHOU       23/05    NAVIG8       NAP      \"},\"header\":{\"0\":\"       NAVIG8 PRECISION   06/06 109 123 250 18 HUIZHOU       23/05    NAVIG8       NAP      \"},\"label_line_index\":31,\"label_string\":\"       NAVIG8 PRECISION   06/06 109 123 250 18 HUIZHOU       23/05    NAVIG8       NAP      \",\"line_index\":31},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI GRACE          06/06 109 125 256 16 MOMBASA       29/05    SCORPIO      CPP/NAP/ULSD                   FUJ08/JUN      \"},\"header\":{\"0\":\"       STI GRACE          06/06 109 125 256 16 MOMBASA       29/05    SCORPIO      CPP/NAP/ULSD                   FUJ08/JUN      \"},\"label_line_index\":32,\"label_string\":\"       STI GRACE          06/06 109 125 256 16 MOMBASA       29/05    SCORPIO      CPP/NAP/ULSD                   FUJ08/JUN      \",\"line_index\":32},{\"StructureType\":\"line\",\"data\":{\"0\":\"       FALCON EXPRESS     08/06 115 119 244 08 SHUIDONG      25/05    ST SHIPPING  COND/JET+ULSD/GO+JET/NAP      \"},\"header\":{\"0\":\"       FALCON EXPRESS     08/06 115 119 244 08 SHUIDONG      25/05    ST SHIPPING  COND/JET+ULSD/GO+JET/NAP      \"},\"label_line_index\":33,\"label_string\":\"       FALCON EXPRESS     08/06 115 119 244 08 SHUIDONG      25/05    ST SHIPPING  COND/JET+ULSD/GO+JET/NAP      \",\"line_index\":33},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MARLIN SANTIAGO    08/06 149 164 275 19 LAGOS         15/05    TRAFIGURA    GO/NB      \"},\"header\":{\"0\":\"       MARLIN SANTIAGO    08/06 149 164 275 19 LAGOS         15/05    TRAFIGURA    GO/NB      \"},\"label_line_index\":34,\"label_string\":\"       MARLIN SANTIAGO    08/06 149 164 275 19 LAGOS         15/05    TRAFIGURA    GO/NB      \",\"line_index\":34},{\"StructureType\":\"line\",\"data\":{\"0\":\"       BRITISH RESTRAINT  09/06 114 118 250 17 JORF LASFAR   22/05    BP           GO/COND/COND                   SUB EX YANBU      \"},\"header\":{\"0\":\"       BRITISH RESTRAINT  09/06 114 118 250 17 JORF LASFAR   22/05    BP           GO/COND/COND                   SUB EX YANBU      \"},\"label_line_index\":35,\"label_string\":\"       BRITISH RESTRAINT  09/06 114 118 250 17 JORF LASFAR   22/05    BP           GO/COND/COND                   SUB EX YANBU      \",\"line_index\":35},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI STABILITY      09/06 109 123 250 16 TOKUYAMA      22/05    SCORPIO      NAP/GO/GO      \"},\"header\":{\"0\":\"       STI STABILITY      09/06 109 123 250 16 TOKUYAMA      22/05    SCORPIO      NAP/GO/GO      \"},\"label_line_index\":36,\"label_string\":\"       STI STABILITY      09/06 109 123 250 16 TOKUYAMA      22/05    SCORPIO      NAP/GO/GO      \",\"line_index\":36},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI GRATITUDE      09/06 109 120 250 17 MELBOURNE     20/05    SCORPIO                                     SLD FUJ12/JUN      \"},\"header\":{\"0\":\"       STI GRATITUDE      09/06 109 120 250 17 MELBOURNE     20/05    SCORPIO                                     SLD FUJ12/JUN      \"},\"label_line_index\":37,\"label_string\":\"       STI GRATITUDE      09/06 109 120 250 17 MELBOURNE     20/05    SCORPIO                                     SLD FUJ12/JUN      \",\"line_index\":37},{\"StructureType\":\"line\",\"data\":{\"0\":\"       TORM HILDE         09/06 114 121 250 18 GIBRALTAR     23/05    TORM         NAP/ULSD/NAP                   SUBS IN BLST EX CARIBS      \"},\"header\":{\"0\":\"       TORM HILDE         09/06 114 121 250 18 GIBRALTAR     23/05    TORM         NAP/ULSD/NAP                   SUBS IN BLST EX CARIBS      \"},\"label_line_index\":38,\"label_string\":\"       TORM HILDE         09/06 114 121 250 18 GIBRALTAR     23/05    TORM         NAP/ULSD/NAP                   SUBS IN BLST EX CARIBS      \",\"line_index\":38},{\"StructureType\":\"line\",\"data\":{\"0\":\"       HUA LIN WAN        09/06 109 118 245 12 YOSU          22/05    COSCO        NAP/ULSD/ULSD      \"},\"header\":{\"0\":\"       HUA LIN WAN        09/06 109 118 245 12 YOSU          22/05    COSCO        NAP/ULSD/ULSD      \"},\"label_line_index\":39,\"label_string\":\"       HUA LIN WAN        09/06 109 118 245 12 YOSU          22/05    COSCO        NAP/ULSD/ULSD      \",\"line_index\":39},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MAERSK PIPER       10/06 109 117 245 08 BOTANY BAY    20/05    MAERSK       ULSD+JET/NAP/ULSD              FUJ12/JUN      \"},\"header\":{\"0\":\"       MAERSK PIPER       10/06 109 117 245 08 BOTANY BAY    20/05    MAERSK       ULSD+JET/NAP/ULSD              FUJ12/JUN      \"},\"label_line_index\":40,\"label_string\":\"       MAERSK PIPER       10/06 109 117 245 08 BOTANY BAY    20/05    MAERSK       ULSD+JET/NAP/ULSD              FUJ12/JUN      \",\"line_index\":40},{\"StructureType\":\"line\",\"data\":{\"0\":\"       NISSOS HERACLEA    11/06 114 125 252 15 YOSU          24/05    TOTAL        NAP/ULSD/CCOND      \"},\"header\":{\"0\":\"       NISSOS HERACLEA    11/06 114 125 252 15 YOSU          24/05    TOTAL        NAP/ULSD/CCOND      \"},\"label_line_index\":42,\"label_string\":\"       NISSOS HERACLEA    11/06 114 125 252 15 YOSU          24/05    TOTAL        NAP/ULSD/CCOND      \",\"line_index\":42},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI SOLIDARITY     11/06 109 123 250 15 CHITA         23/05    SCORPIO      ULSD                           SUB      \"},\"header\":{\"0\":\"       STI SOLIDARITY     11/06 109 123 250 15 CHITA         23/05    SCORPIO      ULSD                           SUB      \"},\"label_line_index\":43,\"label_string\":\"       STI SOLIDARITY     11/06 109 123 250 15 CHITA         23/05    SCORPIO      ULSD                           SUB      \",\"line_index\":43},{\"StructureType\":\"line\",\"data\":{\"0\":\"       TORM HELENE        11/06 99  115 244 97 LUANDA        21/05    TORM         UMS/CCOND/CCOND                IN BLST TO AG      \"},\"header\":{\"0\":\"       TORM HELENE        11/06 99  115 244 97 LUANDA        21/05    TORM         UMS/CCOND/CCOND                IN BLST TO AG      \"},\"label_line_index\":44,\"label_string\":\"       TORM HELENE        11/06 99  115 244 97 LUANDA        21/05    TORM         UMS/CCOND/CCOND                IN BLST TO AG      \",\"line_index\":44},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SKS DODA           11/06 119 131 250 12 DAESAN        24/05    SKS          CCOND/ULSD/NAP      \"},\"header\":{\"0\":\"       SKS DODA           11/06 119 131 250 12 DAESAN        24/05    SKS          CCOND/ULSD/NAP      \"},\"label_line_index\":45,\"label_string\":\"       SKS DODA           11/06 119 131 250 12 DAESAN        24/05    SKS          CCOND/ULSD/NAP      \",\"line_index\":45},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SEA BEAUTY         11/06 156 165 277 18 FALMOUTH      20/05    AMPTC                                       SUBS      \"},\"header\":{\"0\":\"       SEA BEAUTY         11/06 156 165 277 18 FALMOUTH      20/05    AMPTC                                       SUBS      \"},\"label_line_index\":46,\"label_string\":\"       SEA BEAUTY         11/06 156 165 277 18 FALMOUTH      20/05    AMPTC                                       SUBS      \",\"line_index\":46},{\"StructureType\":\"line\",\"data\":{\"0\":\"       FRONT CHEETAH      12/06 112 120 250 16 BARCELONA     27/05    FRONTLINE    GO/NAP      \"},\"header\":{\"0\":\"       FRONT CHEETAH      12/06 112 120 250 16 BARCELONA     27/05    FRONTLINE    GO/NAP      \"},\"label_line_index\":47,\"label_string\":\"       FRONT CHEETAH      12/06 112 120 250 16 BARCELONA     27/05    FRONTLINE    GO/NAP      \",\"line_index\":47},{\"StructureType\":\"line\",\"data\":{\"0\":\"       BRYANSTON          12/06 105 117 244 05 ROTTERDAM     21/05    PDS          GO+JET/NAP/NAP                 SUB      \"},\"header\":{\"0\":\"       BRYANSTON          12/06 105 117 244 05 ROTTERDAM     21/05    PDS          GO+JET/NAP/NAP                 SUB      \"},\"label_line_index\":48,\"label_string\":\"       BRYANSTON          12/06 105 117 244 05 ROTTERDAM     21/05    PDS          GO+JET/NAP/NAP                 SUB      \",\"line_index\":48},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MARLIN LILY        12/06 109 123 250 19 GULEI         28/05    TRAFIGURA    CPP/NB      \"},\"header\":{\"0\":\"       MARLIN LILY        12/06 109 123 250 19 GULEI         28/05    TRAFIGURA    CPP/NB      \"},\"label_line_index\":49,\"label_string\":\"       MARLIN LILY        12/06 109 123 250 19 GULEI         28/05    TRAFIGURA    CPP/NB      \",\"line_index\":49},{\"StructureType\":\"line\",\"data\":{\"0\":\"       BREEZE             13/06 156 165 277 18 LOME          20/05    AMPTC        ULSD/ULSD/ULSD                 IF      \"},\"header\":{\"0\":\"       BREEZE             13/06 156 165 277 18 LOME          20/05    AMPTC        ULSD/ULSD/ULSD                 IF      \"},\"label_line_index\":50,\"label_string\":\"       BREEZE             13/06 156 165 277 18 LOME          20/05    AMPTC        ULSD/ULSD/ULSD                 IF      \",\"line_index\":50},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SOHO SQUARE        13/06 114 123 249 12 DUNKIRK       22/05    ZODIAC       ULSD/ULSD/NAP/NAP/GO+JET      \"},\"header\":{\"0\":\"       SOHO SQUARE        13/06 114 123 249 12 DUNKIRK       22/05    ZODIAC       ULSD/ULSD/NAP/NAP/GO+JET      \"},\"label_line_index\":51,\"label_string\":\"       SOHO SQUARE        13/06 114 123 249 12 DUNKIRK       22/05    ZODIAC       ULSD/ULSD/NAP/NAP/GO+JET      \",\"line_index\":51},{\"StructureType\":\"line\",\"data\":{\"0\":\"       CLIO               13/06 112 124 250 08 RAYONG        01/06    SHELL        FO/FO/ULSD/UMS                 PREFER CCOND-TO CLEAN UP      \"},\"header\":{\"0\":\"       CLIO               13/06 112 124 250 08 RAYONG        01/06    SHELL        FO/FO/ULSD/UMS                 PREFER CCOND-TO CLEAN UP      \"},\"label_line_index\":52,\"label_string\":\"       CLIO               13/06 112 124 250 08 RAYONG        01/06    SHELL        FO/FO/ULSD/UMS                 PREFER CCOND-TO CLEAN UP      \",\"line_index\":52},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SEAENVOY           13/06 113 120 250 17 SINGAPORE     04/06    ST SHIPPING  ULSD/JET+ULSD/NAP/ULSD/      \"},\"header\":{\"0\":\"       SEAENVOY           13/06 113 120 250 17 SINGAPORE     04/06    ST SHIPPING  ULSD/JET+ULSD/NAP/ULSD/      \"},\"label_line_index\":53,\"label_string\":\"       SEAENVOY           13/06 113 120 250 17 SINGAPORE     04/06    ST SHIPPING  ULSD/JET+ULSD/NAP/ULSD/      \",\"line_index\":53},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SEA LEGEND         13/06 112 124 250 08 DAESAN        26-27/05 AMPTC        NAP/ULSD/ULSD      \"},\"header\":{\"0\":\"       SEA LEGEND         13/06 112 124 250 08 DAESAN        26-27/05 AMPTC        NAP/ULSD/ULSD      \"},\"label_line_index\":54,\"label_string\":\"       SEA LEGEND         13/06 112 124 250 08 DAESAN        26-27/05 AMPTC        NAP/ULSD/ULSD      \",\"line_index\":54},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ELKA VASSILIKI     13/06 94  110 228 04 DAKAR         22/05    CARBON       GO+UMS+JET/UMS+GO/JET/ULSD/JET      \"},\"header\":{\"0\":\"       ELKA VASSILIKI     13/06 94  110 228 04 DAKAR         22/05    CARBON       GO+UMS+JET/UMS+GO/JET/ULSD/JET      \"},\"label_line_index\":55,\"label_string\":\"       ELKA VASSILIKI     13/06 94  110 228 04 DAKAR         22/05    CARBON       GO+UMS+JET/UMS+GO/JET/ULSD/JET      \",\"line_index\":55},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ARETEA             13/06 113 124 250 15 LA PALLICE    23/05    TOTAL        ULSD/ULSD/ULSD      \"},\"header\":{\"0\":\"       ARETEA             13/06 113 124 250 15 LA PALLICE    23/05    TOTAL        ULSD/ULSD/ULSD      \"},\"label_line_index\":56,\"label_string\":\"       ARETEA             13/06 113 124 250 15 LA PALLICE    23/05    TOTAL        ULSD/ULSD/ULSD      \",\"line_index\":56},{\"StructureType\":\"line\",\"data\":{\"0\":\"       BURRI              13/06 115 131 250 19 AMSTERDAM     22/05    CARDIFF      ULSD/GO/ULSD/NB      \"},\"header\":{\"0\":\"       BURRI              13/06 115 131 250 19 AMSTERDAM     22/05    CARDIFF      ULSD/GO/ULSD/NB      \"},\"label_line_index\":57,\"label_string\":\"       BURRI              13/06 115 131 250 19 AMSTERDAM     22/05    CARDIFF      ULSD/GO/ULSD/NB      \",\"line_index\":57},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ELKA ARISTOTLE     14/06 94  110 228 03 LAGOS         21/05    ELKA         UMS/ULSD/ULSD                  CANARIES 30/05      \"},\"header\":{\"0\":\"       ELKA ARISTOTLE     14/06 94  110 228 03 LAGOS         21/05    ELKA         UMS/ULSD/ULSD                  CANARIES 30/05      \"},\"label_line_index\":58,\"label_string\":\"       ELKA ARISTOTLE     14/06 94  110 228 03 LAGOS         21/05    ELKA         UMS/ULSD/ULSD                  CANARIES 30/05      \",\"line_index\":58},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MAERSK PHOENIX     14/06 109 117 245 05 SHELLHAVEN    23/05    MAERSK       JET/NAP/ULSD/COND              SUBS      \"},\"header\":{\"0\":\"       MAERSK PHOENIX     14/06 109 117 245 05 SHELLHAVEN    23/05    MAERSK       JET/NAP/ULSD/COND              SUBS      \"},\"label_line_index\":59,\"label_string\":\"       MAERSK PHOENIX     14/06 109 117 245 05 SHELLHAVEN    23/05    MAERSK       JET/NAP/ULSD/COND              SUBS      \",\"line_index\":59},{\"StructureType\":\"line\",\"data\":{\"0\":\"       KASTELORIZO        15/06 109 122 249 19 FUJAIRAH      12/06    ELETSON      JET      \"},\"header\":{\"0\":\"       KASTELORIZO        15/06 109 122 249 19 FUJAIRAH      12/06    ELETSON      JET      \"},\"label_line_index\":60,\"label_string\":\"       KASTELORIZO        15/06 109 122 249 19 FUJAIRAH      12/06    ELETSON      JET      \",\"line_index\":60},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MINERVA ARIES      15/06 105 115 244 08 KAOHSIUNG     31/05    MINERVA      NAP/CPP/JET      \"},\"header\":{\"0\":\"       MINERVA ARIES      15/06 105 115 244 08 KAOHSIUNG     31/05    MINERVA      NAP/CPP/JET      \"},\"label_line_index\":61,\"label_string\":\"       MINERVA ARIES      15/06 105 115 244 08 KAOHSIUNG     31/05    MINERVA      NAP/CPP/JET      \",\"line_index\":61},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI LAUREN         15/06 109 129 256 15 YOSU          28/05    SCORPIO      CPP/ULSD      \"},\"header\":{\"0\":\"       STI LAUREN         15/06 109 129 256 15 YOSU          28/05    SCORPIO      CPP/ULSD      \"},\"label_line_index\":62,\"label_string\":\"       STI LAUREN         15/06 109 129 256 15 YOSU          28/05    SCORPIO      CPP/ULSD      \",\"line_index\":62},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MAERSK PELICAN     15/06 109 117 245 08 CHIBA         27/05    MAERSK       NAP/ULSD/COND                  AG17/JUN      \"},\"header\":{\"0\":\"       MAERSK PELICAN     15/06 109 117 245 08 CHIBA         27/05    MAERSK       NAP/ULSD/COND                  AG17/JUN      \"},\"label_line_index\":63,\"label_string\":\"       MAERSK PELICAN     15/06 109 117 245 08 CHIBA         27/05    MAERSK       NAP/ULSD/COND                  AG17/JUN      \",\"line_index\":63},{\"StructureType\":\"line\",\"data\":{\"0\":\"       FOLEGANDROS        16/06 109 122 250 19 GIBRALTAR     30/05    ELETSON      JET                            IN BLST EX FLORIDA      \"},\"header\":{\"0\":\"       FOLEGANDROS        16/06 109 122 250 19 GIBRALTAR     30/05    ELETSON      JET                            IN BLST EX FLORIDA      \"},\"label_line_index\":64,\"label_string\":\"       FOLEGANDROS        16/06 109 122 250 19 GIBRALTAR     30/05    ELETSON      JET                            IN BLST EX FLORIDA      \",\"line_index\":64},{\"StructureType\":\"line\",\"data\":{\"0\":\"       OCEAN ODYSSEY      16/06 108 125 245 08 OITA          29/05    OTC          NAP/UMS OXY FREE/NAP      \"},\"header\":{\"0\":\"       OCEAN ODYSSEY      16/06 108 125 245 08 OITA          29/05    OTC          NAP/UMS OXY FREE/NAP      \"},\"label_line_index\":65,\"label_string\":\"       OCEAN ODYSSEY      16/06 108 125 245 08 OITA          29/05    OTC          NAP/UMS OXY FREE/NAP      \",\"line_index\":65},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SEA STAR           16/06 112 123 250 12 BOTANY BAY    26-27/05 AMPTC        ULSD+JET/NAP/ULSD      \"},\"header\":{\"0\":\"       SEA STAR           16/06 112 123 250 12 BOTANY BAY    26-27/05 AMPTC        ULSD+JET/NAP/ULSD      \"},\"label_line_index\":66,\"label_string\":\"       SEA STAR           16/06 112 123 250 12 BOTANY BAY    26-27/05 AMPTC        ULSD+JET/NAP/ULSD      \",\"line_index\":66},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI GALLANTRY      16/06 109 120 250 16 UKC           25/05    VITOL        CPP/NAP      \"},\"header\":{\"0\":\"       STI GALLANTRY      16/06 109 120 250 16 UKC           25/05    VITOL        CPP/NAP      \"},\"label_line_index\":67,\"label_string\":\"       STI GALLANTRY      16/06 109 120 250 16 UKC           25/05    VITOL        CPP/NAP      \",\"line_index\":67},{\"StructureType\":\"line\",\"data\":{\"0\":\"       FS ENDEAVOR        16/06 108 125 250 12 SINGAPORE     07/06    SHELL        CPP/NAP/NAP/NAP                IF      \"},\"header\":{\"0\":\"       FS ENDEAVOR        16/06 108 125 250 12 SINGAPORE     07/06    SHELL        CPP/NAP/NAP/NAP                IF      \"},\"label_line_index\":68,\"label_string\":\"       FS ENDEAVOR        16/06 108 125 250 12 SINGAPORE     07/06    SHELL        CPP/NAP/NAP/NAP                IF      \",\"line_index\":68},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI MADISON        17/06 109 132 256 14 BOTANY BAY    27/05    SCORPIO      ULSD/COND/JET+GO/NAP      \"},\"header\":{\"0\":\"       STI MADISON        17/06 109 132 256 14 BOTANY BAY    27/05    SCORPIO      ULSD/COND/JET+GO/NAP      \"},\"label_line_index\":69,\"label_string\":\"       STI MADISON        17/06 109 132 256 14 BOTANY BAY    27/05    SCORPIO      ULSD/COND/JET+GO/NAP      \",\"line_index\":69},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MARLIN LAVENDER    17/06 109 123 250 19 LOME          24/05    TRAFIGURA      \"},\"header\":{\"0\":\"       MARLIN LAVENDER    17/06 109 123 250 19 LOME          24/05    TRAFIGURA      \"},\"label_line_index\":70,\"label_string\":\"       MARLIN LAVENDER    17/06 109 123 250 19 LOME          24/05    TRAFIGURA      \",\"line_index\":70},{\"StructureType\":\"line\",\"data\":{\"0\":\"       AG NEPTUNE         17/06 105 114 244 13 ARA           26/05    KOCH         CPP/JET/GO      \"},\"header\":{\"0\":\"       AG NEPTUNE         17/06 105 114 244 13 ARA           26/05    KOCH         CPP/JET/GO      \"},\"label_line_index\":71,\"label_string\":\"       AG NEPTUNE         17/06 105 114 244 13 ARA           26/05    KOCH         CPP/JET/GO      \",\"line_index\":71},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ENCELIA            17/06 109 122 250 03 BATAAN        03/06    BIHAR        ULSD+MOGAS/NAP/NAP      \"},\"header\":{\"0\":\"       ENCELIA            17/06 109 122 250 03 BATAAN        03/06    BIHAR        ULSD+MOGAS/NAP/NAP      \"},\"label_line_index\":72,\"label_string\":\"       ENCELIA            17/06 109 122 250 03 BATAAN        03/06    BIHAR        ULSD+MOGAS/NAP/NAP      \",\"line_index\":72},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SKS DEE            18/06 119 131 250 10 KALUNDBORG    25-26/05 SKS          GO/NAP/NAP      \"},\"header\":{\"0\":\"       SKS DEE            18/06 119 131 250 10 KALUNDBORG    25-26/05 SKS          GO/NAP/NAP      \"},\"label_line_index\":73,\"label_string\":\"       SKS DEE            18/06 119 131 250 10 KALUNDBORG    25-26/05 SKS          GO/NAP/NAP      \",\"line_index\":73},{\"StructureType\":\"line\",\"data\":{\"0\":\"       BW DESPINA         19/06 109 129 256 19 MILFORD HAVEN 29/05    TRAFIGURA    CPP/GO/GO /NB      \"},\"header\":{\"0\":\"       BW DESPINA         19/06 109 129 256 19 MILFORD HAVEN 29/05    TRAFIGURA    CPP/GO/GO /NB      \"},\"label_line_index\":74,\"label_string\":\"       BW DESPINA         19/06 109 129 256 19 MILFORD HAVEN 29/05    TRAFIGURA    CPP/GO/GO /NB      \",\"line_index\":74},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SWARNA KAMAL       19/06 104 118 244 10 SINGAPORE     10/06    SCI          ULSD/NAP/NAP      \"},\"header\":{\"0\":\"       SWARNA KAMAL       19/06 104 118 244 10 SINGAPORE     10/06    SCI          ULSD/NAP/NAP      \"},\"label_line_index\":75,\"label_string\":\"       SWARNA KAMAL       19/06 104 118 244 10 SINGAPORE     10/06    SCI          ULSD/NAP/NAP      \",\"line_index\":75},{\"StructureType\":\"line\",\"data\":{\"0\":\"       UST LUGA           19/06 109 123 250 17 YOSU          01/06    NOVATEK      ULSD/NAP/JET/UMS      \"},\"header\":{\"0\":\"       UST LUGA           19/06 109 123 250 17 YOSU          01/06    NOVATEK      ULSD/NAP/JET/UMS      \"},\"label_line_index\":76,\"label_string\":\"       UST LUGA           19/06 109 123 250 17 YOSU          01/06    NOVATEK      ULSD/NAP/JET/UMS      \",\"line_index\":76},{\"StructureType\":\"line\",\"data\":{\"0\":\"       TONG LIN WAN       19/06 109 118 245 14 SINGAPORE     10/06    COSCO        GO/XDD/NAP/GO/NAP              IF PROJ TBC      \"},\"header\":{\"0\":\"       TONG LIN WAN       19/06 109 118 245 14 SINGAPORE     10/06    COSCO        GO/XDD/NAP/GO/NAP              IF PROJ TBC      \"},\"label_line_index\":77,\"label_string\":\"       TONG LIN WAN       19/06 109 118 245 14 SINGAPORE     10/06    COSCO        GO/XDD/NAP/GO/NAP              IF PROJ TBC      \",\"line_index\":77},{\"StructureType\":\"line\",\"data\":{\"0\":\"       PROSTAR            19/06 115 124 250 19 DAESAN        01/06    TAURUS       NAP/JET/NB      \"},\"header\":{\"0\":\"       PROSTAR            19/06 115 124 250 19 DAESAN        01/06    TAURUS       NAP/JET/NB      \"},\"label_line_index\":78,\"label_string\":\"       PROSTAR            19/06 115 124 250 19 DAESAN        01/06    TAURUS       NAP/JET/NB      \",\"line_index\":78},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SERIANA            19/06 109 125 238 15 BOTANY BAY    29/05    TRAFIGURA    JET/ULSD/ULSD/ULSD      \"},\"header\":{\"0\":\"       SERIANA            19/06 109 125 238 15 BOTANY BAY    29/05    TRAFIGURA    JET/ULSD/ULSD/ULSD      \"},\"label_line_index\":79,\"label_string\":\"       SERIANA            19/06 109 125 238 15 BOTANY BAY    29/05    TRAFIGURA    JET/ULSD/ULSD/ULSD      \",\"line_index\":79},{\"StructureType\":\"line\",\"data\":{\"0\":\"       CHAMPION PRINCESS  20/06 105 118 245 12 CHIBA         01-06/06 NYK          NAP/UMS OXY FREE/NAP/COND      IF IDLE SIRE SHELL      \"},\"header\":{\"0\":\"       CHAMPION PRINCESS  20/06 105 118 245 12 CHIBA         01-06/06 NYK          NAP/UMS OXY FREE/NAP/COND      IF IDLE SIRE SHELL      \"},\"label_line_index\":80,\"label_string\":\"       CHAMPION PRINCESS  20/06 105 118 245 12 CHIBA         01-06/06 NYK          NAP/UMS OXY FREE/NAP/COND      IF IDLE SIRE SHELL      \",\"line_index\":80},{\"StructureType\":\"line\",\"data\":{\"0\":\"       TORM MAREN         20/06 109 117 245 08 SINGAPORE     11/06    TORM         COND/JET+ULSD/NAP/GO           IF PROJ TBC      \"},\"header\":{\"0\":\"       TORM MAREN         20/06 109 117 245 08 SINGAPORE     11/06    TORM         COND/JET+ULSD/NAP/GO           IF PROJ TBC      \"},\"label_line_index\":81,\"label_string\":\"       TORM MAREN         20/06 109 117 245 08 SINGAPORE     11/06    TORM         COND/JET+ULSD/NAP/GO           IF PROJ TBC      \",\"line_index\":81},{\"StructureType\":\"line\",\"data\":{\"0\":\"       YANG LI HU         20/06 109 117 245 10 YOSU          02/06    HEIDMAR      ULG/JET/NAP                    SLD      \"},\"header\":{\"0\":\"       YANG LI HU         20/06 109 117 245 10 YOSU          02/06    HEIDMAR      ULG/JET/NAP                    SLD      \"},\"label_line_index\":82,\"label_string\":\"       YANG LI HU         20/06 109 117 245 10 YOSU          02/06    HEIDMAR      ULG/JET/NAP                    SLD      \",\"line_index\":82},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SEA SHELL          21/06 158 165 274 16 LUANDA        31/05    AMPTC      \"},\"header\":{\"0\":\"       SEA SHELL          21/06 158 165 274 16 LUANDA        31/05    AMPTC      \"},\"label_line_index\":84,\"label_string\":\"       SEA SHELL          21/06 158 165 274 16 LUANDA        31/05    AMPTC      \",\"line_index\":84},{\"StructureType\":\"line\",\"data\":{\"0\":\"       VAN PHONG 1        21/06 105 120 248 04 LAGOS         28-30/05 PDS          GO/XDD/NAP/NAP      \"},\"header\":{\"0\":\"       VAN PHONG 1        21/06 105 120 248 04 LAGOS         28-30/05 PDS          GO/XDD/NAP/NAP      \"},\"label_line_index\":85,\"label_string\":\"       VAN PHONG 1        21/06 105 120 248 04 LAGOS         28-30/05 PDS          GO/XDD/NAP/NAP      \",\"line_index\":85},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STAR ENERGY        22/06 158 165 274 16 IRELAND       01-05/06 AMPTC        JET/ULSD/UMS/JET+ULSD      \"},\"header\":{\"0\":\"       STAR ENERGY        22/06 158 165 274 16 IRELAND       01-05/06 AMPTC        JET/ULSD/UMS/JET+ULSD      \"},\"label_line_index\":86,\"label_string\":\"       STAR ENERGY        22/06 158 165 274 16 IRELAND       01-05/06 AMPTC        JET/ULSD/UMS/JET+ULSD      \",\"line_index\":86},{\"StructureType\":\"line\",\"data\":{\"0\":\"       BOW PIONEER        22/06 81  84  228 13 USG           20/05    STRAITS TANK NAP/UMS+NAP/ULSD      \"},\"header\":{\"0\":\"       BOW PIONEER        22/06 81  84  228 13 USG           20/05    STRAITS TANK NAP/UMS+NAP/ULSD      \"},\"label_line_index\":87,\"label_string\":\"       BOW PIONEER        22/06 81  84  228 13 USG           20/05    STRAITS TANK NAP/UMS+NAP/ULSD      \",\"line_index\":87},{\"StructureType\":\"line\",\"data\":{\"0\":\"       LR2 POLARIS        23/06 115 119 244 08 LOME          30/05    MAERSK       CPP/NAP/GO+UMS/GO/UMS      \"},\"header\":{\"0\":\"       LR2 POLARIS        23/06 115 119 244 08 LOME          30/05    MAERSK       CPP/NAP/GO+UMS/GO/UMS      \"},\"label_line_index\":88,\"label_string\":\"       LR2 POLARIS        23/06 115 119 244 08 LOME          30/05    MAERSK       CPP/NAP/GO+UMS/GO/UMS      \",\"line_index\":88},{\"StructureType\":\"line\",\"data\":{\"0\":\"       FPMC P IDEAL       23/06 114 119 244 12 SAKAI         05/06    ST SHIPPING  NAP/UMS/UMS/GO/COND      \"},\"header\":{\"0\":\"       FPMC P IDEAL       23/06 114 119 244 12 SAKAI         05/06    ST SHIPPING  NAP/UMS/UMS/GO/COND      \"},\"label_line_index\":89,\"label_string\":\"       FPMC P IDEAL       23/06 114 119 244 12 SAKAI         05/06    ST SHIPPING  NAP/UMS/UMS/GO/COND      \",\"line_index\":89},{\"StructureType\":\"line\",\"data\":{\"0\":\"       NORDDOLPHIN        23/06 113 123 250 17 ARA           01/06    HEIDMAR      ULSD/UMS/ULSD      \"},\"header\":{\"0\":\"       NORDDOLPHIN        23/06 113 123 250 17 ARA           01/06    HEIDMAR      ULSD/UMS/ULSD      \"},\"label_line_index\":90,\"label_string\":\"       NORDDOLPHIN        23/06 113 123 250 17 ARA           01/06    HEIDMAR      ULSD/UMS/ULSD      \",\"line_index\":90},{\"StructureType\":\"line\",\"data\":{\"0\":\"       DONG-A THETIS      24/06 113 123 250 16 DAESAN        06/06    VITOL        CPP/ULSD/NAP/                  IF      \"},\"header\":{\"0\":\"       DONG-A THETIS      24/06 113 123 250 16 DAESAN        06/06    VITOL        CPP/ULSD/NAP/                  IF      \"},\"label_line_index\":91,\"label_string\":\"       DONG-A THETIS      24/06 113 123 250 16 DAESAN        06/06    VITOL        CPP/ULSD/NAP/                  IF      \",\"line_index\":91},{\"StructureType\":\"line\",\"data\":{\"0\":\"       NEPTUNE MOON       24/06 157 168 274 19 ROTTERDAM     02/06    ZODIAC       ULSD/NB                        IF      \"},\"header\":{\"0\":\"       NEPTUNE MOON       24/06 157 168 274 19 ROTTERDAM     02/06    ZODIAC       ULSD/NB                        IF      \"},\"label_line_index\":92,\"label_string\":\"       NEPTUNE MOON       24/06 157 168 274 19 ROTTERDAM     02/06    ZODIAC       ULSD/NB                        IF      \",\"line_index\":92},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SEA JEWEL          24/06 112 123 250 13 SHANGHAI      07-12/06 AMPTC        NAP/CPP/CPP/ULSD      \"},\"header\":{\"0\":\"       SEA JEWEL          24/06 112 123 250 13 SHANGHAI      07-12/06 AMPTC        NAP/CPP/CPP/ULSD      \"},\"label_line_index\":93,\"label_string\":\"       SEA JEWEL          24/06 112 123 250 13 SHANGHAI      07-12/06 AMPTC        NAP/CPP/CPP/ULSD      \",\"line_index\":93},{\"StructureType\":\"line\",\"data\":{\"0\":\"       LUZON SPIRIT       24/06 109 123 243 11 ROTTERDAM     02/06    TAURUS       JET/COND/NAP/NAP      \"},\"header\":{\"0\":\"       LUZON SPIRIT       24/06 109 123 243 11 ROTTERDAM     02/06    TAURUS       JET/COND/NAP/NAP      \"},\"label_line_index\":94,\"label_string\":\"       LUZON SPIRIT       24/06 109 123 243 11 ROTTERDAM     02/06    TAURUS       JET/COND/NAP/NAP      \",\"line_index\":94},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SWARNA JAYANTI     24/06 104 118 244 10 SINGAPORE     15/06    PDS          NAP/NAP/NAP                    IF      \"},\"header\":{\"0\":\"       SWARNA JAYANTI     24/06 104 118 244 10 SINGAPORE     15/06    PDS          NAP/NAP/NAP                    IF      \"},\"label_line_index\":95,\"label_string\":\"       SWARNA JAYANTI     24/06 104 118 244 10 SINGAPORE     15/06    PDS          NAP/NAP/NAP                    IF      \",\"line_index\":95},{\"StructureType\":\"line\",\"data\":{\"0\":\"       PRO TRIUMPH        25/06 105 115 244 09 YOKKAICHI     06-08/06 SK ENERGY    NAP/UMS/NAP/UMS(OXY FREE)      \"},\"header\":{\"0\":\"       PRO TRIUMPH        25/06 105 115 244 09 YOKKAICHI     06-08/06 SK ENERGY    NAP/UMS/NAP/UMS(OXY FREE)      \"},\"label_line_index\":96,\"label_string\":\"       PRO TRIUMPH        25/06 105 115 244 09 YOKKAICHI     06-08/06 SK ENERGY    NAP/UMS/NAP/UMS(OXY FREE)      \",\"line_index\":96},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ZALIV VOSTOK       26/06 106 121 244 09 DALIAN        08/06    SOVCOM       XDD/CPP/JET/ULSD/NAP/ULSD      XDD IN CHINA      \"},\"header\":{\"0\":\"       ZALIV VOSTOK       26/06 106 121 244 09 DALIAN        08/06    SOVCOM       XDD/CPP/JET/ULSD/NAP/ULSD      XDD IN CHINA      \"},\"label_line_index\":97,\"label_string\":\"       ZALIV VOSTOK       26/06 106 121 244 09 DALIAN        08/06    SOVCOM       XDD/CPP/JET/ULSD/NAP/ULSD      XDD IN CHINA      \",\"line_index\":97},{\"StructureType\":\"line\",\"data\":{\"0\":\"       HANOVER SQUARE     26/06 114 120 250 19 HUIZHOU       12/06    ZODIAC       JET/NB      \"},\"header\":{\"0\":\"       HANOVER SQUARE     26/06 114 120 250 19 HUIZHOU       12/06    ZODIAC       JET/NB      \"},\"label_line_index\":98,\"label_string\":\"       HANOVER SQUARE     26/06 114 120 250 19 HUIZHOU       12/06    ZODIAC       JET/NB      \",\"line_index\":98},{\"StructureType\":\"line\",\"data\":{\"0\":\"       RONG LIN WAN       27/06 109 125 250 17 SINGAPORE     18/06    SHELL        ULSD/CCOND/CCOND      \"},\"header\":{\"0\":\"       RONG LIN WAN       27/06 109 125 250 17 SINGAPORE     18/06    SHELL        ULSD/CCOND/CCOND      \"},\"label_line_index\":99,\"label_string\":\"       RONG LIN WAN       27/06 109 125 250 17 SINGAPORE     18/06    SHELL        ULSD/CCOND/CCOND      \",\"line_index\":99},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ALBURAQ            29/06 112 124 250 08 UKC           07-12/06 AMPTC        ULSD/NAP/ULSD      \"},\"header\":{\"0\":\"       ALBURAQ            29/06 112 124 250 08 UKC           07-12/06 AMPTC        ULSD/NAP/ULSD      \"},\"label_line_index\":100,\"label_string\":\"       ALBURAQ            29/06 112 124 250 08 UKC           07-12/06 AMPTC        ULSD/NAP/ULSD      \",\"line_index\":100},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MEI LIN WAN        30/06 109 118 245 12 OITA          12/06    COSCO        NAP/ULSD/UMS/NAP/UMS           IF      \"},\"header\":{\"0\":\"       MEI LIN WAN        30/06 109 118 245 12 OITA          12/06    COSCO        NAP/ULSD/UMS/NAP/UMS           IF      \"},\"label_line_index\":101,\"label_string\":\"       MEI LIN WAN        30/06 109 118 245 12 OITA          12/06    COSCO        NAP/ULSD/UMS/NAP/UMS           IF      \",\"line_index\":101},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI GAUNTLET       03/07 109 120 250 17 JAPAN         14/06    SCORPIO      NAP/UMS/NAP/COND               IF PROJ TBC      \"},\"header\":{\"0\":\"       STI GAUNTLET       03/07 109 120 250 17 JAPAN         14/06    SCORPIO      NAP/UMS/NAP/COND               IF PROJ TBC      \"},\"label_line_index\":103,\"label_string\":\"       STI GAUNTLET       03/07 109 120 250 17 JAPAN         14/06    SCORPIO      NAP/UMS/NAP/COND               IF PROJ TBC      \",\"line_index\":103},{\"StructureType\":\"line\",\"data\":{\"0\":\"       TAO LIN WAN        05/07 109 118 245 12 CHIBA         16/06    COSCO        NAP/NAP/ULSD/NAP      \"},\"header\":{\"0\":\"       TAO LIN WAN        05/07 109 118 245 12 CHIBA         16/06    COSCO        NAP/NAP/ULSD/NAP      \"},\"label_line_index\":104,\"label_string\":\"       TAO LIN WAN        05/07 109 118 245 12 CHIBA         16/06    COSCO        NAP/NAP/ULSD/NAP      \",\"line_index\":104},{\"StructureType\":\"line\",\"data\":{\"0\":\"       CHAMPION PRINCE    05/07 105 118 245 12 BRISBANE      13/06    NYK          UMS+GO/ULSD/NAP/NAP      \"},\"header\":{\"0\":\"       CHAMPION PRINCE    05/07 105 118 245 12 BRISBANE      13/06    NYK          UMS+GO/ULSD/NAP/NAP      \"},\"label_line_index\":105,\"label_string\":\"       CHAMPION PRINCE    05/07 105 118 245 12 BRISBANE      13/06    NYK          UMS+GO/ULSD/NAP/NAP      \",\"line_index\":105},{\"StructureType\":\"line\",\"data\":{\"0\":\"       PORTMAN SQUARE     06/07 105 115 241 02 YOSU          18/06    ZODIAC       NAP/GO/JET/JET      \"},\"header\":{\"0\":\"       PORTMAN SQUARE     06/07 105 115 241 02 YOSU          18/06    ZODIAC       NAP/GO/JET/JET      \"},\"label_line_index\":106,\"label_string\":\"       PORTMAN SQUARE     06/07 105 115 241 02 YOSU          18/06    ZODIAC       NAP/GO/JET/JET      \",\"line_index\":106},{\"StructureType\":\"line\",\"data\":{\"0\":\"       NAVIG8 PROMISE     06/07 110 123 252 19 NANTONG       19/06    NAVIG8       NB                             NB IF UNC      \"},\"header\":{\"0\":\"       NAVIG8 PROMISE     06/07 110 123 252 19 NANTONG       19/06    NAVIG8       NB                             NB IF UNC      \"},\"label_line_index\":107,\"label_string\":\"       NAVIG8 PROMISE     06/07 110 123 252 19 NANTONG       19/06    NAVIG8       NB                             NB IF UNC      \",\"line_index\":107},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ALDANA             07/07 156 165 277 18 ANTWERP       15-20/06 AMPTC      \"},\"header\":{\"0\":\"       ALDANA             07/07 156 165 277 18 ANTWERP       15-20/06 AMPTC      \"},\"label_line_index\":108,\"label_string\":\"       ALDANA             07/07 156 165 277 18 ANTWERP       15-20/06 AMPTC      \",\"line_index\":108},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SANMAR SANGEET     09/07 106 114 241 04 JAPAN         20/06    MAERSK       NAP/NON OXY UMS/ULSD/ULSD      IF PROJ TBC      \"},\"header\":{\"0\":\"       SANMAR SANGEET     09/07 106 114 241 04 JAPAN         20/06    MAERSK       NAP/NON OXY UMS/ULSD/ULSD      IF PROJ TBC      \"},\"label_line_index\":109,\"label_string\":\"       SANMAR SANGEET     09/07 106 114 241 04 JAPAN         20/06    MAERSK       NAP/NON OXY UMS/ULSD/ULSD      IF PROJ TBC      \",\"line_index\":109},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STI ROSE           09/07 109 125 256 15 JAPAN         20/06    VITOL        NAP/NAP                        IF PROJ TBC      \"},\"header\":{\"0\":\"       STI ROSE           09/07 109 125 256 15 JAPAN         20/06    VITOL        NAP/NAP                        IF PROJ TBC      \"},\"label_line_index\":110,\"label_string\":\"       STI ROSE           09/07 109 125 256 15 JAPAN         20/06    VITOL        NAP/NAP                        IF PROJ TBC      \",\"line_index\":110},{\"StructureType\":\"line\",\"data\":{\"0\":\"       PROSKY             11/07 115 124 250 19 BRISBANE      19/06    TAURUS       CPP/NAP/JET/NB                 IF PROJ TBC      \"},\"header\":{\"0\":\"       PROSKY             11/07 115 124 250 19 BRISBANE      19/06    TAURUS       CPP/NAP/JET/NB                 IF PROJ TBC      \"},\"label_line_index\":111,\"label_string\":\"       PROSKY             11/07 115 124 250 19 BRISBANE      19/06    TAURUS       CPP/NAP/JET/NB                 IF PROJ TBC      \",\"line_index\":111},{\"StructureType\":\"line\",\"data\":{\"0\":\"       NAVIG8 PROSPERITY  12/07 110 123 250 19 NANTONG       25/06    NAVIG8       NB                             NB      \"},\"header\":{\"0\":\"       NAVIG8 PROSPERITY  12/07 110 123 250 19 NANTONG       25/06    NAVIG8       NB                             NB      \"},\"label_line_index\":112,\"label_string\":\"       NAVIG8 PROSPERITY  12/07 110 123 250 19 NANTONG       25/06    NAVIG8       NB                             NB      \",\"line_index\":112},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MARE NOSTRUM       12/07 110 123 246 09 JAPAN         23/06    TAURUS       NAP/EXDD/NAP/GO                IF PROJ TBC      \"},\"header\":{\"0\":\"       MARE NOSTRUM       12/07 110 123 246 09 JAPAN         23/06    TAURUS       NAP/EXDD/NAP/GO                IF PROJ TBC      \"},\"label_line_index\":113,\"label_string\":\"       MARE NOSTRUM       12/07 110 123 246 09 JAPAN         23/06    TAURUS       NAP/EXDD/NAP/GO                IF PROJ TBC      \",\"line_index\":113},{\"StructureType\":\"line\",\"data\":{\"0\":\"       STELLATA           14/07 109 122 238 16 JAPAN         25/06    TRAFIGURA    NAP/GO/GO                      IF PROJ TBC      \"},\"header\":{\"0\":\"       STELLATA           14/07 109 122 238 16 JAPAN         25/06    TRAFIGURA    NAP/GO/GO                      IF PROJ TBC      \"},\"label_line_index\":114,\"label_string\":\"       STELLATA           14/07 109 122 238 16 JAPAN         25/06    TRAFIGURA    NAP/GO/GO                      IF PROJ TBC      \",\"line_index\":114},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ALPINE AQUALINA    15/07 105 114 239 11 JAPAN         26/06    ST SHIPPING  NAP/ULSD                       IF PROJ TBC      \"},\"header\":{\"0\":\"       ALPINE AQUALINA    15/07 105 114 239 11 JAPAN         26/06    ST SHIPPING  NAP/ULSD                       IF PROJ TBC      \"},\"label_line_index\":115,\"label_string\":\"       ALPINE AQUALINA    15/07 105 114 239 11 JAPAN         26/06    ST SHIPPING  NAP/ULSD                       IF PROJ TBC      \",\"line_index\":115},{\"StructureType\":\"line\",\"data\":{\"0\":\"       OCEAN QUEST        15/07 108 122 245 08 JAPAN         26/06    OTC          NAP/GO/NAP/NAP                 IF PROJ TBC      \"},\"header\":{\"0\":\"       OCEAN QUEST        15/07 108 122 245 08 JAPAN         26/06    OTC          NAP/GO/NAP/NAP                 IF PROJ TBC      \"},\"label_line_index\":116,\"label_string\":\"       OCEAN QUEST        15/07 108 122 245 08 JAPAN         26/06    OTC          NAP/GO/NAP/NAP                 IF PROJ TBC      \",\"line_index\":116},{\"StructureType\":\"line\",\"data\":{\"0\":\"       HAIMA              16/07 110 123 246 09 ARA           24-29/06 PDS          JET/XDD/NAP/GO                 IF      \"},\"header\":{\"0\":\"       HAIMA              16/07 110 123 246 09 ARA           24-29/06 PDS          JET/XDD/NAP/GO                 IF      \"},\"label_line_index\":117,\"label_string\":\"       HAIMA              16/07 110 123 246 09 ARA           24-29/06 PDS          JET/XDD/NAP/GO                 IF      \",\"line_index\":117},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SEA ICON           17/07 156 165 277 17 UKC           25-30/06 AMPTC        CPP      \"},\"header\":{\"0\":\"       SEA ICON           17/07 156 165 277 17 UKC           25-30/06 AMPTC        CPP      \"},\"label_line_index\":118,\"label_string\":\"       SEA ICON           17/07 156 165 277 17 UKC           25-30/06 AMPTC        CPP      \",\"line_index\":118},{\"StructureType\":\"line\",\"data\":{\"0\":\"       CLEAROCEAN APOLLON 18/07 114 124 250 19 KOREA         30/06    CLEARLAKE    NB                             NB SIKKA19/JUL      \"},\"header\":{\"0\":\"       CLEAROCEAN APOLLON 18/07 114 124 250 19 KOREA         30/06    CLEARLAKE    NB                             NB SIKKA19/JUL      \"},\"label_line_index\":119,\"label_string\":\"       CLEAROCEAN APOLLON 18/07 114 124 250 19 KOREA         30/06    CLEARLAKE    NB                             NB SIKKA19/JUL      \",\"line_index\":119},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MOBILE:(+65)9777 6488      \"},\"header\":{\"0\":\"       CLEAROCEAN APOLLON 18/07 114 124 250 19 KOREA         30/06    CLEARLAKE    NB                             NB SIKKA19/JUL      \"},\"label_line_index\":119,\"label_string\":\"       CLEAROCEAN APOLLON 18/07 114 124 250 19 KOREA         30/06    CLEARLAKE    NB                             NB SIKKA19/JUL      \",\"line_index\":122},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ICE   : VWADHWA      \"},\"header\":{\"0\":\"       CLEAROCEAN APOLLON 18/07 114 124 250 19 KOREA         30/06    CLEARLAKE    NB                             NB SIKKA19/JUL      \"},\"label_line_index\":119,\"label_string\":\"       CLEAROCEAN APOLLON 18/07 114 124 250 19 KOREA         30/06    CLEARLAKE    NB                             NB SIKKA19/JUL      \",\"line_index\":123},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MR MARCUS NEO      \"},\"header\":{\"0\":\"       CLEAROCEAN APOLLON 18/07 114 124 250 19 KOREA         30/06    CLEARLAKE    NB                             NB SIKKA19/JUL      \"},\"label_line_index\":119,\"label_string\":\"       CLEAROCEAN APOLLON 18/07 114 124 250 19 KOREA         30/06    CLEARLAKE    NB                             NB SIKKA19/JUL      \",\"line_index\":124},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MOBILE: (+65)9655 0519      \"},\"header\":{\"0\":\"       CLEAROCEAN APOLLON 18/07 114 124 250 19 KOREA         30/06    CLEARLAKE    NB                             NB SIKKA19/JUL      \"},\"label_line_index\":119,\"label_string\":\"       CLEAROCEAN APOLLON 18/07 114 124 250 19 KOREA         30/06    CLEARLAKE    NB                             NB SIKKA19/JUL      \",\"line_index\":125},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ICE   :  MNEO      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":126},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MR CHRIS XU      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":127},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MOBILE: (+65)9827 8077      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":128},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ICE   : CXU18      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":129},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MR EDWARD DICKENS      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":130},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MOBILE: (+65)9825 6415      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":131},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ICE   : EDICKENS      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":132},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MR JAKE TAN      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":133},{\"StructureType\":\"line\",\"data\":{\"0\":\"       MOBILE: (+65)9841 6604      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":134},{\"StructureType\":\"line\",\"data\":{\"0\":\"       ICE   : JTAN32      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":135},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SIMPSON SPENCE YOUNG(SINGAPORE)      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":136},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SINGAPORE LAND TOWER      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":138},{\"StructureType\":\"line\",\"data\":{\"0\":\"       SINGAPORE 048623      \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":139},{\"StructureType\":\"line\",\"data\":{\"0\":\"  You can view the Simpson Spence Young Privacy Policy at http://www.ssyonline.com/privacy-policy/  \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":144},{\"StructureType\":\"line\",\"data\":{\"0\":\"  Part of the SIMPSON | SPENCE | YOUNG group of companies  \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":145},{\"StructureType\":\"line\",\"data\":{\"0\":\"  Details of all SIMPSON | SPENCE | YOUNG group companies can be found at http://www.ssyonline.com/registered-offices  \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":146},{\"StructureType\":\"line\",\"data\":{\"0\":\"  The SIMPSON | SPENCE | YOUNG policy on international sanctions can be found at http://www.ssyonline.com/disclaimer  \"},\"header\":{\"0\":\"       ICE   :  MNEO      \"},\"label_line_index\":126,\"label_string\":\"       ICE   :  MNEO      \",\"line_index\":147}]}}";

try {
			 returnobj = new JSONObject();
			 obj = new JSONObject(pythonScriptApiData);
			Iterator mainitr = obj.keys();
			while (mainitr.hasNext()) {
				String TableName = (String) mainitr.next();
				 Tableobj = obj.getJSONObject(TableName);
				 Tableobj_new=new JSONObject();
				 table_dataobj = Tableobj.getJSONArray("table_data");
				 header_dataobj = Tableobj.getJSONArray("header_data");
				 table_dataobj_new=new JSONArray();
				// header_dataobj_new = new JSONArray();
				 
				// one loop to check starting of table by checking vesselname
				int vesselline_line_index = 0;
				int vesseljson_count = 0;
				
				String key = null;
				String value = null;
				String solrres= null;
				
				for (int i = 0; i < table_dataobj.length(); i++) {
					 eachrowobj = table_dataobj.getJSONObject(i);
					 data = eachrowobj.getJSONObject("data");
					 header = eachrowobj.getJSONObject("header");
					Iterator itr = data.keys();
					if (vesselline_line_index == 0) {
						while (itr.hasNext()) {
							if (vesselline_line_index == 0) {
								//System.out.println("vesselline_no blank");
								 key = (String) itr.next();
								 value = data.getString(key);
								 solrres = pallavi_SortJSON
										.executesolrQuery_bywords(value.toUpperCase().replace(" ", "_"));
								boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
								if (checkjsonString) {
									// parsejson from solr //get DocumentName
									String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
									//System.out.println("DocumentName  " + DocumentName);
									if (DocumentName.equalsIgnoreCase("VesselName")) {
										// vesselline_no=eachrowobj.getString("Line_no");
										vesselline_line_index = Integer.parseInt(eachrowobj.getString("line_index"));
									}
								}
							} else {
								break;
							}
							//System.out.println("vesselline_line_index " + vesselline_line_index);
						}
						vesseljson_count++;

					} else {
						break;
					}
				}
				System.out.println("vesselline_line_index " + vesselline_line_index);


				 objheader = new JSONObject();
				objheader.put("RepositionRegion", "");
				objheader.put("ReportType", "");
				objheader.put("CargoType", "");
				int previousvessellineindex=vesselline_line_index;	

				if(vesseljson_count==0) {
					findheaderdata(vesselline_line_index, objheader, header_dataobj, -1);
					//call one method 
					if(table_dataobj.length()!=0){
					for (int i = 0; i < table_dataobj.length(); i++) {
						//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						 eachrowobj = table_dataobj.getJSONObject(i);
						 eachrowobj_new= new JSONObject();
						//find difference
						vesselline_line_index=Integer.parseInt(eachrowobj.getString("line_index"));
						int difference= vesselline_line_index-previousvessellineindex;
						//System.out.println("vesselline_line_index "+vesselline_line_index);
						//System.out.println("previousvessellineindex "+previousvessellineindex);
						//System.out.println("difference "+difference);
						if(difference<0) {difference=0;}else if(difference>4) {difference=4;}else if(difference==1) {difference=0;}
						findheaderdata(vesselline_line_index, objheader, header_dataobj, difference);
						//System.out.println("objheader "+objheader);
						previousvessellineindex=Integer.parseInt(eachrowobj.getString("line_index"));
						 data = eachrowobj.getJSONObject("data");
						 header = eachrowobj.getJSONObject("header");
						//System.out.println("header " + header);
						 new_data = new JSONObject();
						 new_header = new JSONObject();
					
					 JSONObject newDataHeaderObj=singleStringParsingmethod(data, header, new_header, objheader);
						if(newDataHeaderObj.has("data")){data=newDataHeaderObj.getJSONObject("data");}
						if(newDataHeaderObj.has("header")){header=newDataHeaderObj.getJSONObject("header");}
						if(newDataHeaderObj.has("new_header")){new_header=newDataHeaderObj.getJSONObject("new_header");}

						
						eachrowobj_new.put("data",data);
						eachrowobj_new.put("header",header);
						eachrowobj_new.put("objheader",objheader);
						eachrowobj_new.put("new_header_data", new_header);
						
//						table_dataobj.getJSONObject(i).put("data",data);
//						table_dataobj.getJSONObject(i).put("header",header);
//
//						table_dataobj.getJSONObject(i).put("objheader",objheader);
//						table_dataobj.getJSONObject(i).put("new_header_data", header);
						//System.out.println("&&&&&&&&&&&& " + eachrowobj_new);

						//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						table_dataobj_new.put(eachrowobj_new);
					
					}
				}
				}
				//System.out.println("vesseljson_count " + vesseljson_count);
				//System.out.println("successfull break");
				LinkedHashMap<String, Integer> lhmap = new LinkedHashMap<String, Integer>();
				
				if(vesseljson_count!=0){
					findheaderdata(vesselline_line_index, objheader, header_dataobj, -1);
					////System.out.println("RepositionRegion "+RepositionRegion);
					////System.out.println("ReportType "+ReportType);
					////System.out.println("CargoType "+CargoType);
				
				for (int i =0; i < table_dataobj.length(); i++) {
					//System.out.println("***************************************************************************************************");

					 eachrowobj = table_dataobj.getJSONObject(i);
					 eachrowobj_new= new  JSONObject();
					
					
					//find difference
					vesselline_line_index=Integer.parseInt(eachrowobj.getString("line_index"));
					int difference= vesselline_line_index-previousvessellineindex;
					//System.out.println("vesselline_line_index "+vesselline_line_index);
					//System.out.println("previousvessellineindex "+previousvessellineindex);
					//System.out.println("difference "+difference);
					////System.out.println("RepositionRegion "+RepositionRegion);
					////System.out.println("ReportType "+ReportType);
					////System.out.println("CargoType "+CargoType);
					
					if(difference<0) {difference=0;}else if(difference>4) {difference=4;}
					
					findheaderdata(vesselline_line_index, objheader, header_dataobj, difference);
					////System.out.println("RepositionRegion_1 "+RepositionRegion);
					////System.out.println("ReportType_1 "+ReportType);
					//System.out.println("objheader "+objheader);
					previousvessellineindex=Integer.parseInt(eachrowobj.getString("line_index"));
					 data = eachrowobj.getJSONObject("data");
					 //System.out.println("data "+data);
					 header = eachrowobj.getJSONObject("header");
					//System.out.println("header " + header);
					 new_data = new JSONObject();
					 new_header = new JSONObject();
					if(data.length()<6) {
						JSONObject newDataHeaderObj=singleStringParsingmethod(data, header, new_header, objheader);
						if(newDataHeaderObj.has("data")){data=newDataHeaderObj.getJSONObject("data");}
						if(newDataHeaderObj.has("header")){header=newDataHeaderObj.getJSONObject("header");}
						if(newDataHeaderObj.has("new_header")){new_header=newDataHeaderObj.getJSONObject("new_header");}

						
						
						eachrowobj_new.put("data",data);
						eachrowobj_new.put("header",header);
						eachrowobj_new.put("objheader",objheader);
						eachrowobj_new.put("new_header_data", new_header);
						
//						table_dataobj.getJSONObject(i).put("data",data);
//						table_dataobj.getJSONObject(i).put("header",header);
//
//						table_dataobj.getJSONObject(i).put("objheader",objheader);
//						table_dataobj.getJSONObject(i).put("new_header_data", header);
						//System.out.println("&&&&&&&&&&&& " + eachrowobj_new);
						table_dataobj_new.put(eachrowobj_new);

					}else {
						
						  header=  replaceLabels( header);

					Iterator itr = header.keys();
					while (itr.hasNext()) {
						String hkey = (String) itr.next();
						String hvalue = header.getString(hkey);
						String dvalue = data.getString(hkey);
						new_header.put(hkey, hvalue);
						//System.out.println("hvalue " + hvalue);
						//System.out.println("dvalue" + dvalue);

						// now check lable by lable
						if (hvalue.contains("unknown")) {
							// 1. call regax script
							// 2.call sorl no as no header
							// 3. call new solr api
							// 4.execute method
							String synonyms = " ";
							String solrmatch = callSolrforDataMatch(dvalue);
							String regexmatch = " ";
							String regexpattern = "";
							//System.out.println("dvalue "+dvalue);
							JSONObject regexmatchresult = callregex(dvalue);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
							if (checkjsonString) {
								if (regexmatchresult.has("Format")) {
									regexpattern = regexmatchresult.getString("Format");
								}
								if (regexmatchresult.has("Regex_Type")) {
									regexmatch = regexmatchresult.getString("Regex_Type");
								}
							}
							
							//System.out.println("hkey  "+hvalue  +"synonyms "+ synonyms +"regexmatch   "+regexmatch +" solrmatch "+ solrmatch );
							String mathodname = pallavi_SortJSON.executesolrQuery_getMethodName(" ".toUpperCase(),
									synonyms.toUpperCase(), regexmatch.toUpperCase(), solrmatch.toUpperCase(),
									"METHODNAME");
							//System.out.println("mathodname unknown " + mathodname);
							allmethodexceution(data, header, new_header, hkey, hvalue, dvalue, mathodname, synonyms,
									regexmatch, regexpattern, solrmatch, lhmap);
						} else {
							String solrKeyList = fetchsolrdata(hvalue.trim().replace(" ", "_"));
							// //System.out.println("solrKeyList "+solrKeyList);
							if (solrKeyList != null) {
								JSONObject headerNameobj = pallavi_SortJSON.parsesolrresp_updated(solrKeyList,
										"HeaderName", "Extrakey");
								//System.out.println("headerName:: " + headerNameobj);
								if (headerNameobj.has("HeaderName")
										&& !headerNameobj.getString("HeaderName").equalsIgnoreCase(" ")) {
									String headername = headerNameobj.getString("HeaderName");
									if (headerNameobj.has("Extrakey")) {
										//new_header.put(hkey, headername);
										// ************************************************************
										String Extrakey = headerNameobj.getString("Extrakey");
										//System.out.println("Extrakey "+Extrakey);
										// call stanbol to get data
										if (Extrakey.equalsIgnoreCase("RepositionRegion")) {
											JSONObject Stanbolmatchobj = getUrlByStanbol(hvalue);
											// //System.out.println("Stanbolmatchobj "+Stanbolmatchobj);
											if (Stanbolmatchobj.length() > 0) {
												// method to add data in json in appropriate key

												Iterator stanbolitr = Stanbolmatchobj.keys();
												// //System.out.println(Stanbolmatchobj.length());
												while (stanbolitr.hasNext()) {
													// //System.out.println("in stanbol while");
													String stanbolkey = (String) stanbolitr.next();
													String stanbolvalue = Stanbolmatchobj.getString(stanbolkey);
													// //System.out.println("stanbolkey "+stanbolkey +"
													// stanbolvalue"+stanbolvalue);
													if (stanbolvalue.equalsIgnoreCase("RepositionRegion") || stanbolvalue.equalsIgnoreCase("Port")) {
														String index=data.length() + "";
														data.put(index, stanbolkey);
														new_header.put(index, stanbolvalue);
														// //System.out.println("new_header ***^^^^%%%% "+new_header);
													}else {
														String index=data.length() + "";
														data.put(index, hvalue);
														new_header.put(index, "RepositionRegion");
													}
												}
											}else{
												//System.out.println("in else");
												String index=data.length()+"";
												data.put(index, hvalue);
												new_header.put(index, "RepositionRegion");
											}
										}
										if (Extrakey.equalsIgnoreCase("VesselType")) {
											JSONObject Stanbolmatchobj = getUrlByStanbol(hvalue);
											// //System.out.println("Stanbolmatchobj "+Stanbolmatchobj);
											if (Stanbolmatchobj.length() > 0) {
												// method to add data in json in appropriate key
												Iterator stanbolitr = Stanbolmatchobj.keys();
												// //System.out.println(Stanbolmatchobj.length());
												while (stanbolitr.hasNext()) {
													// //System.out.println("in stanbol while");
													String stanbolkey = (String) stanbolitr.next();
													String stanbolvalue = Stanbolmatchobj.getString(stanbolkey);
													 //System.out.println("stanbolkey "+stanbolkey +" stanbolvalue"+stanbolvalue);
													if (stanbolvalue.equalsIgnoreCase("VesselType")) {
														String index=data.length()+"";
														data.put(index, stanbolkey);
														new_header.put(index, stanbolvalue);
														// //System.out.println("new_header ***^^^^%%%% "+new_header);
													}else{
														//System.out.println("in else");
														String index=data.length()+"";
														data.put(index, hvalue);
														new_header.put(index, "VesselType");
													}
												}
											}else{
												//System.out.println("in else");
												String index=data.length()+"";
												data.put(index, hvalue);
												new_header.put(index, "VesselType");
											}
										}
										// *********************************************************

									}  // if no Extrakey
												// 1. call regax script
												// 2.call sorl
												// 3. call new solr api
												// 4.execute method
										String synonyms = headername;
										//System.out.println("synonyms "+synonyms);
										String solrmatch = callSolrforDataMatch(dvalue);
										//System.out.println("solrmatch "+solrmatch);
										//System.out.println("dvalue "+dvalue);

										String regexmatch = " ";
										String regexpattern = "";
										JSONObject regexmatchresult = callregex(dvalue);
										boolean checkjsonString = SaveReportDataClass
												.isJSONValid(regexmatchresult.toString());
										if (checkjsonString) {
											if (regexmatchresult.has("Format")) {
												regexpattern = regexmatchresult.getString("Format");
											}
											if (regexmatchresult.has("Regex_Type")) {
												regexmatch = regexmatchresult.getString("Regex_Type");
											}
										}
										//System.out.println("regexpattern "+regexpattern+" regexmatch "+regexmatch);
										String mathodname = pallavi_SortJSON.executesolrQuery_getMethodName(
												hvalue.toUpperCase(), synonyms.toUpperCase(), regexmatch.toUpperCase(),
												solrmatch.toUpperCase(), "METHODNAME");
										//System.out.println("mathodname withsynonym " + mathodname);
									if(mathodname.equalsIgnoreCase("Blank")) {
										String rematch= getSynonymmatch(synonyms, "DataType");
										String newdvalue=dvalue;
										if(rematch.equals(solrmatch)) {
											newdvalue=dvalue;
										}else {
											String newhkey=Integer.toString(Integer.parseInt(hkey)-1);
											if(header.has(newhkey)) {
												String newsolrmatch = callSolrforDataMatch(data.getString(newhkey));
                                               if(rematch.equals(newsolrmatch)) {
       											newdvalue=data.getString(newhkey);
                                               }
											}
										}
										//System.out.println("***here");
										allmethodexceution(data, header, new_header, hkey, hvalue, newdvalue, mathodname,
												synonyms, regexmatch, regexpattern, solrmatch, lhmap);
									}else{
										allmethodexceution(data, header, new_header, hkey, hvalue, dvalue, mathodname,
												synonyms, regexmatch, regexpattern, solrmatch, lhmap);
									}
								} else {
									// what if header do not match
									// 1. call regax script
									// 2.call sorl
									// 3. call new solr api
									// 4.execute method

									String synonyms = " ";
									//System.out.println("synonyms "+synonyms);
									String solrmatch = callSolrforDataMatch(dvalue);
									//System.out.println("solrmatch "+solrmatch);
									String regexmatch = " ";
									String regexpattern = "";
									//System.out.println("dvalue "+dvalue);
									JSONObject regexmatchresult = callregex(dvalue);
									boolean checkjsonString = SaveReportDataClass
											.isJSONValid(regexmatchresult.toString());
									if (checkjsonString) {
										if (regexmatchresult.has("Format")) {
											regexpattern = regexmatchresult.getString("Format");
										}
										if (regexmatchresult.has("Regex_Type")) {
											regexmatch = regexmatchresult.getString("Regex_Type");
										}
									}
	                                   //System.out.println("regexpattern "+regexpattern+" regexmatch "+regexmatch);

									String mathodname = pallavi_SortJSON.executesolrQuery_getMethodName(
											hvalue.toUpperCase(), synonyms.toUpperCase(), regexmatch.toUpperCase(),
											solrmatch.toUpperCase(), "METHODNAME");
									//System.out.println("mathodname withoutsynonym " + mathodname);
									allmethodexceution(data, header, new_header, hkey, hvalue, dvalue, mathodname,
											synonyms, regexmatch, regexpattern, solrmatch, lhmap);
								}
								//System.out.println(3);
							}
							//System.out.println(2);
                           }
					//System.out.println(1 + " hkey " + hkey +" data " + data);
					}
					
					////System.out.println(1+"data  "+data.toString());
					eachrowobj_new.put("data",data);
					//System.out.println(2+"header "+header);
					eachrowobj_new.put("header",header);
					//System.out.println(3+"objheader "+objheader);
					eachrowobj_new.put("objheader",objheader);
					//System.out.println(4+"new_header "+new_header);
					eachrowobj_new.put("new_header_data", new_header);
					//System.out.println(5);
					

					//table_dataobj.getJSONObject(i).put("objheader",objheader);
					//table_dataobj.getJSONObject(i).put("new_header_data", new_header);
					//System.out.println("&&&&&&&&&&&& " + eachrowobj_new);
					//System.out.println("***************************************************************************************************");
					table_dataobj_new.put(eachrowobj_new);

					}
				
					
				
				}}
				// call method to get averagerate position from lhmap

				Tableobj_new.put("Rate_Position", FindAverageRatePosition(lhmap));
				
				Tableobj_new.put("table_data", table_dataobj_new);
				Tableobj_new.put("header_data", header_dataobj);
				returnobj.put(TableName, Tableobj_new);
			}

			System.out.println("returnobj " + returnobj);
//			pallavi_WriteFile.writeUsingOutputStream( "SCRIPTJSON ::====== "+pythonScriptApiData.toString() +
//					"\n OUTPUTJSON::====== "+returnobj .toString() , 
//					filepath);

		//	return returnobj.toString();
		} catch (Exception e) {
//			pallavi_WriteFile.writeUsingOutputStream( "SCRIPTJSON ::====== "+pythonScriptApiData.toString() +
//					"\n OUTPUTJSON::====== "+returnobj .toString() +"\n error"+ e.getMessage(), 
//					filepath);
		e.printStackTrace();
      System.out.println("error  "+e.getMessage());
		//	return returnobj.toString();
		}
		}
	
	public static String FindAverageRatePosition(LinkedHashMap<String, Integer> lhmap) {
		String rateposition = "";
		int maxrate = 0;
		try {
		if (lhmap.size() > 0) {
			for (String key : lhmap.keySet()) {
				int value = lhmap.get(key);
				if (value > maxrate) {
					maxrate = value;
					rateposition = key;
				}
			}
		}}catch(Exception e) {
			e.printStackTrace();
		}
		return rateposition;
	}

	public static String callSolrforDataMatch(String dvalue) {
		String DocumentName = " ";
		String solrres;
		try {
			 if(dvalue.contains(":")) { dvalue=dvalue.replace(":", "");}
             if(dvalue.contains("*")) {dvalue=dvalue.replace("*", "");}
             if(dvalue.contains("\\")) { dvalue=dvalue.replace("\\", "");}
             //if(dvalue.contains("+")) { dvalue=dvalue.replace("+", "");}
             if(dvalue.contains("#")) {dvalue=dvalue.replace("#", "");}
             if(dvalue.contains("?")) {dvalue=dvalue.replace("?", "");}
             
			solrres = pallavi_SortJSON.executesolrQuery_bywords(dvalue.toUpperCase().replace(" ", "_"));
			boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
			if (checkjsonString) {
				DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			solrres=null;
		}
		return DocumentName;
	}

	public static void allmethodexceution(JSONObject data, JSONObject header, JSONObject new_header, String hkey,
			String hvalue, String dvalue, String mathodname, String synonyms, String regexmatch, String regexpattern,
			String solrmatch, LinkedHashMap<String, Integer> lhmap) {
		JSONObject regexmatchresult=null;
		JSONObject regexmatchresult_2=null;
		String index="";
		JSONObject Stanbolmatchobj=null;
		try {
			//System.out.println("dvalue "+dvalue);
			if (mathodname.equalsIgnoreCase("method1")) {
				String[] words = dvalue.split("/");
				int portcount = 0;
				for (int i = 0; i < words.length; i++) {
					String solrres = pallavi_SortJSON
							.executesolrQuery_bywords(words[i].toUpperCase().replace(" ", "_"));
					boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
					if (checkjsonString) {
						// parsejson from solr //get DocumentName
						String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
						//System.out.println("DocumentName  " + DocumentName);
						if (!DocumentName.equals("Port")) {
							portcount++;
						}
					}
				}
				if (portcount > 0) {
					data.put(hkey, words[0]);
					new_header.put(hkey, "LoadPort");
					if (words.length > 1) {
						 index=data.length()+"";
						data.put(index, words[0]);
						new_header.put(index, "DiscPort");
					}
				} else {
					data.put(hkey, words[0]);
					new_header.put(hkey, "LoadPort");
				}
			} else if (mathodname.equalsIgnoreCase("method2")) {
				data.put(hkey, dvalue);
				new_header.put(hkey, "OpenPort");
			} else if (mathodname.equalsIgnoreCase("method3")) {
				// call method to convert date in standard form
				 regexmatchresult = callregex(dvalue);
				boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
				if (checkjsonString) {
					if (regexmatchresult.has("Format")) {
						regexpattern = regexmatchresult.getString("Format");
					}
					if (regexmatchresult.has("Regex_Type")) {
						regexmatch = regexmatchresult.getString("Regex_Type");
					}
				}
				String date = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				data.put(hkey, date);
				new_header.put(hkey, "OpenDate");

			} else if (mathodname.equalsIgnoreCase("method4")) {
				String[] words = dvalue.split(" ");
				String word = "";
				for (int i = 0; i < words.length - 1; i++) {
					word = word + " " + words[i];
					//System.out.println( "word "+word);
				}
				data.put(hkey, word);
				new_header.put(hkey, "OpenPort");

				if (words.length > 0) {
					String date = words[words.length - 1];
					//System.out.println("date  "+date);
					// call regex method to get date pattern
					// convert date into standard format
					// data.put(data.length()+"", date);
					 regexmatchresult = callregex(date);
					//System.out.println("regexmatchresult "+regexmatchresult);
					boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
					if (checkjsonString) {
						if (regexmatchresult.has("Format")) {
							regexpattern = regexmatchresult.getString("Format");
						}
						if (regexmatchresult.has("Regex_Type")) {
							regexmatch = regexmatchresult.getString("Regex_Type");
						}
					}
					String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(date,
							regexpattern);
					 index=data.length()+"";
					data.put(index, date_updared);
					new_header.put(index, "OpenDate");
				}

			} else if (mathodname.equalsIgnoreCase("method5")) {
				// call method to get two dated saperated by &
				String Date1 = "";
				String Date2 = "";
				String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				if (date_updared.contains("&")) {
					String[] datearr = date_updared.split("&");
					if (datearr.length > 1) {
						Date1 = datearr[0];
						Date2 = datearr[1];
						data.put(hkey, Date1);
						new_header.put(hkey, "LCStart");
						 index=data.length() + "";
						data.put(index, Date2);
						new_header.put(index, "LCEnd");
					}else if(datearr.length ==1) {
						Date1 = datearr[0];
						data.put(hkey, Date1);
						new_header.put(hkey, "LCStart");

					}

				}

			} else if (mathodname.equalsIgnoreCase("method6")) {

				String[] words = dvalue.split("/");
				int portcount = 0;
				for (int i = 0; i < words.length; i++) {
					String solrres = pallavi_SortJSON
							.executesolrQuery_bywords(words[i].toUpperCase().replace(" ", "_"));
					boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
					if (checkjsonString) {
						// parsejson from solr //get DocumentName
						String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
						//System.out.println("DocumentName  " + DocumentName);
						if (DocumentName.equals("Port")) {
							portcount++;
						}
					}
				}
				if (portcount > 0) {
					data.put(hkey, words[0]);
					new_header.put(hkey, "Port");
					if (words.length > 1) {
						 index= data.length() + "";
						data.put(index, words[0]);
						new_header.put(index, "Port");
					}
				} else {
					data.put(hkey, dvalue);
					new_header.put(hkey, "Port");
				}

			} else if (mathodname.equalsIgnoreCase("method7")) {
				String[] words = dvalue.split(" ");
				String word = "";
				for (int i = 0; i < words.length - 1; i++) {
					word = word + " " + words[i];
				}
				data.put(hkey, word);
				new_header.put(hkey, "Port");

				if (words.length > 0) {
					String date = words[words.length - 1];
					// all regex method to get date pattern
					// convert date into standard format
					// data.put(data.length()+"", date);
					// new_header.put(data.length()+"", "Date");
					
					
					 regexmatchresult = callregex(date);
					boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
					if (checkjsonString) {
						if (regexmatchresult.has("Format")) {
							regexpattern = regexmatchresult.getString("Format");
						}
						if (regexmatchresult.has("Regex_Type")) {
							regexmatch = regexmatchresult.getString("Regex_Type");
						}
					}
					String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue,
							regexpattern);
					 index=data.length() + "";
					data.put(index, date_updared);
					new_header.put(index, "Date");

				}

			} else if (mathodname.equalsIgnoreCase("method8")) {
				// call method to get two dated saperated by &
				String Date1 = "";
				String Date2 = "";
				String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				if (date_updared.contains("&")) {
					String[] datearr = date_updared.split("&");
					if (datearr.length > 1) {
						Date1 = datearr[0];
						Date2 = datearr[1];
						data.put(hkey, Date1);
						new_header.put(hkey, "Date");
						 index=data.length() + "";
						data.put(index, Date2);
						new_header.put(index, "Date");
					}else if(datearr.length ==1) {
						Date1 = datearr[0];
						data.put(hkey, Date1);
						new_header.put(hkey, "Date");
					}
				}
			} else if (mathodname.equalsIgnoreCase("method9")) {
				if (lhmap.containsKey(hkey)) {
					int count = (int) lhmap.get(hkey);
					lhmap.put(hkey, count + 1);
				} else {
					lhmap.put(hkey, 1);
				}
				data.put(hkey, dvalue);
				new_header.put(hkey, "Rate");

			} else if (mathodname.equalsIgnoreCase("Blank")) {
				//System.out.println("in blank method");
				 regexmatchresult = callregex(dvalue.toUpperCase());
				//System.out.println("regexmatchresult "+regexmatchresult);
				boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
				//System.out.println("checkjsonString "+checkjsonString);
				if (checkjsonString) {
					//System.out.println("in checkjsonString ");
					if (regexmatchresult.has("Format")) {
						regexpattern = regexmatchresult.getString("Format");
					}
					if (regexmatchresult.has("Regex_Type")) {
						regexmatch = regexmatchresult.getString("Regex_Type");
					}
				}
				//System.out.println("regexpattern "+regexpattern  +" regexmatch "+regexmatch);
				String date="";
				if(synonyms.equalsIgnoreCase("Date") || solrmatch.equalsIgnoreCase("Date") || regexmatch.equalsIgnoreCase("date") || regexmatch.equalsIgnoreCase("date/date")) {
				if(!regexpattern.equals("")) {
					date = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				}else {
					date=new pallavi_ConvertDate().convertDateFormat( dvalue);
				}
				}
				//System.out.println("date"+date);
				if (!synonyms.equalsIgnoreCase(" ")) {
					//System.out.println("!synonyms.equalsIgnoreCase(\" \")"+!synonyms.equalsIgnoreCase(" "));
					if(regexmatch.equalsIgnoreCase("date") || regexmatch.equalsIgnoreCase("date/date")) {
					data.put(hkey, date);
					}
					new_header.put(hkey, synonyms);
				} else if (!solrmatch.equalsIgnoreCase(" ")) {
					//System.out.println("!solrmatch.equalsIgnoreCase(\" \") "+!solrmatch.equalsIgnoreCase(" "));
					if(regexmatch.equalsIgnoreCase("date")) {
					data.put(hkey, date);
					}else if(solrmatch.equalsIgnoreCase(date)){
						String date1 = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);

						data.put(hkey, date1);
					}else {
						data.put(hkey, dvalue);

					}
					new_header.put(hkey, solrmatch);
				}else if((!regexmatch.equalsIgnoreCase(" ")) && (regexmatch.equalsIgnoreCase("Rate") || regexmatch.equalsIgnoreCase("Date") )){
					// do nothing
					new_header.put(hkey, regexmatch);

				}else if (regexmatch.equalsIgnoreCase("Word/Date")){
					String[] words = dvalue.split(" ");
					String word = "";
					for (int i = 0; i < words.length - 1; i++) {
						word = word + " " + words[i];
						//System.out.println( "word "+word);
					}
					data.put(hkey, word);
					new_header.put(hkey, "Port");

					if (words.length > 0) {
						 date = words[words.length - 1];
						//System.out.println("date  "+date);
						// call regex method to get date pattern
						// convert date into standard format
						// data.put(data.length()+"", date);
						 regexmatchresult = callregex(date.trim());
						//System.out.println("regexmatchresult "+regexmatchresult);
						boolean checkjsonStringw_d = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
						if (checkjsonStringw_d) {
							if (regexmatchresult.has("Format")) {
								regexpattern = regexmatchresult.getString("Format");
							}
							if (regexmatchresult.has("Regex_Type")) {
								regexmatch = regexmatchresult.getString("Regex_Type");
							}
						}
						//System.out.println("date_w_d "+date+" regexpattern "+regexpattern+" "+regexmatch);
						String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(date,
								regexpattern);
						 index=data.length()+"";
						data.put(index, date_updared);
						new_header.put(index, "Date");
					}
				
			    }else if(regexmatch.equalsIgnoreCase("Word/Word")) {
			    	String[] words = dvalue.split("/");
					int portcount = 0;
					for (int i = 0; i < words.length; i++) {
						//System.out.println("words[i]  "+words[i]);
						String solrres = pallavi_SortJSON
								.executesolrQuery_bywords(words[i].toUpperCase().replace(" ", "_"));
						 checkjsonString = SaveReportDataClass.isJSONValid(solrres);
						if (checkjsonString) {
							// parsejson from solr //get DocumentName
							String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
							//System.out.println("DocumentName  " + DocumentName);
							if (DocumentName.equals("Port")) {
								portcount++;
							}else if(DocumentName.equals("RepositionRegion")) {
								portcount++;
							}
							
						}
					}
					//System.out.println("portcount  "+portcount);
					if (portcount > 0) {
						data.put(hkey, words[0]);
						new_header.put(hkey, "Port");
						if (words.length > 1) {
							 index= data.length() + "";
							data.put(index, words[1]);
							new_header.put(index, "Port");
						}
					} else {
						data.put(hkey, dvalue);
						new_header.put(hkey, "Port");
					}
			    	
			    } if(hvalue.equalsIgnoreCase("open") || hvalue.equalsIgnoreCase("EXPORT/DATE") || 
			    		hvalue.equalsIgnoreCase("EXPORT/PORT")|| hvalue.equalsIgnoreCase("OPEN_PORT")){
					//send data to stanbol
					dvalue=dvalue.toUpperCase();
					  //System.out.println("dvalue* "+dvalue);
						  Stanbolmatchobj= getUrlByStanbol(dvalue);
						 //System.out.println("Stanbolmatchobj "+Stanbolmatchobj);
			      if(Stanbolmatchobj.length()>0 ) {
			    	  // method to add data in json in appropriate key 
			    	  Iterator stanbolitr=Stanbolmatchobj.keys();
			    	  //System.out.println(Stanbolmatchobj.length());
			    	  String ports="";
			    	  while(stanbolitr.hasNext()) {
			    		  String stabbolkey=(String) stanbolitr.next();
			    		  String Stranbolvalue= Stanbolmatchobj.getString(stabbolkey);
			    		  if(Stranbolvalue.equalsIgnoreCase("port")) {
			    			  ports=ports+""+stabbolkey;
			    		  }else {
			    			   index=data.length()+"";
			    		  data.put(index, stabbolkey);
			    		  new_header.put(index, Stranbolvalue);
			    		  }
			  			////System.out.println("stabbolkey  **  "+stabbolkey +" Stranbolvalue "+Stranbolvalue);
			    		  //System.out.println("dvalue ** "+dvalue);
			    		  //System.out.println("ports "+ports);
			    		if(dvalue.contains(stabbolkey.toUpperCase())) {
			    			dvalue=dvalue.replace(stabbolkey.toUpperCase(), "");
			    		}
			    	  }
			    	   index=data.length()+"";
		    		  data.put(index, ports);
		    		  new_header.put(index, "OpenPort");
			    	  }
			      //System.out.println("dvalue % "+dvalue);
			      // send dvalue to regex to check if it is data or not
					 regexmatchresult_2 = callregex(dvalue.toUpperCase().trim());
					//System.out.println("regexmatchresult "+regexmatchresult);
					boolean checkjsonString_2 = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
					//System.out.println("checkjsonString "+checkjsonString);
					if (checkjsonString_2) {
						if (regexmatchresult.has("Format")) {
							regexpattern = regexmatchresult.getString("Format");
						}
						if (regexmatchresult.has("Regex_Type")) {
							regexmatch = regexmatchresult.getString("Regex_Type");
						}
					}
					//System.out.println(regexpattern  +" "+regexmatch);
					String date_2="";
					if( regexmatch.equalsIgnoreCase("date")) {
					if(!regexpattern.equals("")) {
						date = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
						 index=data.length()+"";
						data.put(index, date);
			    		  new_header.put(index, "OpenDate");
					}}else {	
						if(dvalue.contains(" ")) {
						dvalue= dvalue.replace(" ", "");
						}
						if(!dvalue.equals("")){
						data.put(hkey, dvalue);
			    		  new_header.put(hkey, hvalue);
					}}
				}else {
					//do nothing
				}

			}
			//System.out.println("end ofblank method");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {index=null;
		
		}
	}

	public static String fetchsolrdata(String q) {

		// String f1 = "*,score";
		StringBuffer response1 = null;
		// //System.out.println("q: "+q);
		try {
			// q = q.replace(" ", "%20");
			q = URLEncoder.encode(q, "UTF-8");

			String url = "";

			// url = "http://34.73.112.165:8983/solr/TemplateCore/select?q=" + q
			// + "&fl=" + f1 + "&df=" + df;

			// url = "http://34.73.112.165:8983/solr/Header_Synomes/select?q=" +
			// q + "&fl=" + f1;
			// url =
			// "http://34.73.112.165:8983/solr/Header_Synomes/select?q=Data:" +
			// q + "&fl=" + "HeaderName";
			url = "http://34.73.112.165:8983/solr/Header_Synomes/select?q=Data_str:" + q
					+ "&fl=score,HeaderName,Extrakey";
			// url =
			// "http://34.73.112.165:8983/solr/Header_Synomes/select?q=Data:("+q+")&fl=HeaderName";

			// url = url.replace(" ", "%20");

			// //System.out.println("url " + url);
			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			// int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			// //System.out.println(response1);
			in.close();

		} catch (Exception e) {
			// e.printStackTrace();
			// //System.out.println(e.getMessage());
			return "";
		}
		return response1.toString();

	}

	public static JSONObject getUrlByStanbol(String adata) {
		JSONObject urlnadvalue = new JSONObject();
		JSONObject obj=null;
		JSONArray arrobj=null;
		try {
			adata = adata.toLowerCase();
			//System.out.println("adata " + adata);
			if (!adata.isEmpty()) {
				String stanboljson = stanbolBreakResponse(adata);
				// //System.out.println("stanboljson "+stanboljson);
				 arrobj = new JSONArray(stanboljson);
				// //System.out.println(arrobj.length());

				for (int i = 0; i < arrobj.length(); i++) {
					 obj = arrobj.getJSONObject(i);
					String entity_ref_url = null;
					String entity_vlaue = null;
					;
					String entity_type = null;

					if (obj.has("http://fise.iks-project.eu/ontology/entity-reference")) {
						JSONArray entityref = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-reference");
						entity_ref_url = entityref.getJSONObject(0).getString("@id");

						// //System.out.println(entity_ref_url);
					}
					if (obj.has("http://fise.iks-project.eu/ontology/entity-label")) {
						JSONArray entityrlabel = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-label");
						entity_vlaue = entityrlabel.getJSONObject(0).getString("@value");
						// //System.out.println(entity_vlaue);
					}
					if (obj.has("http://fise.iks-project.eu/ontology/entity-type")) {
						JSONArray entityrtype = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-type");
						entity_type = entityrtype.getJSONObject(0).getString("@id");
						// //System.out.println(entity_type);
						if (entity_type.contains("#")) {
							entity_type = entity_type.substring(entity_type.lastIndexOf("#") + 1);
						}
					}
					if ((entity_vlaue != null)) {
						urlnadvalue.put(entity_vlaue, entity_type);
						// subfinaljson.put(entity_type, entity_vlaue);
						// if (adata.contains(entity_vlaue.toLowerCase())) {
						// //System.out.println("contains");
						// adata = adata.replace(entity_vlaue.toLowerCase(), "");
						//
						// }
					}

				}
			}
//			if (!adata.equals("")) {
//				urlnadvalue.put(adata, "unknown");
//
//			}
		} catch (JSONException e) {
			e.printStackTrace();
			try {
				return urlnadvalue.put("error", e.getMessage());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return urlnadvalue;

	}

	public static String stanbolBreakResponse(String plain_text) {
		String res = null;
		try {
			BufferedReader reader = null;

			int BUFFER_SIZE = 4096;
			InputStream isr = new ByteArrayInputStream(plain_text.toString().getBytes("UTF-8"));

			//URL url = new URL("http://35.227.82.195:8080/enhancer/chain/scorpiosvchain");
			URL url = new URL("http://35.227.82.195:8080/enhancer/chain/scorpiosvchain2");

			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

			httpConn.setUseCaches(false);
			httpConn.setDoOutput(true);
			// httpConn.setRequestProperty("Content-Type", "application/json");
			// httpConn.setRequestProperty("Content-Type", "text/plain");
			// httpConn.setRequestProperty("Accept","application/rdf+xml");
			// httpConn.setRequestProperty("Content-Type","application/pdf");
			// httpConn.setRequestMethod("POST");

			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.setRequestProperty("Content-Type", "application/pdf");
			// httpConn.setRequestProperty("Content-Type", "application/json");
			// httpConn.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");

			OutputStream outputStream = httpConn.getOutputStream();
			// outputStream.write("file=".getBytes());

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = isr.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			// //System.out.println("outputStream"+outputStream.toString());
			outputStream.flush();
			outputStream.close();
			isr.close();
			int responseCode = httpConn.getResponseCode();
			// //System.out.println("responseCode " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {

				reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
				String response = reader.readLine();

				String line1;
				StringBuffer sb = new StringBuffer();
				sb.append("[{");
				while ((line1 = reader.readLine()) != null) {
					sb.append(line1);
				}
				// //System.out.println(sb.toString());
				res = sb.toString();
			}
			// //System.out.println(res);

		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return res;
	}

	public static JSONObject callregex(String dvalue) throws UnsupportedEncodingException {
		int responseCode = 0;
		String urlParameters = "";
		StringBuffer response = new StringBuffer();
		JSONObject rtnobj = new JSONObject();
		if(dvalue.contains("00:00:00")){
			dvalue.replace("00:00:00", "");
			
		}
		dvalue=dvalue.trim();
		dvalue=dvalue.toUpperCase();
		
		JSONArray arr=null;
		JSONObject obj=null;
		try {
			// String url1 =
			// "http://35.186.170.65:8983/solr/Vessel/update/json/docs?commit=true";
			// String url1 =
			// "http://35.186.170.65:8983/solr/HSN_Code/update/json/docs?commit=true";
			// String url1 =
			// "http://34.73.112.165:8983/solr/PO_FlatteningTemplate/update/json/docs?commit=true";
			String url1 = "http://dev.bizlem.io:5032/date";

			URL url = new URL(url1);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			// out.println("urlParameters:: " + obj.toString());
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			// urlParameters = urlParameters.replace(" ", "%20");
			wr.writeBytes(dvalue);
			wr.flush();
			wr.close();
			responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// out.println(response.toString());
			//System.out.println(" response "+response.toString());
			if (!response.toString().equalsIgnoreCase("")) {
				 arr = new JSONArray(response.toString());
				//System.out.println(arr);
				int count = 0;
				int jsonno = 0;
				for (int i = 0; i < arr.length(); i++) {
					 obj = arr.getJSONObject(i);
					String length = "";
					if (obj.has("length")) {
						length = obj.getString("length");
					}
					if (Integer.parseInt(length) > count) {
						count = Integer.parseInt(length);
						jsonno = i;
					}

				}
				if (arr.length() > jsonno) {
					 obj = arr.getJSONObject(jsonno);
					if (obj.has("Format")) {
						rtnobj.put("Format", obj.getString("Format"));
					}
					if (obj.has("Regex_Type")) {
						rtnobj.put("Regex_Type", obj.getString("Regex_Type"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// out.println(e.getMessage());
		}
		return rtnobj;
	}
	
	public static void findheaderdata(int vesselline_line_index_currentline, JSONObject objheader,
			JSONArray header_dataobj , int difference ) {
	
	//int difference=0;
		
//		String RepositionRegion;
//		String ReportType;
//		String CargoType;
		JSONObject sub_header_dataobj=null;
		JSONArray arraydata=null;
		JSONObject subdata=null;
	try {
		for (int i=0;i<header_dataobj.length(); i++){
			 sub_header_dataobj = header_dataobj.getJSONObject(i);
			
			int line_index=Integer.parseInt(sub_header_dataobj.getString("line_index"));
			//int vesselline_line_index_int= Integer.parseInt(vesselline_line_index_currentline);
			int vesselline_line_index_int= vesselline_line_index_currentline;
			if(difference==-1) {
int oneless =vesselline_line_index_int-1;
int twoless =vesselline_line_index_int-2;
int threeless =vesselline_line_index_int-3;

if((line_index< oneless) || (line_index< twoless) || (line_index < threeless)) {
	
	 arraydata= sub_header_dataobj.getJSONArray("data");
	for(int j=0; j<arraydata.length(); j++) {
		 subdata= arraydata.getJSONObject(j);
		String url =subdata.getString("url");
		String word= subdata.getString("word");
		String key ="";
		if(url.contains("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018")) {
			key=url.substring(url.indexOf("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018")+"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018".length()+1);
			//System.out.println("key*******   "+key);

				////System.out.println("word "+word);
			        if(key.equalsIgnoreCase("ReportType")) {
						if(!word.equalsIgnoreCase("")) {
							word=word.toUpperCase().replaceAll(" ", "_");
					////System.out.println("word *   "+word);
			        	String resp=getreporttypebysolr(word);
						boolean checkjsonString = SaveReportDataClass.isJSONValid(resp);
						////System.out.println("resp  "+resp);
						if (checkjsonString) {
							// parsejson from solr //get DocumentName
							String HeaderName = pallavi_SortJSON.parsesolrresp(resp, "HeaderName");
							////System.out.println("HeaderName "+HeaderName);
							if(!HeaderName.equalsIgnoreCase("")) {
								//ReportType=HeaderName;
								objheader.put("ReportType", HeaderName);
								////System.out.println("objheader  "+objheader);
					        	}}
						}}
					if(key.equalsIgnoreCase("RepositionRegion")) {
						if(!word.equalsIgnoreCase("")) {
							//RepositionRegion=word;
							objheader.put("RepositionRegion", word);

						}}
					if(key.equalsIgnoreCase("CargoType") ) {
						if(!word.equalsIgnoreCase("")) {
						//CargoType=word;
							objheader.put("CargoType", word);
					}}
			
			}		
		}
		}
	
		}else if( difference!=0 && difference!=1 ) {//<difference
			//if(difference>4) {difference=4;}
////System.out.println("difference* :: i :: "+ i + " :: "+difference);
			for(int k=difference-1;k>0; k--) {
				////System.out.println("k* "+k);
				int lineless=vesselline_line_index_int-k;
				////System.out.println("vesselline_line_index_int*  "+vesselline_line_index_int);

				////System.out.println("lineless*  "+lineless);
				if((line_index==lineless)) {
////System.out.println("inside");
				 arraydata= sub_header_dataobj.getJSONArray("data");
				for(int j=0; j<arraydata.length(); j++) {
					 subdata= arraydata.getJSONObject(j);
					String url =subdata.getString("url");
					String word= subdata.getString("word");
					String key ="";
					
					if(url.contains("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018")) {
						key=url.substring(url.indexOf("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018")+"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018".length()+1);
						//System.out.println("key*******   "+key);
					    if(key.equalsIgnoreCase("ReportType")) {
							if(!word.equalsIgnoreCase("")) {
								word=word.toUpperCase().replaceAll(" ", "_");
						////System.out.println("word *   "+word);
				        	String resp=getreporttypebysolr(word);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(resp);
							//System.out.println("resp  "+resp);
							if (checkjsonString) {
								// parsejson from solr //get DocumentName
								String HeaderName = pallavi_SortJSON.parsesolrresp(resp, "HeaderName");
								////System.out.println("HeaderName "+HeaderName);
								if(!HeaderName.equalsIgnoreCase("")) {
									//ReportType=HeaderName;
									objheader.put("ReportType", HeaderName);
						        	}}
							}}
						        if(key.equalsIgnoreCase("RepositionRegion")) {
						        	if(!word.equalsIgnoreCase("")) {
						        	// RepositionRegion=word;
										objheader.put("RepositionRegion", word);
						        	}}
								if(key.equalsIgnoreCase("CargoType") ) {
									if(!word.equalsIgnoreCase("")) {
									//CargoType=word;
									objheader.put("CargoType", word);
								}}
						}		
					}
				}
		}
	}
		}
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	
	public static String getreporttypebysolr(String passvalue){
		StringBuffer response1=null;
		try {

		passvalue=passvalue.toUpperCase();
		passvalue=passvalue.replaceAll(" ", "_");
		////System.out.println(passvalue);

		passvalue = URLEncoder.encode(passvalue, "UTF-8");

		String url = "";
		url = "http://34.73.112.165:8983/solr/ReportType_Synomes/select?q=Data_str:" + passvalue + "&fl=score,HeaderName";

		URL url1 = new URL(url);
		//System.out.println("url1 "+url1);
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		response1 = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
		response1.append(inputLine);
		}
		////System.out.println(response1);
		in.close();

		} catch (Exception e) {
e.printStackTrace();
		}
		return response1.toString();
		}
	
	public static String getSynonymmatch(String synonym,String DataType){
		StringBuffer response1=null;
		JSONObject Subsorrespobj=null;
		JSONArray docs=null;
		 JSONObject subdocs=null;
		try {
			//System.out.println("in getSynonymmatch");
		//passvalue=passvalue.toUpperCase();
		//passvalue=passvalue.replaceAll(" ", "_");
		////System.out.println(passvalue);
			synonym = URLEncoder.encode(synonym, "UTF-8");
		String url = "";
		url="http://34.73.112.165:8983/solr/HeaderAndDataValidation/select?fl=score,DataType&q=Headersynonyms:"+synonym;
		//url = "http://34.73.112.165:8983/solr/ReportType_Synomes/select?q=Data_str:" + passvalue + "&fl=score,HeaderName";
		//System.out.println("url " +url);
		URL url1 = new URL(url);
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		response1 = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		response1.append(inputLine);
		}
		////System.out.println(response1);
		in.close();
		 JSONObject sorrespobj = new JSONObject(response1.toString());
      if(sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore") && sorrespobj.getJSONObject("response").has("docs")) {
    // //System.out.println("in parse json ********");
       Subsorrespobj=sorrespobj.getJSONObject("response");
       docs=Subsorrespobj.getJSONArray("docs");
      double maxscore=Subsorrespobj.getDouble("maxScore");
    //  //System.out.println("maxscore "+maxscore);
    for(int i=0;i<docs.length();i++) {
      subdocs=docs.getJSONObject(i);
     double score=subdocs.getDouble("score");
     if(score==maxscore) {
     	////System.out.println("maxscore "+maxscore+" score   "+score);
   	   DataType=subdocs.getJSONArray(DataType).getString(0);
     }}}
      //System.out.println("DataType "+DataType);
      //System.out.println("end of getSynonymmatch");
		return DataType;
		} catch (Exception e) {
return DataType;
		}}
	
	 public static JSONObject singleStringParsingmethod(JSONObject data, JSONObject header, JSONObject new_header, JSONObject objheader){
			String alldata="";
			JSONObject returnobj=null;
			JSONObject stanboljson=null;
			JSONArray stanbolarr=null;
			JSONObject dateratejson=null;
			JSONArray dateratearr=null;
			JSONObject solrjson=null;
			JSONArray solrarr=null;
			JSONObject obj=null;
			String alldata_return="";
			TreeMap<Integer, JSONObject> indexwisemap=null;  
			try {
				returnobj= new JSONObject();
		if(data.length()<6) {
			Iterator ditr= data.keys();  while (ditr.hasNext()) {alldata=alldata+" "+data.getString((String) ditr.next());}
			alldata=alldata.toUpperCase();
			stanboljson=getUrlByStanbol_withIndex(alldata,alldata);
			 if(stanboljson!=null) {
			if(stanboljson.has("stanbolarr")) {
				stanbolarr=stanboljson.getJSONArray("stanbolarr");
			}
			if(stanboljson.has("alldata_return")) {
				alldata_return=stanboljson.getString("alldata_return");
			}}
			dateratejson=callregex_forTotalmatch(alldata, alldata_return);
			 if(dateratejson!=null) {
			if(dateratejson.has("datearr")) {
				dateratearr=dateratejson.getJSONArray("datearr");
			}
			if(dateratejson.has("alldata_return")) {
				alldata_return=dateratejson.getString("alldata_return");
			}
			 }
			 solrjson =callToMatchRemainingString( alldata, alldata_return);
			 if(solrjson!=null) {
			 if(solrjson.has("datearr")) {
					solrarr=solrjson.getJSONArray("solrarr");
				}
				if(solrjson.has("alldata_return")) {
					alldata_return=solrjson.getString("alldata_return");
				}
			 }
		System.out.println("alldata "+alldata);
		System.out.println("alldata_return  "+alldata_return);
		System.out.println("stanbolarr  "+stanbolarr);
		System.out.println("dateratearr "+dateratearr);
		System.out.println("solrarr "+solrarr);

		//call method to send data one by one in stanbol and try to match
		
		
       // now create hashtree and create sequance according to index
		indexwisemap= new TreeMap<Integer, JSONObject>();
		if(stanbolarr!=null)
		for(int i=0;i<stanbolarr.length();i++) {
			 obj =stanbolarr.getJSONObject(i);
			int Index=obj.getInt("Index");
			int Index2=obj.getInt("Index2");
			int Index3=obj.getInt("Index3");
			if(Index!=-1) {indexwisemap.put(Index, obj);}
			if(Index2!=-1) {indexwisemap.put(Index2, obj);}
			if(Index3!=-1) {indexwisemap.put(Index3, obj);}
		}}
		if(dateratearr!=null) {
		for(int i=0;i<dateratearr.length();i++) {
			 obj =dateratearr.getJSONObject(i);
			int Index=obj.getInt("Index");
			int Index2=obj.getInt("Index2");
			int Index3=obj.getInt("Index3");
			if(Index!=-1) {indexwisemap.put(Index, obj);}
			if(Index2!=-1) {indexwisemap.put(Index2, obj);}
			if(Index3!=-1) {indexwisemap.put(Index3, obj);}
		}}
			if(solrarr!=null) {
		for(int i=0;i<solrarr.length();i++) {
			 obj =solrarr.getJSONObject(i);
			int Index=obj.getInt("Index");
			int Index2=obj.getInt("Index2");
			int Index3=obj.getInt("Index3");

			if(Index!=-1) {indexwisemap.put(Index, obj);}
			if(Index2!=-1) {indexwisemap.put(Index2, obj);}
			if(Index3!=-1) {indexwisemap.put(Index3, obj);}
		}}
		
		
		System.out.println("indexwisemap "+indexwisemap);
		data=new JSONObject();
		header=new JSONObject();
		int count=0;
		int previousend_index=0;
        for (Map.Entry< Integer,JSONObject> entry : indexwisemap.entrySet()) {
        	Integer index=entry.getKey();
    		System.out.println("previousend_index "+previousend_index);

        	System.out.println("index "+index);
            JSONObject valuejson=entry.getValue();
            if(valuejson.has("key") && valuejson.has("value")) {
            //System.out.println("valuejson "+valuejson);
        	String key=valuejson.getString("key");
        	String value= valuejson.getString("value");
        	int valuelength=valuejson.getInt("valuelength");
        	if(key.equalsIgnoreCase("Date/Date")) {
        		System.out.println("index>previousend_index "+ (index>previousend_index));
        		if(index>previousend_index) {
        		if(value.contains("&")) {
        			String [] dates=value.split("&");
        			for(int d=0; d<dates.length; d++) {
        				String Date=dates[d];
                    data.put(count+"", Date);
                    header.put(count+"", "Date");
                    count++;
        			}
        		}else {  
        			data.put(count+"", value);
                    header.put(count+"", "Date");
                    count++;
                  }
        		}}else if(key.equalsIgnoreCase("Rate")){
            		if(index>previousend_index) {
                        data.put(count+"", valuejson.getString("value"));
                        header.put(count+"", key);
                        count++;
            		}
        		}else if(key.equalsIgnoreCase("Date")){
            		if(index>previousend_index) {
                        data.put(count+"", valuejson.getString("value"));
                        header.put(count+"", key);
                        count++;
            		}
        		}else{
            data.put(count+"", valuejson.getString("value"));
            if(key.equalsIgnoreCase("RepositionRegion")) {
                header.put(count+"", "Port");
            }else {
            header.put(count+"", key);
            }
            count++;}
        	previousend_index=index+valuelength;
           } }
        String index=data.length()+"";
        data.put(index, alldata_return);
        header.put(index, "unknown_"+index);

//System.out.println("data "+data);
//System.out.println("header "+header);
returnobj.put("data", data);
returnobj.put("header", header);
returnobj.put("new_header", header);// as new_header is blank


		
		 return returnobj;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return returnobj;
			}
	 }

	 public static JSONObject getUrlByStanbol_withIndex(String alldata, String alldata_return) {
         JSONArray arr= null;
         JSONObject rtnobj=null;
         JSONObject urlnadvalue=null;
         JSONObject obj=null;
         JSONArray  arrobj=null;
	try {
		arr = new JSONArray();
		rtnobj=new JSONObject();
		if(!alldata.isEmpty()) {
			alldata=alldata.toUpperCase();
    			String stanboljson =stanbolBreakResponse(alldata);
    			////System.out.println("stanboljson "+stanboljson);
    			  arrobj= new JSONArray(stanboljson);
    			////System.out.println(arrobj.length());
    			
    			for(int i=0; i<arrobj.length(); i++) {
    				////System.out.println("**** "+i);
    				 obj= arrobj.getJSONObject(i);
    				String entity_ref_url=null;
    				String entity_vlaue=null;;
    				String entity_type=null;

    				if(obj.has("http://fise.iks-project.eu/ontology/entity-reference")) {
    					JSONArray  entityref=obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-reference");
    					entity_ref_url=entityref.getJSONObject(0).getString("@id");
    					
    					////System.out.println("1 "+entity_ref_url);
    				}
    				if(obj.has("http://fise.iks-project.eu/ontology/entity-label")) {
    					JSONArray  entityrlabel=obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-label");
    					entity_vlaue=entityrlabel.getJSONObject(0).getString("@value");
    					////System.out.println("2 "+entity_vlaue);
    				}
    				if(obj.has("http://fise.iks-project.eu/ontology/entity-type")) {
    					JSONArray  entityrtype=obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-type");
    					entity_type=entityrtype.getJSONObject(0).getString("@id");
    					if(entity_type.contains("#")) {
    						entity_type=entity_type.substring(entity_type.lastIndexOf("#")+1);
    					}
    					////System.out.println(" "+entity_type);

    				}
    				 urlnadvalue= new JSONObject();
    				if((entity_vlaue!=null) ) {
        				urlnadvalue.put("key", entity_type);
        				urlnadvalue.put("value", entity_vlaue);
    				//urlnadvalue.put(entity_vlaue, entity_type);
    				int index=ordinalIndexOf( alldata,  entity_vlaue.toUpperCase(), 0); 
    				int index2= ordinalIndexOf( alldata,  entity_vlaue.toUpperCase(), 1); 
    				int index3= ordinalIndexOf( alldata,  entity_vlaue.toUpperCase(), 2); 
                    int valuelength=entity_vlaue.length();
    				
    				urlnadvalue.put("Index", index);
    				urlnadvalue.put("Index2", index2);
    				urlnadvalue.put("Index3", index3);
    				urlnadvalue.put("valuelength", valuelength);


    				if(alldata_return.contains(entity_vlaue.toUpperCase())) {
    					alldata_return=alldata_return.replace(entity_vlaue.toUpperCase(), " ");
    				}
    				}
    				if(urlnadvalue.length()>0) {
            			arr.put(urlnadvalue);
    				}
    				rtnobj.put("alldata_return", alldata_return);
    				rtnobj.put("stanbolarr", arr);

    			}
		}		
} catch (JSONException e) {
	e.printStackTrace();
	try {
		rtnobj.put("error", e.getMessage());
		return rtnobj;
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

}
return rtnobj;


}
	 public static int ordinalIndexOf(String str, String substr, int n) {
		    int pos = -1;
		    do {
		        pos = str.indexOf(substr, pos + 1);
		    } while (n-- > 0 && pos != -1);
		    return pos;
		}



	 public static JSONObject callregex_forTotalmatch(String alldata, String alldata_return) {
			int responseCode = 0;
			JSONArray datearr=null;
			JSONArray ratearr=null;
			JSONObject rtnobj=null;

			String urlParameters = "";
			StringBuffer response = new StringBuffer();
			JSONObject subobj = null;
			JSONArray arr =null;
			JSONObject obj=null;
			try {
				rtnobj=new JSONObject();
				datearr= new JSONArray();
				ratearr= new JSONArray();
				alldata=alldata.toUpperCase();
				alldata_return =alldata_return.toUpperCase();
				String url1 = "http://dev.bizlem.io:5030/date";
				URL url = new URL(url1);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/json");
				// out.println("urlParameters:: " + obj.toString());
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				// urlParameters = urlParameters.replace(" ", "%20");
				wr.writeBytes(alldata);
				wr.flush();
				wr.close();
				responseCode = con.getResponseCode();
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {response.append(inputLine);}
				in.close();
				// out.println(response.toString());
				System.out.println(" response "+response.toString());
				if (!response.toString().equalsIgnoreCase("")) {
				arr	= new JSONArray(response.toString());
					////System.out.println(arr);
					int count = 0;
					int jsonno = 0;
					JSONArray valuearr=null;
					for (int i = 0; i < arr.length(); i++) {
						 obj = arr.getJSONObject(i);
						subobj= new JSONObject();
						String Regex_Type=obj.getString("Regex_Type");

						String maxvalue="";
						if (obj.has("value")) {
							if(obj.get("value") instanceof JSONArray) {
							valuearr = obj.getJSONArray("value");
							
						for(int j=0;j <valuearr.length(); j++) {
							String val=valuearr.getString(j);
							if(maxvalue.length()<val.length()) {
								maxvalue=val;
							}
						}
						}else if(obj.get("value") instanceof String){
							maxvalue= obj.getString("value");
							
						}
						////System.out.println("maxvalue "+maxvalue);
						
	    				int index=ordinalIndexOf( alldata,  maxvalue.toUpperCase(), 0); 
	    				int index2= ordinalIndexOf( alldata,  maxvalue.toUpperCase(), 1);
	    				int index3= ordinalIndexOf( alldata,  maxvalue.toUpperCase(), 2);
	    				int valuelength=maxvalue.length();

	    				subobj.put("Index", index);
	    				subobj.put("Index2", index2);
	    				subobj.put("Index3", index3);
	    				subobj.put("valuelength", valuelength);


						if(Regex_Type.equalsIgnoreCase("Date") || Regex_Type.equalsIgnoreCase("Date/Date")) {
						String regexpattern=obj.getString("Format");
						//System.out.println("regexpattern "+regexpattern);
						String formateddate = new pallavi_ConvertDatewithPattern().convertDatewithPattern(maxvalue, regexpattern);
						subobj.put("key", Regex_Type);
						subobj.put("value", formateddate);
						if(alldata_return.contains(maxvalue)) {
							alldata_return=alldata_return.replace(maxvalue, " ");
						}
						}else if(Regex_Type.equalsIgnoreCase("Rate")) {
							//subobj.put(maxvalue, Regex_Type);
							subobj.put("key", Regex_Type);
							subobj.put("value", maxvalue);
							if(alldata_return.contains(maxvalue)) {
								alldata_return=alldata_return.replace(maxvalue, " ");
							}
					     }	
                        datearr.put(subobj);
						}
					}
				}
				rtnobj.put("datearr", datearr);
				rtnobj.put("alldata_return", alldata_return);

				////System.out.println("alldata "+alldata );
				////System.out.println("alldata_return "+alldata_return);
                // //System.out.println("datearr  "+datearr);
			} catch (Exception e) {
				e.printStackTrace();
				// out.println(e.getMessage());
			}finally {
				
			}
			return rtnobj;
		}

	 public static JSONObject callToMatchRemainingString(String alldata, String alldata_return) {
		 JSONObject solrobj=null;
		 JSONArray solrarr = null;
		 JSONObject subobj= new JSONObject();
	try {
		alldata_return=alldata_return.toUpperCase();
		solrobj=new JSONObject();
		solrarr= new JSONArray();
		if(!alldata_return.equals("")) {
		String[] words= alldata_return.split(" ");
		for(int i=0; i<words.length; i++) {
			//System.out.println(" $$$$$$ "+words[i]);
			if(!words[i].equals(" ") && !words[i].equals("") ) {
		  String DocumentName=callSolrforDataMatch( words[i]) ;
         if(!DocumentName.equals(" ")) {
        	subobj.put(words[i], DocumentName);
			int index=ordinalIndexOf( alldata,  words[i].toUpperCase(), 0); 
			int index2= ordinalIndexOf( alldata,  words[i].toUpperCase(), 1); 
			int index3= ordinalIndexOf( alldata,  words[i].toUpperCase(), 2); 
			int valuelength=words[i].length();
			subobj.put("Index", index);
			subobj.put("Index2", index2);
			subobj.put("Index3", index3);
			subobj.put("valuelength", valuelength);

			

			if(alldata_return.contains(words[i].toUpperCase())) {
				alldata_return=alldata_return.replace(words[i].toUpperCase(), " ");
			}}
			if(subobj.length()>0) {
				solrarr.put(subobj);
			}
           }}
		}
		if(solrarr.length()>0) {
			solrobj.put("solrarr", solrarr);
		}
	}catch(Exception e) {
		return null;
	}finally {
		subobj=null;
	}
		 
		 return solrobj;	 
	 }
	 public static JSONObject  replaceLabels(JSONObject header) {
			String alldata="";
			String rowtype="";
			JSONObject obj =null;
			try {
				obj= new JSONObject();
				//System.out.println("header  "+header);
			
			Iterator itr = header.keys();
			while(itr.hasNext()) {
				String key = (String)itr.next();
				String value = header.getString(key);
				if(value.equalsIgnoreCase(" ")) {value=value.replace(" ", "");}
				alldata=alldata+" "+value.trim();
			}
			////System.out.println("alldata "+alldata);
			rowtype=getlabelsByStanbol(alldata);
			////System.out.println("rowtype  "+rowtype);
			if(rowtype.equalsIgnoreCase("Tabledata")) {
				Iterator itr2 = header.keys();
				while(itr2.hasNext()) {
					String key = (String)itr2.next();
					obj.put(key, "unknown_"+key);
			}
			}else if (rowtype.equalsIgnoreCase("Tablelabels")){
				obj=header;
			}
			
			////System.out.println("obj$ "+	 obj);

			return obj;

			}catch(Exception e) {
				e.printStackTrace();
				return header;

			}

		}
	 
	 public static String getlabelsByStanbol(String adata) {
			JSONObject urlnadvalue = new JSONObject();
			JSONObject obj=null;
			JSONArray arrobj=null;
			String rowtype="";
			try {
				adata = adata.toLowerCase();
				////System.out.println("adata " + adata);
				if (!adata.isEmpty()) {
					String stanboljson = stanbolBreakResponse(adata);
					// ////System.out.println("stanboljson "+stanboljson);
					 arrobj = new JSONArray(stanboljson);
					// ////System.out.println(arrobj.length());
					for (int i = 0; i < arrobj.length(); i++) {
						 obj = arrobj.getJSONObject(i);
						String entity_ref_url = null;
						String entity_vlaue = null;
						String entity_site="";
						String entity_type = null;

//						if (obj.has("http://fise.iks-project.eu/ontology/entity-reference")) {
//							JSONArray entityref = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-reference");
//							entity_ref_url = entityref.getJSONObject(0).getString("@id");
	//
//							// ////System.out.println(entity_ref_url);
//						}
						if (obj.has("http://fise.iks-project.eu/ontology/entity-label")) {
							JSONArray entityrlabel = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-label");
							entity_vlaue = entityrlabel.getJSONObject(0).getString("@value");
						}
						if (obj.has("http://stanbol.apache.org/ontology/entityhub/entityhub#site")) {
							JSONArray entityrlabel = obj.getJSONArray("http://stanbol.apache.org/ontology/entityhub/entityhub#site");
							entity_site = entityrlabel.getJSONObject(0).getString("@value");
							////System.out.println("entity_site "+entity_site);
							if(entity_site.equalsIgnoreCase("scorpioheaders")) {
							//	//System.out.println("entity_vlaue "+entity_vlaue+" entity_site "+entity_site);
								urlnadvalue.put(entity_vlaue, entity_site);
							}
						}
						
//						if (obj.has("http://fise.iks-project.eu/ontology/entity-type")) {
//							JSONArray entityrtype = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-type");
//							for(int j=0; j<entityrtype.length();j++) {
//							entity_type = entityrtype.getJSONObject(0).getString("@id");
//							//System.out.println(entity_type);
//							if (entity_type.contains("#")) {
//								entity_type = entity_type.substring(entity_type.lastIndexOf("#") + 1);
//	                //			if(entity_type.equalsIgnoreCase("")) {}
//	                //	     }
//								////System.out.println(entity_type);
//							}
//						}
//						if ((entity_vlaue != null)) {
//							urlnadvalue.put(entity_vlaue, entity_type);
//							// subfinaljson.put(entity_type, entity_vlaue);
//							// if (adata.contains(entity_vlaue.toLowerCase())) {
//							// ////System.out.println("contains");
//							// adata = adata.replace(entity_vlaue.toLowerCase(), "");
//							//
//							// }
//						}
	//
//					}
						////System.out.println("+++++++++++++++++++++++==");
				}
				if (urlnadvalue.length()>6) {
					rowtype="Tablelabels";
				}else {
					rowtype="Tabledata";

				}
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					//return urlnadvalue.put("error", e.getMessage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			return rowtype;
		
	}
	 
}
package com.pallavi.code;


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

import com.reportinformationsystem.SaveReportDataClass;

public class pallavi_UpdatedScript_copy_25_04_19 {
	/**
	 * @param args
	 * @throws JSONException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws JSONException   {
//	//	String a = "{  \"table_0\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CLEAN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 0,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#ObjectProperty\",             \"word\": \"size\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#Class\",             \"word\": \"Size\"          }        ],         \"line_index\": 1,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Best\"          }        ],         \"line_index\": 2,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Date\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"date\"          }        ],         \"line_index\": 3,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CLEAN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 4,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jnpt\"          }        ],         \"line_index\": 8,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          }        ],         \"line_index\": 10,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"bp\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Vadinar\"          }        ],         \"line_index\": 11,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Paradip\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 12,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sitra\"          }        ],         \"line_index\": 13,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Salalah\"          }        ],         \"line_index\": 14,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          }        ],         \"line_index\": 15,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"STI GRACE\"          }        ],         \"line_index\": 19,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"FRONT TIGER\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 20,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PLOUTOS\"          }        ],         \"line_index\": 21,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          }        ],         \"line_index\": 22,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PACIFIC MARTINA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          }        ],         \"line_index\": 23,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ec India\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 24,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"BW CLYDE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 25,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NAVE ATROPOS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 26,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"A.R.A.\"          }        ],         \"line_index\": 27,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"INDIA\"          }        ],         \"line_index\": 30,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"KITION M\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sikka\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"SIKKA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"cssa\"          }        ],         \"line_index\": 32,         \"number_of_words\": 6      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JAG PRANAM\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Paradip\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 33,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"TORM CARINA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Kandla\"          }        ],         \"line_index\": 36,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ARDMORE SEAHAWK\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Haldia\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 37,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jinzhou\"          }        ],         \"line_index\": 40,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Zhoushan\"          }        ],         \"line_index\": 41,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"trafigura\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Yokkaichi\"          }        ],         \"line_index\": 42,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"petrolimex\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Vietnam\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"S KOREA\"          }        ],         \"line_index\": 43,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Dalian\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 44,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 47,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Balongan\"          }        ],         \"line_index\": 48,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"spot\"          }        ],         \"line_index\": 49,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"VINALINES GLORY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Balongan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          }        ],         \"line_index\": 53,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Philippines\"          }        ],         \"line_index\": 54,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"China\"          }        ],         \"line_index\": 57,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Russia\"          }        ],         \"line_index\": 60,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"bp\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SKS DELTA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 62,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 63,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Australia-Nz\"          }        ],         \"line_index\": 64,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"OCEAN COSMOS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jakarta\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 69,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ATLANTIC INFINITY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Esperance\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 70,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          }        ],         \"line_index\": 71,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 73,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 76,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"blt\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"CHRYSANTHEMUM\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"lyras\"          }        ],         \"line_index\": 78,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JING YU ZUO\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          }        ],         \"line_index\": 79,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SPOTTAIL\"          }        ],         \"line_index\": 80,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"cbm\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"damico\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HIGH PERFORMANCE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ii\"          }        ],         \"line_index\": 81,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"cbm\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"MISS MARIAROSARIA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          }        ],         \"line_index\": 82,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"montanari\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"VALLE AZZURRA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ii\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          }        ],         \"line_index\": 83,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HIGH SUN\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"DWT\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ii\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"damico\"          }        ],         \"line_index\": 84,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"DWT\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"koch\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JAG PUNIT\"          }        ],         \"line_index\": 85,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Leo\"          }        ],         \"line_index\": 88,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 89,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 96,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Or\"          }        ],         \"line_index\": 97,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"hold\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Or\"          }        ],         \"line_index\": 98,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Us\"          }        ],         \"line_index\": 99,         \"number_of_words\": 1      }    ],     \"table_data\": [      {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"EASTPORT\",           \"1\": \"CLEAN\",           \"2\": \"MARKET_REPORT_DATED_08TH_APR_2019\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\"        },         \"line_index\": 0      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"THIS\",           \"1\": \"MESSAGE\",           \"2\": \"WAS\",           \"3\": \"PREPARED\",           \"4\": \"IN\",           \"5\": \"COURIER\",           \"6\": \"NEW\",           \"7\": \"FONT\",           \"8\": \"SIZE\",           \"9\": \"10.\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\",           \"9\": \"unknown_9\"        },         \"line_index\": 1      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"PLEASE\",           \"1\": \"ARRANGE\",           \"2\": \"ACCORDINGLY\",           \"3\": \"FOR\",           \"4\": \"BEST\",           \"5\": \"VIEW.\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\"        },         \"line_index\": 2      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"EASTPORT\",           \"1\": \"CLEAN\",           \"2\": \"MARKET\",           \"3\": \"REPORT\",           \"4\": \"DATED\",           \"5\": \"08TH\",           \"6\": \"APR\",           \"7\": \"2019\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 4      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"IOC\",           \"1\": \"38\",           \"2\": \"CPP\",           \"3\": \"JNPT\",           \"4\": \"KANDLA\",           \"5\": \"14-15/04\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\"        },         \"line_index\": 8      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"GPC\",           \"1\": \"35\",           \"2\": \"JET\",           \"3\": \"N.MANG/OPTS\",           \"4\": \"16-18/04\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\"        },         \"line_index\": 10      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"BP\",           \"1\": \"35\",           \"2\": \"GO\",           \"3\": \"VADINAR\",           \"4\": \"OPTS\",           \"5\": \"11-15/04\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\"        },         \"line_index\": 11      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"IOC\",           \"1\": \"16\",           \"2\": \"CPP\",           \"3\": \"PARADIP\",           \"4\": \"HALDIA\",           \"5\": \"13-14/04\",           \"6\": \"ON\",           \"7\": \"SUBS\",           \"8\": \"BELOW\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\"        },         \"line_index\": 12      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"MSC\",           \"1\": \"310KB\",           \"2\": \"F76\",           \"3\": \"SITRA\",           \"4\": \"J.ALI-FUJ\",           \"5\": \"22-23/04\",           \"6\": \"CLOSING\",           \"7\": \"10/04,\",           \"8\": \"1100H\",           \"9\": \"EST\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\",           \"9\": \"unknown_9\"        },         \"line_index\": 13      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"STI GRACE\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"22/04\",           \"5\": \"W97.5\",           \"6\": \"ATC=FLD\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\"        },         \"line_index\": 19      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"FRONT TIGER\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"22/04\",           \"5\": \"W97.5\",           \"6\": \"SUBS\",           \"7\": \"RPLC\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 20      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"PLOUTOS\",           \"1\": \"60\",           \"2\": \"UMS\",           \"3\": \"X-AG\",           \"4\": \"15/04\",           \"5\": \"COA\",           \"6\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\"        },         \"line_index\": 21      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"LIAN\",           \"1\": \"GUI\",           \"2\": \"HU\",           \"3\": \"55\",           \"4\": \"NAP\",           \"5\": \"JAPAN\",           \"6\": \"END/04\",           \"7\": \"O/P\",           \"8\": \"SHELL\",           \"9\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\",           \"9\": \"unknown_9\"        },         \"line_index\": 22      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"PACIFIC MARTINA\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"15/04\",           \"5\": \"O/P\",           \"6\": \"SHELL\",           \"7\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 23      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"NORDIC\",           \"1\": \"TRISTAN\",           \"2\": \"55\",           \"3\": \"NAP\",           \"4\": \"KUWAIT/EC.INDIA\",           \"5\": \"20/04\",           \"6\": \"W113\",           \"7\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 24      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"BW CLYDE\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"OPTS\",           \"5\": \"08/04\",           \"6\": \"W100\",           \"7\": \"ATC=FLD\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 25      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"NAVE ATROPOS\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"OPTS\",           \"5\": \"08/04\",           \"6\": \"W100\",           \"7\": \"SUBS\",           \"8\": \"RPLC\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\"        },         \"line_index\": 26      }   ]  }}";
//        String alldata="04/08 SEABREEZE 53   56 04/08  USG              THENAMARIS       PPT";
//        JSONObject data= new JSONObject();
//        data.put("key", "value");
//        data.put("0", "ADELE M.RIZZO     80    COND  28-30/04    CAKERAWALA  / THAILAND $380k");
//
//        JSONObject header= new JSONObject();
//        header.put("key", "value");
//
//System.out.println("data "+data);
//System.out.println("header "+header);
//
//JSONObject obj1 = singleStringParsingmethod(data,  header, null, null);
//   	System.out.println(obj1);
//   	System.out.println("header * "+header);
//
}
//	
	public static String updatedMainScript(PrintWriter out, Session session, String pythonScriptApiData,
			String emailUrl, String textSentMailTime, String subjectNodePath, String from_Source, String timestampDate,
			String timestampDateAndTime, String filepath) {

		
		out.println("start");
	//String pythonScriptApiData="{  \"table_0\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Alliance\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"aframax\"          }        ],         \"line_index\": 0,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"Aframax\"          }        ],         \"line_index\": 1,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"Price\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"crude\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          }        ],         \"line_index\": 2,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Us\"          }        ],         \"line_index\": 3,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Us\"          }        ],         \"line_index\": 7,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Brent\"          }        ],         \"line_index\": 11,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Us\"          }        ],         \"line_index\": 13,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 17,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          }        ],         \"line_index\": 18,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType\",             \"word\": \"ws\"          }        ],         \"line_index\": 21,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Seria\"          }        ],         \"line_index\": 30,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType\",             \"word\": \"ws\"          }        ],         \"line_index\": 35,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Dumai\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType\",             \"word\": \"ws\"          }        ],         \"line_index\": 44,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Kozmino\"          }        ],         \"line_index\": 51,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"N China\"          }        ],         \"line_index\": 53,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          }        ],         \"line_index\": 66,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          }        ],         \"line_index\": 71,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Cilacap\"          }        ],         \"line_index\": 76,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          }        ],         \"line_index\": 83,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"INDIA\"          }        ],         \"line_index\": 84,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sikka\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SUPER LADY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"SIKKA\"          }        ],         \"line_index\": 85,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"KANPUR\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"SIKKA\"          }        ],         \"line_index\": 86,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Seria\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"EBN BATUTA\"          }        ],         \"line_index\": 88,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Thailand\"          }        ],         \"line_index\": 89,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"N China\"          }        ],         \"line_index\": 90,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Linggi\"          }        ],         \"line_index\": 91,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Mumbai\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"DESH MAHIMA\"          }        ],         \"line_index\": 92,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 93,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"INDIA\"          }        ],         \"line_index\": 95,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Das Island\"          }        ],         \"line_index\": 96,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Far East\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"Far East\"          }        ],         \"line_index\": 97,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"fo\"          }        ],         \"line_index\": 98,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Vadinar\"          }        ],         \"line_index\": 99,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Dampier\"          }        ],         \"line_index\": 100,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Seria\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          }        ],         \"line_index\": 101,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"N China\"          }        ],         \"line_index\": 102,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Melbourne\"          }        ],         \"line_index\": 103,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Best\"          }        ],         \"line_index\": 106,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Alliance\"          }        ],         \"line_index\": 107,         \"number_of_words\": 1      }    ],     \"table_data\": [      {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \"        },         \"header\": {          \"0\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \"        },         \"label_line_index\": 0,         \"label_string\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \",         \"line_index\": 0      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"       AFRAMAX MARKET REPORT      \"        },         \"header\": {          \"0\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \"        },         \"label_line_index\": 0,         \"label_string\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \",         \"line_index\": 1      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     BUNKER PRICE IN SINGAPORE                                   CRUDE PRICE     \"        },         \"header\": {          \"0\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \"        },         \"label_line_index\": 0,         \"label_string\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \",         \"line_index\": 2      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     380 CST US$    \"        },         \"header\": {          \"0\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \"        },         \"label_line_index\": 0,         \"label_string\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \",         \"line_index\": 3      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      / MGO US$     \"        },         \"header\": {          \"0\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \"        },         \"label_line_index\": 0,         \"label_string\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \",         \"line_index\": 7      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      0                           BRENT US $     \"        },         \"header\": {          \"0\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \"        },         \"label_line_index\": 0,         \"label_string\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \",         \"line_index\": 11      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      / WTI US $     \"        },         \"header\": {          \"0\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \"        },         \"label_line_index\": 0,         \"label_string\": \"   ALLIANCE_AFRAMAX_MARKET_REPORT_24TH_APR_2019  \",         \"line_index\": 13      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      VOYAGE                    BITR           SHORT-TERM VIEW      R/TRIP T/C RETURN      \"        },         \"header\": {          \"0\": \"      VOYAGE                    BITR           SHORT-TERM VIEW      R/TRIP T/C RETURN      \"        },         \"label_line_index\": 17,         \"label_string\": \"      VOYAGE                    BITR           SHORT-TERM VIEW      R/TRIP T/C RETURN      \",         \"line_index\": 17      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     KUWAIT   /SINGAPORE    \"        },         \"header\": {          \"0\": \"      VOYAGE                    BITR           SHORT-TERM VIEW      R/TRIP T/C RETURN      \"        },         \"label_line_index\": 17,         \"label_string\": \"      VOYAGE                    BITR           SHORT-TERM VIEW      R/TRIP T/C RETURN      \",         \"line_index\": 18      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      WS     \"        },         \"header\": {          \"0\": \"      VOYAGE                    BITR           SHORT-TERM VIEW      R/TRIP T/C RETURN      \"        },         \"label_line_index\": 17,         \"label_string\": \"      VOYAGE                    BITR           SHORT-TERM VIEW      R/TRIP T/C RETURN      \",         \"line_index\": 21      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      SERIA    /SYDNEY     \"        },         \"header\": {          \"0\": \"      VOYAGE                    BITR           SHORT-TERM VIEW      R/TRIP T/C RETURN      \"        },         \"label_line_index\": 17,         \"label_string\": \"      VOYAGE                    BITR           SHORT-TERM VIEW      R/TRIP T/C RETURN      \",         \"line_index\": 30      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      WS     \"        },         \"header\": {          \"0\": \"      VOYAGE                    BITR           SHORT-TERM VIEW      R/TRIP T/C RETURN      \"        },         \"label_line_index\": 17,         \"label_string\": \"      VOYAGE                    BITR           SHORT-TERM VIEW      R/TRIP T/C RETURN      \",         \"line_index\": 35      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      DUMAI    /CHIBA           NA                WS     \"        },         \"header\": {          \"0\": \"      DUMAI    /CHIBA           NA                WS     \"        },         \"label_line_index\": 44,         \"label_string\": \"      DUMAI    /CHIBA           NA                WS     \",         \"line_index\": 44      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     KOZMINO\"        },         \"header\": {          \"0\": \"      DUMAI    /CHIBA           NA                WS     \"        },         \"label_line_index\": 44,         \"label_string\": \"      DUMAI    /CHIBA           NA                WS     \",         \"line_index\": 51      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     n china\"        },         \"header\": {          \"0\": \"      DUMAI    /CHIBA           NA                WS     \"        },         \"label_line_index\": 44,         \"label_string\": \"      DUMAI    /CHIBA           NA                WS     \",         \"line_index\": 53      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      SINGAPORE/HONG KONG       NA                $     \"        },         \"header\": {          \"0\": \"      SINGAPORE/HONG KONG       NA                $     \"        },         \"label_line_index\": 66,         \"label_string\": \"      SINGAPORE/HONG KONG       NA                $     \",         \"line_index\": 66      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      SINGAPORE/SRIRACHA        NA                $     \"        },         \"header\": {          \"0\": \"      SINGAPORE/SRIRACHA        NA                $     \"        },         \"label_line_index\": 71,         \"label_string\": \"      SINGAPORE/SRIRACHA        NA                $     \",         \"line_index\": 71      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      SERIA    /CILACAP         NA                $     \"        },         \"header\": {          \"0\": \"      SERIA    /CILACAP         NA                $     \"        },         \"label_line_index\": 76,         \"label_string\": \"      SERIA    /CILACAP         NA                $     \",         \"line_index\": 76      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"       FIXTURES      \"        },         \"header\": {          \"0\": \"      SERIA    /CILACAP         NA                $     \"        },         \"label_line_index\": 76,         \"label_string\": \"      SERIA    /CILACAP         NA                $     \",         \"line_index\": 83      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      AG-RSEA-INDIA      \"        },         \"header\": {          \"0\": \"      SERIA    /CILACAP         NA                $     \"        },         \"label_line_index\": 76,         \"label_string\": \"      SERIA    /CILACAP         NA                $     \",         \"line_index\": 84      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     SUPER LADY        80    CO    28/04       BASHAYER    / SIKKA       RNR       RELIANCE     \"        },         \"header\": {          \"0\": \"     SUPER LADY        80    CO    28/04       BASHAYER    / SIKKA       RNR       RELIANCE     \"        },         \"label_line_index\": 85,         \"label_string\": \"     SUPER LADY        80    CO    28/04       BASHAYER    / SIKKA       RNR       RELIANCE     \",         \"line_index\": 85      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     KANPUR            60    CBFS  06-07/05    SIKKA       / FUJAIRAH    RNR       SHELL     \"        },         \"header\": {          \"0\": \"     KANPUR            60    CBFS  06-07/05    SIKKA       / FUJAIRAH    RNR       SHELL     \"        },         \"label_line_index\": 86,         \"label_string\": \"     KANPUR            60    CBFS  06-07/05    SIKKA       / FUJAIRAH    RNR       SHELL     \",         \"line_index\": 86      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     EBN BATUTA        80    CO    24-25/04    SERIA       / GEELONG     RNR       VITOL - RPLC     \"        },         \"header\": {          \"0\": \"     EBN BATUTA        80    CO    24-25/04    SERIA       / GEELONG     RNR       VITOL - RPLC     \"        },         \"label_line_index\": 88,         \"label_string\": \"     EBN BATUTA        80    CO    24-25/04    SERIA       / GEELONG     RNR       VITOL - RPLC     \",         \"line_index\": 88      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ADELE M.RIZZO     80    COND  28-30/04    CAKERAWALA  / THAILAND    $380K     PTT - RPLC     \"        },         \"header\": {          \"0\": \"     ADELE M.RIZZO     80    COND  28-30/04    CAKERAWALA  / THAILAND    $380K     PTT - RPLC     \"        },         \"label_line_index\": 89,         \"label_string\": \"     ADELE M.RIZZO     80    COND  28-30/04    CAKERAWALA  / THAILAND    $380K     PTT - RPLC     \",         \"line_index\": 89      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     SOUTHERN ROUSE    100   CO    03-05/05    KOZMINO     / N CHINA     RNR       GLASFORD     \"        },         \"header\": {          \"0\": \"     SOUTHERN ROUSE    100   CO    03-05/05    KOZMINO     / N CHINA     RNR       GLASFORD     \"        },         \"label_line_index\": 90,         \"label_string\": \"     SOUTHERN ROUSE    100   CO    03-05/05    KOZMINO     / N CHINA     RNR       GLASFORD     \",         \"line_index\": 90      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     TK TBN            80    CO    05-06/05    LINGGI+1    / JAPAN       RNR       K LINE     \"        },         \"header\": {          \"0\": \"     TK TBN            80    CO    05-06/05    LINGGI+1    / JAPAN       RNR       K LINE     \"        },         \"label_line_index\": 91,         \"label_string\": \"     TK TBN            80    CO    05-06/05    LINGGI+1    / JAPAN       RNR       K LINE     \",         \"line_index\": 91      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     DESH MAHIMA       78    CO    07/05       SERIA       / MUMBAI      WS74      BPCL     \"        },         \"header\": {          \"0\": \"     DESH MAHIMA       78    CO    07/05       SERIA       / MUMBAI      WS74      BPCL     \"        },         \"label_line_index\": 92,         \"label_string\": \"     DESH MAHIMA       78    CO    07/05       SERIA       / MUMBAI      WS74      BPCL     \",         \"line_index\": 92      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     PUSAKA BORNEO     80    CO    11-13/05    WANDOO      / JAPAN       WS97.5    METS     \"        },         \"header\": {          \"0\": \"     PUSAKA BORNEO     80    CO    11-13/05    WANDOO      / JAPAN       WS97.5    METS     \"        },         \"label_line_index\": 93,         \"label_string\": \"     PUSAKA BORNEO     80    CO    11-13/05    WANDOO      / JAPAN       WS97.5    METS     \",         \"line_index\": 93      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      AG-RSEA-INDIA     \"        },         \"header\": {          \"0\": \"     PUSAKA BORNEO     80    CO    11-13/05    WANDOO      / JAPAN       WS97.5    METS     \"        },         \"label_line_index\": 93,         \"label_string\": \"     PUSAKA BORNEO     80    CO    11-13/05    WANDOO      / JAPAN       WS97.5    METS     \",         \"line_index\": 95      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     SHELL             80    CO    05-06/05    DAS ISLAND  / KARACHI     \"        },         \"header\": {          \"0\": \"     SHELL             80    CO    05-06/05    DAS ISLAND  / KARACHI     \"        },         \"label_line_index\": 96,         \"label_string\": \"     SHELL             80    CO    05-06/05    DAS ISLAND  / KARACHI     \",         \"line_index\": 96      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"      FAR EAST     \"        },         \"header\": {          \"0\": \"     SHELL             80    CO    05-06/05    DAS ISLAND  / KARACHI     \"        },         \"label_line_index\": 96,         \"label_string\": \"     SHELL             80    CO    05-06/05    DAS ISLAND  / KARACHI     \",         \"line_index\": 97      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     SHELL             80    FO    27-28/04    SINGAPORE   / SG-NORTH     \"        },         \"header\": {          \"0\": \"     SHELL             80    FO    27-28/04    SINGAPORE   / SG-NORTH     \"        },         \"label_line_index\": 98,         \"label_string\": \"     SHELL             80    FO    27-28/04    SINGAPORE   / SG-NORTH     \",         \"line_index\": 98      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     IOC               80    CO    05-06/05    MIRI        / VADINAR     \"        },         \"header\": {          \"0\": \"     IOC               80    CO    05-06/05    MIRI        / VADINAR     \"        },         \"label_line_index\": 99,         \"label_string\": \"     IOC               80    CO    05-06/05    MIRI        / VADINAR     \",         \"line_index\": 99      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     PERTAMINA         80    COND  05-06/05    DAMPIER     / TUBAN     \"        },         \"header\": {          \"0\": \"     PERTAMINA         80    COND  05-06/05    DAMPIER     / TUBAN     \"        },         \"label_line_index\": 100,         \"label_string\": \"     PERTAMINA         80    COND  05-06/05    DAMPIER     / TUBAN     \",         \"line_index\": 100      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     SHELL             80    CO    06-09/05    SERIA       / OPTIONS     \"        },         \"header\": {          \"0\": \"     SHELL             80    CO    06-09/05    SERIA       / OPTIONS     \"        },         \"label_line_index\": 101,         \"label_string\": \"     SHELL             80    CO    06-09/05    SERIA       / OPTIONS     \",         \"line_index\": 101      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     UNIPEC            100   CO    09-10/05    KOZMINO     / N CHINA     \"        },         \"header\": {          \"0\": \"     UNIPEC            100   CO    09-10/05    KOZMINO     / N CHINA     \"        },         \"label_line_index\": 102,         \"label_string\": \"     UNIPEC            100   CO    09-10/05    KOZMINO     / N CHINA     \",         \"line_index\": 102      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     INPEX             80    COND  10-15/05    ICTHYS      / MELBOURNE     \"        },         \"header\": {          \"0\": \"     INPEX             80    COND  10-15/05    ICTHYS      / MELBOURNE     \"        },         \"label_line_index\": 103,         \"label_string\": \"     INPEX             80    COND  10-15/05    ICTHYS      / MELBOURNE     \",         \"line_index\": 103      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     BEST REGARDS,     \"        },         \"header\": {          \"0\": \"     INPEX             80    COND  10-15/05    ICTHYS      / MELBOURNE     \"        },         \"label_line_index\": 103,         \"label_string\": \"     INPEX             80    COND  10-15/05    ICTHYS      / MELBOURNE     \",         \"line_index\": 106      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ALLIANCE TANKER CHARTERING     \"        },         \"header\": {          \"0\": \"     INPEX             80    COND  10-15/05    ICTHYS      / MELBOURNE     \"        },         \"label_line_index\": 103,         \"label_string\": \"     INPEX             80    COND  10-15/05    ICTHYS      / MELBOURNE     \",         \"line_index\": 107      }    ]  }}";
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
		//System.out.println("start");
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
				out.println("vesselline_line_index " + vesselline_line_index);


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

			out.println("returnobj " + returnobj);
			pallavi_WriteFile.writeUsingOutputStream( "SCRIPTJSON ::====== "+pythonScriptApiData.toString() +
					"\n OUTPUTJSON::====== "+returnobj .toString() , 
					filepath);

			return returnobj.toString();
		} catch (Exception e) {
			pallavi_WriteFile.writeUsingOutputStream( "SCRIPTJSON ::====== "+pythonScriptApiData.toString() +
					"\n OUTPUTJSON::====== "+returnobj .toString() +"\n error"+ e.getMessage(), 
					filepath);
			e.printStackTrace();
      System.out.println("error  "+e.getMessage());
			return returnobj.toString();
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

			// url = "http://35.231.163.191:8983/solr/TemplateCore/select?q=" + q
			// + "&fl=" + f1 + "&df=" + df;

			// url = "http://35.231.163.191:8983/solr/Header_Synomes/select?q=" +
			// q + "&fl=" + f1;
			// url =
			// "http://35.231.163.191:8983/solr/Header_Synomes/select?q=Data:" +
			// q + "&fl=" + "HeaderName";
			url = "http://35.231.163.191:8983/solr/Header_Synomes/select?q=Data_str:" + q
					+ "&fl=score,HeaderName,Extrakey";
			// url =
			// "http://35.231.163.191:8983/solr/Header_Synomes/select?q=Data:("+q+")&fl=HeaderName";

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
			// "http://35.231.163.191:8983/solr/PO_FlatteningTemplate/update/json/docs?commit=true";
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
		url = "http://35.231.163.191:8983/solr/ReportType_Synomes/select?q=Data_str:" + passvalue + "&fl=score,HeaderName";

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
		url="http://35.231.163.191:8983/solr/HeaderAndDataValidation/select?fl=score,DataType&q=Headersynonyms:"+synonym;
		//url = "http://35.231.163.191:8983/solr/ReportType_Synomes/select?q=Data_str:" + passvalue + "&fl=score,HeaderName";
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
           } 
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
//public static String labelrecognize() {
//	
//}
}
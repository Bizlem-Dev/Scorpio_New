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

public class pallavi_UpdatedScript_copy_18_04 {
	/**
	 * @param args
	 * @throws JSONException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws JSONException   {
	//	String a = "{  \"table_0\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CLEAN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 0,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#ObjectProperty\",             \"word\": \"size\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#Class\",             \"word\": \"Size\"          }        ],         \"line_index\": 1,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Best\"          }        ],         \"line_index\": 2,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Date\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"date\"          }        ],         \"line_index\": 3,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CLEAN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 4,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jnpt\"          }        ],         \"line_index\": 8,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          }        ],         \"line_index\": 10,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"bp\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Vadinar\"          }        ],         \"line_index\": 11,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Paradip\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 12,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sitra\"          }        ],         \"line_index\": 13,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Salalah\"          }        ],         \"line_index\": 14,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          }        ],         \"line_index\": 15,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"STI GRACE\"          }        ],         \"line_index\": 19,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"FRONT TIGER\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 20,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PLOUTOS\"          }        ],         \"line_index\": 21,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          }        ],         \"line_index\": 22,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PACIFIC MARTINA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          }        ],         \"line_index\": 23,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ec India\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 24,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"BW CLYDE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 25,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NAVE ATROPOS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 26,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"A.R.A.\"          }        ],         \"line_index\": 27,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"INDIA\"          }        ],         \"line_index\": 30,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"KITION M\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sikka\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"SIKKA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"cssa\"          }        ],         \"line_index\": 32,         \"number_of_words\": 6      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JAG PRANAM\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Paradip\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 33,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"TORM CARINA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Kandla\"          }        ],         \"line_index\": 36,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ARDMORE SEAHAWK\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Haldia\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 37,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jinzhou\"          }        ],         \"line_index\": 40,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Zhoushan\"          }        ],         \"line_index\": 41,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"trafigura\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Yokkaichi\"          }        ],         \"line_index\": 42,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"petrolimex\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Vietnam\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"S KOREA\"          }        ],         \"line_index\": 43,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Dalian\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 44,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 47,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Balongan\"          }        ],         \"line_index\": 48,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"spot\"          }        ],         \"line_index\": 49,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"VINALINES GLORY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Balongan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          }        ],         \"line_index\": 53,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Philippines\"          }        ],         \"line_index\": 54,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"China\"          }        ],         \"line_index\": 57,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Russia\"          }        ],         \"line_index\": 60,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"bp\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SKS DELTA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 62,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 63,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Australia-Nz\"          }        ],         \"line_index\": 64,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"OCEAN COSMOS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jakarta\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 69,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ATLANTIC INFINITY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Esperance\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 70,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          }        ],         \"line_index\": 71,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 73,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 76,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"blt\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"CHRYSANTHEMUM\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"lyras\"          }        ],         \"line_index\": 78,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JING YU ZUO\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          }        ],         \"line_index\": 79,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SPOTTAIL\"          }        ],         \"line_index\": 80,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"cbm\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"damico\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HIGH PERFORMANCE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ii\"          }        ],         \"line_index\": 81,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"cbm\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"MISS MARIAROSARIA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          }        ],         \"line_index\": 82,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"montanari\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"VALLE AZZURRA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ii\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          }        ],         \"line_index\": 83,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HIGH SUN\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"DWT\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ii\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"damico\"          }        ],         \"line_index\": 84,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"DWT\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"koch\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JAG PUNIT\"          }        ],         \"line_index\": 85,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Leo\"          }        ],         \"line_index\": 88,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 89,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 96,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Or\"          }        ],         \"line_index\": 97,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"hold\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Or\"          }        ],         \"line_index\": 98,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Us\"          }        ],         \"line_index\": 99,         \"number_of_words\": 1      }    ],     \"table_data\": [      {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"EASTPORT\",           \"1\": \"CLEAN\",           \"2\": \"MARKET_REPORT_DATED_08TH_APR_2019\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\"        },         \"line_index\": 0      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"THIS\",           \"1\": \"MESSAGE\",           \"2\": \"WAS\",           \"3\": \"PREPARED\",           \"4\": \"IN\",           \"5\": \"COURIER\",           \"6\": \"NEW\",           \"7\": \"FONT\",           \"8\": \"SIZE\",           \"9\": \"10.\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\",           \"9\": \"unknown_9\"        },         \"line_index\": 1      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"PLEASE\",           \"1\": \"ARRANGE\",           \"2\": \"ACCORDINGLY\",           \"3\": \"FOR\",           \"4\": \"BEST\",           \"5\": \"VIEW.\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\"        },         \"line_index\": 2      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"EASTPORT\",           \"1\": \"CLEAN\",           \"2\": \"MARKET\",           \"3\": \"REPORT\",           \"4\": \"DATED\",           \"5\": \"08TH\",           \"6\": \"APR\",           \"7\": \"2019\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 4      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"IOC\",           \"1\": \"38\",           \"2\": \"CPP\",           \"3\": \"JNPT\",           \"4\": \"KANDLA\",           \"5\": \"14-15/04\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\"        },         \"line_index\": 8      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"GPC\",           \"1\": \"35\",           \"2\": \"JET\",           \"3\": \"N.MANG/OPTS\",           \"4\": \"16-18/04\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\"        },         \"line_index\": 10      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"BP\",           \"1\": \"35\",           \"2\": \"GO\",           \"3\": \"VADINAR\",           \"4\": \"OPTS\",           \"5\": \"11-15/04\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\"        },         \"line_index\": 11      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"IOC\",           \"1\": \"16\",           \"2\": \"CPP\",           \"3\": \"PARADIP\",           \"4\": \"HALDIA\",           \"5\": \"13-14/04\",           \"6\": \"ON\",           \"7\": \"SUBS\",           \"8\": \"BELOW\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\"        },         \"line_index\": 12      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"MSC\",           \"1\": \"310KB\",           \"2\": \"F76\",           \"3\": \"SITRA\",           \"4\": \"J.ALI-FUJ\",           \"5\": \"22-23/04\",           \"6\": \"CLOSING\",           \"7\": \"10/04,\",           \"8\": \"1100H\",           \"9\": \"EST\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\",           \"9\": \"unknown_9\"        },         \"line_index\": 13      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"STI GRACE\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"22/04\",           \"5\": \"W97.5\",           \"6\": \"ATC=FLD\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\"        },         \"line_index\": 19      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"FRONT TIGER\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"22/04\",           \"5\": \"W97.5\",           \"6\": \"SUBS\",           \"7\": \"RPLC\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 20      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"PLOUTOS\",           \"1\": \"60\",           \"2\": \"UMS\",           \"3\": \"X-AG\",           \"4\": \"15/04\",           \"5\": \"COA\",           \"6\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\"        },         \"line_index\": 21      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"LIAN\",           \"1\": \"GUI\",           \"2\": \"HU\",           \"3\": \"55\",           \"4\": \"NAP\",           \"5\": \"JAPAN\",           \"6\": \"END/04\",           \"7\": \"O/P\",           \"8\": \"SHELL\",           \"9\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\",           \"9\": \"unknown_9\"        },         \"line_index\": 22      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"PACIFIC MARTINA\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"15/04\",           \"5\": \"O/P\",           \"6\": \"SHELL\",           \"7\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 23      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"NORDIC\",           \"1\": \"TRISTAN\",           \"2\": \"55\",           \"3\": \"NAP\",           \"4\": \"KUWAIT/EC.INDIA\",           \"5\": \"20/04\",           \"6\": \"W113\",           \"7\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 24      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"BW CLYDE\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"OPTS\",           \"5\": \"08/04\",           \"6\": \"W100\",           \"7\": \"ATC=FLD\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 25      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"NAVE ATROPOS\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"OPTS\",           \"5\": \"08/04\",           \"6\": \"W100\",           \"7\": \"SUBS\",           \"8\": \"RPLC\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\"        },         \"line_index\": 26      }   ]  }}";
//        String alldata="04/08 SEABREEZE 53   56 04/08  USG              THENAMARIS       PPT";
//        JSONObject data= new JSONObject();
//        data.put("key", "value");
//        data.put("0", "04/08 SEABREEZE 53   56 04/08  USG              THENAMARIS       PPT");
//
//        JSONObject header= new JSONObject();
//        header.put("key", "value");
//
////System.out.println("data "+data);
////System.out.println("header "+header);
//
//   	  singleStringParsingmethod(data,  header, null, null);
//   	//System.out.println("data * "+data);
//   	//System.out.println("header * "+header);
//
	}
	public static String updatedMainScript(PrintWriter out, Session session, String pythonScriptApiData,
			String emailUrl, String textSentMailTime, String subjectNodePath, String from_Source, String timestampDate,
			String timestampDateAndTime, String filepath) {

	//String a="{ \"table_0\": { \"header_data\": [ { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Gray\" } ], \"line_index\": 0, \"number_of_words\": 1 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" } ], \"line_index\": 1, \"number_of_words\": 1 }, { \"data\": [ { \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\", \"word\": \"ETA\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Current_Status\", \"word\": \"open\" }, { \"url\": \"http://www.w3.org/2002/07/owl#Class\", \"word\": \"Owner\" } ], \"line_index\": 2, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"SUN\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Cristobal\" } ], \"line_index\": 3, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Mamonal\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" } ], \"line_index\": 4, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"BALBOA\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"SCF PEARL\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\", \"word\": \"st shipping\" } ], \"line_index\": 5, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"SINGLE\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Penfield\" } ], \"line_index\": 6, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Haiti\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"AGISILAOS\" } ], \"line_index\": 7, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"BALBOA\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"STENA PENGUIN\" } ], \"line_index\": 8, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\", \"word\": \"stena\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Rocky Point\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"CAPE TAFT\" } ], \"line_index\": 9, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"ICE POINT\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Port Au Prince\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" } ], \"line_index\": 10, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Chalmette\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"CAPE TAURA\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\", \"word\": \"upt\" } ], \"line_index\": 11, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Port Arthur\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"CABO SAN VICENTE\" } ], \"line_index\": 12, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"New York\" }, { \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\", \"word\": \"DWT\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\", \"word\": \"stena\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"STENA PENGUIN\" } ], \"line_index\": 13, \"number_of_words\": 4 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Usg\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"ANDIMILOS\" } ], \"line_index\": 14, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"SUN\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Loop\" } ], \"line_index\": 15, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"XANTHOS\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Tuxpan\" } ], \"line_index\": 16, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\", \"word\": \"tsakos\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"INCA\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"SINGLE\" } ], \"line_index\": 17, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Garyville\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"PGC ASPROPYRGOS\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" } ], \"line_index\": 18, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Penfield\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"CABO SAN VICENTE\" } ], \"line_index\": 19, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Mississippi River\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"SUN\" } ], \"line_index\": 20, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Mamonal\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" } ], \"line_index\": 21, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\", \"word\": \"ETA\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Usg\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\", \"word\": \"handy\" }, { \"url\": \"http://www.w3.org/2002/07/owl#Class\", \"word\": \"Owners\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Current_Status\", \"word\": \"open\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"ANDIMILOS\" } ], \"line_index\": 22, \"number_of_words\": 6 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"AGISILAOS\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Garyville\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\", \"word\": \"diamond s\" } ], \"line_index\": 23, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"CAPE TAURA\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Chalmette\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\", \"word\": \"handytankers\" } ], \"line_index\": 24, \"number_of_words\": 4 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"SCORPIO\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Tuxpan\" } ], \"line_index\": 25, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"ICE POINT\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Port Au Prince\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" } ], \"line_index\": 26, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"PORT UNION\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Altamira\" } ], \"line_index\": 27, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\", \"word\": \"minerva\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"MINERVA ZEN\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Texas City\" } ], \"line_index\": 28, \"number_of_words\": 4 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"MINERVA JOANNA\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" }, { \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\", \"word\": \"blt\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"New York\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\", \"word\": \"minerva\" } ], \"line_index\": 29, \"number_of_words\": 5 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\", \"word\": \"st shipping\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"La Paz\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"PRINCE I\" } ], \"line_index\": 30, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"KASTOS\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"La Paz\" } ], \"line_index\": 31, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Saint John\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\", \"word\": \"norient\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"SCF PEARL\" } ], \"line_index\": 32, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"BALBOA\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"MARVEL\" } ], \"line_index\": 33, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"subs\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"HELLESPONT PROMISE\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Ecuador\" } ], \"line_index\": 34, \"number_of_words\": 3 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"SANDINO\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"ASOPOS\" } ], \"line_index\": 35, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Quintero\" } ], \"line_index\": 36, \"number_of_words\": 1 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"SEAWAYS SAMAR\" } ], \"line_index\": 37, \"number_of_words\": 1 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"STROFADES\" } ], \"line_index\": 38, \"number_of_words\": 1 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"ASTERION\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Ecuador\" } ], \"line_index\": 39, \"number_of_words\": 2 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\", \"word\": \"ALNIC MC\" } ], \"line_index\": 40, \"number_of_words\": 1 }, { \"data\": [ { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\", \"word\": \"Cherry Point\" }, { \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\", \"word\": \"unc\" } ], \"line_index\": 41, \"number_of_words\": 2 } ], \"table_data\": [ { \"StructureType\": \"table\", \"data\": { \"0\": \"MINERVA JOANNA (NYC 17 APR) - SUBS\", \"1\": \"05\", \"2\": \"72\", \"3\": \"20/4\", \"4\": \"AMAZON BRILLIANCE\", \"5\": \"19/4\", \"6\": \"CRISTOBAL\", \"7\": \"17/4\", \"8\": \"SUN ENTERPRISES\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 7 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"TANKER REMLIN (P.CANAVERAL 17 APR) - SUBS\", \"1\": \"06\", \"2\": \"61\", \"3\": \"23/4\", \"4\": \"LARVIK - SUBS\", \"5\": \"19/4\", \"6\": \"MAMONAL\", \"7\": \"17/4\", \"8\": \"PENFIELD MARINE\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 8 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"BALTIC FROST (SAINT JOHN 21 APR) - ICE 1A\", \"1\": \"11\", \"2\": \"74\", \"3\": \"23/4\", \"4\": \"SCF PEARL\", \"5\": \"20/4\", \"6\": \"BALBOA\", \"7\": \"17/4\", \"8\": \"ST SHIPPING\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 9 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"SINGLE (BAH 17 APR) - ICE 1A\", \"1\": \"07\", \"2\": \"74\", \"3\": \"23/4\", \"4\": \"HELLESPONT PROTECTOR\", \"5\": \"21/4\", \"6\": \"ST EUSTATIUS\", \"7\": \"20/4\", \"8\": \"PENFIELD MARINE\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 10 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"AGISILAOS (HAITI 17 APR) - ICE 1A\", \"1\": \"04\", \"2\": \"71\", \"3\": \"24/4\", \"4\": \"CONFIDENCE - SUBS\", \"5\": \"22/4\", \"6\": \"LOOP\", \"7\": \"17/4\", \"8\": \"DYNACOM\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 11 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"STENA PENGUIN (NYC 22 APR) - ICE 1A\", \"1\": \"17\", \"2\": \"73\", \"3\": \"25/4\", \"4\": \"PERSEPOLIS\", \"5\": \"23/4\", \"6\": \"BALBOA\", \"7\": \"20/4\", \"8\": \"PANAMAX INTL\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 12 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"CAPE TAFT (RIVERHEAD 24 APR)\", \"1\": \"06\", \"2\": \"65\", \"3\": \"27/4\", \"4\": \"STENA PRIMORSK\", \"5\": \"24/4\", \"6\": \"ROCKY POINT\", \"7\": \"22/4\", \"8\": \"STENA\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 13 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"ICE POINT (PORT AU PRINCE 20 APR) - ICE 1A - SUBS\", \"1\": \"03\", \"2\": \"70\", \"3\": \"27/4\", \"4\": \"FEDOR (IN BLST STH)\", \"5\": \"24/4\", \"6\": \"EX MARTINEZ\", \"7\": \"09/4\", \"8\": \"PENFIELD MARINE\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 14 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"STENA PRIMORSK (ROCKY POINT 22 APR) - ICE 1B\", \"1\": \"07\", \"2\": \"73\", \"3\": \"29/4\", \"4\": \"CAPE TAURA\", \"5\": \"27/4\", \"6\": \"CHALMETTE\", \"7\": \"22/4\", \"8\": \"UPT\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 15 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"\", \"1\": \"08\", \"2\": \"68\", \"3\": \"\", \"4\": \"CABO SAN VICENTE\", \"5\": \"27/4\", \"6\": \"PORT ARTHUR\", \"7\": \"21/4\", \"8\": \"PANAMAX INTL\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 16 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"USG POSITIONS\", \"1\": \"10\", \"2\": \"64\", \"3\": \"ETA HOU\", \"4\": \"STENA PENGUIN\", \"5\": \"28/4\", \"6\": \"NEW YORK\", \"7\": \"22/4\", \"8\": \"STENA\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 17 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"MINERVA ZEN (TEXAS 17 APR) - SUBS\", \"1\": \"04\", \"2\": \"72\", \"3\": \"17/4\", \"4\": \"ANDIMILOS\", \"5\": \"28/4\", \"6\": \"USG\", \"7\": \"22/4\", \"8\": \"ELETSON\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 18 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"CONFIDENCE (LOOP 17 APR) - SUBS\", \"1\": \"17\", \"2\": \"72\", \"3\": \"18/4\", \"4\": \"AMAZON FALCON\", \"5\": \"30/4\", \"6\": \"PASCAGOULA\", \"7\": \"25/4\", \"8\": \"SUN ENTERPRISES\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 19 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"PORT UNION (ALTAMIRA MEXICO 17 APR)\", \"1\": \"05\", \"2\": \"61\", \"3\": \"19/4\", \"4\": \"XANTHOS\", \"5\": \"30/4\", \"6\": \"TUXPAN\", \"7\": \"24/4\", \"8\": \"PLEIADES\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 20 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"SINGLE (BAH 17 APR)\", \"1\": \"03\", \"2\": \"68\", \"3\": \"20/4\", \"4\": \"INCA\", \"5\": \"30/4\", \"6\": \"CRISTOBAL\", \"7\": \"28/4\", \"8\": \"TSAKOS\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 21 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"TANKER REMLIN (P.CANAVERAL 17 APR) - SUBS\", \"1\": \"04\", \"2\": \"72\", \"3\": \"21/4\", \"4\": \"PGC ASPROPYRGOS\", \"5\": \"30/4\", \"6\": \"GARYVILLE\", \"7\": \"24/4\", \"8\": \"PENFIELD MARINE\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 22 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"CABO SAN VICENTE (P.ARTHUR 21 APR)\", \"1\": \"08\", \"2\": \"73\", \"3\": \"21/4\", \"4\": \"CAPE TAFT\", \"5\": \"30/4\", \"6\": \"RIVERHEAD\", \"7\": \"24/4\", \"8\": \"PENFIELD MARINE\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 23 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"KASTOS (TUXPAN 19 APR)\", \"1\": \"14\", \"2\": \"73\", \"3\": \"21/4\", \"4\": \"AMAZON VICTORY\", \"5\": \"05/5\", \"6\": \"MISSISSIPPI RIVER\", \"7\": \"30/4\", \"8\": \"SUN ENTERPRISES\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 24 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"LARVIK (MAMONAL 17 APR) - SUBS\", \"1\": \"\", \"2\": \"\", \"3\": \"22/4\", \"4\": \"\", \"5\": \"\", \"6\": \"\", \"7\": \"\", \"8\": \"\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 25 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"ANDIMILOS (USG 22 APR)\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"22/4\", \"4\": \"HANDY/MR\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 26 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"PGC ASPROPYRGOS (GARYVILLE 24 APR)\", \"1\": \"06\", \"2\": \"36\", \"3\": \"24/4\", \"4\": \"AGISILAOS\", \"5\": \"19/4\", \"6\": \"HAITI\", \"7\": \"17/4\", \"8\": \"DIAMOND S \" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 27 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"CAPE TAURA (CHALMETTE 22 APR)\", \"1\": \"03\", \"2\": \"34\", \"3\": \"24/4\", \"4\": \"TANKER REMLIN - SUBS\", \"5\": \"21/4\", \"6\": \"PORT CANAVERAL\", \"7\": \"17/4\", \"8\": \"HANDYTANKERS\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 28 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"XANTHOS (TUXPAN 24 APR)\", \"1\": \"07\", \"2\": \"37\", \"3\": \"26/4\", \"4\": \"SINGLE\", \"5\": \"21/4\", \"6\": \"BAHAMAS\", \"7\": \"17/4\", \"8\": \"SCORPIO\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 29 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"AMAZON FALCON (PASCAGOULA 25 APR)\", \"1\": \"08\", \"2\": \"51\", \"3\": \"27/4\", \"4\": \"ICE POINT - SUBS\", \"5\": \"22/4\", \"6\": \"PORT AU PRINCE\", \"7\": \"20/4\", \"8\": \"PB TANKERS\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 30 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"AMAZON VICTORY (MISS RIVER 30 APR)\", \"1\": \"03\", \"2\": \"46\", \"3\": \"02/5\", \"4\": \"PORT UNION\", \"5\": \"23/4\", \"6\": \"ALTAMIRA (MEX)\", \"7\": \"17/4\", \"8\": \"UNION\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 31 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"\", \"1\": \"09\", \"2\": \"53\", \"3\": \"\", \"4\": \"MINERVA ZEN - SUBS\", \"5\": \"23/4\", \"6\": \"TEXAS CITY\", \"7\": \"17/4\", \"8\": \"MINERVA\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 32 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"USWC POSITIONS\", \"1\": \"08\", \"2\": \"46\", \"3\": \"ETA S.CRUZ\", \"4\": \"MINERVA JOANNA - SUBS\", \"5\": \"23/4\", \"6\": \"NEW YORK\", \"7\": \"17/4\", \"8\": \"MINERVA\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 33 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"FEDOR (EX MARTINEZ 09 APR)\", \"1\": \"06\", \"2\": \"40\", \"3\": \"18/4\", \"4\": \"PRINCE I (IN BLST STH)\", \"5\": \"24/4\", \"6\": \"EX LA PAZ\", \"7\": \"14/4\", \"8\": \"ST SHIPPING\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 34 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"PRINCE I (EX LA PAZ 14 APR)\", \"1\": \"10\", \"2\": \"51\", \"3\": \"20/4\", \"4\": \"KASTOS\", \"5\": \"25/4\", \"6\": \"TUXPAN\", \"7\": \"19/4\", \"8\": \"ELETSON\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 35 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"SCF PEARL (BALBOA 17 APR)\", \"1\": \"06\", \"2\": \"37\", \"3\": \"21/4\", \"4\": \"BALTIC FROST\", \"5\": \"27/4\", \"6\": \"SAINT JOHN\", \"7\": \"21/4\", \"8\": \"NORIENT\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 36 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"PERSEPOLIS (BALBOA 20 APR)\", \"1\": \"08\", \"2\": \"38\", \"3\": \"24/4\", \"4\": \"MARVEL\", \"5\": \"27/4\", \"6\": \"CARIBS\", \"7\": \"27/4\", \"8\": \"ROXANA\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 37 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"HELLESPONT PROMISE (LA 19 APR) - SUBS\", \"1\": \"11\", \"2\": \"37\", \"3\": \"25/4\", \"4\": \"ASTERION\", \"5\": \"01/5\", \"6\": \"ECUADOR\", \"7\": \"26/4\", \"8\": \"SCORPIO\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 38 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"ASOPOS (P.SANDINO 25 APR)\", \"1\": \"\", \"2\": \"\", \"3\": \"27/4\", \"4\": \"\", \"5\": \"\", \"6\": \"\", \"7\": \"\", \"8\": \"\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 39 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"CABO SAN ANTONIO (QUINTERO 19 APR)\", \"1\": \"\", \"2\": \"\", \"3\": \"29/4\", \"4\": \"\", \"5\": \"\", \"6\": \"\", \"7\": \"\", \"8\": \"\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 40 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"SEAWAYS SAMAR (LA 24 APR)\", \"1\": \"\", \"2\": \"\", \"3\": \"30/4\", \"4\": \"\", \"5\": \"\", \"6\": \"\", \"7\": \"\", \"8\": \"\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 41 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"STROFADES (USWC 24 APR)\", \"1\": \"\", \"2\": \"\", \"3\": \"01/5\", \"4\": \"\", \"5\": \"\", \"6\": \"\", \"7\": \"\", \"8\": \"\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 42 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"ASTERION (ECUADOR 26 APR)\", \"1\": \"\", \"2\": \"\", \"3\": \"01/5\", \"4\": \"\", \"5\": \"\", \"6\": \"\", \"7\": \"\", \"8\": \"\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 43 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"ALNIC MC (LA 27 APR)\", \"1\": \"\", \"2\": \"\", \"3\": \"03/5\", \"4\": \"\", \"5\": \"\", \"6\": \"\", \"7\": \"\", \"8\": \"\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 44 }, { \"StructureType\": \"table\", \"data\": { \"0\": \"H'PONT PRIDE (CHERRY POINT 25 APR) - UNC\", \"1\": \"\", \"2\": \"\", \"3\": \"05/5\", \"4\": \"\", \"5\": \"\", \"6\": \"\", \"7\": \"\", \"8\": \"\" }, \"header\": { \"0\": \"USAC POSITIONS\", \"1\": \"BLT\", \"2\": \"DWT\", \"3\": \"ETA CBC\", \"4\": \"PANAMAX\", \"5\": \"ETA STATIA\", \"6\": \"OPEN POSITION\", \"7\": \"OPEN\", \"8\": \"OWNERS\" }, \"line_index\": 45 } ] }}";
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
					 singleStringParsingmethod( data,  header,  new_header,objheader);
					
					 JSONObject newDataHeaderObj=singleStringParsingmethod(data, header, new_header, objheader);
						if(newDataHeaderObj.has("data")){data=newDataHeaderObj.getJSONObject("data");}
						if(newDataHeaderObj.has("header")){header=newDataHeaderObj.getJSONObject("header");}

						
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
							String solrKeyList = fetchsolrdata(hvalue.replace(" ", "_"));
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

			//System.out.println("returnobj " + returnobj);
			pallavi_WriteFile.writeUsingOutputStream( "SCRIPTJSON ::====== "+pythonScriptApiData.toString() +
					"\n OUTPUTJSON::====== "+returnobj .toString() , 
					filepath);

			return returnobj.toString();
		} catch (Exception e) {
			pallavi_WriteFile.writeUsingOutputStream( "SCRIPTJSON ::====== "+pythonScriptApiData.toString() +
					"\n OUTPUTJSON::====== "+returnobj .toString() +"\n error"+ e.getMessage(), 
					filepath);
			e.printStackTrace();
			out.println("error  "+e.getMessage()+"\n obj  "+returnobj);
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

			// url = "http://35.188.227.168:8983/solr/TemplateCore/select?q=" + q
			// + "&fl=" + f1 + "&df=" + df;

			// url = "http://35.188.227.168:8983/solr/Header_Synomes/select?q=" +
			// q + "&fl=" + f1;
			// url =
			// "http://35.188.227.168:8983/solr/Header_Synomes/select?q=Data:" +
			// q + "&fl=" + "HeaderName";
			url = "http://35.188.227.168:8983/solr/Header_Synomes/select?q=Data_str:" + q
					+ "&fl=score,HeaderName,Extrakey";
			// url =
			// "http://35.188.227.168:8983/solr/Header_Synomes/select?q=Data:("+q+")&fl=HeaderName";

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

			URL url = new URL("http://35.188.227.39:8080/enhancer/chain/scorpiosvchain");
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
			dvalue=dvalue.trim();
			dvalue=dvalue.toUpperCase();
		}
		JSONArray arr=null;
		JSONObject obj=null;
		try {
			// String url1 =
			// "http://35.186.170.65:8983/solr/Vessel/update/json/docs?commit=true";
			// String url1 =
			// "http://35.186.170.65:8983/solr/HSN_Code/update/json/docs?commit=true";
			// String url1 =
			// "http://35.188.227.168:8983/solr/PO_FlatteningTemplate/update/json/docs?commit=true";
			String url1 = "http://35.221.160.146:5032/date";

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
		url = "http://35.188.227.168:8983/solr/ReportType_Synomes/select?q=Data_str:" + passvalue + "&fl=score,HeaderName";

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
		url="http://35.188.227.168:8983/solr/HeaderAndDataValidation/select?fl=score,DataType&q=Headersynonyms:"+synonym;
		//url = "http://35.188.227.168:8983/solr/ReportType_Synomes/select?q=Data_str:" + passvalue + "&fl=score,HeaderName";
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
		//System.out.println("alldata "+alldata);
		//System.out.println("alldata_return  "+alldata_return);
		//System.out.println("stanbolarr  "+stanbolarr);
		//System.out.println("dateratearr "+dateratearr);
		//System.out.println("solrarr "+solrarr);

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
		
		
		//System.out.println(indexwisemap);
		data=new JSONObject();
		header=new JSONObject();
		int count=0;
        for (Map.Entry< Integer,JSONObject> entry : indexwisemap.entrySet()) { 
            JSONObject valuejson=entry.getValue();
            //System.out.println("valuejson "+valuejson);
        	String key=valuejson.getString("key");
            data.put(count+"", valuejson.getString("value"));
            if(key.equalsIgnoreCase("RepositionRegion")) {
                header.put(count+"", "Port");
            }else {
            header.put(count+"", key);
            }
            count++;
           } 
        String index=data.length()+"";
        data.put(index, alldata_return);
        header.put(index, "unknown_"+index);

//System.out.println("data "+data);
//System.out.println("header "+header);
returnobj.put("data", data);
returnobj.put("header", header);

		
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

    				
    				urlnadvalue.put("Index", index);
    				urlnadvalue.put("Index2", index2);
    				urlnadvalue.put("Index3", index3);

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
				String url1 = "http://35.221.160.146:5030/date";
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
				////System.out.println(" response "+response.toString());
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


						if (obj.has("value")) {
							valuearr = obj.getJSONArray("value");
							String maxvalue="";
						for(int j=0;j <valuearr.length(); j++) {
							String val=valuearr.getString(j);
							if(maxvalue.length()<val.length()) {
								maxvalue=val;
							}
						}
						////System.out.println("maxvalue "+maxvalue);
						if(alldata_return.contains(maxvalue)) {
							alldata_return=alldata_return.replace(maxvalue, " ");
						}
	    				int index=ordinalIndexOf( alldata,  maxvalue.toUpperCase(), 0); 
	    				int index2= ordinalIndexOf( alldata,  maxvalue.toUpperCase(), 1);
	    				int index3= ordinalIndexOf( alldata,  maxvalue.toUpperCase(), 2);

	    				subobj.put("Index", index);
	    				subobj.put("Index2", index2);
	    				subobj.put("Index3", index3);


						if(Regex_Type.equalsIgnoreCase("Date") || Regex_Type.equalsIgnoreCase("Date/Date")) {
						String regexpattern=obj.getString("Format");
						////System.out.println("regexpattern "+regexpattern);
						String formateddate = new pallavi_ConvertDatewithPattern().convertDatewithPattern(maxvalue, regexpattern);
						subobj.put("key", Regex_Type);
						subobj.put("value", formateddate);

						}else if(Regex_Type.equalsIgnoreCase("Rate")) {
							//subobj.put(maxvalue, Regex_Type);
							subobj.put("key", Regex_Type);
							subobj.put("value", maxvalue);

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
			subobj.put("Index", index);
			subobj.put("Index2", index2);
			subobj.put("Index3", index3);

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
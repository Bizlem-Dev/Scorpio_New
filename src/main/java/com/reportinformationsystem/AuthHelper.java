package com.reportinformationsystem;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.sling.api.SlingHttpServletRequest;

import com.readGmail.GmailMethods;

public class AuthHelper {

	public static boolean isAllowed(String username, String password) {

	    return username.contentEquals("scorpio") && password.contentEquals("scorpio");
	  }
	
	
	public static boolean isAllowedCredentials(String Authorization) {
		  boolean flag=false;
		  String password="$CorpioReport!ntegration$ystem@*3!2";
		  
		  try{
		   
			  if(password.equals(Authorization)){
				  flag=true; 
			  }else {
				  flag=false;
			  }
		    
		    
		    
		  }catch(Exception e){
			  
		  }
			return flag;
		    
		    
	}
	
	
	
	 public static boolean credentialsWithBasicAuthentication(SlingHttpServletRequest request) {
		  boolean flag=false;
		    String authHeader = request.getHeader("Authorization");
		    if (authHeader != null) {
		        StringTokenizer st = new StringTokenizer(authHeader);
		        if (st.hasMoreTokens()) {
		            String basic = st.nextToken();

		            if (basic.equalsIgnoreCase("BasicRealm")) {
		                try {
		                	Base64.Decoder decoder = Base64.getDecoder(); 
		                	
		                    // Decoding string  
		                    String credentials = new String(decoder.decode(st.nextToken()));
		                    //String credentials = new String(Base64.decode(st.nextToken()), "UTF-8");
		                   // System.out.println("Decoded string: "+credentials);  
		                    //String credentials = new String(Base64.decodeBase64(st.nextToken()), "UTF-8");
		                   // System.out.println("Credentials: " + credentials);
		                    int p = credentials.indexOf(":");
		                    if (p != -1) {
		                        String login = credentials.substring(0, p).trim();
		                        String password = credentials.substring(p + 1).trim();
		                         flag =AuthHelper.isAllowed(login,password);
		                       // System.out.println(flag);
		                       // return new CredentialException(login);
		                    } else {
		                    	System.out.println("Invalid authentication token");
		                    }
		                } catch (Exception e) {
		                	System.out.println("Couldn't retrieve authentication"+ e);
		                }
		            }
		        }
		    }

		    return flag;
		}
	 
	 
public static boolean etaBasisCheckDAteformate(String ETABasis1, PrintWriter out, String ReportTimestamp1, String year){
	boolean booleanOpenDate=false;
	try {
		
		
               booleanOpenDate = Pattern.matches("(\\d{2}\\-\\d{2}\\-\\d{4})", ETABasis1);
              
			
			if(booleanOpenDate==true){
				booleanOpenDate=true;
				
			}else{
				
				/*String EtaCheckDataTwoDigit=ChangedApiWithoutMultipleArray.convertStringDayAndMonth(ETABasis1, out);
                 
                 if( !GmailMethods.isNullString(EtaCheckDataTwoDigit) ){
                	 booleanOpenDate=true;
                 }*/
				//........
                  if( ETABasis1.contains("PPT") || ETABasis1.contains("PPT - exten") ){
                	 booleanOpenDate=true;
				
			}else{
				
				
				String data=FifteenMinuteClass.checkSpaceThree(ETABasis1, year);
				if( !GmailMethods.isNullString(data) ){
					booleanOpenDate=true;
				}else{
					String data1=FifteenMinuteClass.twoDate(ETABasis1, year);
					if( !GmailMethods.isNullString(data1) ){
						booleanOpenDate=true;
					}else{
						String data2=FifteenMinuteClass.twoDateSlash(ETABasis1, year);
						if( !GmailMethods.isNullString(data2) ){
							booleanOpenDate=true;
						}else{
							if(ETABasis1.equalsIgnoreCase("null")){
								booleanOpenDate=true;
							}else{
							
								String data3=FifteenMinuteClass.DateSpach(ETABasis1, year);
								if(!GmailMethods.isNullString(data3)){
									booleanOpenDate=true;
								}
							else{
								String data4=FifteenMinuteClass.dateDot(ETABasis1, year, out);
								if(!GmailMethods.isNullString(data4)){
									booleanOpenDate=true;
								}else{
									String data5=FifteenMinuteClass.datedashSingle(ETABasis1, year);
									if(!GmailMethods.isNullString(data5)){
										booleanOpenDate=true;
									}else{
										
										String data6=FifteenMinuteClass.dateSpaceSingle(ETABasis1, year);
										if(!GmailMethods.isNullString(data6)){
											booleanOpenDate=true;
										}else{
											booleanOpenDate=false;
										}
									}
								}
							}
								
							}
						}
					}
				}
				
				/*ETABasis=ETABasis1;*/
			}
			// ......
			
			}
		
		
	} catch (Exception e) {
		
	}
	return booleanOpenDate;
}
	 
	
	
}

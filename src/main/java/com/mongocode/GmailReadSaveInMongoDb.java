package com.mongocode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class GmailReadSaveInMongoDb {

	public static void main(String[] args) throws ParseException {

		// store attachment file name, separated by comma
        String attachFiles = "";
        String fileName ="abc";
        attachFiles += fileName + ", ";
        
        attachFiles = attachFiles.replaceAll(", $", "");
        String[] split=attachFiles.split(",");
        System.out.println(split.length);
        
        String d="Tue Oct 23 16:55:02 IST 2018";
        SimpleDateFormat format1 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date today = format1.parse(d);
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        d=formatter.format(today);
        System.out.println(d);
        String d2=formatter2.format(today);
        System.out.println(d2);
       
        
        String from="dean.marshall@clarksons.com";
		if (from.indexOf("@") != -1) {
			from = from.substring(from.indexOf("@") + 1); // e.g.
															// @google.com
		}
		
		if (from.indexOf(".") != -1) {
			from = from.substring(0, from.indexOf("."));
			System.out.println("from: " + from);
		}
		
	

        
	}
	
	public static DBCollection GmailReadMongo( String Dbname ,String collectionname, String Subject, Date SentDate, String Flag
			, int AttachmentLength
			) {
		 
	   	   DBCollection collection=null;
	   	   MongoClient mongoClient=null;
	       try {
	    	   
	    	   BasicDBObject obj = new BasicDBObject();
	    	   
	            mongoClient = new MongoClient("localhost", 27017);
	            DB mongoDataBase = mongoClient.getDB(Dbname); // databasename
	            
	            
//	            String date="Fri Apr 12 19:19:19 IST 2019";
	    		SimpleDateFormat format1 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");	
	    		
	    try {
	    	Date today = format1.parse(collectionname);
	        //Formatting today's date in EEEE, MMM dd yyyy, hh:mm:ss a format
	         
	        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	        collectionname=formatter.format(today);
	       // System.out.println("Today in EEEE, MMM dd yyyy, hh:mm:ss a format : "+formatter.format(today));

	    	//System.out.println(format1.parse(date));
	    } catch (Exception e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
	            
	           boolean collectionNameExists= MongoDbConnection.collectionExists(collectionname, mongoDataBase);
	            if(collectionNameExists){
	            	// System.out.println("true: "+collectionname);
	            	 collection = mongoDataBase.getCollection(collectionname);
	            	
	            	 obj.put("Subject", Subject);
	            	 obj.put("SentDate", SentDate);
	            	 if(Flag.equals("1")){  // read mails
	            		 obj.put("Flag", Flag);
	            	 }else{
	            		 obj.put("Flag", "2");  // not read mails
	            	 }
	            	 obj.put("AttachmentLength", AttachmentLength);
	            	 
	            }else{
	            	collection=mongoDataBase.createCollection(collectionname,new BasicDBObject());
	            	
	            	 obj.put("Subject", Subject);
	            	 obj.put("SentDate", SentDate);
	            	 if(Flag.equals("1")){  // read mails
	            		 obj.put("Flag", Flag);
	            	 }else{
	            		 obj.put("Flag", "2");  // not read mails
	            	 }
	            	
	            	 obj.put("AttachmentLength", AttachmentLength);
					 
	            }
	            collection.insert(obj);

	       } catch (Exception e) {
	           e.printStackTrace();
	       } finally{
	    	   mongoClient.close();
	       }
	       return collection;

	   }
	
	public static int attachmentCount(String contentType, Message message) {

		int fileName = 0;
		try {
			// store attachment file name, separated by comma
            String attachFiles = "";
			if (contentType.contains("multipart")) {

				Multipart multiPart = (Multipart) message.getContent();
				int numberOfParts = multiPart.getCount();
				MimeBodyPart part = null;
				for (int partCount = 0; partCount < numberOfParts; partCount++) {
					part = (MimeBodyPart) multiPart.getBodyPart(partCount);

					if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
						String filename = part.getFileName();
                        attachFiles += filename + ", ";
						
					}
				} // for loop
				
		        attachFiles = attachFiles.replaceAll(", $", ""); // if at the end any comma left remove
		        String[] split=attachFiles.split(",");
		        fileName=split.length;
		        
			}

		} catch (Exception e) {
			return fileName;
		}
		return fileName;
	}
	
	
	

}

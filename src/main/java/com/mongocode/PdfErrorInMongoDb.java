package com.mongocode;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class PdfErrorInMongoDb {

	public static void main(String[] args) {

	}
	
	 public static DBCollection storePdfErrorMongo( String Dbname ,String collectionname, String Date,  String Subject, String error) {
		 
	   	   DBCollection collection=null;
	   	   MongoClient mongoClient=null;
	       try {
	            mongoClient = new MongoClient("localhost", 27017);
	            DB mongoDataBase = mongoClient.getDB(Dbname);
	            collection = mongoDataBase.getCollection(collectionname);
	            
	            BasicDBObject obj = new BasicDBObject();
				 
				 obj.put("Date", Date);
				 obj.put("Subject", Subject);
				 obj.put("Error", error);
				 collection.insert(obj);

	       } catch (Exception e) {
	           e.printStackTrace();
	       } finally{
	    	   mongoClient.close();
	    	   //System.out.println("insertCatchReadMailData Method Executed Successfully!");
	       }
	       return collection;

	   }   
	

}

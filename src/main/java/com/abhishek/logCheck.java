package com.abhishek;

import org.apache.log4j.Logger;


public class logCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         data();
	}
	
	public static void data(){
		  Logger logger = Logger.getLogger(logCheck.class);
		  System.out.println("hiii");
		  if(logger.isInfoEnabled()){
			  logger.info("yes");
			  
			  
		  }
		  
		  logger.info("checkdata:: "+"abhishek");
	}

}

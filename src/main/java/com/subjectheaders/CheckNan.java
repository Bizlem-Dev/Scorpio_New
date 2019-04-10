package com.subjectheaders;

import java.io.PrintWriter;

public class CheckNan {

	public static void main(String[] args) {

	}
	
	public static String makeNanToBlank(PrintWriter out, String NanDataPass){
//		NanDataPass=NanDataPass.toLowerCase();
		try {
			
			
			if(NanDataPass.equalsIgnoreCase("nan")){
				NanDataPass="";
				out.println("found Nan Value :: ");
			}
			
		} catch (Exception e) {
			return NanDataPass;
		}
		return NanDataPass;
	}

}

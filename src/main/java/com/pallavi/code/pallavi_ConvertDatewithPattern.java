package com.pallavi.code;


import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;  

public class pallavi_ConvertDatewithPattern {	
	int day = 0;
	int month = 0;
	int year = 0;
	String mnth[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	String mon[]= {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	Pattern p = Pattern.compile("\\d");

	public static void main(String[] args) {
		String date="(11-15/MAR/19)";
		String pattern= "DD-DD/MMM/YY";

		pallavi_ConvertDatewithPattern cdn= new pallavi_ConvertDatewithPattern();

		String retDate= cdn.convertDatewithPattern(date, pattern);

		System.out.println("retDate: "+retDate);	
	}

	public String convertDatewithPattern(String date, String pattern) {
		String retDate="";
		if(date.contains("(") && date.contains(")")) {
			date= date.replace("(", "");
			date= date.replace(")", "");
			System.out.println("date: "+date);
		}

		if(pattern.contains(" ") && pattern.contains("-") && pattern.contains("/")) {
			date= date.replace(" ", "");
			pattern= pattern.replace(" ", "");
			retDate= isContainDashnSlash(date, pattern);
		}else if (pattern.contains("-") && pattern.contains(" ")) {
			retDate= isContainDashnSpace(date, pattern);
		}else if(pattern.contains(" ")) {
			retDate= isContainSpace(date, pattern);
		}else if (pattern.contains("-") && pattern.contains("/")) {
			retDate= isContainDashnSlash(date, pattern);
		}else if (pattern.contains("/")) {
			retDate= isContainSlash(date, pattern);
		}else if (pattern.contains(".")) {
			retDate= isContainDot(date, pattern);
		}else if (pattern.contains("-")) {
			retDate= isContainDash(date, pattern);
		}
		return retDate;
	}

	private String isContainDashnSpace(String date, String pattern) {
		int day1=0;
		int day2=0;

		if(date.indexOf("-")<date.indexOf(" ")) {
			String strDate[] = date.split(" ");
			String strPat[] = pattern.split(" ");

			for(int a=0; a<strPat.length; a++) {
				System.out.println("str[a]: "+strDate[a]);
				if(strPat[a].equalsIgnoreCase("DD-DD")) {
					String str1[]= strDate[a].split("-");
					for(int i=0; i<str1.length; i++) {
						if(i==0) {
							day1= Integer.parseInt(str1[i]);
						}else {
							day2= Integer.parseInt(str1[i]);
						}
					}
				}else if(strPat[a].equalsIgnoreCase("MMM") || strPat[a].equalsIgnoreCase("MONTH") || strPat[a].equalsIgnoreCase("MM")) {
					if(strDate[a].matches(".*[a-zA-Z].*")) {
						for(int j=0; j<mon.length; j++) {
							if(mon[j].equalsIgnoreCase(strDate[a])) {
								month= j+1;
							}else if(mnth[j].equalsIgnoreCase(strDate[a])) {
								month= j+1;
							}
						}
					}else {
						month= Integer.parseInt(strDate[a]);
					}

				}else if(strPat[a].equalsIgnoreCase("YYYY") || strPat[a].equalsIgnoreCase("YY")) {
					//year= Integer.parseInt(strDate[i]);
					//					year= Integer.parseInt(strDate[i]);
					Matcher m = p.matcher(strDate[a]);
					int count = 0;
					while(m.find()){
						count++;
						//out.println("count: "+count);
					}
					if(count==4) {
						year= Integer.parseInt(strDate[a]);
					}else if(count==2){
						if(Integer.parseInt(strDate[a])!=Integer.parseInt(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2, 3))){
							year= Integer.parseInt(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(0, 2)+Integer.parseInt(strDate[a]));
						}else {
							year= Calendar.getInstance().get(Calendar.YEAR);
						}				
					}
				}

				if(year==0) {
					year= Calendar.getInstance().get(Calendar.YEAR);
				}

			}

		}
		return day1+"/"+month+"/"+year+"&"+day2+"/"+month+"/"+year;

	}

	public String isContainSpace(String date, String pattern) {

		String strPat[] = pattern.split(" ");
		String strDate[]= date.split(" ");

		for(int i=0; i<strPat.length; i++) {
			System.out.println(strDate[i]);
			if(strPat[i].equalsIgnoreCase("DD")) {
				if(strDate[i].matches(".*[a-zA-Z].*") && strDate[i].matches(".*[0-9].*") && !strDate[i].contains(" ") && !strDate[i].contains("/") && !strDate[i].contains("-") && !strDate[i].contains(".")) {
					String newStr= isAlphaNumeric(strDate[i]);
					System.out.println("newStr: "+newStr);
					day= Integer.parseInt(newStr);
				}else if(strDate[i].contains(",")) {
					String newStr= strDate[i].replace(",", "");
					day= Integer.parseInt(newStr);
				}	
				else{
					day= Integer.parseInt(strDate[i]);
				}					
			}else if(strPat[i].equalsIgnoreCase("MMM") || strPat[i].equalsIgnoreCase("MONTH")) {
				for(int j=0; j<mon.length; j++) {
					if(mon[j].equalsIgnoreCase(strDate[i])) {
						month= j+1;
					}else if(mnth[j].equalsIgnoreCase(strDate[i])) {
						month= j+1;
					}
				}
			}else if(strPat[i].equalsIgnoreCase("YYYY")) {
				year= Integer.parseInt(strDate[i]);
			}

			if(year==0) {
				year= Calendar.getInstance().get(Calendar.YEAR);
			}
		}
		return day+"/"+month+"/"+year;
	}

	public String isContainSlash(String date, String pattern) {
		String strPat[] = pattern.split("/");
		String strDate[]= date.split("/");

		for(int i=0; i<strPat.length; i++) {
			System.out.println(strDate[i]);
			if(strPat[i].equalsIgnoreCase("DD")) {
				if(strDate[i].matches(".*[a-zA-Z].*") && strDate[i].matches(".*[0-9].*") && !strDate[i].contains(" ") && !strDate[i].contains("/") && !strDate[i].contains("-") && !strDate[i].contains(".")) {
					String newStr= isAlphaNumeric(strDate[i]);
					System.out.println("newStr: "+newStr);
					day= Integer.parseInt(newStr);
				}else if(strDate[i].contains(",")) {
					String newStr= strDate[i].replace(",", "");
					day= Integer.parseInt(newStr);
				}	
				else{
					day= Integer.parseInt(strDate[i]);
				}					
			}else if(strPat[i].equalsIgnoreCase("MMM") || strPat[i].equalsIgnoreCase("MONTH") || strPat[i].equalsIgnoreCase("MM")) {
				if(strDate[i].matches(".*[a-zA-Z].*")) {
					for(int j=0; j<mon.length; j++) {
						if(mon[j].equalsIgnoreCase(strDate[i])) {
							month= j+1;
						}else if(mnth[j].equalsIgnoreCase(strDate[i])) {
							month= j+1;
						}
					}
				}else {
					month= Integer.parseInt(strDate[i]);
				}

			}else if(strPat[i].equalsIgnoreCase("YYYY") || strPat[i].equalsIgnoreCase("YY")) {
				//year= Integer.parseInt(strDate[i]);
				//					year= Integer.parseInt(strDate[i]);
				Matcher m = p.matcher(strDate[i]);
				int count = 0;
				while(m.find()){
					count++;
					//out.println("count: "+count);
				}
				if(count==4) {
					year= Integer.parseInt(strDate[i]);
				}else if(count==2){
					if(Integer.parseInt(strDate[i])!=Integer.parseInt(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2, 3))){
						year= Integer.parseInt(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(0, 2)+Integer.parseInt(strDate[i]));
					}else {
						year= Calendar.getInstance().get(Calendar.YEAR);
					}				
				}
			}

			if(year==0) {
				year= Calendar.getInstance().get(Calendar.YEAR);
			}
		}

		return day+"/"+month+"/"+year;
	}

	public String isContainDot(String date, String pattern) {
		String strPat[] = pattern.split("\\.");
		String strDate[]= date.split("\\.");
		
		for(int i=0; i<strPat.length; i++) {
			System.out.println(strDate[i]);
			if(strPat[i].equalsIgnoreCase("DD")) {
				if(strDate[i].matches(".*[a-zA-Z].*") && strDate[i].matches(".*[0-9].*") && !strDate[i].contains(" ") && !strDate[i].contains("/") && !strDate[i].contains("-") && !strDate[i].contains(".")) {
					String newStr= isAlphaNumeric(strDate[i]);
					System.out.println("newStr: "+newStr);
					day= Integer.parseInt(newStr);
				}else if(strDate[i].contains(",")) {
					String newStr= strDate[i].replace(",", "");
					day= Integer.parseInt(newStr);
				}	
				else{
					day= Integer.parseInt(strDate[i]);
				}					
			}else if(strPat[i].equalsIgnoreCase("MMM") || strPat[i].equalsIgnoreCase("MONTH") || strPat[i].equalsIgnoreCase("MM")) {
				if(strDate[i].matches(".*[a-zA-Z].*")) {
					for(int j=0; j<mon.length; j++) {
						if(mon[j].equalsIgnoreCase(strDate[i])) {
							month= j+1;
						}else if(mnth[j].equalsIgnoreCase(strDate[i])) {
							month= j+1;
						}
					}
				}else {
					month= Integer.parseInt(strDate[i]);
				}

			}else if(strPat[i].equalsIgnoreCase("YYYY") || strPat[i].equalsIgnoreCase("YY")) {
				//year= Integer.parseInt(strDate[i]);
				//					year= Integer.parseInt(strDate[i]);
				Matcher m = p.matcher(strDate[i]);
				int count = 0;
				while(m.find()){
					count++;
					//out.println("count: "+count);
				}
				if(count==4) {
					year= Integer.parseInt(strDate[i]);
				}else if(count==2){
					if(Integer.parseInt(strDate[i])!=Integer.parseInt(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2, 3))){
						year= Integer.parseInt(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(0, 2)+Integer.parseInt(strDate[i]));
					}else {
						year= Calendar.getInstance().get(Calendar.YEAR);
					}				
				}
			}

			if(year==0) {
				year= Calendar.getInstance().get(Calendar.YEAR);
			}
		}

		return day+"/"+month+"/"+year;
	}

	public String isContainDashnSlash(String date, String pattern) {
		int day1=0;
		int day2=0;

		if(date.indexOf("-")<date.indexOf("/")) {
			String strDate[] = date.split("/");
			String strPat[] = pattern.split("/");

			for(int a=0; a<strPat.length; a++) {
				System.out.println("str[a]: "+strDate[a]);
				if(strPat[a].equalsIgnoreCase("DD-DD")) {
					String str1[]= strDate[a].split("-");
					for(int i=0; i<str1.length; i++) {
						if(i==0) {
							day1= Integer.parseInt(str1[i]);
						}else {
							day2= Integer.parseInt(str1[i]);
						}
					}
				}else if(strPat[a].equalsIgnoreCase("MMM") || strPat[a].equalsIgnoreCase("MONTH") || strPat[a].equalsIgnoreCase("MM")) {
					if(strDate[a].matches(".*[a-zA-Z].*")) {
						for(int j=0; j<mon.length; j++) {
							if(mon[j].equalsIgnoreCase(strDate[a])) {
								month= j+1;
							}else if(mnth[j].equalsIgnoreCase(strDate[a])) {
								month= j+1;
							}
						}
					}else {
						month= Integer.parseInt(strDate[a]);
					}

				}else if(strPat[a].equalsIgnoreCase("YYYY") || strPat[a].equalsIgnoreCase("YY")) {
					//year= Integer.parseInt(strDate[i]);
					//					year= Integer.parseInt(strDate[i]);
					Matcher m = p.matcher(strDate[a]);
					int count = 0;
					while(m.find()){
						count++;
						//out.println("count: "+count);
					}
					if(count==4) {
						year= Integer.parseInt(strDate[a]);
					}else if(count==2){
						if(Integer.parseInt(strDate[a])!=Integer.parseInt(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2, 3))){
							year= Integer.parseInt(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(0, 2)+Integer.parseInt(strDate[a]));
						}else {
							year= Calendar.getInstance().get(Calendar.YEAR);
						}				
					}
				}

				if(year==0) {
					year= Calendar.getInstance().get(Calendar.YEAR);
				}

			}

		}
		return day1+"/"+month+"/"+year+"&"+day2+"/"+month+"/"+year;

	}

	public String isAlphaNumeric(String date) {
		String number = "";
		String letter = "";
		for (int i = 0; i < date.length(); i++) {
			char a = date.charAt(i);
			if (Character.isDigit(a)) {
				number = number + a;
			} else {
				letter = letter + a;
			}
		}
		String newdate="";
		for(int k=0; k<mnth.length; k++) {
			if(mnth[k].equalsIgnoreCase(letter)) {
				newdate = number+"-"+letter;
			}else {
				newdate= number;
			}
		}
		System.out.println("newDate: "+newdate);
		return newdate;
	}
	public String isContainDash(String date, String pattern) {

		String strPat[] = pattern.split("-");
		String strDate[]= date.split("-");

		for(int i=0; i<strPat.length; i++) {
		System.out.println(strDate[i]);
		if(strPat[i].equalsIgnoreCase("DD")) {
		if(strDate[i].matches(".*[a-zA-Z].*") && strDate[i].matches(".*[0-9].*") && !strDate[i].contains(" ") && !strDate[i].contains("/") && !strDate[i].contains("-") && !strDate[i].contains(".")) {
		String newStr= isAlphaNumeric(strDate[i]);
		System.out.println("newStr: "+newStr);
		day= Integer.parseInt(newStr);
		}else if(strDate[i].contains(",")) {
		String newStr= strDate[i].replace(",", "");
		day= Integer.parseInt(newStr);
		}	
		else{
		day= Integer.parseInt(strDate[i]);
		}	
		}else if(strPat[i].equalsIgnoreCase("MMM") || strPat[i].equalsIgnoreCase("MONTH")) {
		for(int j=0; j<mon.length; j++) {
		if(mon[j].equalsIgnoreCase(strDate[i])) {
		month= j+1;
		}else if(mnth[j].equalsIgnoreCase(strDate[i])) {
		month= j+1;
		}
		}
		}else if(strPat[i].equalsIgnoreCase("YYYY")) {
		year= Integer.parseInt(strDate[i]);
		}

		if(year==0) {
		year= Calendar.getInstance().get(Calendar.YEAR);
		}
		}
		return day+"/"+month+"/"+year;
		}

}



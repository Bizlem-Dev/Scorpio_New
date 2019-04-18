package com.pallavi.code;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import net.rationalminds.LocalDateModel;  
import net.rationalminds.Parser;  

public class pallavi_ConvertDate {	

	int day = 0;
	int month = 0;
	int year = 0;
	String mnth[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	String mon[]= {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	Pattern p = Pattern.compile("\\d");

	Date dd = Calendar.getInstance().getTime();  
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
	String for_dd = dateFormat.format(dd);

	String cur_mon="";
	String cur_day="";
	String cur_yr="";

	public static void main(String[] args) {
		//		System.out.println("day: "+day+" month: "+month+" year: "+year);
		//String sDate1="9 - 10 JAN".replaceAll("\\W", " ");
		String sDate1="Apr 24";
		//s.replaceAll("\\W", ""); 
		System.out.println(sDate1);
		//sDate1.replace(" ", "");
		System.out.println("*sDate1: "+sDate1);
		pallavi_ConvertDate cd= new pallavi_ConvertDate();
		//DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
		//String strDate = dateFormat.format("Mon Feb 11 20:25:32 IST 2019");  

		String date= cd.convertDateFormat(sDate1);

		System.out.println("date_aajka:\t"+date);	
	}

	//public String convertDateFormat(String date, String reportTimeStampDate) {
	public String convertDateFormat(String date, String reportTimeStampDate) {

		System.out.println("*"+"day: "+day+" month: "+month+" year: "+year);
		String retDate="";

		try {
			//PrintWriter out= rep.getWriter();
			//out.println("**"+reportTimeStampDate);
			/*Date cur_date = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
			String strDate = dateFormat.format(cur_date);  
			 */
			//out.println("current Date: "+strDate);

			Parser parser=new Parser();  
			List<LocalDateModel> dates=parser.parse(date); 
			System.out.println("dates: "+dates);
			List<LocalDateModel> curDate= parser.parse(reportTimeStampDate);
			System.out.println("curDate: "+curDate);		       

			//out.println("cur_mon: "+cur_mon+" cur_day: "+cur_day+" cur_yr: "+cur_yr);
			//out.println(dates);

			if(curDate.isEmpty()) {
				System.out.println("**day: "+day+" month: "+month+" year: "+year);
				if(reportTimeStampDate.contains("/")) {
					String cur_str[] = reportTimeStampDate.split("/");
					cur_mon= cur_str[0];
					cur_day= cur_str[1];
					cur_yr= cur_str[2];		    		
				}/*else if (reportTimeStampDate.contains("-")) {
		    		//retDate= isContainsDash(date, mnth, mon, cur_mon, cur_day, cur_yr, p, rep);
		    		retDate= isContainsDash(date, mnth, mon, cur_mon, cur_day, cur_yr, p);
		    		//out.println("isContainsDash: "+retDate);	    			
		    	}else if (reportTimeStampDate.contains(",")) {
		    		//retDate= isContainsComma(date,mnth, mon, cur_mon, cur_day, cur_yr, rep);
		    		retDate= isContainsComma(date,mnth, mon, cur_mon, cur_day, cur_yr);
		    		//out.println("isContainsComma: "+retDate);	    		
		    	}*/else if(reportTimeStampDate.contains(" ")) {
		    		//retDate= isContainsSpace(date,mnth, mon, cur_mon, cur_day, cur_yr, rep);
		    		retDate= isContainsSpace(reportTimeStampDate, mnth, mon);
		    		//out.println("ret_cur_date: "+retDate);
		    		String cur_str[] = retDate.split("/");
		    		cur_mon= cur_str[1];
		    		cur_day= cur_str[0];
		    		cur_yr= cur_str[2];	   
		    		System.out.println("isContainsSpaceCD: "+retDate+"cur_mon: "+cur_mon+"cur_day: "+cur_day+"cur_yr: "+cur_yr);
		    		System.out.println("****day: "+day+" month: "+month+" year: "+year);
		    	}		    	 		
			}else {
				LocalDateModel ldm= curDate.get(0);
				String cur_str[] = ldm.getDateTimeString().split("-");
				cur_mon= cur_str[1];
				cur_day= cur_str[2];
				cur_yr= cur_str[0];				
			}

			if(date.equalsIgnoreCase("PPT") || date.equalsIgnoreCase("null")) {
				System.out.println("*****day: "+day+" month: "+month+" year: "+year);
				Date cur_date = Calendar.getInstance().getTime();  
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
				String strDate = dateFormat.format(cur_date);
				String str[] = strDate.split("/");
				day = Integer.parseInt(str[1]);
				month = Integer.parseInt(str[0]);
				year = Integer.parseInt(str[2]);
				retDate= day+"/"+month+"/"+year;
			}else if(dates.isEmpty()) {
				System.out.println("******day: "+day+" month: "+month+" year: "+year);
				//out.println(cur_mon+" "+cur_day+" "+cur_yr);
				if(date.contains("/")) {
					//retDate= isContainsSlash(date, cur_mon, cur_day, cur_yr, p, rep);
					retDate= isContainsSlash(date, cur_mon, cur_day, cur_yr, p);
					//out.println("isContainsSlash: "+retDate);
				}else if (date.contains("-")) {
					//retDate= isContainsDash(date, mnth, mon, cur_mon, cur_day, cur_yr, p, rep);
					retDate= isContainsDash(date, mnth, mon, cur_mon, cur_day, cur_yr, p);
					System.out.println("*******day: "+day+" month: "+month+" year: "+year);
					//out.println("isContainsDash: "+retDate);	    			
				}else if (date.contains(",")) {
					//retDate= isContainsComma(date,mnth, mon, cur_mon, cur_day, cur_yr, rep);
					retDate= isContainsComma(date, mnth, mon, cur_mon, cur_day, cur_yr);
					//out.println("isContainsComma: "+retDate);	    		
				}else if(date.contains(" ")) {
					//retDate= isContainsSpace(date,mnth, mon, cur_mon, cur_day, cur_yr, rep);
					retDate= isContainsSpace(date,mnth, mon, cur_mon, cur_day, cur_yr);
					//out.println("isContainsSpace: "+retDate);
				}else if (date.contains(".")) {
					retDate= isContainsDot(date,mnth, mon, cur_mon, cur_day, cur_yr, p);
				}/*else {
					Matcher m = p.matcher(date);
					int count = 0;
					while(m.find()){
						count++;
						//out.println("count: "+count);
					}
					if(count==4) {
						year= Integer.parseInt(date);
						//out.println("year: "+year);
					}
					retDate= day+"/"+month+"/"+year;
				}*/else {
					System.out.println("no match found");
					retDate =date;
				}
			}else {
				LocalDateModel d= dates.get(0);
				//out.println(dates.get(0));
				//out.println(d.getDateTimeString());
				String str[] = d.getDateTimeString().split("-");
				day = Integer.parseInt(str[2]);
				month = Integer.parseInt(str[1]);
				year = Integer.parseInt(str[0]);
				retDate= day+"/"+month+"/"+year;
				//out.println("retDate_LDM: "+retDate);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retDate;			
	}

	public String convertDateFormat(String date) {
		// TODO Auto-generated method stub
		String retDate="";
		Parser parser=new Parser();  
		List<LocalDateModel> dates=parser.parse(date); 
		System.out.println("dates: "+dates);

		if(dates.isEmpty()) {
			if (date.contains("/") && date.contains("-")) {
				retDate= isContainsSlashnDash(date);
				System.out.println("isContainsSlashnDash: "+retDate);				
			}else if(date.contains(" ") && date.contains("-")) {//update
				date= date.replaceAll("\\W", " ");
				System.out.println("__"+date);
				retDate= isContainsSpace(date, mnth, mon);
				System.out.println("isContainsSpace*: "+retDate);	    
			}else if (date.contains("-")) {
				retDate= isContainsDash(date, mnth, mon, p);
				System.out.println("isContainsDash: "+retDate);	    			
			}else if (date.contains("/")) {
				retDate= isContainsSlash(date, mnth, mon);
				System.out.println("isContainsSlash: "+retDate);	    			
			}else if (date.contains(".")) {
				retDate= isContainsDot(date, mnth, mon);
				System.out.println("isContainsDot: "+retDate);	    			
			}else if(date.contains(" ")){
				System.out.println("Date: "+date);
				/*if(!date.contains(".*\\d+.*")) {
					System.out.println(date.contains(".*\\d+.*"));

					String str[]= date.split(" ");
					for(int i=0; i<str.length; i++) {
						for(int k=0; k<mnth.length; k++) {
							System.out.println("mnth: "+mnth[k] +"&&"+ str[i]);
							if(mnth[k].equalsIgnoreCase(str[i])) {
								month= k+1;
								System.out.println("month: "+month);
//								yr1="2019";
//								yr2="2019";
							}else if(mon[k].equalsIgnoreCase(str[i])){
								System.out.println("00"+str[i]+" "+k);
								month= k+1;
								System.out.println("mon: "+month);
								yr1="2019";
								yr2="2019";
							}else {
								yr1= str[0];
								yr2= str[1];
							}
						}
					}
					retDate= day+"/"+month+"/"+yr1+"&"+day+"/"+month+"/"+yr2;
				}*//*else {
					retDate= isContainsSpace(date);
				}*/

				/*String opDate = isContainsSpace(date);
				String str[]= opDate.split("&");
				for(int i=0; i<str.length; i++) {
					if(i==0) {
						retDate= str[0];
					}else {
						retDate= str[1];
					}
				}*/
				retDate= isContainsSpace(date, mnth, mon);
				System.out.println("isContainsSpace: "+retDate);
			}else if(date.matches(".*[a-zA-Z].*") && date.matches(".*[0-9].*") && !date.contains(" ") && !date.contains("/") && !date.contains("-") && !date.contains(".")) {
				String newdate= isAlphaNumeric(date);
				retDate= isContainsDash(newdate, mnth, mon, p);
				System.out.println("retDate- "+retDate);
			}//update

			/*else if(!date.contains(".*\\d+.*") && date.contains("/")){
				String str[]= date.split("/");
				for(int i=0; i<str.length; i++) {
					for(int k=0; k<mnth.length; k++) {
						//out.println("mnth: "+mnth[k]);
						if(mnth[k].equalsIgnoreCase(str[i])) {
							month= k+1;
						}else if(mon[k].equalsIgnoreCase(str[i])){
							//out.println("00"+str[0]+" "+k);
							month= k+1;
						}else {
							day= 0; 
							month= 0;							
						}
					}
				}
				retDate= day+"/"+month+"/"+str[0]+"&"+day+"/"+month+"/"+str[1];
			}*//*else if (date.contains("/") && date.contains("-")) {
				retDate= isContainsSlashnDash(date);
				System.out.println("isContainsSlashnDash: "+retDate);				
			}else if (date.contains("-") && date.contains(" ")) {
				System.out.println("test");
				retDate= isContainsDashnSpace(date);
				System.out.println("isContainsSlashnDash: "+retDate);				
			}*/else {
				//retDate= day+"/"+month+"/"+date;
				retDate= date;
			}
		}else {
			LocalDateModel d= dates.get(0);
			String str[] = d.getDateTimeString().split("-");
			day = Integer.parseInt(str[2]);
			month = Integer.parseInt(str[1]);
			year = Integer.parseInt(str[0]);
			retDate= day+"/"+month+"/"+year;			
		}
		return retDate;
	}
	//update
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
	/*public String isContainsSpace(String date) {
		String str[] = date.split(" ");
		int day1=0;
		int day2=0;
		for(int a=0; a<str.length; a++) {
			if(str[a].matches(".*\\d+.*")) {
				System.out.println("..."+str[a]);
				if(a==0) {
					day1= Integer.parseInt(str[a]);
					System.out.println("day1: "+day1);
				}else if(a==1){
					day2= Integer.parseInt(str[a]);
					System.out.println("day2: "+day2);
				}else if(a==2) {
					month= Integer.parseInt(str[a]);
				}

			}else {
				for(int k=0; k<mnth.length; k++) {
					//out.println("mnth: "+mnth[k]);
					if(mnth[k].equalsIgnoreCase(str[a])) {
						month= k+1;
						System.out.println("month*: "+month);
					}else if(mon[k].equalsIgnoreCase(str[a])){
						//out.println("00"+str[0]+" "+k);
						month= k+1;
						System.out.println("month**: "+month);
					}				
				}
			}
		}
		String cur_date= Calendar.getInstance().getTime().toString();
		String cur_year= cur_date.substring(cur_date.lastIndexOf(" ")+1);
		System.out.println(cur_year);
		year=  Integer.parseInt(cur_year);
		String date1= day1+"/"+month+"/"+year;
		String date2= day2+"/"+month+"/"+year;
		return date1+"&"+date2;
	}*/


	/*public String isContainsDashnSpace(String date) {
		String str[] = date.split(" ");
		int day1=0;
		int day2=0;
		for(int a=0; a<str.length; a++) {
			if(str[a].contains("")) {
				String str1[]= str[a].split("-");
				for(int i=0; i<str1.length; i++) {
					if(i==0) {
						day1= Integer.parseInt(str1[i]);
					}else {
						day2= Integer.parseInt(str1[i]);
					}
				}
			}else {
				for(int k=0; k<mnth.length; k++) {
					//out.println("mnth: "+mnth[k]);
					if(mnth[k].equalsIgnoreCase(str[a])) {
						month= k+1;
						System.out.println("month*: "+month);
					}else if(mon[k].equalsIgnoreCase(str[a])){
						//out.println("00"+str[0]+" "+k);
						month= k+1;
						System.out.println("month**: "+month);
					}
				}
			}
		}
		String date1= day1+"/"+month+"/"+year;
		String date2= day2+"/"+month+"/"+year;
		return date1+"&"+date2;
	}
	 */
	public String isContainsSlashnDash(String date) {
		/*System.out.println("*"+date);
		int day1=0;
		int day2=0;

		if(date.indexOf("-")<date.indexOf("/")) {
			String str[] = date.split("/");

			for(int a=0; a<str.length; a++) {
				System.out.println("str[a]: "+str[a]);
				if(str[a].contains("-")) {
					String str1[]= str[a].split("-");
					for(int i=0; i<str1.length; i++) {
						if(i==0) {
							day1= Integer.parseInt(str1[i]);
						}else {
							day2= Integer.parseInt(str1[i]);
						}
					}
				}else {
					month= Integer.parseInt(str[a]);
			}
			}

		}else {
			String str[] = date.split("-");
			for(int a=0; a<str.length; a++) {
				if(str[a].contains("/")) {
					String str1[]= str[a].split("/");
					for(int i=0; i<str1.length; i++) {
						if(i==0) {
							day1= Integer.parseInt(str1[i]);
						}else {
							day2= Integer.parseInt(str1[i]);
						}
					}
				}else {
					month= Integer.parseInt(str[a]);
				}
			}
		}
		 */		
		return date;
	}

	public String isContainsDot(String date, String[] mnth, String[] mon, String cur_mon, String cur_day, String cur_yr, Pattern p) {
		String str[] = date.split("\\.");
		System.out.println("in .");
		for(int a=0; a<str.length; a++) {
			System.out.println(str[a]);
			if(str[a].matches(".*\\d+.*") && !str[a].contains(":")) {
				Matcher m = p.matcher(str[a]);
				int count = 0;
				while(m.find()){
					count++;
					//out.println("count: "+count);
				}
				if(count==4) {
					year= Integer.parseInt(str[a]);
					//out.println("year: "+year);
				}else if(count==2){
					if(month!=0 && day!=0) {
						if((Integer.parseInt(str[a])==Integer.parseInt(cur_yr.substring(2, 3))-1 && month==12)  || (Integer.parseInt(str[a])==Integer.parseInt(cur_yr.substring(2, 3))+1 && month==1)) {
							year= Integer.parseInt(cur_yr.substring(0, 2))+Integer.parseInt(str[a]);
						}else {
							year= Integer.parseInt(cur_yr);
						}										
					}else if(day==0 && Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=31){
						day= Integer.parseInt(str[a]);
					}else if(month==0 && Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=12 || (Integer.parseInt(str[a])==Integer.parseInt(cur_mon)-1 || Integer.parseInt(str[a])==Integer.parseInt(cur_mon)+1) || Integer.parseInt(str[a])==Integer.parseInt(cur_mon)){
						if(day==0) {
							day= Integer.parseInt(str[a]);
						}else {
							month= Integer.parseInt(str[a]);
						}
					}
				}
				/*if(month==0 && (Integer.parseInt(str[a])==Integer.parseInt(cur_mon)-1 || Integer.parseInt(str[a])==Integer.parseInt(cur_mon)+1) || Integer.parseInt(str[a])==Integer.parseInt(cur_mon)){
					if(a==0) {
						day= Integer.parseInt(str[a]);
					}else {
						month= Integer.parseInt(str[a]);
					}						
					//out.println("month: "+month);
				}else if(day==0 && Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=31){
					day= Integer.parseInt(str[a]);
					//out.println("day: "+day);
				}else if (year==0 && (count==4 || Integer.parseInt(str[a])==Integer.parseInt(cur_yr)-1 || Integer.parseInt(str[a])==Integer.parseInt(cur_yr)+1)) {
					if(count==2 && a==2) {
						year= Integer.parseInt(str[a]);
					}
				}*/
			}else {
				for(int k=0; k<mnth.length; k++) {
					//out.println("mnth: "+mnth[k]);
					if(mnth[k].equalsIgnoreCase(str[0])) {
						month= k+1;
					}else if(mon[k].equalsIgnoreCase(str[0])){
						//out.println("00"+str[0]+" "+k);
						month= k+1;
					}
				}
			}
		}
		return day+"/"+month+"/"+year;
	}

	public String isContainsDot(String date, String[] mnth, String[] mon) {
		//PrintWriter out;		
		String str[] = date.split("\\.");
		String dd_str[] = for_dd.split("/");
		String yr_two= dd_str[2].substring(2, 3);

		for(int a=0; a<str.length; a++) {
			if(str[a].matches(".*\\d+.*") && !str[a].contains(":")) {
				Matcher m = p.matcher(str[a]);
				int count = 0;
				while(m.find()){
					count++;
					//out.println("count: "+count);
				}
				if(count==4) {
					year= Integer.parseInt(str[a]);
					//out.println("year: "+year);
				}else if(count==2){
					if(month!=0 && day!=0) {
						if(Integer.parseInt(str[a])!=Integer.parseInt(yr_two)){
							year= Integer.parseInt(dd_str[2].substring(0, 2)+Integer.parseInt(str[a]));
						}else {
							year= Integer.parseInt(dd_str[2]);
						}						
					}else if(day==0 && Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=31){
						day= Integer.parseInt(str[a]);
					}else if(month==0 && Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=12){
						if(day==0) {
							day= Integer.parseInt(str[a]);
						}else {
							month= Integer.parseInt(str[a]);
						}
					}
				}
			}else {
				for(int k=0; k<mnth.length; k++) {
					//out.println("mnth: "+mnth[k]);
					if(mnth[k].equalsIgnoreCase(str[a])) {
						month= k+1;
						System.out.println("month*: "+month);
					}else if(mon[k].equalsIgnoreCase(str[a])){
						//out.println("00"+str[0]+" "+k);
						month= k+1;
						System.out.println("month**: "+month);
					}
				}
			}
		}
		if(year==0) {
			//String dd_str[] = for_dd.split("/");
			year= Integer.parseInt(dd_str[2]);
		}
		return day+"/"+month+"/"+year;		
	}

	//public String isContainsSlash(String date, String cur_mon, String cur_day, String cur_yr, Pattern p, SlingHttpServletResponse rep) {
	public String isContainsSlash(String date, String cur_mon, String cur_day, String cur_yr, Pattern p) {
		//PrintWriter out;
		try {
			//out = rep.getWriter();

			String str[] = date.split("/");

			for(int a=0; a<str.length; a++) {
				/*if(Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=12 && (Integer.parseInt(str[a])== Integer.parseInt(cur_mon)+1 || Integer.parseInt(str[a])== Integer.parseInt(cur_mon)-1)) {
					month = Integer.parseInt(str[a]);
				}else {
					day = Integer.parseInt(str[a]);
				}
				Matcher m = p.matcher(str[a]);
				int count = 0;
				while(m.find()){
				   count++;
				   out.println("count: "+count);
				}
				if(count==4) {
					year= Integer.parseInt(str[a]);
				}
				if(str[2].contains(" ")) {
					year=Integer.parseInt(str[a].substring(0, str[a].indexOf(" ")));
				}else {
					year=Integer.parseInt(str[a]);
				}*/
				if(a==0) {
					if(Integer.parseInt(str[0])== Integer.parseInt(cur_mon)+1 || Integer.parseInt(str[0])== Integer.parseInt(cur_mon)-1) {
						if(Integer.parseInt(str[0])>=1 && Integer.parseInt(str[0])<=12) {
							month = Integer.parseInt(str[0]);
						}
						month = Integer.parseInt(str[0]);
					}else {
						day = Integer.parseInt(str[0]);
					}	

				}else if (a==1) {
					if(month==0) {
						if(Integer.parseInt(str[1])>=1 && Integer.parseInt(str[1])<=12) {
							month = Integer.parseInt(str[1]);
						}
					}else {
						day = Integer.parseInt(str[1]);
					}	
				}else if (a==2) {
					Matcher m = p.matcher(str[2]);
					int count = 0;
					while(m.find()){
						count++;
						//out.println("count: "+count);
					}
					if(count==4) {
						year= Integer.parseInt(str[2]);
					}
					if(str[2].contains(" ")) {
						year=Integer.parseInt(str[2].substring(0, str[2].indexOf(" ")));
					}else {
						year=Integer.parseInt(str[2]);
					}
				}
			}	
			if(year==0) {
				year= Integer.parseInt(cur_yr);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return day+"/"+month+"/"+year;		
	}

	public String isContainsSlash(String date, String[] mnth, String[] mon) {
		//System.out.println("date: "+date);
		//PrintWriter out;
		String str[] = date.split("/");
		for(int a=0; a<str.length; a++) {
			//System.out.println("str[a]: "+str[a]);
			if(str[a].matches(".*\\d+.*") && !str[a].contains(":")) {
				Matcher m = p.matcher(str[a]);
				int count = 0;
				while(m.find()){
					count++;
				}
				//System.out.println("count: "+count);
				if(count==4) {
					year= Integer.parseInt(str[a]);
					//out.println("year: "+year);
				}else if(count==2){
					if(month!=0 && day!=0) {
						year= Integer.parseInt(str[a]);
					}else if(day==0 && Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=31){
						day= Integer.parseInt(str[a]);
						//out.println("day: "+day);
					}else if(month==0 && Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=12){
						if(day==0) {
							day= Integer.parseInt(str[a]);
						}else {
							month= Integer.parseInt(str[a]);
						}
					}
				}else if(count==1) {
					if(day==0) {
						day= Integer.parseInt(str[a]);
					}else if (month==0) {
						if(day==0) {
							day= Integer.parseInt(str[a]);
						}else {
							month= Integer.parseInt(str[a]);
						}
					}
				}
			}else {
				for(int k=0; k<mnth.length; k++) {
					//out.println("mnth: "+mnth[k]);
					if(mnth[k].equalsIgnoreCase(str[a])) {
						month= k+1;
						System.out.println("month*: "+month);
					}else if(mon[k].equalsIgnoreCase(str[a])){
						//out.println("00"+str[0]+" "+k);
						month= k+1;
						System.out.println("month**: "+month);
					}
				}
			}
		}
		if(year==0) {
			String dd_str[] = for_dd.split("/");
			year= Integer.parseInt(dd_str[2]);
		}
		return day+"/"+month+"/"+year;		
	}

	//public String isContainsDash(String date, String mnth[], String mon[], String cur_mon, String cur_day, String cur_yr, Pattern p, SlingHttpServletResponse rep) {
	public String isContainsDash(String date, String mnth[], String mon[], String cur_mon, String cur_day, String cur_yr, Pattern p) {
		System.out.println("********day: "+day+" month: "+month+" year: "+year);
		//PrintWriter out;
		try {
			//out = rep.getWriter();

			System.out.println("day: "+day+" month: "+month+" year: "+year+" cur_mon: "+cur_mon);
			String str[] = date.split("-");
			for(int a=0; a<str.length; a++) {
				System.out.println("str[a]: "+str[a]);
				if(str[a].matches(".*\\d+.*") && !str[a].contains(":")) {
					System.out.println("is digit");
					Matcher m = p.matcher(str[a]);
					int count = 0;
					while(m.find()){
						count++;
						System.out.println("count: "+count);
					}
					if(count==4 || Integer.parseInt(str[a])==Integer.parseInt(cur_yr)-1 || Integer.parseInt(str[a])==Integer.parseInt(cur_yr)+1) {
						year= Integer.parseInt(str[a]);
						System.out.println("year: "+year);
					}else if(count==2){	
						if(Integer.parseInt(str[a])>12 && Integer.parseInt(str[a])<=31) {
							day= Integer.parseInt(str[a]);
							System.out.println("day*: "+day);
						}else if(month==0 && (Integer.parseInt(str[a])==Integer.parseInt(cur_mon)-1 || Integer.parseInt(str[a])==Integer.parseInt(cur_mon)+1) || Integer.parseInt(str[a])==Integer.parseInt(cur_mon)){
							System.out.println("in if");
							if(a==0) {
								day= Integer.parseInt(str[a]);
								System.out.println("day: "+day);
							}else {
								month= Integer.parseInt(str[a]);
								System.out.println("month: "+month);
							}						

						}else if(day==0 && Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=31){
							day= Integer.parseInt(str[a]);
							System.out.println("day: "+day);
						}
					}
					/**/
				}else {
					for(int k=0; k<mnth.length; k++) {
						//out.println("mnth: "+mnth[k]);
						if(mnth[k].equalsIgnoreCase(str[a])) {
							month= k+1;
							System.out.println("month*: "+month);
						}else if(mon[k].equalsIgnoreCase(str[a])){
							//out.println("00"+str[0]+" "+k);
							month= k+1;
							System.out.println("month**: "+month);
						}
					}
				}

				/*if(a==0) {
					if(str[0].matches(".*\\d+.*") && !str[0].contains(":")) {
						day = Integer.parseInt(str[0]);
					}else {
						for(int k=0; k<mnth.length; k++) {
							out.println("mnth: "+mnth[k]);
							if(mnth[k].equalsIgnoreCase(str[0])) {
								month= k+1;
							}else if(mon[k].equalsIgnoreCase(str[0])){
								out.println("00"+str[0]+" "+k);
								month= k+1;
							}
						}
					}
				}else if (a==1) {
					if(str[1].matches(".*\\d+.*") && !str[0].contains(":")) {
						if(month==0) {
							month = Integer.parseInt(str[1]);
						}					
					}else {
						for(int k=0; k<mnth.length; k++) {
							out.println("mnth: "+mnth[k]);
							if(mnth[k].equalsIgnoreCase(str[1])) {
								month= k+1;
							}else if(mon[k].equalsIgnoreCase(str[1])){
								month= k+1;
							}
						}
					}
				}else if (a==2) {
					if(str[2].contains(" ")) {
						String str1[] = str[2].split(" ");
						for(int i=0; i<str1.length; i++) {
							if(str1[i].matches(".*\\d+.*") && !str1[i].contains(":")) {
								year = Integer.parseInt(str1[i]);
							}
						}
					}				
				}*/
			}
			if(year==0) {
				year= Integer.parseInt(cur_yr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return day+"/"+month+"/"+year;
	}

	public String isContainsDash(String date, String mnth[], String mon[], Pattern p) {
		String str[] = date.split("-");
		for(int a=0; a<str.length; a++) {
			if(str[a].matches(".*\\d+.*") && !str[a].contains(":")) {
				Matcher m = p.matcher(str[a]);
				int count = 0;
				while(m.find()){
					count++;
					//out.println("count: "+count);
				}
				if(count==4) {
					year= Integer.parseInt(str[a]);
					//out.println("year: "+year);
				}else if(count==2){
					if(month!=0 && day!=0) {
						year= Integer.parseInt(str[a]);
					}else if(day==0 && Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=31){
						day= Integer.parseInt(str[a]);
						//out.println("day: "+day);
					}else if(month==0 && Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=12){
						if(day==0) {
							day= Integer.parseInt(str[a]);
						}else {
							month= Integer.parseInt(str[a]);
						}
					}
				}else {//changes done at 9/4/2019
					if(day==0 && Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=31){

						day= Integer.parseInt(str[a]);
						//out.println("day: "+day);
					}else if(month==0 && Integer.parseInt(str[a])>=1 && Integer.parseInt(str[a])<=12){
						if(day==0) {
							day= Integer.parseInt(str[a]);
						}else {
							month= Integer.parseInt(str[a]);
						}
					}
				}
			}else {
				for(int k=0; k<mnth.length; k++) {
					//out.println("mnth: "+mnth[k]);
					if(mnth[k].equalsIgnoreCase(str[a])) {
						month= k+1;
						System.out.println("month*: "+month);
					}else if(mon[k].equalsIgnoreCase(str[a])){
						//out.println("00"+str[0]+" "+k);
						month= k+1;
						System.out.println("month**: "+month);
					}
				}
			}
		}
		if(year==0) {
			String dd_str[] = for_dd.split("/");
			year= Integer.parseInt(dd_str[2]);
		}
		return day+"/"+month+"/"+year;		
	}
	//public String isContainsSpace(String date, String mnth[], String mon[], String cur_mon, String cur_day, String cur_yr, SlingHttpServletResponse rep) {
	public String isContainsSpace(String date, String mnth[], String mon[]) {	
		System.out.println("***day: "+day+" month: "+month+" year: "+year);
		//PrintWriter out;
		int retday=0, retmonth=0, retyear=0;
		try {
			//out = rep.getWriter();

			String str[] = date.split(" ");

			for (int i = 0; i < str.length; i++) {
				if(str[i].matches(".*\\d+.*") && !str[i].contains(":")) {
					if(str[i].contains(",")) {
						str[i].replace(",", "");
					}else if(str[i].matches(".*[a-zA-Z].*") && str[i].matches(".*[0-9].*") && !str[i].contains(" ") && !str[i].contains("/") && !str[i].contains("-") && !str[i].contains(".")) {
						str[i]= isAlphaNumeric(str[i]);
						System.out.println(str[i]);
					}//update

					if(Integer.parseInt(str[i])>=1 && Integer.parseInt(str[i])<=31) {
						retday= Integer.parseInt(str[i]);
					}
					Pattern p = Pattern.compile("\\d"); // "\d" is for digits in regex
					Matcher m = p.matcher(str[i]);
					int count = 0;
					while(m.find()){
						count++;
						//out.println("count: "+count);
					}
					if(count==4) {
						retyear= Integer.parseInt(str[i]);
					}
				}

				for(int j=0; j<mon.length; j++) {
					if(mon[j].equalsIgnoreCase(str[i])) {
						retmonth= j+1;
					}else if(mnth[j].equalsIgnoreCase(str[i])) {
						retmonth= j+1;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(retday!=0 && retmonth!=0 && retyear==0) {
			retyear=2019;
		}
		return retday+"/"+retmonth+"/"+retyear;		
	}

	//public String isContainsComma(String date, String mnth[], String mon[], String cur_yr, String cur_day, String cur_yr2, SlingHttpServletResponse rep) {
	public String isContainsComma(String date, String mnth[], String mon[], String cur_mon, String cur_yr, String cur_day) {	
		//PrintWriter out;
		try {
			//out = rep.getWriter();

			String str[] = date.split(",");
			for(int i=0; i<str.length; i++) {
				//out.println("**"+str[i]);
				if(str[i].contains(" ")) {
					String str1[] = str[i].split(" ");
					for (int j = 0; j < str1.length; j++) {
						//out.println(j+" "+str1[j]);
						if(str1[j].matches(".*\\d+.*")) {
							if(Integer.parseInt(str1[j])>=1 && Integer.parseInt(str1[j])<=31) {
								day= Integer.parseInt(str1[j]);
							}
							Pattern p = Pattern.compile("\\d"); // "\d" is for digits in regex
							Matcher m = p.matcher(str1[j]);
							int count = 0;
							while(m.find()){
								count++;
								//out.println("count: "+count);
							}
							if(count==4) {
								year= Integer.parseInt(str1[j]);
							}
						}
						for(int k=0; k<mnth.length; k++) {
							if(mnth[k].equalsIgnoreCase(str1[j])) {
								month= k+1;
							}
						}
					}

				}	    			
			}
			if(year==0) {
				year= Integer.parseInt(cur_yr);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return day+"/"+month+"/"+year;
	}

	public String isContainsSpace(String date, String mnth[], String mon[], String cur_mon, String cur_day, String cur_yr) {	
		//PrintWriter out;
		try {
			//out = rep.getWriter();

			String str[] = date.split(" ");
			for (int i = 0; i < str.length; i++) {
				if(str[i].matches(".*\\d+.*") && !str[i].contains(":")) {
					if(str[i].contains(",")) {
						str[i].replace(",", "");
					}
					if(Integer.parseInt(str[i])>=1 && Integer.parseInt(str[i])<=31) {
						day= Integer.parseInt(str[i]);
					}
					Pattern p = Pattern.compile("\\d"); // "\d" is for digits in regex
					Matcher m = p.matcher(str[i]);
					int count = 0;
					while(m.find()){
						count++;
						//out.println("count: "+count);
					}
					if(count==4) {
						year= Integer.parseInt(str[i]);
					}
				}

				for(int j=0; j<mon.length; j++) {
					if(mon[j].equalsIgnoreCase(str[i])) {
						month= j+1;
					}else if(mnth[j].equalsIgnoreCase(str[i])) {
						month= j+1;
					}
				}
			}
			if(year==0) {
				year= Integer.parseInt(cur_yr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return day+"/"+month+"/"+year;		
	}
	
	public  String spotFormat1(String date) {
		//	try {
			String str[] = date.split(" ");
			int day1=0;
			int day2=0;
			for(int a=0; a<str.length; a++) {
			if(str[a].matches(".*\\d+.*")) {
			System.out.println("..."+str[a]);
			if(a==0) {
			day1= Integer.parseInt(str[a]);
			System.out.println("day1: "+day1);
			}else if(a==1){
			day2= Integer.parseInt(str[a]);
			System.out.println("day2: "+day2);
			}else if(a==2) {
			month= Integer.parseInt(str[a]);
			}

			}else {
			for(int k=0; k<mnth.length; k++) {
			//out.println("mnth: "+mnth[k]);
			if(mnth[k].equalsIgnoreCase(str[a])) {
			month= k+1;
			System.out.println("month*: "+month);
			}else if(mon[k].equalsIgnoreCase(str[a])){
			//out.println("00"+str[0]+" "+k);
			month= k+1;
			System.out.println("month**: "+month);
			}	
			}
			}
			}
			String cur_date= Calendar.getInstance().getTime().toString();
			String cur_year= cur_date.substring(cur_date.lastIndexOf(" ")+1);
			System.out.println(cur_year);
			year= Integer.parseInt(cur_year);
			String date1= day1+"/"+month+"/"+year;
			String date2= day2+"/"+month+"/"+year;
			return date1+"&"+date2;
			
		//}catch(Exception e) {
			//e.printStackTrace();
			//return date;

		//}
	}



}

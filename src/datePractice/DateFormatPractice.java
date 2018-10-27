package datePractice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatPractice {
public static void main(String[] args) throws ParseException {
	//2017-05-05T09:58:35-0400
	String str="2017-05-05T09:58:35-0400";
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
	
	Date date =format.parse(str);
	System.out.println(date.toString());
}
}

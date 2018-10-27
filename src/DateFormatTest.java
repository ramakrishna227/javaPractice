import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;


public class DateFormatTest {

	public static void main(String[] args) throws ParseException {
		String format="yyyy-MM-dd'T'HH:mm:ssXXX";
	    
	    String time="2017-05-15T20:02:08-0400";
	    
	    
//	    SimpleDateFormat formatter=new SimpleDateFormat(format);
	    
//	    Date formattedTime =formatter.parse(time);
	    
	    
//	    System.out.println(formattedTime);
	    
	    
	   /* DateTimeFormatter parser2 = ISODateTimeFormat.dateTimeNoMillis();
	    String jtdate = "2010-01-01T12:00:00+01:00";
	    System.out.println(parser2.parseDateTime(jtdate));*/
	    
	    
	    DateTime dt = new DateTime(time) ;
	    
	    System.out.println(dt);
	    
	    
	    
	    String simpleFormat="yyyy-MM-dd'T'HH:mm:ssZ";
	    
	    
	    
	    SimpleDateFormat sdf=new SimpleDateFormat(simpleFormat);
	    
	    Date simpledate = sdf.parse(time);
	    
	    
	    System.out.println(simpledate);
	    
	    
	    SimpleDateFormat sdf1=new SimpleDateFormat(format);
	    
	    System.out.println(sdf.format(simpledate));
	    
	    
	    
	    SimpleDateFormat sdf2=new SimpleDateFormat(format);
	    
	    sdf2.setTimeZone(TimeZone.getTimeZone("PST"));
	    
	    
	    System.out.println(sdf2.format(simpledate));
	    
	}

}

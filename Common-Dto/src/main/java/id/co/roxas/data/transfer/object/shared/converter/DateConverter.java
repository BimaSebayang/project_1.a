package id.co.roxas.data.transfer.object.shared.converter;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
     public static Date parseStringToDate(String date, String pattern) throws ParseException {
    	    if(date!=null) {
    		Date conv = new SimpleDateFormat(pattern).parse(date);
    		return conv;
    	    }
    	    else {
    	    	return null;
    	    }
     }
     
     public static String parseDateToString(Date date, String pattern) throws ParseException {
    	 if(date!=null) {
    	 DateFormat dateFormat = new SimpleDateFormat(pattern);
    	 String conv = dateFormat.format(date);
    	 return conv;
    	 }
    	 else {
    		 return "";
    	 }
     }
     
     public static String parseStringFomatToOtherFormat(String oldDate, String currentPattern, String neededPattern) throws ParseException {
    		SimpleDateFormat sdf = new SimpleDateFormat(currentPattern);
    		Date date = sdf.parse(oldDate);
    		sdf.applyPattern(neededPattern);
    		String newDate = sdf.format(date);
    		return newDate;
     }
     
     public static Date getMaximumDateValue() throws ParseException {
    	 return parseStringToDate(getMaxDateValueInString(), "ddMMyyyy");
     }
     
     public static String getMaxDateValueInString() {
    	 return "12123000";
     }
     
     public static Date getMinimumDateValue() throws ParseException {
    	 return parseStringToDate(getMinDateValueString(), "ddMMyyyy");	 
     }
     
     public static String getMinDateValueString() {
    	return "01011970";
     }
     
}

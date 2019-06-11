package id.co.roxas.lang.identifier.core.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import id.co.roxas.data.transfer.object.shared.converter.DateConverter;
import id.co.roxas.lang.identifier.core.UltimateBase;

@Component
public class BaseService extends UltimateBase{
    protected Date startDat;
	protected Date endDat;
	public String getSorter(Pageable pageable) {
		return new Gson().toJson(pageable.getSort());
	}
	
	public void getStartDateEndDate(String startDate, String endDate, String pattern) {
		System.err.println("start date and end date " + startDate + " " + endDate + " with pattern " + pattern);
		if(Strings.isBlank(startDate) || startDate.trim().contains(",")) {
			try {
				this.startDat = DateConverter.getMinimumDateValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				this.startDat = DateConverter.parseStringToDate(startDate, pattern);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(Strings.isBlank(endDate) || endDate.trim().contains(",")) {
			try {
				this.endDat = DateConverter.getMaximumDateValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				Calendar c = Calendar.getInstance();
				c.setTime(DateConverter.parseStringToDate(endDate, pattern));
				c.add(Calendar.DATE, 1);
				this.endDat = c.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		System.err.println("final start date and end date " + this.startDat + " " + this.endDat);
		
	}
	
}

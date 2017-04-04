package com.samarthsoft.prabandhak.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.DayAndDate;
import com.samarthsoft.prabandhak.entities.Event;
import com.samarthsoft.prabandhak.entities.Filter;
import com.samarthsoft.prabandhak.entities.SelectValues;
import com.samarthsoft.prabandhak.enums.RestrictionType;
import com.samarthsoft.prabandhak.form.entities.EventFormObject;

@Component
public class CalendarControllerUtility {

	public List<DayAndDate> getDayAndDateFromCalendar(Calendar calendar) {
		List<DayAndDate> result = new ArrayList<DayAndDate>();
		int minDate = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		int maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		
		Map<Integer,List<String>> events = getEvents(month, year);
		
		Calendar calendarStart = getCalendar(1, month, year);
		if(!getDayName(calendarStart.get(Calendar.DAY_OF_WEEK)).equals("sun")){
			addBlankObjects(calendarStart.get(Calendar.DAY_OF_WEEK),result);
		}
		
		for (int count = minDate;count<=maxDate;count++) {
			DayAndDate dayAndDate = new DayAndDate();
			dayAndDate.setDate(count + "/" + month + "/" + year);
			Calendar cal = getCalendar(count, month, year);
			dayAndDate.setDay(getDayName(cal.get(Calendar.DAY_OF_WEEK)));
			dayAndDate.setWeek(cal.get(Calendar.WEEK_OF_MONTH));
			dayAndDate.setDayNumber(count+"");
			dayAndDate.setEvents(events.get(cal.get(Calendar.DATE)));
			result.add(dayAndDate);
		}
		return result;
	}
	
	private Map<Integer,List<String>> getEvents(Integer month,Integer year){
		Map<Integer,List<String>> result = new HashMap<Integer, List<String>>();
		Calendar calendarStart = getCalendar(1, month, year);
		Calendar calendarEnd = getCalendar(calendarStart.getActualMaximum(Calendar.DAY_OF_MONTH), month, year);
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("date", calendarStart.getTimeInMillis(), RestrictionType.GE));
		filters.add(new Filter("date", calendarEnd.getTimeInMillis(), RestrictionType.LE));
		List<SelectValues> selectValues = new ArrayList<SelectValues>();
		selectValues.add(new SelectValues("name"));
		selectValues.add(new SelectValues("date"));
		List<Object> resultEvents = DBCommunicator.getApiServices().getGenericApi().getFilteredList(Event.class, filters, selectValues);
		for (Object object : resultEvents) {
			Event event = (Event) object;
			Calendar calendarToCompare = Calendar.getInstance();
			calendarToCompare.setTimeInMillis(event.getDate());
			if(result.get(calendarToCompare.get(Calendar.DATE))==null){
				List<String> obj = new ArrayList<String>();
				obj.add(event.getName());
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(event.getDate());
				result.put(calendar.get(Calendar.DATE), obj);
			}else {
				List<String> obj = result.get(calendarToCompare.get(Calendar.DATE));
				obj.add(event.getName());
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(event.getDate());
				result.put(calendar.get(Calendar.DATE), obj);
			}
		}
		return result;
	}
	private void addBlankObjects(Integer tillDay,List<DayAndDate> dayAndDates) {
		for(int i=0;i<tillDay-1; i++){
			DayAndDate dayAndDate = new DayAndDate();
			dayAndDate.setDate("");
			dayAndDate.setDay(getDayName(i));
			dayAndDate.setDayNumber("");
			dayAndDate.setWeek(1);
			dayAndDates.add(dayAndDate);
		}
	}

	private String getDayName(Integer day){
		String result = "";
		switch(day){
		  case 1: result = "sun";
		  break;
		  case 2: result = "mon";
		  break;
		  case 3: result = "tue";
		  break;
		  case 4: result = "wed";
		  break;
		  case 5: result = "thu";
		  break;
		  case 6: result = "fri";
		  break;
		  case 7: result = "sat";
		  break;
		  }
		return result;
	}
	
	private Calendar getCalendar(int day,int month,int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH,day);
		calendar.set(Calendar.YEAR,year);
		return calendar; 
	}
	
	public EventFormObject getEventFormObject(Event event){
		EventFormObject eventFormObject = new EventFormObject();
		eventFormObject.setEvent(event);
		eventFormObject.setDate(DateUtility.convertTimeToString(event.getDate()));
		return eventFormObject;
	}
	
	public Event getEvent(EventFormObject eventFormObject){
		Event event = eventFormObject.getEvent();
		event.setDate(DateUtility.convertStringToTime(eventFormObject.getDate()));
		return event;
	}
}
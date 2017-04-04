package com.samarthsoft.prabandhak.webapp.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.samarthsoft.prabandhak.common.DateUtility;
import com.samarthsoft.prabandhak.common.RequestCommon;
import com.samarthsoft.prabandhak.connector.DBCommunicator;
import com.samarthsoft.prabandhak.entities.Event;
import com.samarthsoft.prabandhak.entities.PaginationObject;
import com.samarthsoft.prabandhak.form.entities.EventFormObject;
import com.samarthsoft.prabandhak.utilities.CalendarControllerUtility;
import com.samarthsoft.prabandhak.utilities.CommonControllerUtility;

@Controller
public class CalendarController {
	@Autowired
	private CommonControllerUtility commonControllerUtility;
	@Autowired
	private CalendarControllerUtility calendarControllerUtility;
	
	@RequestMapping(value="/calendar" , method=RequestMethod.GET)
	public ModelAndView showCalendar(ModelMap model,HttpServletRequest request){
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		Calendar calendar = Calendar.getInstance();
		if(month!=null && Integer.parseInt(month) < 13 && Integer.parseInt(month) > 0){
			calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
			calendar.set(Calendar.YEAR,Integer.parseInt(year));
		}
		if(month!=null && Integer.parseInt(month) == 0){
			calendar.set(Calendar.MONTH, 11);
			calendar.set(Calendar.YEAR,Integer.parseInt(year) - 1);
		}
		if(month!=null && Integer.parseInt(month) > 12){
			calendar.set(Calendar.MONTH, 1);
			calendar.set(Calendar.YEAR,Integer.parseInt(year) + 1);
		}
		model.put("dayAndDateValues", calendarControllerUtility.getDayAndDateFromCalendar(calendar));
		model.put("totalWeeks",calendar.getActualMaximum(Calendar.WEEK_OF_MONTH));
		model.put("month", calendar.getDisplayName(Calendar.MONTH,1,Locale.getDefault()) + "-" + calendar.get(Calendar.YEAR));
		model.put("currentMonth",calendar.get(Calendar.MONTH)+1);
		model.put("currentYear",calendar.get(Calendar.YEAR));
		PaginationObject paginationObject = commonControllerUtility.getObjectsByClass(Event.class,commonControllerUtility.getPageNumber(request));
		if(paginationObject.getRecords()!=null && paginationObject.getRecords().size() > 0){
			for (Object object : paginationObject.getRecords()) {
				Event event = (Event) object;
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(event.getDate());
				System.out.println(cal.get(Calendar.DATE));
				model.put("number_of_events_" + cal.get(Calendar.DATE),paginationObject.getRecords().size());
			}
		}
		return new ModelAndView("calendar",model);
	}
	
	@RequestMapping(value="/events" , method=RequestMethod.GET)
	public ModelAndView showEvents(ModelMap model,HttpServletRequest request){
		String date = RequestCommon.getAttributeValueFromRequest("date", request);
		if(date!=null && !date.isEmpty())
			model.put("date",date);
		else model.put("date",DateUtility.convertTimeToString(Calendar.getInstance().getTimeInMillis()));
		PaginationObject paginationObject = commonControllerUtility.getObjectsByClass(Event.class,commonControllerUtility.getPageNumber(request));
		List<EventFormObject> events = new ArrayList<EventFormObject>();
		if(paginationObject.getRecords()!=null && paginationObject.getRecords().size() > 0){
			for (Object object : paginationObject.getRecords()) {
				events.add(calendarControllerUtility.getEventFormObject((Event)object));
			}
		}
		model.put("events",events);
		if(events!=null && events.size()>0)
			commonControllerUtility.setCommonAttributesOfPagination(paginationObject, model, commonControllerUtility.getPageNumber(request));
		return new ModelAndView("events_list",model);
	}

	@RequestMapping(value="/addevent" , method=RequestMethod.GET)
	public ModelAndView showEventForm(ModelMap model,HttpServletRequest request){
		String date = RequestCommon.getAttributeValueFromRequest("date", request);
		EventFormObject eventFormObject = new EventFormObject();
		eventFormObject.setEvent(new Event());
		if(date!=null && !date.isEmpty()){
			eventFormObject.setDate(date);
			model.put("date", date);
		}
		else eventFormObject.setDate(DateUtility.convertTimeToString(Calendar.getInstance().getTimeInMillis()));
		model.put("event", eventFormObject);
		return new ModelAndView("event_form",model);
	}

	@RequestMapping(value="/addevent" , method=RequestMethod.POST)
	public ModelAndView submitEventForm(@Valid EventFormObject event , BindingResult bindingResult , ModelMap model,HttpServletRequest request){
		String date = RequestCommon.getAttributeValueFromRequest("date", request);
		if(date!=null && !date.isEmpty())
			model.put("date", date);
		if(!bindingResult.hasErrors()){
			Boolean result = false;
			result = DBCommunicator.getApiServices().getGenericApi().insert(calendarControllerUtility.getEvent(event));
			if(result){
				return new ModelAndView("redirect:/calendar.htm", model);
			}
		}
		model.put("event", event);
		return new ModelAndView("event_form",model);
	}

	@RequestMapping(value="/editevent" , method=RequestMethod.GET)
	public ModelAndView showEditEventForm(ModelMap model,HttpServletRequest request){
		String date = RequestCommon.getAttributeValueFromRequest("date", request);
		if(date!=null && !date.isEmpty())
			model.put("date", date);
		String id = RequestCommon.getAttributeValueFromRequest("id", request);
		if(id!=null && !id.isEmpty()){
			model.put("event", calendarControllerUtility.getEventFormObject((Event)DBCommunicator.getApiServices().getGenericApi().fetchObjectbyID(Event.class, id)));
		}else model.put("event", calendarControllerUtility.getEventFormObject(new Event()));
		return new ModelAndView("event_form",model);
	}

	@RequestMapping(value="/editevent" , method=RequestMethod.POST)
	public ModelAndView submitEditEventForm(@Valid EventFormObject eventFormObject , BindingResult bindingResult , ModelMap model,HttpServletRequest request){
		if(!bindingResult.hasErrors()){
			Boolean result = false;
			result = DBCommunicator.getApiServices().getGenericApi().update(calendarControllerUtility.getEvent(eventFormObject));
			if(result){
				return new ModelAndView("redirect:/events.htm?date="+eventFormObject.getDate(), model);
			}
		}
		model.put("event", eventFormObject);
		return new ModelAndView("event_form",model);
	}
}
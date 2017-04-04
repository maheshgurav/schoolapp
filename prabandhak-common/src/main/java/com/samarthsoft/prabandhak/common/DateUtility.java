package com.samarthsoft.prabandhak.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

public class DateUtility {
	private static final Logger LOG = LoggerFactory.getLogger(DateUtility.class);

	private DateUtility() {
		// utility class
	}

	public static String convertTimeToString(Long timeInMillis) {
		String result = "";
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(timeInMillis);
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			result = format.format(calendar.getTime());
		} catch (Exception ex) {
			LOG.error("", ex);
		}
		return result;
	}

	public static Long convertStringToTime(String dateInStringFormat) {
		Long result = 0l;
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", LocaleContextHolder.getLocale());
			calendar.setTime(format.parse(dateInStringFormat));
			result = calendar.getTimeInMillis();
		} catch (ParseException parseException) {
			LOG.error("", parseException);
		}
		return result;
	}

	public static Calendar getCalendarWithoutTime() {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
		} catch (Exception ex) {
			LOG.error("", ex);
		}
		return calendar;
	}
}
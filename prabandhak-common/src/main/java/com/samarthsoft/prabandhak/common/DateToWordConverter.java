package com.samarthsoft.prabandhak.common;

import java.text.DateFormatSymbols;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class DateToWordConverter {
	private String string;
	private String st1[] = { "", "One", "Two", "Three", "Four", "Five", "Six",
			"Seven ", "Eight", "Nine", };
	private String st2[] = { "Hundred", "Thousand" };
	private String st3[] = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
			"Fifteen", "Sixteen", "Seventeen", "Eighteen", "Ninteen", };
	private String st4[] = { "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety", };

	public String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month - 1];
	}

	public String convert(int number) {
		int n = 1;
		int word;
		string = "";
		while (number != 0) {
			switch (n) {
			case 1:
				word = number % 100;
				pass(word);
				if (number > 100 && number % 100 != 0) {
					show("and ");
				}
				number /= 100;
				break;

			case 2:
				word = number % 10;
				if (word != 0) {
					show(" ");
					show(st2[0]);
					show(" ");
					pass(word);
				}
				number /= 10;
				break;

			case 3:
				word = number % 100;
				if (word != 0) {
					show(" ");
					show(st2[1]);
					show(" ");
					pass(word);
				}
				number /= 100;
				break;

			case 4:
				word = number % 100;
				if (word != 0) {
					show(" ");
					show(st2[2]);
					show(" ");
					pass(word);
				}
				number /= 100;
				break;

			case 5:
				word = number % 100;
				if (word != 0) {
					show(" ");
					show(st2[3]);
					show(" ");
					pass(word);
				}
				number /= 100;
				break;

			}
			n++;
		}
		return string;
	}

	public String getMonthName(int month) {
		switch (month) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		}
		return "";
	}

	public void pass(int number) {
		int word, q;
		if (number < 10) {
			show(st1[number]);
		}
		if (number > 9 && number < 20) {
			show(st3[number - 10]);
		}
		if (number > 19) {
			word = number % 10;
			if (word == 0) {
				q = number / 10;
				show(st4[q - 2]);
			} else {
				q = number / 10;
				show(st1[word]);
				show(" ");
				show(st4[q - 2]);
			}
		}
	}

	public void show(String s) {
		String st;
		st = string;
		string = s;
		string += st;
	}
	
	public String getDateInWords(Calendar calendar){
		String result = "";
		result = convert(calendar.get(Calendar.DATE)) + " " + getMonthName(calendar.get(Calendar.MONTH) + 1) + " "
				+ convert(calendar.get(Calendar.YEAR));
		return result;
	}
}
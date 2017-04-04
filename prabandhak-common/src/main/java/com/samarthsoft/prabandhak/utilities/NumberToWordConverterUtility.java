package com.samarthsoft.prabandhak.utilities;

import org.springframework.stereotype.Component;

@Component
class NumberToWordConverterUtility {
	private boolean HelperConvertNumberToText(int num, String[] result) {
		String[] strones = {
		"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh",
				"Eighth", "Ninth", "Tenth", "Eleventh", "Twelfth",
				"Thirteenth", "Fourteenth", "Fifteenth", "Sixteenth",
				"Seventeenth", "Eighteenth", "Nineteenth", };
		String[] strtens = { "Ten", "Twenty", "Thirty", "Fourty", "Fifty",
				"Sixty", "Seventy", "Eighty", "Ninety", "Hundred" };
		result[0] = "";
		int single, tens, hundreds;
		if (num > 1000)
			return false;
		hundreds = num / 100;
		num = num - hundreds * 100;

		if (num < 20) {
			tens = 0; // special case
			single = num;
		} else {
			tens = num / 10;
			num = num - tens * 10;
			single = num;
		}
		if (hundreds > 0) {
			result[0] += strones[hundreds - 1];
			result[0] += " Hundred ";
		}
		if (tens > 0) {
			result[0] += strtens[tens - 1];
			result[0] += " ";
		}
		if (single > 0) {
			result[0] += strones[single - 1];
			result[0] += " ";
		}
		return true;
	}
	
	private boolean HelperConvertYearToText(int num, String[] result) {
        String [] strones = {
                "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
                "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
                "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen",
              };

		String[] strtens = { "Ten", "Twenty", "Thirty", "Fourty", "Fifty",
				"Sixty", "Seventy", "Eighty", "Ninety", "Hundred" };

		result[0] = "";
		int single, tens, hundreds;
		if (num > 1000)
			return false;
		hundreds = num / 100;
		num = num - hundreds * 100;

		if (num < 20) {
			tens = 0; // special case
			single = num;
		} else {
			tens = num / 10;
			num = num - tens * 10;
			single = num;
		}
		if (hundreds > 0) {
			result[0] += strones[hundreds - 1];
			result[0] += " Hundred ";
		}
		if (tens > 0) {
			result[0] += strtens[tens - 1];
			result[0] += " ";
		}
		if (single > 0) {
			result[0] += strones[single - 1];
			result[0] += " ";
		}
		return true;
	}
	
	private String HelperConvertNumberToMonthName(int num) {
		String[] monthNames = { "January", "Februrary", "March", "April",
				"May", "June", "July", "August", "September", "October",
				"November", "December", };
		return monthNames[num - 1];
	}

	private boolean ConvertNumberToText(int num, String[] result) {
		String tempString[] = new String[1];
		tempString[0] = "";
		int thousands;
		int temp;
		result[0] = "";
		if (num < 0 || num > 100000) {
			System.out.println(num + " \tNot Supported");
			return false;
		}
		if (num == 0) {
			System.out.println(num + " \tZero");
			return false;
		}
		if (num < 1000) {
			HelperConvertYearToText(num, tempString);
			result[0] += tempString[0];
		} else {
			thousands = num / 1000;
			temp = num - thousands * 1000;
			HelperConvertYearToText(thousands, tempString);
			result[0] += tempString[0];
			result[0] += "Thousand ";
			HelperConvertYearToText(temp, tempString);
			result[0] += tempString[0];
		}
		return true;
	}
	
	
	private boolean ConvertDateToText(int num, String[] result) {
		String tempString[] = new String[1];
		tempString[0] = "";
		int thousands;
		int temp;
		result[0] = "";
		if (num < 0 || num > 100000) {
			System.out.println(num + " \tNot Supported");
			return false;
		}
		if (num == 0) {
			System.out.println(num + " \tZero");
			return false;
		}
		if (num < 1000) {
			HelperConvertNumberToText(num, tempString);
			result[0] += tempString[0];
		} else {
			thousands = num / 1000;
			temp = num - thousands * 1000;
			HelperConvertNumberToText(thousands, tempString);
			result[0] += tempString[0];
			result[0] += "Thousand ";
			HelperConvertNumberToText(temp, tempString);
			result[0] += tempString[0];
		}
		return true;
	}
	
	public String convertDateToWord(int date, int month, int year){
		String[] result = new String[1];
		result[0] = "";
		String dateInWords = "";
		ConvertDateToText(date, result);
		dateInWords = dateInWords + result[0];
		dateInWords = dateInWords + HelperConvertNumberToMonthName(month) + " ";
		
		if(year>1800 && year<2000){
			String yearInString = year + "";
			ConvertNumberToText(Integer.parseInt(yearInString.substring(0,2)),result);
			dateInWords =  dateInWords + result[0];

			ConvertNumberToText(Integer.parseInt(yearInString.substring(2,4)),result);
			dateInWords =  dateInWords + result[0];
		}
		if(year>=2000){
			String yearInString = year + "";
			ConvertNumberToText(Integer.parseInt(yearInString),result);
			dateInWords =  dateInWords + result[0];
		}
		return dateInWords;
	}
	
	public static void main(String[] args) {
		NumberToWordConverterUtility b = new NumberToWordConverterUtility();
		System.out.println(b.convertDateToWord(24, 6, 2024));
	}
}
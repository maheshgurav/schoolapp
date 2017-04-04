package com.samarthsoft.prabandhak.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordEncoder {
	/**
	 * Array to store number characters.
	 */
	private char numberChars[] = "0123456789".toCharArray();

	/**
	 * Array to store lower case characters.
	 */
	private char lowerChars[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * Array to store upper case characters.
	 */
	private char upperChars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	/**
	 * List contains set of number to get from each array, total of all is the
	 * length of password i.e. 8
	 */
	private List<Integer> listForLengthOfPassword = new ArrayList<Integer>();

	/**
	 * Random object to generate the random number to pick up random character
	 * from array.
	 */
	private Random random = new Random();

	/**
	 * Final password string object holder list.
	 */
	private List<Character> finalpasswordList;

	private static final Logger logger = LoggerFactory
			.getLogger(PasswordEncoder.class);

	/**
	 * Constructor.
	 */
	public PasswordEncoder() {
		listForLengthOfPassword.add(1);
		listForLengthOfPassword.add(2);
		listForLengthOfPassword.add(2);
	}

	/**
	 * getPassword() creates password by picking random character from each
	 * array. Every time it is invoked, shuffle the number of character to take
	 * from each array dynamically.
	 * 
	 * @return string as generated password.
	 * @throws NoSuchAlgorithmException
	 */
	public String getPassword() {

		Collections.shuffle(listForLengthOfPassword);
		finalpasswordList = new ArrayList<Character>();

		for (int t = 0; t < listForLengthOfPassword.size(); t++) {
			int numberOfCharPerArray = listForLengthOfPassword.get(t);

			for (int z = 0; z < numberOfCharPerArray; z++)
				if (t == 0)
					finalpasswordList.add(numberChars[random.nextInt(10)]);
				else if (t == 1)
					finalpasswordList.add(lowerChars[random.nextInt(26)]);
				else if (t == 2)
					finalpasswordList.add(upperChars[random.nextInt(26)]);
		}// end for

		String password = new String();
		Collections.shuffle(finalpasswordList);

		for (int s = 0; s < finalpasswordList.size(); s++)
			password += finalpasswordList.get(s);

		return password;
	}

	public static String hashPassword(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte messageDigest[] = md.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++)
				hexString
						.append(Integer.toHexString(0x00FF & messageDigest[i]));
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error("Error in hashing password : ", e);
			return null;
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(PasswordEncoder.hashPassword("admin"));	
	}
}
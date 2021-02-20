package com.utility;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;

public class Utils {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static String getAlphaNumString(int lenalfa, int numlen) {
		String randomString = "";
		randomString = genarateRandom(lenalfa, "abcdefghijklmnpqrstvwxyz");
		randomString = randomString + genarateRandom(numlen, "123456789");
		return randomString;
	}

	public static String genarateRandom(int len, String chars) {
		final int PW_LENGTH = len;
		Random rnd = new SecureRandom();
		StringBuilder randomString = new StringBuilder();
		for (int i = 0; i < PW_LENGTH; i++)
			randomString.append(chars.charAt(rnd.nextInt(chars.length())));
		return randomString.toString();
	}

	/**
	 * Generate random number.
	 */
	public static String generateRandomNumber(int len) {
		String chars = "1234567890";
		final int PW_LENGTH = len;
		Random rnd = new SecureRandom();
		StringBuilder pass = new StringBuilder();
		for (int i = 0; i < PW_LENGTH; i++)
			pass.append(chars.charAt(rnd.nextInt(chars.length())));
		return pass.toString();
	}

	/**
	 * Generate random string of given length
	 * 
	 * @param length
	 * @return
	 */
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static Date addDays(Date date, Long days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days.intValue());
		return c.getTime();
	}

	public static String getUserNo(String header) throws UnsupportedEncodingException {
		if (null != header) {
			final String encodedUserPassword = header.replaceFirst("Bearer" + " ", "");
			byte[] decodedBytes = Base64.decodeBase64(encodedUserPassword);
			String usernameAndPassword = new String(decodedBytes, "UTF-8");
			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			return tokenizer.nextToken();
		} else {
			return "";
		}
	}

	public static String randomStr(int length) {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(length);
	}
}

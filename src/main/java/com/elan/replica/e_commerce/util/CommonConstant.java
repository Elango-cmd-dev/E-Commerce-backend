package com.elan.replica.e_commerce.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonConstant {
	
	private static String whenCreatedIST;
	private static String whenUpdatedIST;

	private CommonConstant() {
	}

	/**
	 * @param whenCreatedIST yyyy-MM-dd hh:mm:ss a zzz
	 */
	public static String getWhenCreatedIST() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static void setWhenCreatedIST(String whenCreatedIST) {
		CommonConstant.whenCreatedIST = whenCreatedIST;
	}

	/**
	 * 
	 * @return whenUpdatedIST
	 */
	public static String getWhenUpdatedIST() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static void setWhenUpdatedIST(String whenUpdatedIST) {
		CommonConstant.whenUpdatedIST = whenUpdatedIST;
	}

	public static final String SENDER_MAIL ="elangogaming994@gmail.com";
	public static final String EVILAN_TEAM ="EVILAN E-COM TEAM ";
	public static final String NOT_VERIFIED ="Not-verified!";
	public static final String VERIFIED ="Verified!";
	public static final String PAYMENT_SUCCESS = "Payment Success";
	public static final String PAYMENT_WITHDRAW = "Payment Withdraw";
	public static final String STATUS = "status";
	public static final String FLAG = "flag";
	public static final String ERROR = "Error";
	public static final String WEBSITE_NAME = "";
	public static final String ADMIN = "ADMIN";

}

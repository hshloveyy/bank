package com.util;

import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * ����һ�������࣬������һЩ��̬�ķ���
 */
public class Util {
	
	/**
	 * �����ַ���ת�������ͣ���ʽΪyyyy-MM-dd,��2016-03-30������һ����������
	 */
	public static Date newDate(String s) throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		Date date = new Date();
		date = sdf.parse(s);
		return date;
	}

	/**
	 * �����ַ���ת��������,��ʱ�֣���ʽΪyyyy-MM-dd HH:mm,��2016-03-30 20:15������һ����������
	 */
	public static Date newDate1(String s) throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		Date date = new Date();
		date = sdf.parse(s);
		return date;
	}

	/**
	 * �����ַ���ת�������ͣ���ʱ���룬��ʽΪyyyy-MM-dd HH:mm:ss,��2016-03-30 20:15:30������һ����������
	 */
	public static Date FormatFullDate(String s) throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		date = sdf.parse(s);
		return date;

	}

	/**
	 * ��������ת�����ַ�������ʱ���룬��ʽΪyyyy-MM-dd,��2016-03-30������һ���ַ���
	 */
	public static String splitDate(Date d) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		return sdf.format(d);
	}

	/**
	 * ��ʽ�����ڣ�����yyyy��MM��dd�ո�ʽ������
	 */
	public static String splitDate1(Date d) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy��MM��dd��");
		return sdf.format(d);
	}

	/**
	 * ���ַ����ض̣�ȡǰn���ַ���Ӣ�������ַ���
	 * 
	 * @param orignalString
	 *            ԭ�ַ���
	 * @param length
	 *            ����
	 * @param chopedString
	 *            �������ֵı�ʾ�ַ���
	 * @return ��ȡ���ַ���
	 */
	public static String chop(String orignalString, double length,
			String chopedString) {
		if (orignalString == null || orignalString.length() == 0) {
			return orignalString;
		}
		orignalString = orignalString.replaceAll(" ", " ");
		if (orignalString.length() < length) {
			return orignalString;
		}
		StringBuffer buffer = new StringBuffer((int) length);
		length = length * 2;
		int count = 0;
		int stringLength = orignalString.length();
		int i = 0;
		for (; count < length && i < stringLength; i++) {
			char c = orignalString.charAt(i);
			if (c < '\u00ff') {
				count++;
			} else {
				count += 2;
			}
			buffer.append(c);
		}
		if (i < stringLength) {
			buffer.append(chopedString);
		}
		return buffer.toString();
	}

	/**
	 * ��ȡһ��ʱ���
	 */
	public static long getPrimeKey() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.getTimeInMillis();
	}

	/**
	 * �ַ���תlong��
	 */
	public static long stringToLong(String source) {
		return Long.parseLong(source);
	}
	/*
	 * public static void main(String[] args){ for(int i=0;i<10;i++){
	 * System.out.println(Util.getPrimeKey()); } }
	 */
}

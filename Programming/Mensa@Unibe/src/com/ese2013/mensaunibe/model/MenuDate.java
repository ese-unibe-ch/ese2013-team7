package com.ese2013.mensaunibe.model;

import java.util.Calendar;
import java.util.Date;

public class MenuDate implements Comparable<MenuDate> {
	private int day;
	private int month;
	private int year;
	public MenuDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
	}
	public MenuDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	@Override
	public int compareTo(MenuDate md) {
		if(day == md.getDay() && month == md.getMonth() && year == md.getYear()) return 0;
		return 1;
	}
	
	public boolean equals(Object obj) {
		if(obj != null && this.getClass() != obj.getClass()) return false;
		MenuDate md = (MenuDate) obj;
		if(day == md.getDay() && month == md.getMonth() && year == md.getYear()) return true;
		return false;
	}
	
	public String toString() {
		return day+"."+month+"."+year;
	}
	
	public int hashCode() {
		return day+month+year;
	}
	
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	public int getDay() {
		return day;
	}
}

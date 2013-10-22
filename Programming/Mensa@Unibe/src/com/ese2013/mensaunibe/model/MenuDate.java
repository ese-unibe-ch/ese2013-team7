package com.ese2013.mensaunibe.model;

import java.util.Calendar;
import java.util.Date;

import com.ese2013.mensaunibe.App;
import com.ese2013.mensaunibe.R;

import android.util.Log;

public class MenuDate implements Comparable<MenuDate> {
	
	private static final String TAG = "MenuDate";
	private int day;
	private int month;
	private int year;
	public MenuDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH) + 1; //January = 0
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
	
	public String toText() {
		String dayName = "";
		Calendar cal = Calendar.getInstance();
		if(compareDay(cal))//today
			return App.getAppContext().getString(R.string.today);
		
		cal.add(Calendar.DATE, 1);
		
		if(compareDay(cal))//tomorrow
			return App.getAppContext().getString(R.string.tomorrow);
		
		cal.set(year, month-1, day);
		int dayNr = cal.get(Calendar.DAY_OF_WEEK);
		dayName = dayOfWeek(dayNr);
		
		return dayName +", " + day+"."+month+"."+year;
	}
	
	private String dayOfWeek(int day){
		String dayOfWeek = "";
		if(day==Calendar.MONDAY)
			dayOfWeek = App.getAppContext().getString(R.string.Monday);
		if(day==Calendar.TUESDAY)
			dayOfWeek = App.getAppContext().getString(R.string.Tuesday);
		if(day==Calendar.WEDNESDAY)
			dayOfWeek = App.getAppContext().getString(R.string.Wednesday);
		if(day==Calendar.THURSDAY)
			dayOfWeek = App.getAppContext().getString(R.string.Thursday);
		if(day==Calendar.FRIDAY)
			dayOfWeek = App.getAppContext().getString(R.string.Friday);
		if(day==Calendar.SATURDAY)
			dayOfWeek = App.getAppContext().getString(R.string.Saturday);
		if(day==Calendar.SUNDAY)
			dayOfWeek = App.getAppContext().getString(R.string.Sunday);
		return dayOfWeek;
	}
	
	private boolean compareDay(Calendar c){
		return (day == c.get(Calendar.DAY_OF_MONTH)
				&& month == (c.get(Calendar.MONTH) + 1)
				&& year == c.get(Calendar.YEAR));
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

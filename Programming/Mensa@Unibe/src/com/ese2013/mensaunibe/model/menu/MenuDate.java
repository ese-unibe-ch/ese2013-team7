package com.ese2013.mensaunibe.model.menu;

import java.util.Calendar;
import java.util.Date;
import com.ese2013.mensaunibe.App;
import com.ese2013.mensaunibe.R;

/**
 * @author group7
 * @author Andreas Hohler
 * @author Sandor Torok
 */

public class MenuDate implements Comparable<MenuDate> {

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
		assert md != null;
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

	/**
	 * Returns a nice formatted string of the object's date.
	 * @param simple: the view is simple - only the day is displayed. other else, the whole information.
	 * @return string with the day name or the whole date.
	 */
	public String toText(boolean simple) {
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
		if(simple) return dayName;
		return dayName +", " + day+"."+month+"."+year;
	}

	/**
	 * returns the name of a specific day (1-7) (not real day number)
	 * @param day as an integer
	 * @return name of day
	 */
	private String dayOfWeek(int day){
		String dayOfWeek = "";
		switch(day){
			case Calendar.MONDAY:
				dayOfWeek = App.getAppContext().getString(R.string.Monday);
				break;
			case Calendar.TUESDAY:
				dayOfWeek = App.getAppContext().getString(R.string.Tuesday);
				break;
			case Calendar.WEDNESDAY:
				dayOfWeek = App.getAppContext().getString(R.string.Wednesday);
				break;
			case Calendar.THURSDAY:
				dayOfWeek = App.getAppContext().getString(R.string.Thursday);
				break;
			case Calendar.FRIDAY:
				dayOfWeek = App.getAppContext().getString(R.string.Friday);
				break;
			case Calendar.SATURDAY:
				dayOfWeek = App.getAppContext().getString(R.string.Saturday);
				break;
			case Calendar.SUNDAY:
				dayOfWeek = App.getAppContext().getString(R.string.Sunday);
				break;
		}
		return dayOfWeek;
	}

	/**
	 * Compares this date with another Calendar date.
	 * @param date as Calendar
	 * @return true if equal, other else false
	 */
	private boolean compareDay(Calendar c){
		assert c != null;
		return (day == c.get(Calendar.DAY_OF_MONTH)
				&& month == (c.get(Calendar.MONTH) + 1)
				&& year == c.get(Calendar.YEAR));
	}

	public int hashCode() {
		return day+month+year;
	}

	/**
	 * returns the month of this date
	 * @return month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * returns the year of this date
	 * @return year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * returns the day of this date
	 * @return day
	 */
	public int getDay() {
		return day;
	}
}

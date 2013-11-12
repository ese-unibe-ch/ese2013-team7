package com.ese2013.mensaunibe.model.test;

import java.util.Calendar;

import android.content.Context;
import android.test.AndroidTestCase;

import com.ese2013.mensaunibe.App;
import com.ese2013.mensaunibe.model.MenuDate;

/**
 * @author group7
 * @author Sandor Torok
 */
public class MenuDateTest extends AndroidTestCase{


	private MenuDate menuDate;
	private	Calendar cal;

	/**
	 * @see android.test.AndroidTestCase#setUp()
	 */
	@Override
	public void setUp() throws Exception {
		super.setUp();
		cal = Calendar.getInstance();
		cal.set(2013, Calendar.NOVEMBER, 4);
		menuDate = new MenuDate(cal.getTime());
	}


	public void testHashCode() {
		int result = cal.get(Calendar.DAY_OF_MONTH)
				+(cal.get(Calendar.MONTH) + 1)
				+cal.get(Calendar.YEAR);
		assertEquals("Should be 2034", menuDate.hashCode(), result);
	}

	public void testCompareTo() {
		cal.set(2013, Calendar.NOVEMBER, 4);
		assertEquals("Should be equal", menuDate.compareTo(new MenuDate(cal.getTime())), 0);
		cal.set(2013, Calendar.OCTOBER, 4);
		assertEquals("Should not be equal", menuDate.compareTo(new MenuDate(cal.getTime())), 1);
	}

	public void testToText() {
		Context ctx = App.getAppContext();
		assertEquals("Should be Monday",
				ctx.getString(com.ese2013.mensaunibe.R.string.Monday),
				menuDate.toText(true));
		assertEquals("Should be Monday, 4.11.2013",
				ctx.getString(com.ese2013.mensaunibe.R.string.Monday)+", 4.11.2013",
				menuDate.toText(false));
		cal.add(Calendar.DATE, 2);
		menuDate = new MenuDate(cal.getTime());
		assertEquals("Should be Wednesday",
				ctx.getString(com.ese2013.mensaunibe.R.string.Wednesday),
				menuDate.toText(true));
		assertEquals("Should be Wednesday, 6.11.2013",
				ctx.getString(com.ese2013.mensaunibe.R.string.Wednesday)+", 6.11.2013",
				menuDate.toText(false));
		menuDate = new MenuDate(Calendar.getInstance().getTime());
		assertEquals("Should be Today",
				ctx.getString(com.ese2013.mensaunibe.R.string.today),
				menuDate.toText(true));
		assertEquals("Should be Today",
				ctx.getString(com.ese2013.mensaunibe.R.string.today),
				menuDate.toText(false));

	}

}

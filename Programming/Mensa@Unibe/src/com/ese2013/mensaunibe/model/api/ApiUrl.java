package com.ese2013.mensaunibe.model.api;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class ApiUrl {
	public final static String API_TOKEN = "6112255ca02b3040711015bbbda8d955";
	public final static String API_MENSA_LIST = "http://mensa.xonix.ch/v1/mensas?tok="+API_TOKEN;
	public final static String API_DAILY_MENU = "http://mensa.xonix.ch/v1/mensas/%d/dailyplan?tok="+API_TOKEN;
	public final static String API_WEEKLY_MENU = "http://mensa.xonix.ch/v1/mensas/%d/weeklyplan?tok="+API_TOKEN;
	public final static String API_NEXT_WEEK_MENU = "http://mensa.xonix.ch/v1/mensas/%d/weeklyplan/%d?tok="+API_TOKEN;
	
	public static final String API_TYP_MENSA = "MENSA";
	
	public static final String API_RATING_GET = "http://ese.andi-hohler.ch/rating_get.php?androidrequest";
	public static final String API_RATING_POST = "http://ese.andi-hohler.ch/rating_submit.php";
}

package com.ese2013.mensaunibe.model.utils;

/**
 * Defines app-wide constants and utilities
 */

/**
 * @author group7
 * @author Sandor Torok
 */

public final class AppUtils {

    // Debugging tag for the application
    public static final String APPTAG = "Mensa@Unibe";

    // Name of shared preferences repository that stores persistent state
    public static final String SHARED_PREFERENCES =
            "com.ese2013.mensaunibe.SHARED_PREFERENCES";

    // Key for storing the "updates requested" flag in shared preferences
    public static final String KEY_UPDATES_REQUESTED =
            "com.ese2013.mensaunibe.KEY_UPDATES_REQUESTED";

    /*
     * Define a request code to send to Google Play services
     * This code is returned in Activity.onActivityResult
     */
    public final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    /*
     * Constants for location update parameters
     */
    // Milliseconds per second
    public static final int MILLISECONDS_PER_SECOND = 1000;

    // The update interval
    public static final int UPDATE_INTERVAL_IN_SECONDS = 30;

    // A fast interval ceiling
    public static final int FAST_CEILING_IN_SECONDS = 1;

    // Update interval in milliseconds
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS =
            MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;

    // A fast ceiling of update intervals, used when the app is visible
    public static final long FAST_INTERVAL_CEILING_IN_MILLISECONDS =
            MILLISECONDS_PER_SECOND * FAST_CEILING_IN_SECONDS;

    
    
    public static final String MENSA_ID = "MENSA_ID";
    
    public static final String TAG_MENSA_INFO_DIALOG = "MENSA_INFO_DIALOG";
    
    public static final String TAG_MENSALIST_FRAGMENT ="MensaListFragment_tag";
    public static final String TAG_RATINGLIST_FRAGMENT ="RatingListFragment_tag";
    public static final String TAG_SETTINGS_FRAGMENT ="SettingsFragment_tag";
    
    public static final String TAG_CURRENT_DAY_MENU_FRAGMENT = "CURRENT_DAY_MENU_FRAGMENT";
    
	public static final int FIRST_MENU = 0;
	
	public static final int ALL_EXCEPT_FIRST = 1;

	public static final String TAG_NOTIFICATION_FRAGMENT = "NotificationFragment_tag";
	
	
	public static boolean hasUpperChars(String s) {
		boolean upperFound = false;
		for (char c : s.toCharArray()) {
		    if (Character.isUpperCase(c)) {
		        upperFound = true;
		        break;
		    }
		}
		return upperFound;
	}
}

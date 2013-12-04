package com.ese2013.mensaunibe.settings.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Spinner;
import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.settings.SettingsActivity;

public class SettingsActivityTest extends ActivityInstrumentationTestCase2<SettingsActivity> {
	private Activity activity;
	private int origSpinnerPosition;
	private final int TEST_SPINNER_POSITION_1 = 0;
	private final int TEST_SPINNER_POSITION_2 = 1;
	private  Spinner spinner;
	
    public SettingsActivityTest() {
        super(SettingsActivity.class);
    }
    
    @Override
    public void setUp() throws Exception{
    	super.setUp();
    	activity = getActivity();
    	spinner = (Spinner) activity.findViewById(R.id.language_spinner);
    	origSpinnerPosition = spinner.getSelectedItemPosition();
    }

    @Override
    public void tearDown() throws Exception{
    	spinner = (Spinner) activity.findViewById(R.id.language_spinner);
    	 activity.runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 spinner.requestFocus();
                 spinner.setSelection(origSpinnerPosition);
             }
         });
    }
    
    /**
     * Test to make sure that spinner values are persisted across activity restarts.
     *
     * <p>Launches the settings activity, sets a spinner value, closes the activity, then relaunches
     * that activity. Checks to make sure that the spinner values match what we set them to.
     */

    @SuppressLint("CutPasteId")
	public void testSpinnerValuePersistedBetweenLaunches() {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spinner.requestFocus();
                spinner.setSelection(TEST_SPINNER_POSITION_1);
            }
        });

        // Close the activity
        activity.finish();
        setActivity(null);

        // Relaunch the activity
        activity = this.getActivity();
        final Spinner spinner1 = (Spinner) activity.findViewById(R.id.language_spinner);
        int currentPosition = spinner1.getSelectedItemPosition();
        
        assertEquals(TEST_SPINNER_POSITION_1, currentPosition);

        // Set spinner to test position 2
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spinner1.requestFocus();
                spinner1.setSelection(TEST_SPINNER_POSITION_2);
            }
        });

        activity.finish();
        setActivity(null);
        activity = this.getActivity();

        final Spinner spinner2 = (Spinner) activity.findViewById(R.id.language_spinner);
        currentPosition = spinner2.getSelectedItemPosition();
        
        assertEquals(TEST_SPINNER_POSITION_2, currentPosition);
    }
}

package com.ese2013.mensaunibe.mensa.test;

import android.app.Instrumentation.ActivityMonitor;
import android.support.v4.app.ListFragment;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.KeyEvent;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ese2013.mensaunibe.R;
import com.ese2013.mensaunibe.mensa.MensaActivity;
import com.ese2013.mensaunibe.mensa.MensaListAdapter;
import com.ese2013.mensaunibe.mensa.MensaListFragment;
import com.ese2013.mensaunibe.menu.MenuActivity;
import com.ese2013.mensaunibe.model.utils.AppUtils;

/**
 * @author group7
 * @author Sandor Torok
 */
public class MensaActivityTest extends
		ActivityInstrumentationTestCase2<MensaActivity> {

	private MensaActivity activity;
	private ListFragment mFragment;
	private ListAdapter mAdapter;
	private ToggleButton mFavButton;
	private static final int AVAILABLE_MENSAS = 8;

	public MensaActivityTest() {
		super(MensaActivity.class);
	}

	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setActivityInitialTouchMode(false);

		activity = getActivity();
		mFragment = (MensaListFragment) getActivity().getSupportFragmentManager()
				.findFragmentByTag(AppUtils.TAG_MENSALIST_FRAGMENT);
		mAdapter = mFragment.getListAdapter();
		mFavButton = (ToggleButton) getActivity()
				.findViewById(R.id.tgl_favorite);

	}

	/**
	 * Testing preconditions for test cases
	 */
	public void testPreConditions() {
		assertNotNull(activity);
		assertNotNull(mFragment);
		assertNotNull(mAdapter);
		assertTrue(mAdapter instanceof MensaListAdapter);
	}

	/**
	 * Test if correct number of list elements are in the List View
	 */
	@SmallTest
	public void testListCount() {
		int adapterCount = AVAILABLE_MENSAS;
		for (int i = 0; i < mAdapter.getCount(); i++) {
			// if it is a section, then will be null, so we should add it to
			// number of canteens
			if (mAdapter.getItem(i) == null)
				adapterCount++;
		}
		assertEquals("List count does not match", mAdapter.getCount(),
				adapterCount);
	}

	/**
	 * Test favorite Button state.
	 * Emulator should be started with wiped user data for this test case to
	 * work properly.
	 */
	@SmallTest
	public void testFavoriteToggleButton() {
		assertNotNull("Favorite button not allowed to be null", mFavButton);
		assertTrue("Favorite toggle button should not be checked", !mFavButton.isChecked());
	}
	
	@MediumTest
	public void testMenuActivityStarted(){
	    // add monitor to check for the second activity
	    ActivityMonitor monitor =
	        getInstrumentation().
	          addMonitor(MenuActivity.class.getName(), null, false);

		TextView listItem = (TextView) activity
				.findViewById(R.id.mensa_list_row);
	    
	    // TouchUtils handles the sync with the main thread internally
	    TouchUtils.clickView(this, listItem);

	    // wait 2 seconds for the start of the activity
	    MenuActivity startedActivity = (MenuActivity) monitor
	        .waitForActivityWithTimeout(2000);
	    assertNotNull(startedActivity);

	    // search for the textView
	    TextView textView = (TextView) startedActivity.findViewById(R.id.menu_text);
	    
	    // check that the TextView is on the screen
	    ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
	        textView);
	    
	    // press back
	    this.sendKeys(KeyEvent.KEYCODE_BACK);
	}
	
	/**
	 * Test if application state is preserved between launches 
	 */
	@MediumTest
	public void testFavMensaPersistedBetweenLaunches() {
		mFavButton = (ToggleButton) getActivity()
				.findViewById(R.id.tgl_favorite);
		assertEquals(mFavButton.isChecked(), false);

		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				mFavButton.setChecked(true);
			}
		});

		// close the activity
		activity.finish();
		setActivity(null);//force creation of a new Activity
		// relaunch the activity
		activity = this.getActivity();

		mFavButton = (ToggleButton) getActivity()
				.findViewById(R.id.tgl_favorite);
		assertEquals(mFavButton.isChecked(), true);

		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				mFavButton.setChecked(false);
			}
		});

		activity.finish();
		setActivity(null);
		activity = this.getActivity();

		mFavButton = (ToggleButton) getActivity()
				.findViewById(R.id.tgl_favorite);
		assertEquals(mFavButton.isChecked(), false);
	}
}

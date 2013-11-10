package com.ese2013.mensaunibe.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ese2013.mensaunibe.MensaActivity;

public class MensaListTest extends ActivityInstrumentationTestCase2<MensaActivity> {

	private MensaActivity mActivity;
	private ListView mListView;
	private ListAdapter mAdapter;
	public static final int ADAPTER_COUNT = 8;

	public MensaListTest() {
		super(MensaActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setActivityInitialTouchMode(false);

		mActivity = getActivity();

		mListView = (ListView) mActivity.findViewById(com.ese2013.mensaunibe.R.id.listViewMensa);
		
		mAdapter = mListView.getAdapter();

	}

	public void testPreConditions() {
		assertNotNull(mActivity);
		assertEquals(mAdapter.getCount(), ADAPTER_COUNT);
	}
	
}

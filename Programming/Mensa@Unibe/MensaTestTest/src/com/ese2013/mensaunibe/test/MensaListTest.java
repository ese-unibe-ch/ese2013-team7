package com.ese2013.mensaunibe.test;

import com.ese2013.mensaunibe.MensaList;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MensaListTest extends ActivityInstrumentationTestCase2<MensaList> {

	private MensaList mActivity;
	private ListView mListView;
	private ListAdapter mAdapter;
	public static final int ADAPTER_COUNT = 8;

	public MensaListTest() {
		super(MensaList.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		setActivityInitialTouchMode(false);

		mActivity = getActivity();

		mListView = (ListView) mActivity.findViewById(com.ese2013.mensaunibe.R.id.listView1);
		
		mAdapter = mListView.getAdapter();

	}

	public void testPreConditions() {
		assertNotNull(mListView.getOnItemClickListener());
		assertNotNull(mAdapter);
		assertEquals(mAdapter.getCount(), ADAPTER_COUNT);
	}

}

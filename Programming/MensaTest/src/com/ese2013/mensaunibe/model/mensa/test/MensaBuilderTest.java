package com.ese2013.mensaunibe.model.mensa.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONObject;

import android.test.InstrumentationTestCase;

import com.ese2013.mensaunibe.model.api.PreferenceRequest;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.mensa.MensaBuilder;

public class MensaBuilderTest extends InstrumentationTestCase{

	private JSONObject mockJSON;
	private MensaBuilder mensaBuilder;
	private int mensaId = 1;
	private PreferenceRequest prefReq;
    private boolean favorite;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		//have to add this line for Mockito to work properly
		System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());
		
		mockJSON = mock(JSONObject.class);
		prefReq = new PreferenceRequest();
		favorite = prefReq.readPreference(mensaId);
		if(!favorite) prefReq.writePreference(true, mensaId);

		when(mockJSON.getInt("id")).thenReturn(mensaId);
		when(mockJSON.getString("mensa")).thenReturn("Mensa Gesellschaftsstrasse");
    	when(mockJSON.getString("street")).thenReturn("Gesellschaftsstrasse 2");
    	when(mockJSON.getString("plz")).thenReturn("3012 Bern");
    	when(mockJSON.getDouble("lat")).thenReturn(46.9518);
    	when(mockJSON.getDouble("lon")).thenReturn(7.438350);
		mensaBuilder = new MensaBuilder(mockJSON);

	}
	
	@Override
	public void tearDown() throws Exception{
		prefReq.writePreference(favorite, mensaId);

	}
	
	public void testId(){
		assertEquals("Id should be equal", mensaId, mensaBuilder.getId());
	}
	
	public void testName(){
		assertEquals("Name should be equal",
				"Mensa Gesellschaftsstrasse", mensaBuilder.getName());
	}
	
	public void testStreetAndPLZ(){
		assertEquals("Street is not the same",
				"Gesellschaftsstrasse 2", mensaBuilder.getStreet());
		assertEquals("StPLZ is not the same",
				"3012 Bern", mensaBuilder.getPlz());
	}
	
	public void testLatLong(){
		assertEquals("Latitude is not matching", 46.9518, mensaBuilder.getLat());
		assertEquals("Longitude is not matching", 7.438350, mensaBuilder.getLon());
	}
	
	public void testFavorite(){
		assertTrue(mensaBuilder.getFav());
		prefReq.writePreference(false, mensaId);
		mensaBuilder = new MensaBuilder(mockJSON);
		assertTrue(!mensaBuilder.getFav());
	}
	
	public void testCreate(){
		Mensa mensa = mensaBuilder.create();
		assertNotNull(mensa);
	}
	
}

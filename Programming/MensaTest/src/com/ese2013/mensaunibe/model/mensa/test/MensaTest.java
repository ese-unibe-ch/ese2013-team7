package com.ese2013.mensaunibe.model.mensa.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import android.location.Location;
import android.location.LocationManager;
import android.test.InstrumentationTestCase;

import com.ese2013.mensaunibe.model.api.MyLocation;
import com.ese2013.mensaunibe.model.mensa.Mensa;
import com.ese2013.mensaunibe.model.mensa.MensaBuilder;
import com.ese2013.mensaunibe.model.menu.WeeklyMenu;
import com.memetix.mst.language.Language;

public class MensaTest extends InstrumentationTestCase{


	private MensaBuilder mockBuilder;
	private Mensa mensa;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		//have to add this line for Mockito to work properly
		System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());
		
		mockBuilder = mock(MensaBuilder.class);
		when(mockBuilder.getId()).thenReturn(1);
		when(mockBuilder.getName()).thenReturn("Mensa Gesellschaftsstrasse");
    	when(mockBuilder.getStreet()).thenReturn("Gesellschaftsstrasse 2");
    	when(mockBuilder.getPlz()).thenReturn("3012 Bern");
    	when(mockBuilder.getLat()).thenReturn(46.9518);
    	when(mockBuilder.getLon()).thenReturn(7.438350);
    	when(mockBuilder.getFav()).thenReturn(true);
		mensa = new Mensa(mockBuilder);
		verify(mockBuilder).getId();
		verify(mockBuilder).getName();
		verify(mockBuilder).getStreet();
		verify(mockBuilder).getPlz();
		verify(mockBuilder).getLat();
		verify(mockBuilder).getLon();
		verify(mockBuilder).getFav();
	}
	
	@Override
	public void tearDown() throws Exception{
		mensa.setFavorite(false);
	}

	public void testIsFavorite() {
		assertTrue(mensa.isFavorite());
	}

	public void testIsSection() {
		assertTrue(!mensa.isSection());
	}
	
	public void testLanguage(){
		mensa.setLanguage(Language.ENGLISH);
		assertEquals("Language should be English", Language.ENGLISH, mensa.getLanguage());
	}
	
	public void testToString() {
		WeeklyMenu mockWm = mock(WeeklyMenu.class);
		when(mockWm.toString()).thenReturn("Yummi");
		mensa.setWeeklyMenu(mockWm);
		assertEquals("String should be equal",
				"mensa:Mensa Gesellschaftsstrasse,"
				+ "street:Gesellschaftsstrasse 2,"
				+ "plz:3012 Bern,"
				+ "lat:46.951800,"
				+ "lon:7.438350,"
				+ "id:1,"
				+ "menu:Yummi",
				 mensa.toString());
	}

	public void testDistance() {
		MyLocation mockLocation = mock(MyLocation.class);
		Location testLocation = new Location(LocationManager.GPS_PROVIDER);
		testLocation.setLatitude(46.9518);
		testLocation.setLongitude(7.43835);
		
		when(mockLocation.getLocation()).thenReturn(testLocation);
		assertEquals("Should be 0 m", "0 m", mensa.getDistance(mockLocation));
		
		testLocation.setLatitude(46.9536);
		testLocation.setLongitude(7.44004);
		assertEquals("Should be 237 m", "237 m", mensa.getDistance(mockLocation));
		
		testLocation.setLatitude(46.9726);
		testLocation.setLongitude(7.47968);
		assertEquals("Should be 3.9 km", "3.9 km", mensa.getDistance(mockLocation));
		
		testLocation.setLatitude(47.9370);
		testLocation.setLongitude(10.78988);
		assertEquals("Should be 275 km", "275 km", mensa.getDistance(mockLocation));

	}
}
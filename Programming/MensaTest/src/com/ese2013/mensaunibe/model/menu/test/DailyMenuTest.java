package com.ese2013.mensaunibe.model.menu.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import android.test.InstrumentationTestCase;

import com.ese2013.mensaunibe.model.MenuDate;
import com.ese2013.mensaunibe.model.menu.DailyMenu;
import com.ese2013.mensaunibe.model.menu.DailyMenuBuilder;

/**
 * @author group7
 * @author Sandor Torok
 */
public class DailyMenuTest extends InstrumentationTestCase{


	private DailyMenuBuilder mockBuilder;
	private MenuDate mockMenuDate;
	private DailyMenu dMenu;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		//have to add this line for Mockito to work properly
		System.setProperty("dexmaker.dexcache", getInstrumentation().getTargetContext().getCacheDir().getPath());
		mockMenuDate = mock(MenuDate.class);
		mockBuilder = mock(DailyMenuBuilder.class);
		when(mockBuilder.getTitle()).thenReturn("einfach gut");
		when(mockBuilder.getMenu()).thenReturn("Schweinshalssteak an Pfeffersauce"+"\n"
            +"Rissole Kartoffeln"+"\n"
            +"Rosenkohl"+"\n"
            +"Fleisch: Schweiz"+"\n"
            +"CHF 6.90 / 12.60");
    	when(mockBuilder.getDate()).thenReturn(mockMenuDate);

		dMenu = new DailyMenu(mockBuilder);
		
		verify(mockBuilder).getTitle();
		verify(mockBuilder).getMenu();
		verify(mockBuilder).getDate();
	}

	public void testGetDate() {
		assertEquals("Should be same MenuDate object", mockMenuDate, dMenu.getDate());
	}

	public void testIsSection() {
		assertTrue(!dMenu.isSection());
	}
	
	public void testToString() {
		assertEquals("String should be equal",
				"einfach gut" + "\n"
				+ "Schweinshalssteak an Pfeffersauce"+"\n"
				+"Rissole Kartoffeln"+"\n"
	            +"Rosenkohl"+"\n"
	            +"Fleisch: Schweiz"+"\n"
	            +"CHF 6.90 / 12.60",
				 dMenu.toString());
	}
}
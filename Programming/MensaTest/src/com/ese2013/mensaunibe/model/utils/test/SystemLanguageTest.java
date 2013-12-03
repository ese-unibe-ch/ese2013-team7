package com.ese2013.mensaunibe.model.utils.test;

import java.util.Locale;

import com.ese2013.mensaunibe.App;
import com.ese2013.mensaunibe.model.utils.SystemLanguage;

import android.content.Context;
import android.test.ApplicationTestCase;

public class SystemLanguageTest extends ApplicationTestCase<App>{
	private Context ctx;
	private Locale systemLocale;
	private Locale current;

	public SystemLanguageTest() {
		super(App.class);
	}

	@Override
	public void setUp() throws Exception{
		super.setUp();
		ctx = getSystemContext();
		createApplication();
		systemLocale = ctx.getResources().getConfiguration().locale;
	}
	
	@Override
	public void tearDown() throws Exception{
		current = null;
		terminateApplication();
	}
	
	public void testAutoLanguage(){
		SystemLanguage.autoLanguage();
		current = ctx.getResources().getConfiguration().locale;
		assertEquals("Locale should be same", systemLocale, current);
		
	}
	
	public void testGetLanguage(){
		current = new Locale(SystemLanguage.getLanguage());
		assertEquals("Locale should be same", systemLocale, current);
	}
	
	public void testChangeLanguage(){
		SystemLanguage.changeLanguage("de");
		current = new Locale("de");
		assertTrue(current!=systemLocale);
		
		systemLocale = ctx.getResources().getConfiguration().locale;
		assertEquals("Locale should be same", systemLocale, current);
		
		SystemLanguage.changeLanguage("en");
		current = new Locale("en");
		systemLocale = ctx.getResources().getConfiguration().locale;
		assertEquals("Locale should be persisted", systemLocale, current);
	}
}

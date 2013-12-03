package com.ese2013.mensaunibe.settings.test;

import com.ese2013.mensaunibe.settings.LanguageItem;

import junit.framework.TestCase;

public class LanguageItemTest extends TestCase{
	
	private static final String LANG_CODE = "de";
	private static final String LANGUAGE = "Deutsch";
	private LanguageItem langItem;
	
	@Override
	public void setUp() throws Exception{
		super.setUp();
		langItem = new LanguageItem(LANG_CODE, LANGUAGE);
	}
	
	public void testToString(){
		assertEquals(LANGUAGE, langItem.toString());
	}

	public void testGetLanguageCode(){
		assertEquals(LANG_CODE, langItem.getLangCode());
	}
}

package com.ese2013.mensaunibe.model.utils.test;

import com.ese2013.mensaunibe.model.utils.ListSectionItem;

import junit.framework.TestCase;

public class ListSectionItemTest extends TestCase {
	private final static String LIST_TEXT = "list item text";
	private ListSectionItem listSectionItem;

	@Override
	public void setUp() throws Exception{
		super.setUp();
		
		listSectionItem = new ListSectionItem(LIST_TEXT);
		
	}
	
	public void testToString(){
		assertEquals(LIST_TEXT, listSectionItem.toString());
	}
	
	public void testIsSection(){
		assertTrue(listSectionItem.isSection());
	}
}

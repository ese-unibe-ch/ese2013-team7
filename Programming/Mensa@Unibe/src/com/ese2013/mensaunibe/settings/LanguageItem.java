package com.ese2013.mensaunibe.settings;

public class LanguageItem {
	private String langCode;
	private String language;
	public LanguageItem(String langCode, String language) {
		this.langCode = langCode;
		this.language = language;
	}
	
	public String toString() {
		return this.language;
	}
	
	public String getLangCode() {
		return this.langCode;
	}
}
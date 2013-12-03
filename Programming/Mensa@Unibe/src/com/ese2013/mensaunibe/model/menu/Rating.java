package com.ese2013.mensaunibe.model.menu;

import java.util.Date;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class Rating {
	private String text;
	private int rating;
	private String nickname;
	private MenuDate date;
	
	public Rating(String nickname, String text, int rating, long time) {
		this.nickname = nickname;
		this.text = text;
		this.rating = rating;
		this.date = new MenuDate( new Date(time*1000));
	}
	
	public String toString() {
		return text;
	}
	
	public String getText() {
		return text;
	}
	
	public String getNickname() {
		return nickname;
	}
	public int getRating() {
		return rating;
	}
	public String getDate() {
		return date.toString();
	}
}

package com.ese2013.mensaunibe.model.menu;

/**
 * @author group7
 * @author Andreas Hohler
 */

public class Rating {
	private String text;
	private int rating;
	private String nickname;
	private boolean avg;
	
	public Rating(String nickname, String text, int rating) {
		this.nickname = nickname;
		this.text = text;
		this.rating = rating;
		this.avg = false;
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

	/**
	 * for the average rating of every menu
	 * @param true, if this is an average object or not
	 */
	public void setAvg(boolean b) {
		this.avg = b;
	}
	
	public boolean isAvg() {
		return avg;
	}
}

package com.ese2013.mensaunibe.notification;

public class NotificationHolder {
	private int mensaId;
	private String keyword;
	public NotificationHolder(int mensaId, String keyword) {
		this.mensaId = mensaId;
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}
	public int getMensaId() {
		return mensaId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 7;
		int hash = 1;
		hash = hash * prime + mensaId;
		hash = hash * prime + keyword.hashCode();
		return hash;
	}
	
	public String toString() {
		return mensaId + ", " + keyword;
	}
	
	public boolean equals(Object obj){
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof NotificationHolder))
		      return false;
		NotificationHolder nh = (NotificationHolder) obj;
		return (nh.getKeyword().equals(this.keyword) && nh.getMensaId() == this.mensaId);
	}
}
package com.ese2013.mensaunibe.notification;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author group7
 * @author Andreas Hohler
 * @author Marc Dojtschinov
 */

public class NotificationHolder implements Parcelable {
	private int mensaId;
	private String keyword;
	public NotificationHolder(int mensaId, String keyword) {
		this.mensaId = mensaId;
		this.keyword = keyword;
	}
	public NotificationHolder(Parcel in) {
		mensaId = in.readInt();
		keyword = in.readString();
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
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel out, int flags) {
		//Again this order must match the Question(Parcel) constructor
		out.writeInt(mensaId);
		out.writeString(keyword);
		
	}
	
	public static final Parcelable.Creator<NotificationHolder> CREATOR = new Parcelable.Creator<NotificationHolder>() {
        public NotificationHolder createFromParcel(Parcel in) {
        	return new NotificationHolder(in);
        }

        public NotificationHolder[] newArray(int size) {
            return new NotificationHolder[size];
        }
	};
}
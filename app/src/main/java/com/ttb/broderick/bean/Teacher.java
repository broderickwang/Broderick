package com.ttb.broderick.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kevin on 16/6/21.
 */
public class Teacher implements Parcelable {
	String name;
	int age;


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeInt(this.age);
	}

	public Teacher() {
	}

	protected Teacher(Parcel in) {
		this.name = in.readString();
		this.age = in.readInt();
	}

	public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
		@Override
		public Teacher createFromParcel(Parcel source) {
			return new Teacher(source);
		}

		@Override
		public Teacher[] newArray(int size) {
			return new Teacher[size];
		}
	};
}

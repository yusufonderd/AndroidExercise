package com.yonder.exercise.network.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BookEpub implements Parcelable {

    private boolean isAvailable;

    protected BookEpub(Parcel in) {
        isAvailable = in.readByte() != 0;
    }

    public static final Creator<BookEpub> CREATOR = new Creator<BookEpub>() {
        @Override
        public BookEpub createFromParcel(Parcel in) {
            return new BookEpub(in);
        }

        @Override
        public BookEpub[] newArray(int size) {
            return new BookEpub[size];
        }
    };

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (isAvailable ? 1 : 0));
    }
}

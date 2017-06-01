package com.yonder.exercise.network.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BookPdf implements Parcelable {

    private boolean isAvailable;

    protected BookPdf(Parcel in) {
        isAvailable = in.readByte() != 0;
    }

    public static final Creator<BookPdf> CREATOR = new Creator<BookPdf>() {
        @Override
        public BookPdf createFromParcel(Parcel in) {
            return new BookPdf(in);
        }

        @Override
        public BookPdf[] newArray(int size) {
            return new BookPdf[size];
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

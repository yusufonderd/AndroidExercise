package com.yonder.exercise.network.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BookSaleInfo implements Parcelable {
    private String country;
    private String saleability;
    private boolean isEbook;

    protected BookSaleInfo(Parcel in) {
        country = in.readString();
        saleability = in.readString();
        isEbook = in.readByte() != 0;
    }

    public static final Creator<BookSaleInfo> CREATOR = new Creator<BookSaleInfo>() {
        @Override
        public BookSaleInfo createFromParcel(Parcel in) {
            return new BookSaleInfo(in);
        }

        @Override
        public BookSaleInfo[] newArray(int size) {
            return new BookSaleInfo[size];
        }
    };

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSaleability() {
        return saleability;
    }

    public void setSaleability(String saleability) {
        this.saleability = saleability;
    }

    public boolean isIsEbook() {
        return isEbook;
    }

    public void setIsEbook(boolean isEbook) {
        this.isEbook = isEbook;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(country);
        parcel.writeString(saleability);
        parcel.writeByte((byte) (isEbook ? 1 : 0));
    }
}

package com.yonder.exercise.network.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BookIndustryIdentifiers implements Parcelable {
    /**
     * type : ISBN_10
     * identifier : 1406300209
     */

    private String type;
    private String identifier;

    protected BookIndustryIdentifiers(Parcel in) {
        type = in.readString();
        identifier = in.readString();
    }

    public static final Creator<BookIndustryIdentifiers> CREATOR = new Creator<BookIndustryIdentifiers>() {
        @Override
        public BookIndustryIdentifiers createFromParcel(Parcel in) {
            return new BookIndustryIdentifiers(in);
        }

        @Override
        public BookIndustryIdentifiers[] newArray(int size) {
            return new BookIndustryIdentifiers[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(identifier);
    }
}

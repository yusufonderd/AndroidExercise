package com.yonder.exercise.network.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BookImageLinks implements Parcelable {
    private String smallThumbnail;
    private String thumbnail;
    private String small;
    private String medium;
    private String large;
    private String extraLarge;

    protected BookImageLinks(Parcel in) {
        smallThumbnail = in.readString();
        thumbnail = in.readString();
        small = in.readString();
        medium = in.readString();
        large = in.readString();
        extraLarge = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(smallThumbnail);
        dest.writeString(thumbnail);
        dest.writeString(small);
        dest.writeString(medium);
        dest.writeString(large);
        dest.writeString(extraLarge);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BookImageLinks> CREATOR = new Creator<BookImageLinks>() {
        @Override
        public BookImageLinks createFromParcel(Parcel in) {
            return new BookImageLinks(in);
        }

        @Override
        public BookImageLinks[] newArray(int size) {
            return new BookImageLinks[size];
        }
    };

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getExtraLarge() {
        return extraLarge;
    }

    public void setExtraLarge(String extraLarge) {
        this.extraLarge = extraLarge;
    }
}

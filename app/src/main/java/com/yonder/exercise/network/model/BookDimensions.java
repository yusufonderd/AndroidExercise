package com.yonder.exercise.network.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BookDimensions implements Parcelable {
    /**
     * height : 20.00 cm
     * width : 12.90 cm
     * thickness : 1.40 cm
     */

    private String height;
    private String width;
    private String thickness;

    protected BookDimensions(Parcel in) {
        height = in.readString();
        width = in.readString();
        thickness = in.readString();
    }

    public static final Creator<BookDimensions> CREATOR = new Creator<BookDimensions>() {
        @Override
        public BookDimensions createFromParcel(Parcel in) {
            return new BookDimensions(in);
        }

        @Override
        public BookDimensions[] newArray(int size) {
            return new BookDimensions[size];
        }
    };

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(height);
        parcel.writeString(width);
        parcel.writeString(thickness);
    }
}

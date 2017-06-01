package com.yonder.exercise.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


/**
 * Created by YusufMac on 29/05/17.
 */

class BookSearchResult : Parcelable {
    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @SerializedName("items")
    var books: List<SingleBook>? = null

    override fun toString(): String {
        return "BookSearchResult(books=$books)"
    }
}

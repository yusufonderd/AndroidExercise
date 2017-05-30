package com.yonder.exercise.network.model

import com.google.gson.annotations.SerializedName


/**
 * Created by YusufMac on 29/05/17.
 */
class Book {
    @SerializedName("volumeInfo")
    var volumeInfo: BookVolumeInfo? = null
        internal set

}

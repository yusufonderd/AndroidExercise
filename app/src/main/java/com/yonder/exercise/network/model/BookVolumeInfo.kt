package com.yonder.exercise.network.model

import com.google.gson.annotations.SerializedName


/**
 * Created by YusufMac on 29/05/17.
 */
class BookVolumeInfo {
    @SerializedName("authors")
    var author: List<String>? = null
    @SerializedName("title")
    var title: String? = null


}

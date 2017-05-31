package com.yonder.exercise.network.model

import com.google.gson.annotations.SerializedName


/**
 * Created by YusufMac on 29/05/17.
 */

class BookSearchResult {
    @SerializedName("items")
    var books: List<SingleBook>? = null

    override fun toString(): String {
        return "BookSearchResult(books=$books)"
    }
}

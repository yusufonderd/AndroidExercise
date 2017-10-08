package com.yonder.exercise.ui.detail

import android.arch.lifecycle.LiveData

import com.yonder.exercise.db.BookModel
import com.yonder.exercise.network.model.SingleBook

import io.reactivex.Completable

/**
 * Created by YusufMac on 02/06/17.
 */

interface IBookDetailRepository {
    val book: LiveData<SingleBook>
    fun loadBook(bookId: String)
    fun updateBook(model: BookModel)
}

package com.yonder.exercise.ui.books

import android.arch.lifecycle.LiveData

import com.yonder.exercise.db.BookModel

/**
 * Created by YusufMac on 08/10/2017.
 */


interface IBooksRepository {

    val allBooks: LiveData<List<BookModel>>
    fun loadBooks(query: String)
    fun deleteAllBooks()
    fun insertAllBooks(bookModels: List<BookModel>)
}
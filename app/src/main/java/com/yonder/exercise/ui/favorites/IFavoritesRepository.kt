package com.yonder.exercise.ui.favorites

import android.arch.lifecycle.LiveData

import com.yonder.exercise.db.BookModel

/**
 * Created by YusufMac on 02/06/17.
 */

interface IFavoritesRepository {
    val favoritedBooks: LiveData<List<BookModel>>
}

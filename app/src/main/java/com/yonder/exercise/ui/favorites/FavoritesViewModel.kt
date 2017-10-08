package com.yonder.exercise.ui.favorites

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import com.yonder.exercise.app.App
import com.yonder.exercise.db.AppDatabase
import com.yonder.exercise.db.BookModel
import com.yonder.exercise.db.DBConstants

import javax.inject.Inject

/**
 * Created by YusufMac on 01/06/17.
 */

class FavoritesViewModel(application: Application) : AndroidViewModel(application), IFavoritesRepository {

    @Inject
    lateinit var appDatabase: AppDatabase

    override val favoritedBooks: LiveData<List<BookModel>>

    init {
        (this.getApplication<Application>() as App).appComponent.inject(this)
        favoritedBooks = appDatabase.bookModelDao().getAllFavoritedItems(DBConstants.FAV)
    }


}

package com.yonder.exercise.ui.favorites;

import android.arch.lifecycle.LiveData;

import com.yonder.exercise.db.BookModel;

import java.util.List;

/**
 * Created by YusufMac on 02/06/17.
 */

public interface IFavoritesRepository {
    LiveData<List<BookModel>> getFavoritedBooks();
}

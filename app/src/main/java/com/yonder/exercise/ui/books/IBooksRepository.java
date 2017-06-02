package com.yonder.exercise.ui.books;

import android.arch.lifecycle.LiveData;

import com.yonder.exercise.db.BookModel;

import java.util.List;

/**
 * Created by YusufMac on 02/06/17.
 */

public interface IBooksRepository {

    LiveData<List<BookModel>> getAllBooks();

    void loadBooks(String query);

    void deleteAllBooks();

    void insertAllBooks(List<BookModel> bookModels);
}

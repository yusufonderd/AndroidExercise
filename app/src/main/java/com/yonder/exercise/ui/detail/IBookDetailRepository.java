package com.yonder.exercise.ui.detail;

import android.arch.lifecycle.LiveData;

import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.network.model.SingleBook;

import io.reactivex.Completable;

/**
 * Created by YusufMac on 02/06/17.
 */

public interface IBookDetailRepository {

    void loadBook(String bookId);

    void updateBook(BookModel event);

    LiveData<SingleBook> getBook();
}

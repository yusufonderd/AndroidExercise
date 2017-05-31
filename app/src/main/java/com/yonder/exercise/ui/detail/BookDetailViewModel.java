package com.yonder.exercise.ui.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.yonder.exercise.app.App;
import com.yonder.exercise.network.BooksApi;
import com.yonder.exercise.network.model.SingleBook;


import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BookDetailViewModel extends AndroidViewModel {

    @Inject
    BooksApi booksApi;

    MutableLiveData<SingleBook> book;

    public BookDetailViewModel(Application application) {
        super(application);
        ((App) this.getApplication()).getAppComponent().inject(this);
    }

    void loadBook(String bookId) {
        booksApi.getItemById(bookId).enqueue(new Callback<SingleBook>() {
            @Override
            public void onResponse(Call<SingleBook> call, Response<SingleBook> response) {
                book.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SingleBook> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    LiveData<SingleBook> getBook() {
        if (book == null)
            book = new MutableLiveData<>();
        return book;
    }
}

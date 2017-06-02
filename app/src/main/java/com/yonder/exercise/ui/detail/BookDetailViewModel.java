package com.yonder.exercise.ui.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.yonder.exercise.app.App;
import com.yonder.exercise.db.AppDatabase;
import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.network.BooksApi;
import com.yonder.exercise.network.model.SingleBook;
import com.yonder.exercise.shared.utils.ObservableUtil;


import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BookDetailViewModel extends AndroidViewModel implements IBookDetailRepository {

    @Inject
    BooksApi booksApi;

    @Inject
    AppDatabase appDatabase;

    private MutableLiveData<SingleBook> book;

    public BookDetailViewModel(Application application) {
        super(application);
        ((App) this.getApplication()).getAppComponent().inject(this);
        book = new MutableLiveData<>();
    }

    @Override
    public void loadBook(String bookId) {
        booksApi.getItemById(bookId).enqueue(new Callback<SingleBook>() {
            @Override
            public void onResponse(@NotNull Call<SingleBook> call, @NotNull Response<SingleBook> response) {
                book.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<SingleBook> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void updateBook(BookModel bookModel) {
        Completable completable = Completable.fromAction(() -> appDatabase.bookModelDao().update(bookModel.getBookFav(), bookModel.getBookId()));
        ObservableUtil.completableObserver(completable);
    }

    @Override
    public LiveData<SingleBook> getBook() {
        return book;
    }


}

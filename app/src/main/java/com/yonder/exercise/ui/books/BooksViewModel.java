package com.yonder.exercise.ui.books;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.yonder.exercise.app.App;
import com.yonder.exercise.db.AppDatabase;
import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.db.BookModelDao;
import com.yonder.exercise.network.BooksApi;
import com.yonder.exercise.network.model.BookSearchResult;
import com.yonder.exercise.network.model.SingleBook;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BooksViewModel extends AndroidViewModel {

    @Inject
    BooksApi booksApi;

    private BookModelDao bookModelDao;
    private LiveData<List<BookModel>> books;

    private AppDatabase appDatabase;

    public BooksViewModel(Application application) {
        super(application);
        ((App) this.getApplication()).getAppComponent().inject(this);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
        bookModelDao = appDatabase.bookModelDao();
        books = bookModelDao.getAllItems();
    }

    LiveData<List<BookModel>> getBooks() {
        return books;
    }

    private void addBookList(BookSearchResult bookSearchResult) {
        List<BookModel> bookModels = new ArrayList<>();
        if (bookSearchResult.getBooks() != null)
            for (SingleBook book : bookSearchResult.getBooks()) {
                BookModel bookModel = Utils.getBookModel(book);
                bookModels.add(bookModel);
            }
        addAll(bookModels);
    }

    void loadBooks(String query) {
        booksApi.search("search+" + query)
                .enqueue(new Callback<BookSearchResult>() {
                    @Override
                    public void onResponse(@NonNull Call<BookSearchResult> call, @NonNull Response<BookSearchResult> response) {
                        addBookList(response.body());
                    }
                    @Override
                    public void onFailure(@NonNull Call<BookSearchResult> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }


    private void addAll(final List<BookModel> bookModel) {
        Observable<Void> observable = new Observable<Void>() {
            @Override
            protected void subscribeActual(Observer<? super Void> observer) {
                appDatabase.bookModelDao().insertAll(bookModel);
                observer.onComplete();
            }
        };
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }

    void deleteAll() {
        Observable<Void> observable = new Observable<Void>() {
            @Override
            protected void subscribeActual(Observer<? super Void> observer) {
                appDatabase.bookModelDao().deleteAll(getBooks().getValue());
                observer.onComplete();
            }
        };
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }


}

package com.yonder.exercise.ui.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.yonder.exercise.app.App;
import com.yonder.exercise.db.AppDatabase;
import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.db.BookModelDao;
import com.yonder.exercise.network.BooksApi;
import com.yonder.exercise.network.model.SingleBook;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BookDetailViewModel extends AndroidViewModel {

    public static String TAG = BookDetailViewModel.class.getSimpleName();
    @Inject
    BooksApi booksApi;

    MutableLiveData<SingleBook> book;

    private AppDatabase appDatabase;
    BookModelDao bookModelDao;

    public BookDetailViewModel(Application application) {
        super(application);
        ((App) this.getApplication()).getAppComponent().inject(this);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
        bookModelDao = appDatabase.bookModelDao();
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


    public void updateModel(final BookModel bookModel) {
        Observable<BookModel> observable = new Observable<BookModel>() {
            @Override
            protected void subscribeActual(Observer<? super BookModel> observer) {
                appDatabase.bookModelDao().update(bookModel);
                observer.onNext(bookModel);
                observer.onComplete();
            }
        };
        executeObservable(observable);
    }

    private void executeObservable(Observable<BookModel> observable) {
        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BookModel bookModel) {
                        Log.i(TAG, "onNext: " + bookModel.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                    }
                });
    }


    LiveData<SingleBook> getBook() {
        if (book == null)
            book = new MutableLiveData<>();
        return book;
    }
}

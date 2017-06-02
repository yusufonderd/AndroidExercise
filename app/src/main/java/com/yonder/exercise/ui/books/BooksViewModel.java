package com.yonder.exercise.ui.books;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.yonder.exercise.app.App;
import com.yonder.exercise.db.AppDatabase;
import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.network.BooksApi;
import com.yonder.exercise.network.model.BookSearchResult;
import com.yonder.exercise.network.model.SingleBook;
import com.yonder.exercise.shared.utils.BooksUtils;
import com.yonder.exercise.shared.utils.ObservableUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BooksViewModel extends AndroidViewModel implements IBooksRepository {
    String TAG = BooksViewModel.class.getSimpleName();


    @Inject
    BooksApi booksApi;
    @Inject
    AppDatabase appDatabase;

    private LiveData<List<BookModel>> books;


    private BookSearchResult bookSearchResult;

    public BooksViewModel(Application application) {
        super(application);
        ((App) this.getApplication()).getAppComponent().inject(this);
        books = appDatabase.bookModelDao().getAllItems();


    }


    @Override
    public LiveData<List<BookModel>> getAllBooks() {
        return books;
    }

    @Override
    public void loadBooks(String query) {
        booksApi.search("search+" + query)
                .enqueue(new Callback<BookSearchResult>() {
                    @Override
                    public void onResponse(@NonNull Call<BookSearchResult> call, @NonNull Response<BookSearchResult> response) {
                        bookSearchResult = response.body();
                        List<BookModel> bookModels = new ArrayList<>();
                        if (bookSearchResult.getBooks() != null)
                            for (SingleBook book : bookSearchResult.getBooks()) {
                                BookModel bookModel = BooksUtils.getBookModel(book);
                                bookModels.add(bookModel);
                            }
                        insertAllBooks(bookModels);
                    }

                    @Override
                    public void onFailure(@NonNull Call<BookSearchResult> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void deleteAllBooks() {
        Completable completable = Completable.fromAction(() -> appDatabase.bookModelDao().deleteAll());
        ObservableUtil.completableObserver(completable);
    }


    @Override
    public void insertAllBooks(List<BookModel> bookModels) {
        Completable completable = Completable.fromAction(() -> appDatabase.bookModelDao().insertAll(bookModels));
        ObservableUtil.completableObserver(completable);
    }
}

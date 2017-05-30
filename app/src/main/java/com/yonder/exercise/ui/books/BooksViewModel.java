package com.yonder.exercise.ui.books;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.yonder.exercise.app.App;
import com.yonder.exercise.db.AppDatabase;
import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.db.BookModelDao;
import com.yonder.exercise.network.BooksApi;
import com.yonder.exercise.network.model.Book;
import com.yonder.exercise.network.model.BookSearchResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YusufMac on 30/05/17.
 */

public class BooksViewModel extends AndroidViewModel {


    private static final String TAG = BooksViewModel.class.getSimpleName();
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

    private List<BookModel> getBookModelList(BookSearchResult bookSearchResult) {

        List<BookModel> bookModels = new ArrayList<>();
        for (Book book : bookSearchResult.getBooks()) {
            BookModel bookModel = Utils.getBookModel(book);
            bookModels.add(bookModel);
        }
        addAll(bookModels);
        return bookModels;
    }

    void loadBooks(String query) {
        booksApi.search("search+" + query)
                .enqueue(new Callback<BookSearchResult>() {
                    @Override
                    public void onResponse(Call<BookSearchResult> call, Response<BookSearchResult> response) {
                        Log.i(TAG, "onResponse toString(): " + response.toString());
                        getBookModelList(response.body());
                    }

                    @Override
                    public void onFailure(Call<BookSearchResult> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private static class addAsyncTask extends AsyncTask<List<BookModel>, Void, Void> {
        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(final List<BookModel>... params) {
            db.bookModelDao().insertAll(params[0]);
            return null;
        }
    }

    private void addAll(final List<BookModel> bookModel) {
        new addAsyncTask(appDatabase).execute(bookModel);
    }

    void deleteAll() {
        new deleteAllBooksAsyncTask(appDatabase, getBooks().getValue()).execute();
    }

    private static class deleteAllBooksAsyncTask extends AsyncTask<Void, Void, Void> {
        private AppDatabase db;
        private List<BookModel> bookModels;

        deleteAllBooksAsyncTask(AppDatabase appDatabase, List<BookModel> bookModels) {
            db = appDatabase;
            this.bookModels = bookModels;
        }

        @Override
        protected Void doInBackground(Void... params) {
            db.bookModelDao().deleteAll(bookModels);
            return null;
        }

    }
}

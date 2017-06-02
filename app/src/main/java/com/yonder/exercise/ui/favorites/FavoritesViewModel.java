package com.yonder.exercise.ui.favorites;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;


import com.yonder.exercise.app.App;
import com.yonder.exercise.db.AppDatabase;
import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.db.BookModelDao;
import com.yonder.exercise.db.DBConstants;
import com.yonder.exercise.shared.utils.ObservableUtil;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by YusufMac on 01/06/17.
 */

public class FavoritesViewModel extends AndroidViewModel implements IFavoritesRepository {

    @Inject
    AppDatabase appDatabase;

    LiveData<List<BookModel>> favoritedBooks;

    public FavoritesViewModel(Application application) {
        super(application);
        ((App) this.getApplication()).getAppComponent().inject(this);
        favoritedBooks = appDatabase.bookModelDao().getAllFavoritedItems(DBConstants.FAV);
    }

    @Override
    public LiveData<List<BookModel>> getFavoritedBooks() {
        return favoritedBooks;
    }


}

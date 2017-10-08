package com.yonder.exercise.ui.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

import com.yonder.exercise.app.App
import com.yonder.exercise.db.AppDatabase
import com.yonder.exercise.db.BookModel
import com.yonder.exercise.network.BooksApi
import com.yonder.exercise.network.model.SingleBook
import com.yonder.exercise.shared.utils.ObservableUtil

import javax.inject.Inject

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by YusufMac on 30/05/17.
 */

class BookDetailViewModel(application: Application) : AndroidViewModel(application), IBookDetailRepository {

    @Inject
    lateinit var booksApi: BooksApi

    @Inject
    lateinit var appDatabase: AppDatabase

    override lateinit var book: MutableLiveData<SingleBook>

    init {
        (this.getApplication<Application>() as App).appComponent.inject(this)
        book = MutableLiveData()
    }

    override fun loadBook(bookId: String) {
        booksApi.getItemById(bookId).enqueue(object : Callback<SingleBook> {
            override fun onResponse(call: Call<SingleBook>, response: Response<SingleBook>) {
                book.value = response.body()
            }
            override fun onFailure(call: Call<SingleBook>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    override fun updateBook(model: BookModel) {
        val completable = Completable.fromAction { appDatabase.bookModelDao().update(model.bookFav, model.bookId) }
        ObservableUtil.completableObserver(completable)
    }


}

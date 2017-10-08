package com.yonder.exercise.ui.books

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.yonder.exercise.app.App
import com.yonder.exercise.db.AppDatabase
import com.yonder.exercise.db.BookModel
import com.yonder.exercise.network.BooksApi
import com.yonder.exercise.network.model.BookSearchResult
import com.yonder.exercise.shared.utils.BooksUtils
import com.yonder.exercise.shared.utils.ObservableUtil
import java.util.ArrayList
import javax.inject.Inject
import io.reactivex.Completable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by YusufMac on 30/05/17.
 */

class BooksViewModel(application: Application) : AndroidViewModel(application), IBooksRepository {

    @Inject
    lateinit var booksApi: BooksApi
    @Inject
    lateinit var appDatabase: AppDatabase

    private val books: LiveData<List<BookModel>>
    private var bookSearchResult: BookSearchResult? = null

    init {
        (this.getApplication<Application>() as App).appComponent.inject(this)
        books = appDatabase.bookModelDao().allItems
    }
    override val allBooks: LiveData<List<BookModel>> get() = books

    override fun loadBooks(query: String) {
        booksApi.search("search+" + query)
                .enqueue(object : Callback<BookSearchResult> {
                    override fun onResponse(call: Call<BookSearchResult>, response: Response<BookSearchResult>) {
                        bookSearchResult = response.body()
                        val bookModels = ArrayList<BookModel>()
                        if (bookSearchResult!!.books != null)
                            for (book in bookSearchResult!!.books!!) {
                                val bookModel = BooksUtils.getBookModel(book)
                                bookModels.add(bookModel)
                            }
                        insertAllBooks(bookModels)
                    }

                    override fun onFailure(call: Call<BookSearchResult>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
    }
    override fun deleteAllBooks() {
        val completable = Completable.fromAction { appDatabase.bookModelDao().deleteAll() }
        ObservableUtil.completableObserver(completable)
    }

    override fun insertAllBooks(bookModels: List<BookModel>) {
        val completable = Completable.fromAction { appDatabase.bookModelDao().insertAll(bookModels) }
        ObservableUtil.completableObserver(completable)
    }
}

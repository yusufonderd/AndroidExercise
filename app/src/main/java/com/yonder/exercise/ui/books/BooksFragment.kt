package com.yonder.exercise.ui.books

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.orhanobut.hawk.Hawk
import com.yonder.exercise.R
import com.yonder.exercise.db.BookModel
import com.yonder.exercise.shared.adapters.BooksRecyclerViewAdapter
import com.yonder.exercise.ui.detail.BookDetailActivity

class BooksFragment : Fragment(), BooksRecyclerViewAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {


    lateinit var basicViewModel: BooksViewModel
    var basicAdapter: BooksRecyclerViewAdapter? = null

    lateinit var lRefresh: SwipeRefreshLayout
    lateinit var bookRv: RecyclerView
    lateinit var bookNameEt: EditText
    lateinit var fab: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_books, container, false)
        Hawk.init(context).build()
        initViews(view)
        basicViewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)
        basicViewModel.allBooks.observe(this@BooksFragment, Observer {
            if (it != null) {
                lRefresh.isRefreshing = false
                if (basicAdapter == null) {
                    basicAdapter = BooksRecyclerViewAdapter(it, this@BooksFragment)
                    bookRv.adapter = basicAdapter
                } else {
                    basicAdapter!!.setBooksItemList(it)
                    basicAdapter!!.notifyDataSetChanged()
                }
            }
        })

        lRefresh.setOnRefreshListener(this)
        fab.setOnClickListener { loadBooks() }
        return view
    }

    private fun loadBooks() {
        val query = bookNameEt.text.toString()
        if (!TextUtils.isEmpty(query)) {
            lRefresh.isRefreshing = true
            Hawk.deleteAll()
            basicViewModel.deleteAllBooks()
            basicViewModel.loadBooks(query)
        }
    }

    fun initViews(view: View) {
        lRefresh = view.findViewById<SwipeRefreshLayout>(R.id.lRefresh) as SwipeRefreshLayout
        bookRv = view.findViewById<RecyclerView>(R.id.bookRv) as RecyclerView
        bookNameEt = view.findViewById<EditText>(R.id.bookNameEt) as EditText
        fab = view.findViewById<FloatingActionButton>(R.id.fab) as FloatingActionButton

    }

    override fun onClick(bookModel: BookModel) {
        startActivity(Intent(context, BookDetailActivity::class.java).putExtra(BookDetailActivity.BOOK_ID, bookModel.bookId))
    }

    override fun onRefresh() {
        Handler().postDelayed({ lRefresh.isRefreshing = false }, 1000)
    }

    companion object {
        fun newInstance(): BooksFragment {
            return BooksFragment()
        }
    }
}

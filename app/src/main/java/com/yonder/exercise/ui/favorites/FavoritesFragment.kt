package com.yonder.exercise.ui.favorites


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.orhanobut.hawk.Hawk
import com.yonder.exercise.R
import com.yonder.exercise.db.BookModel
import com.yonder.exercise.shared.adapters.BooksRecyclerViewAdapter

import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.support.v4.toast


class FavoritesFragment : Fragment(), BooksRecyclerViewAdapter.OnItemClickListener {

    lateinit var favoriteViewModel: FavoritesViewModel
    var booksRecyclerViewAdapter: BooksRecyclerViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_favorite, container, false)
        ButterKnife.bind(this, view)
        favoriteViewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
        Hawk.init(context).build()
        favoriteViewModel.favoritedBooks.observe(this, Observer {
            if (it != null) {
                if (booksRecyclerViewAdapter == null) {
                    booksRecyclerViewAdapter = BooksRecyclerViewAdapter(it, this@FavoritesFragment)
                    rv.adapter = booksRecyclerViewAdapter
                } else {
                    booksRecyclerViewAdapter!!.setBooksItemList(it)
                    booksRecyclerViewAdapter!!.notifyDataSetChanged()
                }
            }
        })
        return view
    }


    override fun onClick(bookModel: BookModel) {
        toast(bookModel.bookTitle)
    }

    companion object {
        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

}

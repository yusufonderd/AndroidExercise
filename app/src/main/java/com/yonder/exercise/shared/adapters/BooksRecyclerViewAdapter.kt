package com.yonder.exercise.shared.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yonder.exercise.R
import com.yonder.exercise.db.BookModel

import java.util.ArrayList

/**
 * Created by YusufMac on 30/05/17.
 */

class BooksRecyclerViewAdapter(booksItemList: List<BookModel>, private val iBookModel: OnItemClickListener) : RecyclerView.Adapter<BooksViewHolder>() {

    private var booksItemList: List<BookModel>? = null

    init {
        setBooksItemList(booksItemList)
    }

    fun setBooksItemList(booksItemList: List<BookModel>?) {
        if (booksItemList == null)
            this.booksItemList = ArrayList()
        else
            this.booksItemList = booksItemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_book, parent, false)
        return BooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val bookModel = booksItemList!![position]
        holder.tvDescription.text = bookModel.bookTitle
        holder.tvName.text = bookModel.bookAuthor
        holder.tvCardView.setOnClickListener { view -> iBookModel.onClick(bookModel) }
    }


    override fun getItemCount(): Int {
        return booksItemList!!.size
    }

    interface OnItemClickListener {
        fun onClick(bookModel: BookModel)

    }
}



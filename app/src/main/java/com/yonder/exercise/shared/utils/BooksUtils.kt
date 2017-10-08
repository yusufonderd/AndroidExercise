package com.yonder.exercise.shared.utils

import com.yonder.exercise.db.BookModel
import com.yonder.exercise.db.DBConstants
import com.yonder.exercise.network.model.SingleBook

import java.util.Date

/**
 * Created by YusufMac on 30/05/17.
 */

object BooksUtils {

    fun getAuthor(book: SingleBook): String {
        var author = ""
        if (book.volumeInfo.authors != null) {
            val builder = StringBuilder()
            for (authorName in book.volumeInfo.authors) {
                if (builder.length > 0) {
                    builder.append(", ")
                }
                builder.append(authorName)
            }
            author = builder.toString()
        }
        return author
    }

    fun getBookModel(book: SingleBook): BookModel {
        return BookModel(book.id, book.volumeInfo.title, getAuthor(book), Date())
    }


    private fun isBookFav(bookModel: BookModel): Boolean {
        return bookModel.bookFav != null && bookModel.bookFav == DBConstants.FAV
    }


    fun getBookFab(bookModel: BookModel): String {
        val isFav = isBookFav(bookModel)
        return if (isFav)
            DBConstants.FAV
        else
            DBConstants.UNFAV

    }
}

package com.yonder.exercise.ui.books;

import android.util.Log;

import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.network.model.SingleBook;

import java.util.Date;

/**
 * Created by YusufMac on 30/05/17.
 */

public class Utils {


    public static String getAuthor(SingleBook book) {
        String author = "";
        if (book.getVolumeInfo().getAuthors() != null) {
            StringBuilder builder = new StringBuilder();
            for (String authorName : book.getVolumeInfo().getAuthors()) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append(authorName);
            }
            author = builder.toString();
        }
        return author;
    }

    static BookModel getBookModel(SingleBook book) {
        Log.i("BookModel", "getBookModel: " + book.getId());
        return new BookModel(book.getId(), book.getVolumeInfo().getTitle(), getAuthor(book), new Date());
    }


}

package com.yonder.exercise.ui.books;

import com.yonder.exercise.db.BookModel;
import com.yonder.exercise.network.model.Book;

import java.util.Date;

/**
 * Created by YusufMac on 30/05/17.
 */

class Utils {


    private static String getAuthor(Book book) {
        String author = "";
        if (book.getVolumeInfo().getAuthor() != null) {
            StringBuilder builder = new StringBuilder();
            for (String authorName : book.getVolumeInfo().getAuthor()) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder.append(authorName);
            }
            author = builder.toString();
        }
        return author;
    }

    static BookModel getBookModel(Book book) {
        return new BookModel(book.getVolumeInfo().getTitle(), getAuthor(book), new Date());
    }


}

package com.yonder.exercise.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Created by YusufMac on 30/05/17.
 */

@Entity
public class BookModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String bookTitle;
    private String bookAuthor;
    private String bookId;
    @TypeConverters(DateConverter.class)
    private Date bookDate;

    public BookModel(String bookId, String bookTitle, String bookAuthor, Date bookDate) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDate = bookDate;
    }

    public String getBookId() {
        return bookId;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookDate=" + bookDate +
                '}';
    }
}

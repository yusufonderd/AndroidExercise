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
    private String title;
    private String author;
    @TypeConverters(DateConverter.class)
    private Date bookDate;


    public BookModel(String title, String author, Date bookDate) {
        this.title = title;
        this.author = author;
        this.bookDate = bookDate;

    }

    public Date getBookDate() {
        return bookDate;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", bookDate=" + bookDate +
                '}';
    }
}

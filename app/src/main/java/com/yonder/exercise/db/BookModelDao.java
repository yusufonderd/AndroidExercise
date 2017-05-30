package com.yonder.exercise.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by YusufMac on 30/05/17.
 */

@Dao
public interface BookModelDao {

    @Query("select * from BookModel")
    LiveData<List<BookModel>> getAllItems();

    @Query("select * from BookModel where id = :id")
    BookModel getItembyId(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BookModel bookModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<BookModel> bookModel);

    @Delete
    void delete(BookModel bookModel);


    @Delete
    void deleteAll(List<BookModel> bookModel);

}



package com.yonder.exercise.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


/**
 * Created by YusufMac on 30/05/17.
 */

@Database(entities = {BookModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookModelDao bookModelDao();
}

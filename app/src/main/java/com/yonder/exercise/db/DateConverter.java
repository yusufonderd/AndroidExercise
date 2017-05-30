package com.yonder.exercise.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by YusufMac on 30/05/17.
 */

class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

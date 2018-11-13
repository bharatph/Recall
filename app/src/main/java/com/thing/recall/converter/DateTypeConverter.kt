package com.thing.recall.converter

import android.annotation.SuppressLint
import android.arch.persistence.room.TypeConverter
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class DateTypeConverter {

    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @TypeConverter
    fun toDate(timestamp: String): Date {
        return sdf.parse(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date): String {
        return sdf.format(date)
    }
}
package com.thing.recall.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun dateFromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value * 1000)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return if (date == null) null else date.time / 1000
    }
}
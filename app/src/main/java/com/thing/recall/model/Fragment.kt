package com.thing.recall.model

import android.arch.persistence.room.*
import com.thing.recall.converter.DateTypeConverter
import java.util.*

@Entity(tableName = "fragments")
data class Fragment(
    @PrimaryKey
    @TypeConverters(DateTypeConverter::class)
    var timeStamp: Date = Date(),
    var description: String = ""
)
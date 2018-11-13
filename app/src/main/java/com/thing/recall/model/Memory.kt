package com.thing.recall.model

import android.arch.persistence.room.*
import com.thing.recall.converter.FragmentListConverter
import com.thing.recall.converter.DateTypeConverter
import java.util.*

@Entity(tableName = "memories")
data class Memory(
    @PrimaryKey
    @TypeConverters(DateTypeConverter::class)
    var memoryOn: Date = Date(),
    @TypeConverters(FragmentListConverter::class)
    var fragments: List<Fragment> = Collections.emptyList()
)
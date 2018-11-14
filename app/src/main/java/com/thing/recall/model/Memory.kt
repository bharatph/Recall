package com.thing.recall.model

import android.arch.persistence.room.*
import com.thing.recall.converter.FragmentListConverter
import com.thing.recall.converter.DateTypeConverter
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class Memory(
    @PrimaryKey
    @TypeConverters(DateTypeConverter::class)
    var memoryOn: Date,
    var description: String
//    @TypeConverters(FragmentListConverter::class)
//    var fragments: LinkedList<Fragment> = LinkedList()
)
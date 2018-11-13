package com.thing.recall


import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.thing.recall.converter.FragmentListConverter
import com.thing.recall.converter.DateTypeConverter
import com.thing.recall.model.Fragment
import com.thing.recall.model.Memory

@Database(entities = arrayOf(Memory::class, Fragment::class), version = 1, exportSchema = false)
@TypeConverters(FragmentListConverter::class, DateTypeConverter::class)
abstract class RecallDatabase : RoomDatabase() {
    abstract fun memoryDao(): MemoryDao
}

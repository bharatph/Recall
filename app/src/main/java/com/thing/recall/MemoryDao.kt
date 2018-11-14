package com.thing.recall

import android.arch.persistence.room.*
import com.thing.recall.model.Memory
import java.util.*

@Dao
interface MemoryDao {
    @Query("SELECT * FROM memory " +
            "where " +
            "strftime('%m', datetime(memoryOn, 'unixepoch')) = strftime('%m', datetime(:memoryOn, 'unixepoch')) " +
            "and " +
            "strftime('%d', datetime(memoryOn, 'unixepoch')) =  strftime('%d', datetime(:memoryOn, 'unixepoch'))" +
            "and " +
            "strftime('%Y', datetime(memoryOn, 'unixepoch')) =  strftime('%Y', datetime(:memoryOn, 'unixepoch'))")
    fun getMemory(memoryOn: Date): Memory?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg memories: Memory)

    @Delete
    fun delete(memory: Memory)

    @Update
    fun update(memory: Memory)
}

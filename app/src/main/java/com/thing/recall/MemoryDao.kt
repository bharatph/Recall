package com.thing.recall

import android.arch.persistence.room.*
import com.thing.recall.model.Memory
import java.util.*

@Dao
interface MemoryDao {
    @Query("SELECT * FROM 'memories' where cast(memoryOn as DATE) = cast(:memoryOn as Date)")
    fun getMemory(memoryOn: Date) : Memory?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg memories: Memory)

    @Delete
    fun delete(memory: Memory)
}

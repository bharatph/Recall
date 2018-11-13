package com.thing.recall.helper

import com.thing.recall.model.Memory
import java.util.*
import kotlin.collections.HashMap

object RecallHelper {

    var memories = LinkedList<Memory>()

    init {

    }

    fun saveMemory(memory: Memory) {
    }

    class SQLMap(val reg: () -> Unit) : HashMap<Date, Memory>() {
        override fun get(key: Date): Memory? {
            //TODO fetch memory from local db
            //if memory is empty return empty memory
            return super.get(key)
        }

        override fun put(key: Date, value: Memory): Memory? {
            return super.put(key, value)
        }
    }
}
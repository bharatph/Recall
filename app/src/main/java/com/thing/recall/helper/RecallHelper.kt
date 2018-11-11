package com.thing.recall.helper

import com.thing.recall.model.Memory
import java.util.*

object RecallHelper {

    var memories = LinkedList<Memory>()

    init {
        memories.add(Memory(Date(), "Hi hello first entry"))
        memories.add(Memory(Date(), "Hi hello first entry"))
        memories.add(Memory(Date(), "Hi hello first entry"))
        memories.add(Memory(Date(), "Hi hello first entry"))
        memories.add(Memory(Date(), "Hi hello first entry"))
    }
}
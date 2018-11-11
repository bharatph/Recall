package com.thing.recall.model

import java.util.*

data class Memory(val date: Date, val description: String) {
    constructor() : this(Date(), "")
}
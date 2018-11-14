package com.thing.recall.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import com.thing.recall.model.Fragment
import java.util.*
import kotlin.collections.ArrayList


class FragmentListConverter {

    var gson = Gson()

    @TypeConverter
    fun stringToFragmentList(data: String): LinkedList<Fragment> {
        if (data.isEmpty()) {
            return LinkedList()
        }

        val listType = object : TypeToken<LinkedList<Fragment>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fragmentListToString(someObjects: LinkedList<Fragment>): String {
        return gson.toJson(someObjects)
    }
}
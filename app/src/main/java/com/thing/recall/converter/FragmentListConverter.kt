package com.thing.recall.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import com.thing.recall.model.Fragment
import java.util.*


class FragmentListConverter {

    var gson = Gson()

    @TypeConverter
    fun stringToFragmentList(data: String): List<Fragment> {
        if (data.isEmpty()) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Fragment>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fragmentListToString(someObjects: List<Fragment>): String {
        return gson.toJson(someObjects)
    }
}
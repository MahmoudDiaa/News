package com.diaa.news.pojo.typeConveters

import androidx.room.TypeConverter
import com.diaa.news.pojo.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SourceTypeConverter {

    @TypeConverter
    fun fromSource(source: Source): String {

        val gson = Gson()
        val type: Type = object : TypeToken<Source>() {}.type
        return gson.toJson(source, type)
    }

    @TypeConverter
    fun toSource(sourceString: String): Source {
        val gson = Gson()
        val type = object : TypeToken<Source>() {}.type
        return gson.fromJson(sourceString, type)
    }
}

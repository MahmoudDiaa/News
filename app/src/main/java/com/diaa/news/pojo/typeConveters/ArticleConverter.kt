package com.diaa.news.pojo.typeConveters

import androidx.room.TypeConverter
import com.diaa.news.pojo.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ArticleConverter {

    @TypeConverter
    fun fromArticleList(articleList: ArrayList<Article>?): String? {

        if (articleList == null)
            return null
        val gson = Gson()
        val type: Type = object : TypeToken<ArrayList<Article?>?>() {}.type
        return gson.toJson(articleList, type)
    }

    @TypeConverter
    fun toArticleList(articleString: String?): ArrayList<Article?>? {
        if (articleString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Article?>?>() {}.type
        return gson.fromJson(articleString, type)
    }
}

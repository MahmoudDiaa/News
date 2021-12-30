package com.diaa.news.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.diaa.news.pojo.typeConveters.ArticleConverter

@Entity(tableName = "news")
data class NewsRespond(
    @PrimaryKey(autoGenerate = true)
    val newsID: Int,
    @TypeConverters(ArticleConverter::class)
    val articles: ArrayList<Article>,
    val status: String,
    val totalResults: Int
)

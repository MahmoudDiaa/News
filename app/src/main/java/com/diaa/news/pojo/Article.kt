package com.diaa.news.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class Article(

    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    @PrimaryKey
    val title: String,
    val url: String?,
    val urlToImage: String?,
    var isFav: Boolean = false,
)

@Entity(tableName = "source")
data class Source(
    val id: Any,
    val name: String
)

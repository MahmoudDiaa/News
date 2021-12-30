package com.diaa.news.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.diaa.news.pojo.Article
import com.diaa.news.pojo.NewsRespond
import com.diaa.news.pojo.typeConveters.ArticleConverter
import com.diaa.news.pojo.typeConveters.SourceTypeConverter

@Database(entities = [NewsRespond::class, Article::class], version = 1)

@TypeConverters(ArticleConverter::class, SourceTypeConverter::class)
abstract class NewsDataBase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao
    abstract fun getArticleDao(): ArticleDao
}

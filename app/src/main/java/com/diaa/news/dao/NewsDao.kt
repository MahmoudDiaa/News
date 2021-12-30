package com.diaa.news.dao

import androidx.room.*
import com.diaa.news.pojo.NewsRespond
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsRespond)

    @Delete
    suspend fun deleteNews(news: NewsRespond)

    @Query("SELECT * from news ")
    fun getAllNews(): Flow<List<NewsRespond>>
}

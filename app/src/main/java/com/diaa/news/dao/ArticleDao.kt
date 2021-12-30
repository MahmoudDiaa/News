package com.diaa.news.dao

import androidx.room.*
import com.diaa.news.pojo.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * from article where isFav")
    fun getFavArticle(): Flow<List<Article>>

    @Query("SELECT * from article ")
    fun getAllArticle(): Flow<List<Article>>?
}

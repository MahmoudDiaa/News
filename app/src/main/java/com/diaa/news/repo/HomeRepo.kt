package com.diaa.news.repo

import com.diaa.news.dao.ArticleDao
import com.diaa.news.network.ApiHelper
import com.diaa.news.pojo.Article
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepo @Inject constructor(
    private val apiHelper: ApiHelper,
    private val articleDao: ArticleDao
) {

    suspend fun getNewsResponse() = flow {
        emit(apiHelper.getNews())
    }

    suspend fun addFavArticle(article: Article) = articleDao.insertArticle(article)
    fun getFavArticle() = articleDao.getAllArticle()
    suspend fun removeFavArticle(article: Article) = articleDao.deleteArticle(article)
}

package com.diaa.news.repo

import com.diaa.news.dao.ArticleDao
import com.diaa.news.pojo.Article
import javax.inject.Inject

class FavouriteRepo @Inject constructor(

    private val articleDao: ArticleDao
) {

    fun getFavArticle() = articleDao.getAllArticle()
    suspend fun removeFromFav(article: Article) = articleDao.deleteArticle(article)
}

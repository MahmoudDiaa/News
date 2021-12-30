package com.diaa.news.network

import com.diaa.news.pojo.NewsRespond
import retrofit2.Response

interface ApiHelper {
    suspend fun getNews(): Response<NewsRespond>
    suspend fun searchForNews(query: String): Response<NewsRespond>
}

package com.diaa.news.network

import com.diaa.news.pojo.NewsRespond
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getNews(): Response<NewsRespond> = apiService.getNews()
    override suspend fun searchForNews(query: String): Response<NewsRespond> = apiService.searchForNews(query = query)
}

package com.diaa.news.network

import com.diaa.news.others.Constants.API_KEY
import com.diaa.news.pojo.NewsRespond
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    companion object {
        private const val JSON_CONTENT_TYPE: String = "Content-Type: application/json"
    }

    @Headers(JSON_CONTENT_TYPE)
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "eg",
        @Query("apiKey") apiKey: String = API_KEY,
    ): Response<NewsRespond>

    @Headers(JSON_CONTENT_TYPE)
    @GET("top-headlines")
    suspend fun searchForNews(

        @Query("apiKey") apiKey: String = API_KEY,
        @Query("q") query: String = "",

    ): Response<NewsRespond>
}

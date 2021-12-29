package com.diaa.news.models

data class News(
    val articles: ArrayList<Article> ,
    val status: String ,
    val totalResults: Int
) {



}
data class Article(
    val author: String,
    val content: Any,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)

data class Source(
    val id: Any,
    val name: String
)

package com.diaa.news.pojo

@NewsDslMarker
class NewsBuilder {
    private val articles: ArrayList<Article> = ArrayList()
    private val status: String = ""
    private val totalResults: Int = 0
    private val id = 0
    fun build(): NewsRespond = NewsRespond(id, articles, status, totalResults)
}

fun news(newsBuilder: NewsBuilder.() -> Unit) = NewsBuilder().apply(newsBuilder).build()

@DslMarker
annotation class NewsDslMarker

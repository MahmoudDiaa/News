package com.diaa.news.repo

import com.diaa.news.network.ApiHelper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepo @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getSearchResult(query: String) = flow { emit(apiHelper.searchForNews(query)) }
}

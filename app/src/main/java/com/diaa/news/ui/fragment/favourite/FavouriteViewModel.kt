package com.diaa.news.ui.fragment.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diaa.news.pojo.Article
import com.diaa.news.repo.FavouriteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val repo: FavouriteRepo) : ViewModel() {
    fun removeFromFav(article: Article) {
        viewModelScope.launch { repo.removeFromFav(article) }
    }

    private val _favMutableLive = MutableLiveData<ArrayList<Article>>()
    val favArticleLiveData: LiveData<ArrayList<Article>> get() = _favMutableLive

    init {
        viewModelScope.launch {
            repo.getFavArticle()?.collect { _favMutableLive.postValue(it as ArrayList<Article>?) }
        }
    }
}

package com.diaa.news.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diaa.news.pojo.Article
import com.diaa.news.repo.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: HomeRepo) : ViewModel() {
    fun addArticleToFav(article: Article) {
        viewModelScope.launch {
            article.isFav = true
            repo.addFavArticle(article)
        }
    }

    private val _newMutableLive = MutableLiveData<ArrayList<Article>>()
    val articleLiveData: LiveData<ArrayList<Article>> get() = _newMutableLive

    private val _favMutableLive = MutableLiveData<ArrayList<Article>>()

    private val _errorMutableLive = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorMutableLive

    private suspend fun getFavDB() {

        repo.getFavArticle()?.collect {

            if (!it.isNullOrEmpty()) {
                _favMutableLive.postValue(it.toCollection(ArrayList()))
            }
        }
    }

    private suspend fun getNewOnline() {
        repo.getNewsResponse().collect {

            if (it.isSuccessful && it.code() == 200) {
                if (!_favMutableLive.value.isNullOrEmpty()) {
                    _favMutableLive.value?.forEach { fav
                        ->
                        it.body()?.articles?.removeIf { article ->
                            fav.title.trim() == article.title.trim()
                        }
                    }
                    it.body()?.articles?.addAll(_favMutableLive.value!!)
                }
                _newMutableLive.postValue(it.body()?.articles)
            } else
                _errorMutableLive.postValue(it.message())
        }
    }

    init {

        viewModelScope.launch {
            launch {
                getFavDB()
            }

            launch {
                getNewOnline()
            }
        }
    }
}

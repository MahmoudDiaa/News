package com.diaa.news.ui.fragment.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diaa.news.pojo.Article
import com.diaa.news.repo.SearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo: SearchRepo) : ViewModel() {

    private val _searchNewMutableLive = MutableLiveData<ArrayList<Article>>()
    val articleLiveData: LiveData<ArrayList<Article>> get() = _searchNewMutableLive

    private val _errorMutableLive = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorMutableLive

    fun search(query: String?) {
        Log.e("view mocel", "search: $query",)
        viewModelScope.launch {
            repo.getSearchResult(query!!.trim()).collect {
                if (it.isSuccessful && it.code() == 200) {

                    if (it.body()?.articles!!.isNotEmpty())
                        _searchNewMutableLive.postValue(it.body()?.articles)
                    else
                        _errorMutableLive.postValue("no news Found")
                } else
                    _errorMutableLive.postValue(it.message())
            }
        }
    }
}

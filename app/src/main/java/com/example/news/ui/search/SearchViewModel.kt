package com.example.news.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.News
import com.example.news.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    val news: MutableLiveData<News> = MutableLiveData()

    fun search(string: String) {
        viewModelScope.launch {
            mainRepository.searchNew(string).let {
                if (it.isSuccessful)
                    news.postValue(it.body())
            }
        }
    }
}
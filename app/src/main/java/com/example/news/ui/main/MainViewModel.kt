package com.example.news.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.News
import com.example.news.models.RoomArticle
import com.example.news.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    val news: MutableLiveData<News> = MutableLiveData()

    init {
        getAllNews()
    }

    private fun getAllNews() {
        viewModelScope.launch {
            mainRepository.getAllRetrofit().let {
                if (it.isSuccessful)
                    news.postValue(it.body())
            }
        }
    }

    fun insert(roomArticle: RoomArticle) {
        viewModelScope.launch {
            mainRepository.insert(roomArticle)
        }
    }
}
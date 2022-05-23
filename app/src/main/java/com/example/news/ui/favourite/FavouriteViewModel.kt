package com.example.news.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.News
import com.example.news.models.RoomArticle
import com.example.news.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel  @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    val news: MutableLiveData<List<RoomArticle>> = MutableLiveData()

    init {
        getAllNews()
    }

    fun delete(roomArticle: RoomArticle) {
        viewModelScope.launch {
            mainRepository.delete(roomArticle)
        }
    }

    private fun getAllNews() {
        viewModelScope.launch {
            news.postValue(mainRepository.getAllRoom())
        }
    }
}
package com.example.news.repository

import com.example.news.models.News
import com.example.news.models.RoomArticle
import com.example.news.retrofit.ApiService
import com.example.news.room.NewsDao
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val roomDao: NewsDao
    ): MainRepository {
    override suspend fun getAllRetrofit(): Response<News> {
        return apiService.getAllNews()
    }

    override suspend fun searchNew(string: String): Response<News> {
        return apiService.searchNews(string)
    }

    override suspend fun insert(roomArticle: RoomArticle) {
        return roomDao.insert(roomArticle)
    }

    override suspend fun getAllRoom(): List<RoomArticle> {
        return roomDao.getAllNews()
    }

    override suspend fun delete(roomArticle: RoomArticle) {
        return roomDao.delete(roomArticle)
    }

}
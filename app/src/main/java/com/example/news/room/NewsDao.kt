package com.example.news.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.news.models.RoomArticle

@Dao
interface NewsDao {
    @Insert
    suspend fun insert(article: RoomArticle)

    @Delete
    suspend fun delete(article: RoomArticle)

    @Query("SELECT * FROM news")
    suspend fun getAllNews(): List<RoomArticle>
}
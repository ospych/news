package com.example.news.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class RoomArticle(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

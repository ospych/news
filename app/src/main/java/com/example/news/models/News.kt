package com.example.news.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<Articles>
)

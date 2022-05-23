package com.example.news.room

import android.content.Context
import androidx.room.*
import com.example.news.models.News
import com.example.news.models.RoomArticle
import java.util.*

@Database(entities = [RoomArticle::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract val newsDao: NewsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                   "news_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}

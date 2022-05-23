package com.example.news.modules

import com.example.news.repository.MainRepository
import com.example.news.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun mainRepository(mainRepository: MainRepositoryImpl): MainRepository
}
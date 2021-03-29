package com.academy.android.di

import com.academy.android.data.repositories.ChatsRepository
import com.academy.android.data.repositories.ChatsRepositorySource
import com.academy.android.data.repositories.NewsRepository
import com.academy.android.data.repositories.NewsRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoriesModule {
    @Binds
    @Singleton
    abstract fun bindChatsRepository(
        chatsRepository: ChatsRepository
    ): ChatsRepositorySource

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepository: NewsRepository
    ): NewsRepositorySource

}
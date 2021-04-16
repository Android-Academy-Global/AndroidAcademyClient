package com.academy.android.di

import com.academy.android.data.repositories.*
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

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        profileRepository: ProfileRepository
    ): ProfileRepositorySource
}
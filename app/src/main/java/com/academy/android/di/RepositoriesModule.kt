package com.academy.android.di

import com.academy.android.data.repositories.ChatsRepository
import com.academy.android.data.repositories.ChatsRepositorySource
import com.academy.android.data.repositories.NewsRepository
import com.academy.android.data.repositories.NewsRepositorySource
import com.academy.android.data.repositories.ProfileRepository
import com.academy.android.data.repositories.ProfileRepositorySource
import com.academy.android.data.repositories.VideosRepository
import com.academy.android.data.repositories.VideosRepositoryStub
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class RepositoriesModule {
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
    abstract fun bindVideosRepository(
        videosRepository: VideosRepositoryStub
    ): VideosRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        profileRepository: ProfileRepository
    ): ProfileRepositorySource
}
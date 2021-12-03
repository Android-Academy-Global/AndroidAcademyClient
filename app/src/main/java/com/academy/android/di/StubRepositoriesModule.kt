package com.academy.android.di

import com.academy.android.data.repositories.stubs.ChatsRepositoryStub
import com.academy.android.data.repositories.stubs.NewsRepositoryStub
import com.academy.android.data.repositories.stubs.ProfileRepositoryStub
import com.academy.android.data.repositories.stubs.VideosRepositoryStub
import com.academy.android.domain.repositories.ChatsRepository
import com.academy.android.domain.repositories.NewsRepository
import com.academy.android.domain.repositories.ProfileRepository
import com.academy.android.domain.repositories.VideosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class StubRepositoriesModule {
    @Binds
    @Singleton
    abstract fun bindChatsRepository(
        chatsRepository: ChatsRepositoryStub
    ): ChatsRepository

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepository: NewsRepositoryStub
    ): NewsRepository

    @Binds
    @Singleton
    abstract fun bindVideosRepository(
        videosRepository: VideosRepositoryStub
    ): VideosRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        profileRepository: ProfileRepositoryStub
    ): ProfileRepository
}
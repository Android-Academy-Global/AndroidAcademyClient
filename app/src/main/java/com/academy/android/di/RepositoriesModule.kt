package com.academy.android.di

import com.academy.android.data.repositories.AuthRepositoryImpl
import com.academy.android.domain.repositories.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface RepositoriesModule {
    @Binds
    @Singleton
    fun bindAuhRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository
}
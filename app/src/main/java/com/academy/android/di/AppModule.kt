package com.academy.android.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class AppModule {
    @Provides
    @Singleton
    fun provideAppContext(application: Application): Context = application.applicationContext
}
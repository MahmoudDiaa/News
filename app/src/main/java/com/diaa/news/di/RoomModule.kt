package com.diaa.news.di

import android.content.Context
import androidx.room.Room
import com.diaa.news.dao.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        NewsDataBase::class.java,
        "newsDatabase"
    ).build()

    @Singleton
    @Provides
    fun provideArticleDao(db: NewsDataBase) = db.getArticleDao()

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDataBase) = db.getNewsDao()
}

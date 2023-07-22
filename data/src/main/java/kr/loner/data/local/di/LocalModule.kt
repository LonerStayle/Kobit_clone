package kr.loner.data.local.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.loner.data.local.dao.BookMarkDao
import kr.loner.data.local.source.BookMarkLocalSource
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalModule {
    @Provides
    @Singleton
    fun provideBookMarkLocalSource(bookMarkDao: BookMarkDao) = BookMarkLocalSource(bookMarkDao)

}
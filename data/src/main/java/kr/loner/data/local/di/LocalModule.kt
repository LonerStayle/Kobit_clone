package kr.loner.data.local.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.loner.data.local.dao.BookMarkDao
import kr.loner.data.local.source.BookMarkLocalSource

@InstallIn(SingletonComponent::class)
@Module
class LocalModule {

    @Provides
    fun provideBookMarkLocalSource(bookMarkDao: BookMarkDao) = BookMarkLocalSource(bookMarkDao)
}
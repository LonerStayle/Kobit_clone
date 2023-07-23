package kr.loner.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.loner.data.local.LocalDataBase
import kr.loner.data.local.dao.BookMarkDao

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LocalDataBase =
        Room.databaseBuilder(context, LocalDataBase::class.java, MOVIE_TABLE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideBookMarkDao(localDataBase: LocalDataBase): BookMarkDao =
        localDataBase.getBookMarkDao()


    companion object {
        private const val MOVIE_TABLE_NAME = "local.db"
    }
}
package kr.loner.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.loner.data.TickerApi
import retrofit2.Retrofit
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    fun provideTickerApi(retrofit: Retrofit): TickerApi = retrofit.create()
}
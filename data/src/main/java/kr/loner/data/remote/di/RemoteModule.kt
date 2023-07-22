package kr.loner.data.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.loner.data.remote.service.TickerService
import kr.loner.data.remote.source.TickerRemoteSource
import retrofit2.Retrofit
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
class RemoteModule {

    @Provides
    fun provideTickerDataSource(service: TickerService) = TickerRemoteSource(service)

}
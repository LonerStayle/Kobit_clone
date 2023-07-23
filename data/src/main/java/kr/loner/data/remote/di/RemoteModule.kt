package kr.loner.data.remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.loner.data.remote.service.TickerService
import kr.loner.data.remote.source.TickerRemoteSource

@InstallIn(SingletonComponent::class)
@Module
class RemoteModule {

    @Provides
    fun provideTickerDataSource(service: TickerService) = TickerRemoteSource(service)

}
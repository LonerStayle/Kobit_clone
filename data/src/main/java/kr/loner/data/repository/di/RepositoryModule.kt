package kr.loner.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.loner.data.repository.TickerRepositoryImpl
import kr.loner.domain.repository.TickerRepository

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun provideLocalRepository(repositoryImpl: TickerRepositoryImpl): TickerRepository
}
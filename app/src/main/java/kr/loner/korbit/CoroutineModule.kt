package kr.loner.korbit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class CoroutineModule {

    @IoDispatcher
    @Provides
    fun providesIoDispatcher() = Dispatchers.IO

    @MainImmediateDispatcher
    @Provides
    fun providesMainImmDispatcher() = Dispatchers.Main.immediate
}
package kr.loner.data.remote.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.loner.data.mapper.TickerMapper
import kr.loner.data.remote.service.TickerService
import kr.loner.domain.model.TickerList
import javax.inject.Inject


class TickerRemoteSource @Inject constructor(private val tickerService: TickerService) {
    suspend fun getTickerList(): Flow<TickerList> = flow {
        val tickerList = TickerMapper.tickerAllToListModel(tickerService.getTickerAll())
        emit(tickerList)
    }

}
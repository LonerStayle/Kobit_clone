package kr.loner.domain.usecase.ticker

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kr.loner.domain.model.Ticker
import kr.loner.domain.model.TickerList
import kr.loner.domain.repository.TickerRepository
import kr.loner.domain.usecase.base.NonParamUseCase
import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject

class GetFavoriteListUseCase @Inject constructor(
    private val repository: TickerRepository
) : NonParamUseCase<Flow<TickerList>>() {
    override suspend fun invoke(): Flow<TickerList> = repository.getFavoriteTickerList().map {list ->
        list.copy(tickers = list.tickers.sortedBy(Ticker::volume))
    }.catch {
        Logger.getLogger(GetFavoriteListUseCase::class.java.simpleName)
            .log(Level.WARNING, "throw error", it.message)
    }
}
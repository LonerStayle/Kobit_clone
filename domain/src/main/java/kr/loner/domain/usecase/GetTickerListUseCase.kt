package kr.loner.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kr.loner.domain.model.BookMark
import kr.loner.domain.model.Ticker
import kr.loner.domain.model.TickerList
import kr.loner.domain.repository.TickerRepository
import kr.loner.domain.usecase.base.NonParamCoroutineUseCase
import javax.inject.Inject

class GetTickerListUseCase @Inject constructor(
    private val repository: TickerRepository,
) : NonParamCoroutineUseCase<Flow<TickerList>>() {
    override suspend fun execute(): Flow<TickerList> = repository.getTickerList()
}
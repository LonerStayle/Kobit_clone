package kr.loner.domain.usecase.ticker

import kr.loner.domain.model.BookMark
import kr.loner.domain.model.Ticker
import kr.loner.domain.repository.TickerRepository
import kr.loner.domain.usecase.base.UseCase
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(private val tickerRepository: TickerRepository) :
    UseCase<Ticker, Unit>() {
    override suspend fun invoke(param: Ticker) {
        tickerRepository.isTickerFavorite(
            BookMark(
                param.currencyPair,
                BookMark.Type.Ticker,
                !param.isFavorite
            )
        )
    }
}


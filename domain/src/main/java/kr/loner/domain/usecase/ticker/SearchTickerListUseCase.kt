package kr.loner.domain.usecase.ticker

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.loner.domain.model.Ticker
import kr.loner.domain.model.TickerList
import kr.loner.domain.repository.TickerRepository
import kr.loner.domain.usecase.base.UseCase
import javax.inject.Inject

class SearchTickerListUseCase @Inject constructor(
    private val tickerRepository: TickerRepository
) : UseCase<SearchTickerListUseCase.SearchQuery, Flow<TickerList>>() {
    override suspend fun invoke(param: SearchQuery): Flow<TickerList> {
        return tickerRepository.getTickerList().map { list ->
            val filtersByQuery = list.tickers.sortedByDescending(Ticker::volume).filter { ticker ->
                ticker.currencyPair.contains(param.queryText)
            }

            val result: List<Ticker> = filtersByQuery.sortedWith(
                when (param.order) {
                    Order.CurrencyPairASC -> {
                        compareBy { it.currencyPair}
                    }

                    Order.CurrencyPairDESC -> {
                        compareByDescending { it.currencyPair}
                    }

                    Order.LastASC -> {
                        compareBy { it.last }
                    }

                    Order.LastDESC -> {
                        compareByDescending { it.last }
                    }

                    Order.ChangePercentASC -> {
                        compareBy { it.changePercent }
                    }

                    Order.ChangePercentDESC -> {
                        compareByDescending { it.changePercent }
                    }

                    Order.VolumeASC -> {
                        compareBy { it.volume }
                    }

                    Order.VolumeDESC -> {
                        compareByDescending { it.volume }
                    }

                    Order.None -> Comparator { _, _ -> 0 }
                }
            )

            list.copy(tickers = result)
        }
    }

    data class SearchQuery(val queryText: String = "", val order: Order = Order.None)
    enum class Order {
        CurrencyPairASC,
        CurrencyPairDESC,
        LastASC,
        LastDESC,
        ChangePercentASC,
        ChangePercentDESC,
        VolumeASC,
        VolumeDESC,
        None
    }
}
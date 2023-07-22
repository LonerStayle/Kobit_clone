package kr.loner.domain.usecase.ticker

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.loner.domain.model.Ticker
import kr.loner.domain.model.TickerList
import kr.loner.domain.repository.TickerRepository
import kr.loner.domain.usecase.base.UseCase
import javax.inject.Inject

class SearchListUseCase @Inject constructor(
    private val tickerRepository: TickerRepository
) : UseCase<SearchListUseCase.SearchQuery, Flow<TickerList>>() {
    override suspend fun invoke(param: SearchQuery): Flow<TickerList> {
        return tickerRepository.getTickerList().map { list ->
            val filtersByQuery = list.tickers.sortedBy(Ticker::volume).filter { ticker ->
                ticker.currencyPair.contains(param.queryText)
            }

            val result: List<Ticker> = filtersByQuery.sortedWith(
                when (param.order) {

                    Order.CurrencyPairASC -> { compareByDescending { it.currencyPair } }

                    Order.CurrencyPairDESC -> { compareBy { it.currencyPair } }

                    Order.LastASC -> { compareByDescending { it.last } }

                    Order.LastDESC -> { compareBy { it.last } }

                    Order.ChangePercentASC -> { compareByDescending { it.changePercent } }

                    Order.ChangePercentDESC -> { compareBy { it.changePercent } }

                    Order.VolumeASC -> { compareByDescending { it.volume } }

                    Order.VolumeDESC -> { compareBy { it.volume } }

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
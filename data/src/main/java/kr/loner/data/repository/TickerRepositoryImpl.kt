package kr.loner.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kr.loner.data.local.model.BookMarkDto
import kr.loner.data.local.model.BookMarkDtoType
import kr.loner.data.local.source.BookMarkLocalSource
import kr.loner.data.mapper.toDomain
import kr.loner.data.mapper.toDto
import kr.loner.data.remote.source.TickerRemoteSource
import kr.loner.domain.model.BookMark
import kr.loner.domain.model.Ticker
import kr.loner.domain.model.TickerList
import kr.loner.domain.repository.TickerRepository
import javax.inject.Inject

class TickerRepositoryImpl @Inject constructor(
    private val tickerRemoteSource: TickerRemoteSource,
    private val bookMarkLocalSource: BookMarkLocalSource
) : TickerRepository {

    override suspend fun getTickerList(): Flow<TickerList> {
        return tickerRemoteSource.getTickerList()
            .combine(getTickerBookMarkList()) { tickers: TickerList, bookMark: List<BookMark> ->
                val filters = tickers.tickers.map { ticker: Ticker ->
                    val checkBook = bookMark.find { it.keyKrw == ticker.currencyPair }
                    ticker.copy(isFavorite = checkBook?.isLike == true)
                }
                tickers.copy(tickers = filters)
            }.catch {
                throw Exception("Ticker mapping Exception")
            }

    }

    override suspend fun isTickerFavorite(bookMark: BookMark) {
        bookMarkLocalSource.isFavorite(bookMark.copy(type = BookMark.Type.Ticker).toDto())
    }

    override suspend fun getTickerBookMarkList(): Flow<List<BookMark>> =
        bookMarkLocalSource.getBookMarkList(BookMarkDtoType.Ticker).map { bookMark ->
            bookMark.map(BookMarkDto::toDomain)
        }

}
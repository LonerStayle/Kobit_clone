package kr.loner.domain.repository

import kotlinx.coroutines.flow.Flow
import kr.loner.domain.model.BookMark
import kr.loner.domain.model.TickerList

interface TickerRepository {
    suspend fun getTickerList(): Flow<TickerList>
    suspend fun toggleTickerFavorite(bookMark: BookMark)
    suspend fun getTickerBookMarkList(): Flow<List<BookMark>>
}
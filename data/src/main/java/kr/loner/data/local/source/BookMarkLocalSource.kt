package kr.loner.data.local.source

import kotlinx.coroutines.flow.Flow
import kr.loner.data.local.dao.BookMarkDao
import kr.loner.data.local.model.BookMarkDto
import javax.inject.Inject

class BookMarkLocalSource @Inject constructor(private val bookMarkDao: BookMarkDao) {

    suspend fun isFavorite(bookMarkDto: BookMarkDto) = bookMarkDao.isFavorite(bookMarkDto)

    suspend fun getBookMarkList(type:BookMarkDto.Type): Flow<List<BookMarkDto>> = bookMarkDao.getBookMarkList(type)
}
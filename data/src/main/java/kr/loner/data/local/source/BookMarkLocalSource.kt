package kr.loner.data.local.source

import kotlinx.coroutines.flow.Flow
import kr.loner.data.local.dao.BookMarkDao
import kr.loner.data.local.model.BookMarkDto
import kr.loner.data.local.model.BookMarkDtoType
import javax.inject.Inject

class BookMarkLocalSource @Inject constructor(private val bookMarkDao: BookMarkDao) {

    suspend fun isFavorite(bookMarkDto: BookMarkDto) = bookMarkDao.isFavorite(bookMarkDto)

    suspend fun getBookMarkList(type: BookMarkDtoType): Flow<List<BookMarkDto>> =
        bookMarkDao.getBookMarkList(type)
}
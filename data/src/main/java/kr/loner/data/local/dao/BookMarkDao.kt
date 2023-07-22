package kr.loner.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kr.loner.data.local.model.BookMarkDto
import kr.loner.data.local.model.BookMarkDtoType


@Dao
interface BookMarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun isFavorite(bookMarkDto: BookMarkDto)

    @Query("SELECT * FROM BookMarkDto WHERE type = :type")
    fun getBookMarkList(type:BookMarkDtoType): Flow<List<BookMarkDto>>
}

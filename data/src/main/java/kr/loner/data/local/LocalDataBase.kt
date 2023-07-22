package kr.loner.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kr.loner.data.local.dao.BookMarkDao
import kr.loner.data.local.model.BookMarkDto
import kr.loner.data.local.model.BookMarkDtoTypeConverter

@Database(
    entities = [BookMarkDto::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun getBookMarkDao(): BookMarkDao
}

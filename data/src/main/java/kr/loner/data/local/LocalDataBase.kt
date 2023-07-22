package kr.loner.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.loner.data.local.model.BookMarkDto

@Database(
    entities = [BookMarkDto::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun getBookMarkDao(): BookMarkDto
}

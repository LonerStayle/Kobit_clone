package kr.loner.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity
@TypeConverters(BookMarkDtoTypeConverter::class)
data class BookMarkDto(
    @PrimaryKey
    val keyKrw: String = "",
    val type: BookMarkDtoType,
    val isLike: Boolean = false
)

enum class BookMarkDtoType { Ticker }

class BookMarkDtoTypeConverter {
    @TypeConverter
    fun fromBookMarkDtoType(value: BookMarkDtoType): String {
        return value.name // enum을 String으로 변환하여 저장
    }

    @TypeConverter
    fun toBookMarkDtoType(value: String): BookMarkDtoType {
        return enumValueOf(value) // String을 enum으로 변환하여 사용
    }
}